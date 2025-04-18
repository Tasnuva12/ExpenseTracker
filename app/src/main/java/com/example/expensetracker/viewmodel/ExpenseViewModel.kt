package com.example.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
 import com.example.expensetracker.R;

@HiltViewModel
class ExpenseViewModel @Inject  constructor(
     private val expenseRepository: ExpenseRepository
): ViewModel(){

    val allExpenses: Flow<List<ExpenseEntity>> =expenseRepository.getAllExpense()
    fun getBalance( list: List<ExpenseEntity>):String {
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            } else {
                total -= it.amount
            }

        }
        return "${total}"
    }

    fun getTotalExpense(list: List<ExpenseEntity>):String{
        var total =0.0
        list.forEach {
            if(it.type=="Expense"){
                total+=it.amount
            }
        }
        return "${total}"


    }
    fun getTotalIncome(list: List<ExpenseEntity>):String{
        var total =0.0
        list.forEach {
            if(it.type=="Income"){
                total+=it.amount
            }
        }
        return "${total}"
    }
    fun getItemIcon(item: ExpenseEntity):Int {
        if (item.category == "Upwork") {
            return R.drawable.upwork
        } else if (item.category == "Youtube") {


        } else if (item.category == "Salary") {

        } else if (item.category == "Spotify") {

        } else if (item.category == "House Rent") {

        } else if (item.category == "Netflix") {

        } else if (item.category == "Electricity ") {

        }
    }


}