package com.example.expensetracker.repository

import androidx.room.Database
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepository  @Inject constructor(private val db: ExpenseDatabase){
    suspend fun insertExpense(expenseEntity: ExpenseEntity)=db.getExpenseDAO().insertExpense(expenseEntity)
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)=db.getExpenseDAO().deleteExpense(expenseEntity)
    suspend fun updateExpense(expenseEntity: ExpenseEntity)=db.getExpenseDAO().updateExpense(expenseEntity)
    fun getAllExpense():Flow<List<ExpenseEntity>> =db.getExpenseDAO().getAllExpense()

}