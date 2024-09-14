package com.example.newsapp.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.data.model.Article
import com.example.newsapp.R

@Composable
fun ArticleDetailScreen(article: Article, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Display the image from the article's imageUrl
        AsyncImage(
            model = article.urlToImage,
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_default_image), // Placeholder image
            error = painterResource(id = R.drawable.ic_default_image)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display the article title
        Text(text = article.title, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        // Display the article description or content
        Text(text = article.content ?: "No content available")

        // Additional content like author, published date, etc., if needed
    }
}
