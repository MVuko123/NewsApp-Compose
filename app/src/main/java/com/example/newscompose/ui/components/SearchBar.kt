package com.example.newscompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newscompose.R
import com.example.newscompose.ui.theme.NewsComposeTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .height(56.dp)
            .background(Color.LightGray),
            value = text,
            onValueChange = {
            text = it
            onSearch(it)
        }, leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "Search Icon",
                tint = Color.Black)
        }, label = {
            Text(text = "Search News", color = Color.Black)
        })
    }
}

@Preview
@Composable
fun SearchBarPreview(){
    NewsComposeTheme {
        SearchBar()
    }
}
