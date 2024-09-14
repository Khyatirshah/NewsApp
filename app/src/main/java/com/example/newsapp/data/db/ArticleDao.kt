package com.example.newsapp.data.db

import com.example.newsapp.data.model.Article

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleDao(private val dbHelper: ArticleDatabaseHelper) {

    fun insert(article: Article) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(ArticleDatabaseHelper.COLUMN_TITLE, article.title)
            put(ArticleDatabaseHelper.COLUMN_CONTENT, article.content)
        }
        val resultVal = db.insert(ArticleDatabaseHelper.TABLE_NAME, null, values)
        Log.d("Insertion Result:", resultVal.toString())
        db.close()
    }

    fun getAllArticles(): Flow<List<Article>> = flow {
        val articles = mutableListOf<Article>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            ArticleDatabaseHelper.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ArticleDatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(ArticleDatabaseHelper.COLUMN_TITLE))
                val content = getString(getColumnIndexOrThrow(ArticleDatabaseHelper.COLUMN_CONTENT))

                val article = Article(
                    id = id,
                    title = title,
                    description = "",
                    content = content,
                    author = "",
                    url = "",
                    urlToImage = "",
                    publishedAt = ""
                )
                articles.add(article)
            }
        }
        cursor.close()
        db.close()
        emit(articles)
    }

    fun delete(article: Article) {
        val db = dbHelper.writableDatabase
        db.delete(
            ArticleDatabaseHelper.TABLE_NAME,
            "${ArticleDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(article.id.toString())
        )
        db.close()
    }
}
