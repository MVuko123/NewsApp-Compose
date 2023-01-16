package com.example.newscompose.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {
    @Query("SELECT * FROM savedNews")
    fun saved(): Flow<List<DbSavedNews>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoSaved(savedNews: DbSavedNews)

    @Delete
    fun deleteFromSaved(savedNews: DbSavedNews)
}
