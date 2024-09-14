package com.example.newsapp.data.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<Article>
)

data class Source(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String
)
