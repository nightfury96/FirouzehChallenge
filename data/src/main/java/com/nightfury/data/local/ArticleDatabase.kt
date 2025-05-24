package com.nightfury.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nightfury.data.local.entity.ArticleEntity
import com.nightfury.data.local.entity.NewsDao

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}