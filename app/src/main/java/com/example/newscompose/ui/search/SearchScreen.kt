package com.example.newscompose.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newscompose.R
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.navigation.NavigationItem
import com.example.newscompose.ui.components.NewsCard
import com.example.newscompose.ui.components.NewsCardViewState
import com.example.newscompose.ui.components.SearchBar

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onNavigateToNewsDetails: (String) -> Unit,
    source: Source?,
){
    val search: SearchCategoryViewState by searchViewModel.searched.collectAsState()
    SearchScreen(searchViewState = search, modifier = modifier, onNavigateToNewsDetails = onNavigateToNewsDetails, onSavedClick = {searchViewModel.toggleSaved(source)} )
}



@Composable
fun SearchScreen(
    searchViewState: SearchCategoryViewState,
    modifier: Modifier = Modifier,
    onNavigateToNewsDetails: (String) -> Unit,
    onSavedClick: (String?) -> Unit,
){
    /*if(searchViewState.search.isEmpty()){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.error), fontSize = 30.sp)
        }
    }else{*/
        SearchBar()
        LazyColumn(
            modifier = modifier
                .height(800.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 12.dp,
            )
        ) {
            items(
                items = searchViewState.search,
                key = { news -> news.hashCode() }
            ) { news ->
                NewsCard(
                    newsCardViewState = NewsCardViewState(
                        news.headImageUrl,
                        news.title,
                        news.publishedAt,
                        news.isSaved
                    ),
                    toNewsDetails = {
                        onNavigateToNewsDetails(NavigationItem.NewsDetailsDestination.createNavigationRoute(
                            news.source
                        ))
                    },
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth(),
                    onSavedClick = { onSavedClick(news.source?.name) }
                )
                Column(Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    Arrangement.SpaceBetween
                ) {
                    Text(text = "Date: " + news.publishedAt , fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text(text = news.title, fontSize = 12.sp, fontWeight = FontWeight.Normal, maxLines = 1)
                }
            }
        }
    //}
}
