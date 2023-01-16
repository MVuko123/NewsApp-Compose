package com.example.newscompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newscompose.R
import com.example.newscompose.ui.theme.NewsComposeTheme

data class NewsCardViewState(
    val newsImageUrl: String?,
    val title: String,
    val publishedAt: String,
    val isSaved: Boolean,
)

@Composable
fun NewsCard(
    newsCardViewState: NewsCardViewState,
    toNewsDetails: () -> Unit,
    modifier: Modifier = Modifier,
    onSavedClick: (Boolean) -> Unit,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable(onClick = toNewsDetails)
    ) {
        val shape = RoundedCornerShape(12.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape),
                model = newsCardViewState.newsImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_baseline_warning_24),
                error = painterResource(id = R.drawable.ic_baseline_warning_24)
            )
            SaveButton(
                isSaved = newsCardViewState.isSaved, modifier = Modifier,
                savedClick = { onSavedClick(newsCardViewState.isSaved) }
            )
        }
        Row(Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 5.dp),
            Arrangement.SpaceBetween
        ) {
            Text(text = newsCardViewState.publishedAt,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray)
            Text(text = newsCardViewState.title, fontSize = 12.sp, fontWeight = FontWeight.Normal)
        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsComposeTheme {
        NewsCard(newsCardViewState = NewsCardViewState(
            newsImageUrl = "https://i.ytimg.com/vi/VXBO4jTxI2o/maxresdefault.jpg",
            publishedAt = "25.02.2022",
            title = "Russia Attacks Ukraine!",
            isSaved = true
        ),
            toNewsDetails = { /*TODO*/ },
            onSavedClick = {}
        )
    }
}
