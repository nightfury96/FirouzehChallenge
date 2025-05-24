package com.nightfury.firouzehchallenge.utils

import com.nightfury.domain.model.Article
import com.nightfury.presentation.utils.ArticleUtils
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ArticleUtilsTest {
    @Test
    fun testCustomSortArticles_interleavesCorrectly() {
        val microsoftLabel = "microsoft"
        val appleLabel = "apple"
        val googleLabel = "google"
        val fakeLabel = "fake"
        val emptyLabel = "empty"

        val queryOrder = listOf(microsoftLabel, appleLabel, googleLabel, fakeLabel, emptyLabel)

        val articleM1 = dummyArticle("M1")
        val articleM2 = dummyArticle("M2")
        val articleA1 = dummyArticle("A1")
        val articleG1 = dummyArticle("G1")
        val articleG2 = dummyArticle("G2")
        val articleG3 = dummyArticle("G3")

        val queryArticlesMap = mutableMapOf(
            googleLabel to listOf(articleG1, articleG2, articleG3),
            microsoftLabel to listOf(articleM1, articleM2),
            appleLabel to listOf(articleA1),
            emptyLabel to emptyList(),
        )

        val expected = listOf(
            Pair(articleM1, microsoftLabel),
            Pair(articleA1, appleLabel),
            Pair(articleG1, googleLabel),
            Pair(articleM2, microsoftLabel),
            Pair(articleG2, googleLabel),
            Pair(articleG3, googleLabel)
        )

        // Act
        val result = ArticleUtils.customSortArticles(queryOrder, queryArticlesMap)

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun testCustomSortArticles_emptyResultCorrectly() {
        // Arrange
        val queryOrder = listOf("microsoft", "apple", "google", "fake", "empty")


        val queryArticlesMap = mutableMapOf<String, List<Article>>(
            "google" to emptyList(),
            "microsoft" to emptyList(),
            "apple" to emptyList(),
            "empty" to emptyList(),
        )

        val expected = emptyList<Article>()

        // Act
        val result = ArticleUtils.customSortArticles(queryOrder, queryArticlesMap)

        // Assert
        assertEquals(expected, result)
    }

    private fun dummyArticle(title: String): Article {
        return Article(
            title = title,
            description = null,
            content = null,
            imageUrl = null,
            publishedAt = "",
            source = "",
            author = null
        )
    }
}