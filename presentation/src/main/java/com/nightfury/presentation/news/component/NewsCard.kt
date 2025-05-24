package com.nightfury.presentation.news.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nightfury.domain.model.Article
import com.nightfury.presentation.R
import com.nightfury.presentation.utils.ArticleUtils
import com.nightfury.presentation.utils.DateUtils

@Composable
fun NewsCard(
    article: Article,
    queryLabel: String, // the source keyword (Microsoft, Apple, ...)
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = article.imageUrl,
                placeholder = painterResource(R.drawable.ic_android_black_24dp),
                error = painterResource(R.drawable.ic_android_red_24dp),
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium
            )

            article.description?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = ArticleUtils.capitalize(queryLabel),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = DateUtils.formatDate(article.publishedAt),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
// --- Previews ---

val sampleArticle1 = Article(
    title = "Exciting Tech Breakthrough Announced by Major Company",
    description = "This is a detailed description of the recent tech breakthrough. It covers various aspects and implications for the future of technology. The innovation promises to revolutionize the industry.",
    content = "Full content of the article about the tech breakthrough...",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg",
    publishedAt = "2025-05-23T10:15:30Z",
    source = "tech chronicle",
    author = "Jane Doe"
)

val sampleArticleNoImage = Article(
    title = "The Rise of AI in Creative Writing",
    description = "Artificial intelligence is increasingly being used in creative fields. This article explores its potential and limitations in the realm of writing fiction and poetry.",
    content = "More details about AI in creative writing...",
    imageUrl = null,
    publishedAt = "2025-05-22T14:00:00Z",
    source = "AI Today",
    author = "John Smith"
)

val sampleArticleNoDescription = Article(
    title = "Market Update: Stocks Rally After Positive Economic News",
    description = null,
    content = "The stock market saw a significant rally today...",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg",
    publishedAt = "2025-05-23T09:30:00Z",
    source = "Finance Weekly",
    author = null
)

val sampleArticleShortContent = Article(
    title = "Brief News",
    description = "Short update.",
    content = "Just a quick update.",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg",
    publishedAt = "2025-05-21T18:45:10Z",
    source = "Quick Bytes",
    author = "Admin"
)

val sampleArticleLongTitle = Article(
    title = "This is an Extremely Long News Title That Should Ideally Be Truncated or Wrap to Multiple Lines Depending on the Design System and Requirements for Presenting Such Information",
    description = "A regular description to accompany the very long title. We are testing how the title handles overflow.",
    content = "Content for the article with a very long title...",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg",
    publishedAt = "2025-05-20T11:00:00Z",
    source = "Headline News",
    author = "Editor"
)

val sampleArticleLongDescription = Article(
    title = "Exploring Deep Sea Mysteries",
    description = "This article delves into the profound and often unexplored mysteries of the deep sea, discussing recent discoveries, the unique ecosystems found at extreme depths, the challenges of exploration, and the importance of ocean conservation efforts for these fragile environments. The text is intentionally long to test the ellipsis functionality for the description field, ensuring it truncates gracefully after three lines as specified in the composable.",
    content = "Full scientific report on deep sea mysteries...",
    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg",
    publishedAt = "2025-05-19T16:20:55Z",
    source = "Science Journal",
    author = "Dr. Ocean"
)

@Preview(showBackground = true, name = "NewsCard - Full")
@Composable
fun NewsCardPreviewFull() {
    MaterialTheme {
        NewsCard(
            article = sampleArticle1,
            queryLabel = sampleArticle1.source, // Using article.source for queryLabel
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "NewsCard - No Image")
@Composable
fun NewsCardPreviewNoImage() {
    MaterialTheme {
        NewsCard(
            article = sampleArticleNoImage,
            queryLabel = sampleArticleNoImage.source,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "NewsCard - No Description")
@Composable
fun NewsCardPreviewNoDescription() {
    MaterialTheme {
        NewsCard(
            article = sampleArticleNoDescription,
            queryLabel = sampleArticleNoDescription.source,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "NewsCard - Short Content")
@Composable
fun NewsCardPreviewShortContent() {
    MaterialTheme {
        NewsCard(
            article = sampleArticleShortContent,
            queryLabel = sampleArticleShortContent.source,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "NewsCard - Long Title")
@Composable
fun NewsCardPreviewLongTitle() {
    MaterialTheme {
        NewsCard(
            article = sampleArticleLongTitle,
            queryLabel = sampleArticleLongTitle.source,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "NewsCard - Long Description")
@Composable
fun NewsCardPreviewLongDescription() {
    MaterialTheme {
        NewsCard(
            article = sampleArticleLongDescription,
            queryLabel = sampleArticleLongDescription.source,
            onClick = {}
        )
    }
}