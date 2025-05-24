package com.nightfury.data.local.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val publishedAt: String,
    val source: String,
    val author: String?,
    val queryLabel: String
)

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles WHERE queryLabel = :query ORDER BY publishedAt DESC")
    suspend fun getArticlesByQuery(query: String): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles WHERE queryLabel = :query")
    suspend fun deleteArticlesByQuery(query: String)
}