package com.example.newsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int?,           // ID can be nullable if it is not always available
    val title: String,             // Title of the article
    val description: String?,
    val content: String?,           // Content or summary of the article
    val author: String?,    // Optional author field
    val url: String?,       // Optional URL field for the article
    val urlToImage: String?,  // Optional URL for the article image
    val publishedAt: String? // Optional published date
) : Parcelable
