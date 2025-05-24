package com.nightfury.presentation.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nightfury.domain.model.Article
import com.nightfury.presentation.R
import com.nightfury.presentation.utils.DateUtils

@Composable
fun NewsDetailScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            placeholder = painterResource(R.drawable.ic_android_black_24dp),
            error = painterResource(R.drawable.ic_android_red_24dp),
            contentDescription = article.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = article.title, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "By ${article.author ?: "Unknown"} | ${article.source}",
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = DateUtils.formatDate(article.publishedAt),
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.content ?: article.description ?: "No content available.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}