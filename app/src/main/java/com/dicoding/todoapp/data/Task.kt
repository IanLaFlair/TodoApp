package com.dicoding.todoapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 1 : Define a local database table using the schema in app/schema/tasks.json
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "duedatemillis")
    val dueDateMillis: Long,

    @ColumnInfo(name = "iscompleted")
    val isCompleted: Boolean = false
)