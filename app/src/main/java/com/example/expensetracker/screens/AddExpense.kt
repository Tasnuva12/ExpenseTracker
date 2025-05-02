package com.example.expensetracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.expensetracker.R

import com.example.expensetracker.widgets.ExpenseTrackerText


@Composable
fun AddExpense() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar) = createRefs()
            Image(painter = painterResource(
                id = R.drawable.topbar
            ), contentDescription = "topbar",
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
              ExpenseTrackerText(
                  text = "Add Expense",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)

                )
                Image(
                    painter = painterResource(R.drawable.three_dot),

                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
                Image(
                    painter = painterResource(R.drawable.previouspagenavigationicon),

                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            DataForm(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .constrainAs(card) {
                        top.linkTo(nameRow.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)


                    })

        }


    }
}

@Composable
fun DataForm(modifier: Modifier) {
    val name = remember{
        mutableStateOf("")
    }
    val amount = remember{
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)

            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        ExpenseTrackerText(text = "Name", fontSize = 14.sp,color=Color.Gray)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = name.value, onValueChange = {name.value=it}, modifier = Modifier.fillMaxWidth())
        ExpenseTrackerText(text = "Amount", fontSize = 14.sp,color=Color.Gray)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(value = amount.value, onValueChange = {amount.value=it}, modifier = Modifier.fillMaxWidth())

        //Button to submit the form

       Button(onClick = { },modifier=Modifier
           .clip(RoundedCornerShape(2.dp))
           .fillMaxWidth()) {
         ExpenseTrackerText(text="Add Expense", fontSize = 14.sp,color=Color.White)
       }
    }
}

@Composable
@Preview(showBackground = true)
fun AddExpensePreview() {
    AddExpense()
}
