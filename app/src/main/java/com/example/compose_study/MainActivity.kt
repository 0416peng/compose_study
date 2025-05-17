package com.example.compose_study
import android.R.attr.background
import android.R.attr.fontWeight
import android.R.attr.shape
import android.R.attr.text
import android.R.attr.textColor
import android.content.res.Configuration
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.example.compose_study.ui.theme.Compose_studyTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  enableEdgeToEdge()
        setContent {
            Compose_studyTheme {
                Column {
                   //  MessageCard(Message("Jetpack Compose","study"))
                   // Conversation(messages = MsgData.messages)
                   // ButtonDemo()
                   // ImageDemo()
                   // Slidrdemo()
                    //Animademo()
                  //  VerticalScrollScreen()
                    //Pagerdemo()
                    //TabPagerWithIndicator()
                    CallCounter()
                }

            }
        }
    }
    }
       data class Message(val author: String,val body: String)
       data class ButtonState(var text: String,var textColor: Color,var buttonColor: Color)
@Composable
fun VerticalScrollScreen(){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        ) {
       Conversation(messages = MsgData.messages)
        ButtonDemo()
        ImageDemo()
        Slidrdemo()
        }

    }

        //卡片
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

     }}//列表
        @Composable
        fun Conversation(messages: List<Message>){
            LazyColumn (
                modifier = Modifier.height(500.dp)
            ){
                items(messages) { messages ->
                    MessageCard(msg = messages)
                }
            }}
//按键
           @Composable
           fun ButtonDemo(){

             val interactionState =remember { MutableInteractionSource() }
               val(text,textColor,buttonColor)=when{
                   interactionState.collectIsPressedAsState().value->ButtonState("Just Pressed",Color.Red , Color.Black)
                   else->ButtonState("Just Button", Color.White, Color.Red)
               }
               Button(onClick = {/*TODO*/},
                   interactionSource = interactionState,
                   elevation = null,
                   shape = RoundedCornerShape(50),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = buttonColor
                   ),
                   modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min)
                   ){Text(
                       text=text, color = textColor
                   )

               }
           }
@Composable
fun ImageDemo(){
    Image(
        painter = painterResource(R.drawable.compose),
        contentDescription=null,
        modifier = Modifier.size(350.dp)
    )
}
@Composable
fun Slidrdemo(){
    var progress by remember { mutableStateOf(0f) }
    Slider(
        value = progress,
        colors=SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color(0xFF0079D3)
        ),
        onValueChange = {
            progress=it
        },
    )
}
@Composable
fun Animademo(){
    var state by remember { mutableStateOf(true) }
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        AnimatedVisibility(visible = state) {
            Text(
                text ="正文",
                fontWeight= FontWeight.W900,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Spacer(Modifier.padding(vertical = 50.dp))
        Button(onClick = {state=!state}) {
            Text(if(state){"隐藏"} else{"显示"})
        }
    }
}

@Composable
fun Pagerdemo(){
    val pagerState=rememberPagerState{3}

    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) {
        page->
        when(page){
            0->Text("page1", modifier = Modifier.background(Color.Red))
            1->Text("page2", modifier = Modifier.background(Color.Green))
            2->Text("page3", modifier = Modifier.background(Color.Blue))
        }
    }
}
@Composable
fun TabPagerWithIndicator(){
    val coroutineScope=rememberCoroutineScope()
    val tabs=listOf("Tab1","Tab2","Tab3")
    val pagerState=rememberPagerState { tabs.size }
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = {tabPositons->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositons[pagerState.currentPage]),
                    color=Color.Red
                )
            }
        ) {tabs.forEachIndexed { index,title->
            Tab(selected=pagerState.currentPage==index,
                onClick ={coroutineScope.launch { pagerState.animateScrollToPage(index) } } ,
                text={Text(title)}
                )
        } }
    }
    HorizontalPager(state = pagerState) {page->
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(text="page${page+1}")
        }
    }
}
@Composable
fun CallCounter(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val count by viewModel.count.observeAsState(0)
    val doubleCount by viewModel.doubleCount.observeAsState(0)
    Column {
        Counter(
            count = count,
            onIncrement = { viewModel.incrementCount() },
            modifier.fillMaxWidth()
        )
        Counter(
            count = doubleCount,
            onIncrement = { viewModel.incrementDoubleCount() },
            modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Counter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$count",
            fontSize = 50.sp
        )
        Button(
            onClick = { onIncrement() }
        ) {
            Text(
                text = "Click me",
                fontSize = 26.sp
            )
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRefreshText(
    modifier: Modifier= Modifier
){
    val list=remember{
        List(4){
            "Item $it"
        }.toMutableList()
    }
    var refreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val state = rememberPullToRefreshState(refreshing =refreshing,onRefresh={
        scope.launch{
            refreshing=true
            delay(1000)
            list+="Item ${list.size+1}"
            refreshing=false
        }
    })
    Box(modifier =modifier
        .fillMaxSize()
        .pullRefresh(state)){
        LazyColumn(Modifier.fillMaxWidth()) {
            //...
        }
        PullRefreshIndicator(refreshing,state,align(Alignment.TopCenter))
    }
}*/



//预览
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
