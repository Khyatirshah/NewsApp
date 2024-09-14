package com.example.newsapp.ui.article

sealed class NavigationRoutes(val route: String) {
    object ArticleList : NavigationRoutes("article_list")
    object ArticleDetail : NavigationRoutes("article_detail/{articleId}") {
        fun createRoute(articleId: String) = "article_detail/$articleId"
    }
    object Bookmarks : NavigationRoutes("bookmarks")
}
