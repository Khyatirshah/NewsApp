package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApiService
import com.example.newsapp.data.db.ArticleDao
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val apiService: NewsApiService, private val articleDao: ArticleDao) {

    suspend fun fetchTopHeadlines(country: String, category: String, apiKey: String) = apiService.getTopHeadlines(country, category, apiKey)

    suspend fun saveArticle(article: Article) = articleDao.insert(article)

    fun getAllBookmarkedArticles(): Flow<List<Article>> = articleDao.getAllArticles()
}
