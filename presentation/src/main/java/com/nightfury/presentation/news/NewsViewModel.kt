package com.nightfury.presentation.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nightfury.domain.model.Article
import com.nightfury.domain.usecase.GetNewsUseCase
import com.nightfury.presentation.utils.ArticleUtils
import com.nightfury.presentation.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for handling news screen logic and state.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var uiState by mutableStateOf<NewsUiState>(NewsUiState.Loading)
        private set

    init {
        fetchNews()
    }

    /**
     * Fetch articles in round-robin order for the defined queries.
     */
    private fun fetchNews() {
        viewModelScope.launch {
            uiState = NewsUiState.Loading
            try {
                val (from, to) = DateUtils.getYesterdayAndNowDates()
                val results = mutableMapOf<String, List<Article>>()
                val sortedQueries = listOf("microsoft", "apple", "google", "tesla")
                sortedQueries.forEach { query ->
                    val articles = getNewsUseCase(query, from, to)
                    results[query] = articles
                }
                uiState =
                    NewsUiState.Success(ArticleUtils.customSortArticles(sortedQueries, results))
            } catch (e: Exception) {
                uiState = NewsUiState.Error(e.message ?: "Unexpected error")
            }
        }
    }
}