package com.example.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject  constructor(
     private val expenseRepository: ExpenseRepository
): ViewModel(){

    val allExpenses: Flow<List<ExpenseEntity>> =expenseRepository.getAllExpense()
    fun getBalance( ):String{


    }
    fun getTotalExpense():String{

    }
    fun getTotalIncome():String{

    }

}