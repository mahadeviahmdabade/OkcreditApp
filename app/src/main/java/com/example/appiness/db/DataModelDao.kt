package com.example.appiness.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appiness.model.ItemsItem

@Dao
interface DataModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listOfMovieInfo : List<ItemsItem>)

    @Query("Select * From ItemsItem where login like '%' || :query  || '%'")
    fun getMoviesInfo(query : String) : List<ItemsItem>?
}