package com.zero.parallax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.zero.parallax.ui.components.AppImage
import com.zero.parallax.ui.theme.ParallaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.isStatusBarVisible = false // Status bar

            ParallaxTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Page()
                }
            }
        }
    }
}

@Composable
fun Page() {

    var move by remember { mutableStateOf(false) }
    val skyMovement by animateDpAsState(
        targetValue = if (move) 20.dp else (-20).dp,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    val groundMovement by animateDpAsState(
        targetValue = if (move) 0.dp else (-15).dp,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val fragmentsMovement by animateDpAsState(
        targetValue = if (move) 5.dp else 0.dp,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(Unit) { move = true }

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Box(
            modifier = Modifier
                .scale(1.1f)
                .padding(bottom = 12.dp)
                .clipToBounds()
        ) {
            AppImage(
                painter = painterResource(id = R.mipmap.sky),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(skyMovement)
            )
            AppImage(
                painter = painterResource(id = R.mipmap.ground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = groundMovement)
                    .absoluteOffset(y = (scrollState.value * 0.1f).dp)
            )
            AppImage(
                painter = painterResource(id = R.mipmap.fragments),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = fragmentsMovement)
            )
            AppImage(
                painter = painterResource(id = R.mipmap.portal),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(text = stringResource(id = R.string.lorem_ipsum), modifier = Modifier.padding(16.dp))
        Text(text = stringResource(id = R.string.lorem_ipsum), modifier = Modifier.padding(16.dp))
        Text(text = stringResource(id = R.string.lorem_ipsum), modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ParallaxTheme {
        Page()
    }
}