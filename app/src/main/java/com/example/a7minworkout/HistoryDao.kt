package com.example.a7minworkout

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)
    @Update
    suspend fun update(historyEntity: HistoryEntity)
    @Delete
    suspend fun  delete(historyEntity: HistoryEntity)

    @Query("SELECT*FROM 'history_table'")
    fun getAll():Flow<List<HistoryEntity>>
    @Query("SELECT*FROM'history_table'where id=:id")
    fun getByID(id:Int):Flow<List<HistoryEntity>>

}