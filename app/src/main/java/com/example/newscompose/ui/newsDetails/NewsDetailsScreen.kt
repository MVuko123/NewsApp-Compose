package com.example.newscompose.ui.newsDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newscompose.R
import com.example.newscompose.data.network.model.Source
import com.example.newscompose.ui.components.SaveButton

@Composable
fun NewsDetailsRoute(
    newsDetailsViewModel: NewsDetailsViewModel,
    modifier: Modifier = Modifier,
){
    val details: NewsDetailsViewState by newsDetailsViewModel.newsDetailsViewState.collectAsState()

    NewsDetailsScreen(
        newsDetailsViewState = details,
        modifier = modifier,
        onSavedClick = { newsDetailsViewModel.toggleSaved(it)})
}

@Composable
fun NewsDetailsScreen(
    newsDetailsViewState: NewsDetailsViewState,
    modifier: Modifier = Modifier,
    onSavedClick: (Long?) -> Unit,
){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = newsDetailsViewState.headImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_baseline_warning_24),
                error = painterResource(id = R.drawable.ic_baseline_warning_24)
            )
            SaveButton(isSaved = newsDetailsViewState.isSaved, modifier = Modifier, savedClick = {onSavedClick(newsDetailsViewState.id)})
        }
        Row(Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 5.dp),
            Arrangement.SpaceBetween
        ) {
            Text(text = "Date: ${newsDetailsViewState.date}" , fontSize = 12.sp, fontWeight = FontWeight.Bold,color = MaterialTheme.colors.onSurface)
            Text(text = newsDetailsViewState.title, fontSize = 12.sp, fontWeight = FontWeight.Bold,color = MaterialTheme.colors.onSurface)
        }
        Text(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),text = newsDetailsViewState.content, color = MaterialTheme.colors.onSurface)
    }
}

