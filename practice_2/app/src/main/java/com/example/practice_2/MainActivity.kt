package com.example.practice_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice_2.ui.theme.Practice_2Theme
/*
Box
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //안에있는 자식들이 겹쳐서 정렬 FrameLayout 과 비슷함
            Box(
                modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxWidth()
                    .height(200.dp),//가로
                //세로
//                    .fillMaxSize(), //가로 세로 전체
                contentAlignment = Alignment.TopEnd, //자식들이 상단 우측으로 정렬됨
            /*
            밑 글자만 오른쪽 아래로 옮기고 싶다라고 한다면
            둘 다 옮겨지게됨.
             */
            ){
                Text("Hello") //겹쳤기 떄문에 하나로 보임
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomEnd,
                ){
                    Text("123455~~~~")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) { //함수인데도 불구하고 대문자로 시작함
    //Composable 은 대문자로 시작함
    Text(text = "Hello $name!")
}

//Preview는 미리보기로 볼 수 있음
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Practice_2Theme {
        Greeting("gg")
    }
}