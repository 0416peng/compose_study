                                                                                                                     package com.example.compose_study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.Compose_studyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageCard(Message("Jetpack Compose","study"))
        }
    }
    }
       data class Message(val author: String,val body: String)
     @Composable
     fun MessageCard(msg: Message){
         Column {
             Text(text = msg.author)
             Text(text = msg.body)
         }

     }
       @Preview
       @Composable
       fun PreviewMessageCard(){
           MessageCard(
               msg = Message("Jetpack Compose","study")
           )

       }