package com.example.newscompose.ui.saved

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.model.News
import com.example.newscompose.navigation.NavigationItem
import com.example.newscompose.ui.components.NewsCard
import com.example.newscompose.ui.components.NewsCardViewState
import io.ktor.util.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SavedRoute(
    savedViewModel: SavedViewModel,
    onNavigateToNewsDetails: (String) -> Unit
){
    val savedState: SavedViewState by savedViewModel.savedNewsViewState.collectAsState()

    SavedScreen(
        savedViewState = savedState,
        onNavigateToNewsDetails = onNavigateToNewsDetails,
        onSavedClick = { savedViewModel.toggleSaved(it) }
    )
}

@Composable
fun SavedScreen(
    savedViewState: SavedViewState,
    modifier: Modifier = Modifier,
    onNavigateToNewsDetails: (String) -> Unit,
    onSavedClick: (String) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
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
                items = savedViewState.savedNewsViewState,
                key = { news -> news.url }
            ) { news ->
                val encodedUrl = URLEncoder.encode(news.url, StandardCharsets.UTF_8.toString()).encodeBase64()
                NewsCard(
                    newsCardViewState = news.newsCard,
                    toNewsDetails = {
                        onNavigateToNewsDetails(NavigationItem.NewsDetailsDestination.createNavigationRoute(
                            encodedUrl
                        ))
                    },
                    modifier = Modifier
                        .height(200.dp)
                        .width(150.dp),
                    onSavedClick = { onSavedClick(news.url) }
                )
                Column(Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    Arrangement.SpaceBetween
                ) {
                    Text(text = "Date: " + news.newsCard.publishedAt , fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text(text = news.newsCard.title, fontSize = 12.sp, fontWeight = FontWeight.Normal, maxLines = 1)
                }
            }
        }
    }
}

