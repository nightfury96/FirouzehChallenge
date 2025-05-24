package com.nightfury.data.repository

import com.nightfury.data.local.entity.NewsDao
import com.nightfury.data.local.mapper.toDomain
import com.nightfury.data.local.mapper.toEntity
import com.nightfury.data.remote.api.NewsApi
import com.nightfury.domain.model.Article
import com.nightfury.domain.repository.NewsRepository

/**
 * Repository implementation that fetches news from the network
 * and caches it locally using Room.
 */
class NewsRepositoryImpl(private val api: NewsApi, private val dao: NewsDao) : NewsRepository {
    override suspend fun getNewsByQuery(query: String, from: String, to: String): List<Article> {
        return try {
            val response = api.getNews(query, from, to)
            val entities = response.articles.map { it.toEntity(query) }

            dao.deleteArticlesByQuery(query)
            dao.insertArticles(entities)

            entities.map { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to use cache in case of any error
            dao.getArticlesByQuery(query).map { it.toDomain() }
        }
    }
}