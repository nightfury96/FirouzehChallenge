package com.nightfury.data.di

import android.content.Context
import androidx.room.Room
import com.nightfury.data.local.ArticleDatabase
import com.nightfury.data.local.entity.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideNewsDao(db: ArticleDatabase): NewsDao = db.newsDao()
}