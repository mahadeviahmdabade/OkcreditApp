package com.example.appiness.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appiness.model.ItemsItem

@Database(entities = arrayOf(ItemsItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieInfoDao(): DataModelDao
}