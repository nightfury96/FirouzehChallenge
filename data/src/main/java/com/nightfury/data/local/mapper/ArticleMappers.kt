package com.nightfury.data.local.mapper

import com.nightfury.data.local.entity.ArticleEntity
import com.nightfury.data.remote.model.ArticleDto
import com.nightfury.domain.model.Article

fun ArticleDto.toEntity(query: String): ArticleEntity {
    return ArticleEntity(
        title = title,
        description = description,
        content = content,
        imageUrl = urlToImage,
        publishedAt = publishedAt,
        source = source.name,
        author = author,
        queryLabel = query
    )
}

fun ArticleEntity.toDomain(): Article {
    return Article(
        title = title,
        description = description,
        content = content,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        source = source,
        author = author
    )
}