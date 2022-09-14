package com.example.practice_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice_4.ui.theme.Practice_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isFavorite by rememberSaveable{ //remember가 기억을 해주는 형태
                mutableStateOf(false)
            }
            //이미지 카드를 여러 개 사용하고, 재사용할 수 있음
            ImageCard(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp),
                isFavorite = isFavorite
            ){ favorite ->
                isFavorite = favorite //값을 갱신함
            }
        }
    }
}
//화면을 돌리면 초기화가됨. saveInstanceOf 를 사용할 수 있었음
// rememberSaveable 을 사용하면됨
//보존이 필요한 부분에서는 이걸 사용할 수 있음

//Composable은 image밑에 사항들을 재사용하기 위해 만들어졌는데
//이런 변수들이 있으면 재사용이 어려워짐
//재사용하기 좋게 만드려면 내부에 modifier를 지정하는 것보다
//외부에서 만드는게 훨씬 좋아보임
@Composable
fun ImageCard(
    modifier: Modifier = Modifier, //기본값으로 지정
    isFavorite : Boolean, //상수임 변수가 아니라
    onTabFavorite : (Boolean) -> Unit //콜백으로 만들수있음
){
//    var isFavorite : Boolean  //이렇게 할 수 있었음
    //state를 활용할 수 있음 , Compose에서는

//    var isFavorite by rememberSaveable{ //remember가 기억을 해주는 형태
//        mutableStateOf(false)
//    } //mutableState 형식

    //by를 사용해서 mutablestate 속성에서 -> boolean형식으로 바뀜

    Card(
        modifier = modifier,
//            .fillMaxWidth(0.5f)
//            .padding(16.dp),//절반을 의미함
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
    ){
        Box(
            modifier = Modifier.height(200.dp)
        ){
            Image(painter = painterResource(id = R.drawable.servicelogo), //이미지 id
                contentDescription = "logo", //이미지 설명
                contentScale = ContentScale.Crop, //이미지가 좀 예쁘게
            )
            Box(
                modifier = Modifier.fillMaxSize(), //크기를 꽉 채움
                contentAlignment = Alignment.TopEnd //오른쪽 맨위
            ){
                IconButton(onClick = {
                    //isFavorite = !isFavorite //value를 써주는 불편함이 있음 by를 적어주어야함. 생략하기 위해
                    onTabFavorite(!isFavorite)
                }){
                    Icon(imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "like",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}