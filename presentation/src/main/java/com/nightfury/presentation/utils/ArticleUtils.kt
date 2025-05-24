package com.nightfury.presentation.utils

import com.nightfury.domain.model.Article
import java.util.Locale

class ArticleUtils {
    companion object {
        fun capitalize(text: String) =
            text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

        fun customSortArticles(
            sortedQueries: List<String>,
            queryArtistsMap: MutableMap<String, List<Article>>
        ): List<Pair<Article, String>> {
            val result = mutableListOf<Pair<Article, String>>()

            val allArticleListSortedQuery = sortedQueries.map { sortedQuery ->
                queryArtistsMap[sortedQuery]?.map { it to sortedQuery }?.toMutableList()
                    ?: mutableListOf()
            }
            var isAnyArticleLeft = true
            while (isAnyArticleLeft) {
                isAnyArticleLeft = false
                allArticleListSortedQuery.forEach { articles ->
                    articles.removeFirstOrNull()?.let {
                        result.add(it)
                        isAnyArticleLeft = true
                    }
                }
            }
            return result
        }
    }
}