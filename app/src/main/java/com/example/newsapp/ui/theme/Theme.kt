package com.example.newsapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color

// Define your color palette
private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFF039BE5),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Define typography
/*private val AppTypography = Typography(
    font = FontFamily.SansSerif,
    h1 = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
    h2 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    h3 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    body1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    body2 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)
)*/

// Define shapes
private val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun NewsReaderTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        //typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}

// Preview function to see your theme in the Android Studio preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsReaderTheme {
        // Your preview content here
    }
}
