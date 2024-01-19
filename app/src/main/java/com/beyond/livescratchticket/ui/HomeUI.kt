package com.beyond.livescratchticket.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun addHome(navController: NavController, tasks: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            text = "家庭任务",
            fontSize = 30.sp,
            color = Color.Black,
        )
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
        ) {
            itemsIndexed(items = tasks) { index, item ->
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("detail/$index/$item")
                        },
                    text = "${index + 1}: $item"
                )
            }
        }
    }
}