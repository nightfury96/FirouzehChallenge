package com.nightfury.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nightfury.domain.model.Article
import com.nightfury.presentation.news.NewsDetailScreen
import com.nightfury.presentation.news.NewsListScreen

@Composable
fun NewsApp(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController, modifier = modifier, startDestination = "news_list") {
        composable("news_list") {
            NewsListScreen(onArticleClick = { article ->
                navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                navController.navigate("news_detail")
            })
        }

        composable("news_detail") {
            val article = navController.previousBackStackEntry
                ?.savedStateHandle?.get<Article>("article")

            article?.let {
                NewsDetailScreen(article = it)
            }
        }
    }
}