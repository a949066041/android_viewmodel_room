package com.example.android.roomwordssample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_test_table")
data class TestWord(@PrimaryKey val id: String,
                    @ColumnInfo(name = "word") val word: String)