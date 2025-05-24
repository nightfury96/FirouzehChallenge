package com.nightfury.data.remote.model

data class NewsResponse(
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class SourceDto(
    val name: String
)