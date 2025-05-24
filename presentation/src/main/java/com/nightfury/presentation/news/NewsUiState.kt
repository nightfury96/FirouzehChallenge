package com.nightfury.presentation.news

import com.nightfury.domain.model.Article

sealed class NewsUiState {
    data object Loading : NewsUiState()
    data class Success(val articles: List<Pair<Article, String>>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}