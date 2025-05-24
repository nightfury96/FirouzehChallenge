package com.nightfury.domain.usecase

import com.nightfury.domain.model.Article
import com.nightfury.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Use case for retrieving articles from a specific query within a time range.
 */
class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    /**
     * Fetch articles by query and date range.
     */
    suspend operator fun invoke(query: String, from: String, to: String): List<Article> {
        return repository.getNewsByQuery(query, from, to)
    }

}