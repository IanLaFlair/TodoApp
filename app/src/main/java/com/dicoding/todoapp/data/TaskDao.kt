package com.dicoding.todoapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO)
@Dao
interface TaskDao {

    @RawQuery(observedEntities = [Task::class])
    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

    @Query("SELECT * from tasks where id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Query("SELECT * from tasks where iscompleted = :isCompleted ORDER BY duedatemillis ASC")
    fun getNearestActiveTask(isCompleted: Boolean): Task

    @Insert
    suspend fun insertTask(task: Task): Long

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks set iscompleted = :completed WHERE id = :taskId")
    suspend fun updateCompleted(taskId: Int, completed: Boolean)
    
}