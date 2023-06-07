package com.yakogdan.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideEffectTest(number: MyNumber) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "Number: ${number.a}")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        android.os.Handler().postDelayed({
            number.a = 5
        }, 3000)

        LazyColumn {
            repeat(5) {
                item {
                    Text(text = "Number: ${number.a}")
                }
            }
        }
    }
}

data class MyNumber(var a: Int)