package com.nightfury.domain.repository

import com.nightfury.domain.model.Article

interface NewsRepository {
    suspend fun getNewsByQuery(query: String, from: String, to: String): List<Article>
}