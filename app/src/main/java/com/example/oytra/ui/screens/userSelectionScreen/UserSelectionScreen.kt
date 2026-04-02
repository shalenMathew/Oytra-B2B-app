package com.example.oytra.ui.screens.userSelectionScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.oytra.data.model.UserType
import com.example.oytra.ui.theme.Pink40
import com.example.oytra.ui.theme.VibrantPink


@Composable
fun UserSelectionScreen(
    userTypeSelected : (String) -> Unit
){

    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        Text(text = "Oytra Internal App",
            color = VibrantPink,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Select your account type to continue",
            color = Color.Black,
            fontSize = 15.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            userTypeSelected(UserType.DEALER.toString())
        }, colors = ButtonDefaults.buttonColors(containerColor = Pink40 ),) {
            Text(text = "Dealer",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 25.dp,vertical = 5.dp),
                fontSize = 15.sp
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            Log.d("testing","from userSel - ${UserType.RETAILER.customerType}")

            userTypeSelected(UserType.RETAILER.toString())
        }, colors = ButtonDefaults.buttonColors(containerColor = Pink40 )) {
            Text(text = "Retailer",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 25.dp,vertical = 5.dp),
                fontSize = 15.sp
            )
        }

    }

}