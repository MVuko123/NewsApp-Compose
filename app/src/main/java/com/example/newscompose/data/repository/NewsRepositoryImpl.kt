package com.example.newscompose.data.repository

import com.example.newscompose.data.database.DbSavedNews
import com.example.newscompose.data.database.SavedNewsDao
import com.example.newscompose.data.network.NewsService
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import com.example.newscompose.model.NewsCategory
import com.example.newscompose.model.NewsDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class NewsRepositoryImpl(
    private val newsService: NewsService,
    private val newsDao: SavedNewsDao,
    private val bgDispatcher: CoroutineDispatcher
) : NewsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val newsByCategory: Map<NewsCategory, Flow<List<News>>> = NewsCategory.values()
        .associateWith { newsCategory ->
            flow {
                val newsResponse = when (newsCategory) {
                    NewsCategory.NEWS_EU -> newsService.fetchEUNews()
                    NewsCategory.NEWS_UKRAINE -> newsService.fetchUkraineNews()
                    NewsCategory.NEWS_CLIMATE -> newsService.fetchClimateNews()
                    NewsCategory.NEWS_TECHNOLOGY -> newsService.fetchTechnologyNews()
                    NewsCategory.NEWS_POLITICS -> newsService.fetchPoliticsNews()
                    NewsCategory.NEWS_US_POLITICS -> newsService.fetchUsPoliticsNews()
                }
                emit(newsResponse.news)
            }.flatMapLatest { apiNews ->
                    newsDao.saved()
                        .map {
                            apiNews.map { apiNewss ->
                                apiNewss.toNews(isSaved = it.any{it.url == apiNewss.url})
                            }
                        }
            }.shareIn(
                scope = CoroutineScope(bgDispatcher),
                started = SharingStarted.WhileSubscribed(1000L),
                replay = 1
            )
        }

    private val saved = newsDao.saved().map {
        it.map { dbSavedNews ->
            News(
                url = dbSavedNews.url,
                source = Source("",""),
                headImageUrl = dbSavedNews.headImageUrl,
                headline = "",
                date = "",
                isSaved = true
            )
        }
    }.shareIn(
        scope = CoroutineScope(bgDispatcher),
        started = SharingStarted.WhileSubscribed(1000L),
        replay = 1
    )

    private suspend fun findNews(url: String): News {
        lateinit var news: News
        newsByCategory.values.forEach { value ->
            val newss = value.first()
            newss.forEach {
                if (it.url == url) {
                    news = it
                }
            }
        }
        return news
    }

    override fun news(newsCategory: NewsCategory): Flow<List<News>> = newsByCategory[newsCategory]!!

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun newsDetails(url: String): Flow<NewsDetails> = flow {
       emit(newsService.fetchNewsDetails(url) to newsService.fetchNewsCredits(url))
    }.flatMapLatest { (apiNewsDetails, apiNewsCredits) ->
        newsDao.saved()
            .map { savedNews ->
                apiNewsDetails.toNewsDetails(
                    isSaved = savedNews.any{it.url == apiNewsDetails.url}
                )
            }
    }

    override fun savedNews(): Flow<List<News>> = saved

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun searchNews(topic: String): Flow<List<News>> = flow {
        emit(newsService.fetchSearchedNews(topic))
    }.flatMapLatest {  response ->
            newsDao.saved().map { savedNews ->
                response.news.map { news -> news.toNews(isSaved = savedNews.any { it.url == news.url }) }
            }
        }

    override suspend fun addNewsToSaved(url: String) {
        runBlocking (bgDispatcher){
            newsDao.insertIntoSaved(
                DbSavedNews(
                    url = url,
                    headImageUrl = "${newsService.fetchNewsDetails(url).headImageUrl}"
                )
            )
        }
    }

    override suspend fun removeNewsFromSaved(url: String) {
        runBlocking (bgDispatcher){
            newsDao.deleteFromSaved(
                DbSavedNews(
                    url = url,
                    headImageUrl = "${newsService.fetchNewsDetails(url).headImageUrl}"
                )
            )
        }
    }

    override suspend fun toggleSaved(url: String) {
        runBlocking (bgDispatcher) {
            val savedNews = findNews(url = url)
            if (savedNews.isSaved) {
                removeNewsFromSaved(url)
            } else {
                addNewsToSaved(url)
            }
        }
    }
}

