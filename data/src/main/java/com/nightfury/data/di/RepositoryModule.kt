package com.nightfury.data.di

import com.nightfury.data.local.entity.NewsDao
import com.nightfury.data.remote.api.NewsApi
import com.nightfury.data.repository.NewsRepositoryImpl
import com.nightfury.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, dao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(api, dao)
    }
}