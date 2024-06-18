package com.example.appdelclima

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appdelclima.ui.theme.AppDelClimaTheme

@Composable
fun MainPage(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    AppDelClimaTheme {
        MainPage("Android")
    }
}