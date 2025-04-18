package com.example.expensetracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import  com.example.expensetracker.R
import com.example.expensetracker.viewmodel.ExpenseViewModel
import com.example.expensetracker.widgets.ExpenseTrackerText
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.data.model.ExpenseEntity
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(homeViewModel : ExpenseViewModel= hiltViewModel()){
    Surface (
        modifier = Modifier.fillMaxSize() .systemBarsPadding()
    ){
        val expenseList = homeViewModel.allExpenses.collectAsState(initial = emptyList()).value
        val balance = homeViewModel.getBalance(expenseList)
        val totalIncome = homeViewModel.getTotalIncome(expenseList)
        val totalExpense =homeViewModel.getTotalExpense(expenseList)




        ConstraintLayout ( modifier = Modifier.fillMaxSize()){
            val (nameRow, list, card , topBar)=createRefs()
            Image(painter= painterResource(
                id=R.drawable.topbar),contentDescription = "topbar",
                modifier=Modifier.constrainAs(topBar){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                )

            Box(modifier=Modifier.fillMaxWidth().padding(top=32.dp,start=16.dp,end=16.dp).constrainAs(nameRow){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }){
                Column(){
                  ExpenseTrackerText(text="Good Morning", fontSize = 14.sp, fontWeight = FontWeight.Medium,color= colorResource(R.color.white))
                  ExpenseTrackerText(text="Tasnuva Mehnaz",fontSize = 20.sp, fontWeight = FontWeight.SemiBold,color= colorResource(R.color.white))

                }

                Image(
                    painter = painterResource(R.drawable.notification_bell),
                    contentDescription = "Menu",
                    modifier = Modifier.align(CenterEnd)
                )

            }
            CardItem(
                modifier= Modifier.constrainAs(card){
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },balance,expenseList,totalIncome,totalExpense)
            TransactionList(
                modifier = Modifier.fillMaxWidth().constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints// fill available space
                },
                list = expenseList
            )

        }
    }


}

@Composable
fun CardItem(
    modifier: Modifier,
    balance: String,
    expenseList:  List<ExpenseEntity>,
    totalIncome: String,
    totalExpense: String
){

    Column(
        modifier = modifier.padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color=colorResource(R.color.elm))
//
            .height(200.dp)
            .padding(16.dp)

    ){
        Box(modifier=Modifier.fillMaxWidth().weight(1f)) {
            Column (modifier=Modifier.align(Alignment.CenterStart)){
              ExpenseTrackerText(text="Total Balance",fontSize = 15.sp, fontWeight = FontWeight.SemiBold,color= colorResource(R.color.white))
              ExpenseTrackerText(text="$balance",fontSize = 30.sp, fontWeight = FontWeight.SemiBold,color= colorResource(R.color.white))


            }
            Image(
                painter = painterResource(R.drawable.three_dot),
                contentDescription = "Menu",
                modifier = Modifier.align(CenterEnd)
            )


        }
        Box(modifier=Modifier.fillMaxWidth().weight(1f)){

            CardRowItem(
                modifier = Modifier.align(Alignment.CenterStart),
                title = "Income",
                amount = "$totalIncome",
                image = R.drawable.arrow_down_2
            )
            CardRowItem(
                modifier =  Modifier.align(CenterEnd),
                title = "Expenses",
                amount = "$totalExpense",
                image = R.drawable.arrow_up_2
            )
        }



    }

}

@Composable
fun CardRowItem(modifier:Modifier,title:String,amount:String,image:Int){
    Column(modifier=modifier){
         Row{
             Image(painter=painterResource(id=image),
                 contentDescription = null
                 )
             Spacer(modifier=Modifier.size(8.dp))
           ExpenseTrackerText(text=title,
                 fontSize = 16.sp, fontWeight = FontWeight.Medium,
                 color= colorResource(R.color.white))
         }
        Spacer(modifier=Modifier.size(8.dp))
      ExpenseTrackerText(text=amount,fontSize = 20.sp, fontWeight = FontWeight.SemiBold,color= colorResource(R.color.white))
    }
}

@Composable
fun TransactionList(modifier:Modifier,list:List<ExpenseEntity>){
    LazyColumn (modifier=modifier.padding(horizontal = 16.dp)){
       item{
           Box(modifier=Modifier.fillMaxWidth().padding(vertical = 8.dp)){
               ExpenseTrackerText(text="Recent Transactions", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
               ExpenseTrackerText(text="See All",fontSize = 16.sp,color= colorResource(R.color.black),  modifier=Modifier.align(
                   CenterEnd
               ))
           }
       }
        items(list){item->
            TransactionItem(
                title =item.title,
                amount = item.amount.toString(),
                icon = R.drawable.upwork,
                date = "Today",
                color =Color.Green,
                type=item.type
            )
        }



    }

}
@Composable
fun TransactionItem(
    title: String,
    amount: String,
    icon: Int,
    date: String,
    color: Color,
    type: String
){


    Box(modifier=Modifier.fillMaxWidth()){
              Row{
                  Image(
                   painter = painterResource(id=icon),
                      modifier=Modifier.size(50.dp),
                      contentDescription = null
                  )
                  Spacer(modifier=Modifier.size(8.dp))
                  Column{
                    ExpenseTrackerText(text=title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    ExpenseTrackerText(text=date, fontSize = 12.sp)
                  }


              }
      ExpenseTrackerText(
          text = amount,
            fontSize = 20.sp,
            color=color,
          fontWeight = FontWeight.SemiBold,

            modifier = Modifier.align(CenterEnd)



        )
    }
}
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreen()
}