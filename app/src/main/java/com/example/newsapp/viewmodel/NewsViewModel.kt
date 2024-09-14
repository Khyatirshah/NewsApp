package com.example.newsapp.viewmodel

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    fun fetchTopHeadlines(country: String, category: String, apiKey: String) {
        // Reset the articles list to force UI recomposition
        _articles.value = emptyList() // This clears the previous articles
        viewModelScope.launch {
            val response = repository.fetchTopHeadlines(country, category, apiKey)
            if (response.isSuccessful) {
                _articles.value = response.body()?.articles ?: emptyList()
            }
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            repository.saveArticle(article)
        }
    }

    // Expose bookmarked articles
    val bookmarkedArticles: Flow<List<Article>> = repository.getAllBookmarkedArticles()
    fun getArticleById(articleId: String): Article? {
        return _articles.value.find { it.id == articleId.toInt() }
    }
}
