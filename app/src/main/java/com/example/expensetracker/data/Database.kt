package com.example.expensetracker.data

import android.content.Context
import androidx.room.Room
import com.example.expensetracker.data.dao.ExpenseDAO
import com.example.expensetracker.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides
    @Singleton

    fun provideExpenseDatabase(@ApplicationContext context:Context):ExpenseDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            ExpenseDatabase::class.java,
            "expense_db"
        ).build()

    }
    @Provides
    @Singleton
    fun provideExpenseRepository(db:ExpenseDatabase): ExpenseRepository{

       return ExpenseRepository(db)
    }

}