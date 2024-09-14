package com.example.newsapp.ui.article

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.newsapp.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ArticleListScreen(viewModel: NewsViewModel, navController: NavController) {
    val categories = listOf("general", "business", "technology", "sports", "entertainment")
    var expanded by remember { mutableStateOf(false) }  // For showing the dropdown menu
    var selectedCategory by remember { mutableStateOf("general") }  // Default category
    val articles by viewModel.articles.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News Reader") },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter"
                        )
                    }
                    IconButton(onClick = { navController.navigate("bookmarkList") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = "Bookmarks"
                        )
                    }

                    // DropdownMenu for category selection
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedCategory = category
                                //viewModel.fetchTopHeadlines("us", selectedCategory, "your-api-key")
                                viewModel.fetchTopHeadlines("us", selectedCategory, "f9986c83df5a41fbb6ee6ff400de417e")
                            }) {
                                Text(category.capitalize())
                            }
                        }
                    }

                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(articles) { index, article ->
                ArticleListItem(article, onClick = {
                    // Pass the selected article index instead of the entire article
                    navController.navigate("articleDetail/$index")
                }, onBookmarkClick = {
                    viewModel.saveArticle(article)
                })
            }
        }
    }
}

@Composable
fun ArticleListItem(
    article: Article,
    onClick: () -> Unit,
    onBookmarkClick: () -> Unit
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
            IconButton(onClick = { onBookmarkClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Bookmark"
                )
            }
        }
    }
}
