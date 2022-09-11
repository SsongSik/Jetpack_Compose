package com.example.practice_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice_3.ui.theme.Practice_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //컴포즈가 시작되는 부분은 setContent 부터 시작됨.
//            val scrollState = rememberScrollState()
            //스크롤 정보를 기억해주는 state
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),//사방에 padding
                verticalArrangement = Arrangement.spacedBy(8.dp) //아이템간의 공간
                   // .verticalScroll(scrollState) //기존 안드로이드 스크롤 뷰
            //리니어레이아웃으로 아이템들을 정의 해놓은 것과 같음, 데이터가 많으면 문제가 생길 수 있음
            //따라서 Lazy컬럼이 있음
            ){
                //빠르게 텍스트를 여러 개 만들 수 있음
                //스크롤이 안됨

                //레이지 컬럼은 for문으로 작성되게 안되어있음
//                for(i in 1..50){
//                    Text("글씨 $i")
//                }
                item{
                    Text("Header")
                }
                items(50){ index ->
                    Text("글씨 $index")
                }
                item{
                    Text("Footer")
                }
                //LazyColumn 을 사용하면 만들기 쉬워짐
                //리사이클러뷰를 사용하면 만들기 까다로웠던 것이 쉬워짐
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Practice_3Theme {
        Greeting("Android")
    }
}