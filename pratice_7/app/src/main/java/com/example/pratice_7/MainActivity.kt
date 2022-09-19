package com.example.pratice_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pratice_7.ui.theme.Pratice_7Theme

class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val data = remember{mutableStateOf("Hello")}
            //있던 스테이트를 기억하기 위해 : 이런식으로 코드 작성을 했음

            val viewModel = viewModel<MainViewModel>()

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    viewModel.data.value,
                    fontSize = 30.sp,
                )
                Button(onClick = {
//                    viewModel.data.value = "World" //눌러도 변경이 안됨.
                    //변경이 되면 컴포즈가 다시 리셋이되어 hello가 계속 나옴
                    //따라서 리멤버 사용
                    viewModel.changeValue()
                }){
                    Text("변경")
                }
            }
        }
    }
}

class MainViewModel : ViewModel(){
    private val _data = mutableStateOf("Hello")
    val data : State<String> = _data

    fun changeValue(){
        _data.value = "World"
    }
}
