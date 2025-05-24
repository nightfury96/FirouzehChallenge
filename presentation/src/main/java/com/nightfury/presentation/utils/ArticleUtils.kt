package com.nightfury.presentation.utils

import com.nightfury.domain.model.Article
import java.util.Locale

class ArticleUtils {
    companion object {
        /**
         * Capitalizes the first character of the given string using the default locale.
         *
         * If the first character is lowercase, it is converted to its title case equivalent.
         * If it's already uppercase or not a letter, it remains unchanged.
         *
         * @param text The input string to capitalize.
         * @return A copy of the input string with the first character capitalized (if applicable).
         *
         * @sample
         * capitalize("microsoft") -> "Microsoft"
         * capitalize("Apple")     -> "Apple"
         * capitalize("123tech")   -> "123tech"
         */
        fun capitalize(text: String) =
            text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

        /**
         * Interleaves articles from multiple query sources in round-robin order.
         *
         * Given a list of sorted queries (e.g., "microsoft", "apple", "google", "tesla") and a map of
         * query → list of articles, this function returns a single list of articles arranged in
         * repeating order of the queries. For example, if each query has articles, the result would
         * alternate like:
         *   microsoft → apple → google → tesla → microsoft → ...
         *
         * Each result is returned as a Pair of the Article and its corresponding query label.
         * This helps retain source context in the UI.
         *
         * @param sortedQueries The fixed order of queries used to interleave results (e.g., ["microsoft", "apple", "google", "tesla"]).
         * @param queryArticlesMap A map of query name to list of articles fetched for that query.
         *                          Each list is assumed to be already sorted by date (most recent first).
         *
         * @return A single list of articles interleaved in round-robin order, with each article paired with its originating query.
         */
        fun customSortArticles(
            sortedQueries: List<String>,
            queryArticlesMap: MutableMap<String, List<Article>>
        ): List<Pair<Article, String>> {
            val result = mutableListOf<Pair<Article, String>>()

            // Prepare per-query article queues in fixed order
            val allArticleListSortedQuery = sortedQueries.map { sortedQuery ->
                queryArticlesMap[sortedQuery]?.map { it to sortedQuery }?.toMutableList()
                    ?: mutableListOf()
            }

            // Keep looping until all queues are empty
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