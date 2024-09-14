package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.newsapp.ui.article.ArticleListScreen
import com.example.newsapp.viewmodel.NewsViewModel

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.data.api.NewsApiService
import com.example.newsapp.data.db.ArticleDao
import com.example.newsapp.data.db.ArticleDatabaseHelper
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.repository.NewsViewModelFactory
import com.example.newsapp.ui.article.ArticleDetailScreen
import com.example.newsapp.ui.article.BookmarkScreen
import com.example.newsapp.ui.theme.NewsReaderTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize your API service
        val apiService = NewsApiService.create() // Replace with actual implementation

        // Initialize SQLiteOpenHelper or other database helper
        val articleDao =
            ArticleDao(ArticleDatabaseHelper(this)) // Replace with actual implementation

        // Initialize the repository
        val repository = NewsRepository(apiService, articleDao)

        // Initialize ViewModel with the factory
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
// Fetch headlines
        viewModel.fetchTopHeadlines("us", "general", "f9986c83df5a41fbb6ee6ff400de417e")
        setContent {
            NewsReaderTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "articleList") {
                        composable("articleList") {
                            ArticleListScreen(viewModel = viewModel, navController = navController)
                        }
                        composable(
                            "articleDetail/{articleIndex}"
                        )
                        { backStackEntry ->
                            val articleIndex =
                                backStackEntry.arguments?.getString("articleIndex")?.toIntOrNull()
                            val article =
                                articleIndex?.let { viewModel.articles.value.getOrNull(it) }
                            if (article != null) {
                                ArticleDetailScreen(
                                    article = article,
                                    navController = navController
                                )
                            }
                        }
                        composable("bookmarkList") {
                            BookmarkScreen(viewModel = viewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}