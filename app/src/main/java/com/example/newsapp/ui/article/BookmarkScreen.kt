package com.example.newsapp.ui.article

import com.example.newsapp.viewmodel.NewsViewModel


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.data.model.Article

@Composable
fun BookmarkScreen(viewModel: NewsViewModel, navController: NavController) {
    // Collect bookmarked articles from the ViewModel
    val bookmarkedArticles by viewModel.bookmarkedArticles.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookmarked Articles") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            items(bookmarkedArticles) { article ->
                ArticleListItemBookMark(article = article, onClick = {
                    /*// Navigate to article detail screen
                    val index = bookmarkedArticles.indexOf(article)
                    navController.navigate("articleDetail/$index")*/
                })
            }
        }
    }
}

@Composable
fun ArticleListItemBookMark(
    article: Article,
    onClick: () -> Unit,

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(article.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(article.description ?: "", maxLines = 2, color = Color.Gray)
            }

        }
    }
}


