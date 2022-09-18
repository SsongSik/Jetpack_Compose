package com.example.practice_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.practice_5.ui.theme.Practice_5Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //mutableState라는 상태가 됨.
            //방법 1
//            val textValue = remember{
//                mutableStateOf("")
//            }

            val (text, setValue) = remember{
                mutableStateOf("")
                //text : String, setValue : (String) -> Unit 을 리턴하는 형태
                //값을 다시 할당하는 setter에 해당하게 됨
            }

            val scaffoldState = rememberScaffoldState() //최근의 상태를 지정
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current

            //스낵바 사용할 때에는 Scaffold로 감싸고 사용함
            Scaffold(
                scaffoldState = scaffoldState //스낵바를 활용하기위해
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    TextField(
//                    value = textValue.value, //비어있으면 입력이 안됨(값이 변하지 않기 때문에)
//                    onValueChange = {
//                        textValue.value = it
//                    } //값이 변했을 때의 함수
                        value = text,
                        onValueChange = setValue,
                    )
                    Button(onClick = {
                        keyboardController?.hide()
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello $text")
                        }
                    }) {
                        Text("클릭")
                    }
                }
            }

        }
    }
}
