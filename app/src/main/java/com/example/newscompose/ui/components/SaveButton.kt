package com.example.newscompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newscompose.R
import com.example.newscompose.ui.theme.NewsComposeTheme

@Composable
fun SaveButton(
    isSaved: Boolean = false,
    modifier: Modifier = Modifier,
    savedClick: () -> Unit,
) {
    Surface(
        shape = CircleShape,
        color = Color(0x77968383),
        modifier = modifier.padding(10.dp)
    ) {
        Image(
            modifier = Modifier
                .clickable { savedClick() }
                .size(40.dp)
                .padding(8.dp),
            painter = painterResource(id = if (isSaved) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun SaveButtonPreview() {
    NewsComposeTheme {
        SaveButton(isSaved = true, savedClick = {})
    }
}
