package com.nightfury.presentation.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nightfury.domain.model.Article
import com.nightfury.presentation.news.component.NewsCard

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    when (val state = viewModel.uiState) {
        is NewsUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is NewsUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${state.message}")
            }
        }

        is NewsUiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(state.articles) { (article, query) ->
                    NewsCard(
                        article = article,
                        queryLabel = query,
                        onClick = { onArticleClick(article) })
                }
            }
        }
    }
}