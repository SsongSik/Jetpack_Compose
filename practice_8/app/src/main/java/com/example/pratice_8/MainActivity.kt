package com.example.pratice_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pratice_8.ui.theme.Pratice_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}
/*
state
화면의 표시된내용이 변경되면 state를 활용하였음

컴포즈는 스테이트기반으로 항상 화면이 그려지기 때문에 스테이트에 대한 개념이중요함
 */
@Composable
fun HomeScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val (text, setText) = remember {
        mutableStateOf("Hello")
    }//하나만 작성했을 때에는 mutableState타입이지만,
    //이렇게 구조분해를 하면 text가 String이다.
    var text1 : String by remember {
        mutableStateOf("Hello")
    }

    Column() {
        Text("Hello")
        Button(onClick = {
            print(text)
            text1 = "변경" //이런식으로 변경이 가능함
//            viewModel.value.value = "변경" //함수를 통해서
            viewModel.changeValue("변경")
        }){
           Text("클릭")
        }
        TextField(value = text, onValueChange = setText)
        //다시 컴포지션이 발생하면서 입력되는 것처럼 보임
    }
}

class MainViewModel : ViewModel() {
    //mutableState 쓰기 와 읽기가 가능
    //State 는 읽기만
    private val _value = mutableStateOf("Hello")
    val value : State<String> = _value

    fun changeValue(value : String){
        _value.value = value
    }
}