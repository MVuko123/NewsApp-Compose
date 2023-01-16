package com.example.newscompose.ui.search

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newscompose.R
import com.example.newscompose.ui.components.NewsCard
import com.example.newscompose.ui.components.NewsCardViewState

@Composable
fun SearchRoute(
    searchViewModel: SearchViewModel,
    modifier: Modifier = Modifier,
) {
    val search: SearchCategoryViewState by searchViewModel.searched.collectAsState()
    SearchScreen(
        searchViewState = search,
        modifier = modifier,
        onSavedClick = { searchViewModel.toggleSaved(it) },
        searchViewModel = searchViewModel
    )
}

@Composable
fun SearchScreen(
    searchViewState: SearchCategoryViewState,
    modifier: Modifier = Modifier,
    onSavedClick: (String) -> Unit,
    searchViewModel: SearchViewModel,
) {
    if (searchViewState.search.isEmpty()) {
        SearchBar(topic = searchViewModel.topic.value,
            onTextChange = { searchViewModel.setTopic(it) })
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.error), fontSize = 30.sp)
        }
    } else {
        val context = LocalContext.current
        Column(Modifier.verticalScroll(rememberScrollState())) {
            SearchBar(topic = searchViewModel.topic.value,
                onTextChange = { searchViewModel.setTopic(it) })
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
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(topic: String, onTextChange: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = topic,
                onValueChange = { onTextChange(it) },
                label = { Text(text = "Search news") },
                modifier = Modifier.weight(1f),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                })
            )
        }
    }
}
