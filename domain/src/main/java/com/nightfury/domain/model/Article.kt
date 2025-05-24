package com.nightfury.domain.model

import java.io.Serializable


data class Article(
    val title: String,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val publishedAt: String,
    val source: String,
    val author: String?
) : Serializable