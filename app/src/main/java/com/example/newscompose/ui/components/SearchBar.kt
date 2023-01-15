package com.example.newscompose.ui.components

import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newscompose.R
import com.example.newscompose.ui.theme.NewsComposeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val text = String()
    val keyboardController = LocalSoftwareKeyboardController.current
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
            onValueChange = { onSearch(it) },
            leadingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "Search Icon",
                tint = Color.Black)
        }, label = {
            Text(text = "Search News", color = Color.Black)
        },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
            }
            )
        )
    }
}

@Preview
@Composable
fun SearchBarPreview(){
    NewsComposeTheme {
        SearchBar()
    }
}
