package com.example.newscompose.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newscompose.ui.components.*

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
) {
    val newsCategory: HomeCategoryViewState by homeViewModel.newsViewState.collectAsState()
    NewsScreen(
        homeViewState = newsCategory,
        onSavedClick = { homeViewModel.toggleSaved(it) },
        onLabelClick = { homeViewModel.switchSeletectedCategory(it) }
    )
}

@Composable
fun NewsScreen(
    homeViewState: HomeCategoryViewState,
    modifier: Modifier = Modifier,
    onSavedClick: (String) -> Unit,
    onLabelClick: (Int) -> Unit,
) {

    val context = LocalContext.current
    Column(Modifier.verticalScroll(rememberScrollState())) {
        LazyRow(
            modifier = modifier.height(50.dp),
            contentPadding = PaddingValues(start = 10.dp)
        ) {
            items(
                items = homeViewState.newsCategory,
                key = { categories -> categories.itemId }
            ) { categories ->

                NewsLabel(
                    newsLabelViewState = NewsLabelViewState(
                        categories.itemId,
                        categories.isSelected,
                        categories.text
                    ),
                    onLabelClick = { onLabelClick(categories.itemId) }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .height(800.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 10.dp,
                end = 12.dp,
            )
        ) {
            items(
                items = homeViewState.news,
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
                        val url = news.url
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth(),
                    onSavedClick = { onSavedClick(news.url) }
                )
                Column(Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    Arrangement.SpaceBetween
                ) {
                    Text(text = "Date: " + news.publishedAt,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray)
                    Text(text = news.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1)
                }
            }
        }
    }
}
