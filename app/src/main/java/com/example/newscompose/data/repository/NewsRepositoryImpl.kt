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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class NewsRepositoryImpl(
    private val newsService: NewsService,
    private val newsDao: SavedNewsDao,
    private val bgDispatcher: CoroutineDispatcher
) : NewsRepository {

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
                                apiNewss.toNews(isSaved = it.any{it.newsSource == apiNewss.source})
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
                source = dbSavedNews.newsSource,
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

    private suspend fun findNews(source: Source?): News {
        lateinit var news: News
        newsByCategory.values.forEach { value ->
            val newss = value.first()
            newss.forEach {
                if (it.source == source) {
                    news = it
                }
            }
        }
        return news
    }


    override fun news(newsCategory: NewsCategory): Flow<List<News>> = newsByCategory[newsCategory]!!

    override fun newsDetails(source: Source?): Flow<NewsDetails> = flow {
       emit(newsService.fetchNewsDetails(source) to newsService.fetchNewsCredits(source))
    }.flatMapLatest { (apiNewsDetails, apiNewsCredits) ->
        newsDao.saved()
            .map { savedNews ->
                apiNewsDetails.toNewsDetails(
                    isSaved = savedNews.any{it.newsSource == apiNewsDetails.source}
                )
            }
    }

    override fun savedNews(): Flow<List<News>> = saved

    override suspend fun addNewsToSaved(source: Source?) {
        runBlocking (bgDispatcher){
            newsDao.insertIntoSaved(
                DbSavedNews(
                    newsSource = source,
                    headImageUrl = "${newsService.fetchNewsDetails(source).headImageUrl}"
                )
            )
        }
    }

    override suspend fun removeNewsFromSaved(source: Source?) {
        runBlocking (bgDispatcher){
            newsDao.deleteFromSaved(
                DbSavedNews(
                    newsSource = source,
                    headImageUrl = "${newsService.fetchNewsDetails(source).headImageUrl}"
                )
            )
        }
    }

    override suspend fun toggleSaved(source: Source?) {
        runBlocking (bgDispatcher) {
            val savedNews = findNews(source = source)
            if (savedNews.isSaved) {
                removeNewsFromSaved(source)
            } else {
                addNewsToSaved(source)
            }
        }
    }
}
