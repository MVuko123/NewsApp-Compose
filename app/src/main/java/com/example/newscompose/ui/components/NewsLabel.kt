package com.example.newscompose.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newscompose.ui.theme.NewsComposeTheme

sealed class NewsLabelTextViewState{
    class NewsString(val category: String): NewsLabelTextViewState()
    class NewsStringRes(@StringRes val textRes: Int): NewsLabelTextViewState()
}

data class NewsLabelViewState(
    val itemId: Int,
    var isSelected: Boolean,
    val text: NewsLabelTextViewState
)

@Composable
fun NewsLabel(
    newsLabelViewState: NewsLabelViewState,
    modifier: Modifier = Modifier,
    onLabelClick: () -> Unit
){
    Column(modifier = modifier.padding(10.dp)) {
        var selected = newsLabelViewState.isSelected
        Column(
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Max)
                .clickable { onLabelClick() }
        ) {
            Text(
                text = TextSource(newsLabelViewState = newsLabelViewState),
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
            )
            if(selected)
                Divider(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth(),
                    thickness = 5.dp,
                    color = Color.Gray
                )
        }
    }
}

@Composable
fun TextSource(newsLabelViewState: NewsLabelViewState): String{
    val text = newsLabelViewState.text
    return when (text){
        is NewsLabelTextViewState.NewsString -> text.category
        is NewsLabelTextViewState.NewsStringRes -> stringResource(id = text.textRes)
    }
}

@Preview
@Composable
fun NewsLabelPreview(){
    NewsComposeTheme {
        NewsLabel(newsLabelViewState = NewsLabelViewState(
            itemId = 2,
            isSelected = true,
            text = NewsLabelTextViewState.NewsString("News")),
            modifier = Modifier.padding(5.dp),
            onLabelClick = {}
        )
    }
}
