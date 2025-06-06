package com.example.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDAO{

    @Query("SELECT* FROM expense_table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)
    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)
    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)



}