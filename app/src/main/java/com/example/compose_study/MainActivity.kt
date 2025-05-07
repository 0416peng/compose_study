                                                                                                                     package com.example.compose_study

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        setContent {
            Compose_studyTheme {
               // MessageCard(Message("Jetpack Compose","study"))
                Conversation(messages = MsgData.messages)
            }
        }
    }
    }
       data class Message(val author: String,val body: String)
     @Composable
     fun MessageCard(msg: Message){
         var isExpanded by remember { mutableStateOf(false) }
         Surface (shape = MaterialTheme.shapes.medium,
             shadowElevation = 5.dp,
             modifier = Modifier
                 .padding(all=8.dp)
                 .clickable{
                     isExpanded =!isExpanded
                 }
             ){
         Row (modifier = Modifier.padding(all =8.dp))
         {
             Image(
                 painterResource(R.drawable.compose),//这里注意逗号
                 contentDescription = "profile picture",
                 modifier = Modifier
                     .size(50.dp)
                     .clip(CircleShape)
                     .border(1.5.dp,MaterialTheme.colorScheme.secondary, shape = CircleShape)//添加边框
             )
             Spacer(Modifier.padding(horizontal = 8.dp))//添加空控件用来填充水平间距
             Column {
                 Text(text = msg.author,
                     color = MaterialTheme.colorScheme.secondary,
                     style = MaterialTheme.typography.titleMedium
                 )
                 Spacer(Modifier.padding(vertical = 4.dp))
                 Text(text = msg.body,
                     style = MaterialTheme.typography.bodyMedium,
                     maxLines = if(isExpanded)Int.MAX_VALUE else 1,
                     modifier = Modifier.animateContentSize()
                     )

             }
         }//modifiers用于改变尺寸

     }}
        @Composable
        fun Conversation(messages: List<Message>){
            LazyColumn {
                items(messages) { messages ->
                    MessageCard(msg = messages)
                }
            }}

       @Preview(name = "Light Mode")
       @Preview(
           uiMode = Configuration.UI_MODE_NIGHT_YES,
           showBackground = true,
           name="Dark Mode"
       )
       @Composable
       fun PreviewMessageCard(){
           Compose_studyTheme{
               Conversation(messages =MsgData.messages)
           }
       }
