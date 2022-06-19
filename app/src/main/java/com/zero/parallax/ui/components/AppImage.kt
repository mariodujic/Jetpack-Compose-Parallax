package com.zero.parallax.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun AppImage(painter: Painter, contentScale: ContentScale, modifier: Modifier) {
    Image(
        painter = painter,
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}