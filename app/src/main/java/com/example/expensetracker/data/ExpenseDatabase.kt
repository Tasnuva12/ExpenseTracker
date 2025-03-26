package com.example.expensetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetracker.data.dao.ExpenseDAO
import com.example.expensetracker.data.model.ExpenseEntity

@Database(entities=[ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase: RoomDatabase()
{
    abstract fun getExpenseDAO(): ExpenseDAO
}