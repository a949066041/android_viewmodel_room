package com.example.android.roomwordssample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TestWordDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM word_test_table ORDER BY word ASC")
    fun getTestWordLists(): Flow<List<TestWord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(testWord: TestWord)

    @Query("DELETE FROM word_test_table")
    suspend fun deleteAll()
}