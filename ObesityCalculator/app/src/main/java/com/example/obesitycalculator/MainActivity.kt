package com.example.obesitycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obesitycalculator.ui.theme.ObesityCalculatorTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<BmiViewModel>()
            val navController = rememberNavController()

            val bmi = viewModel.bmi.value

            NavHost(navController = navController, startDestination = "home"){
                composable(route = "home"){
                    HomeScreen(){ height, weight ->
                        viewModel.bmiCalculate(height, weight)
                        navController.navigate("result")
                    }
                }
                composable(route = "result"){
                    ResultScreen(navController, bmi)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onResultClicked : (Double, Double) -> Unit,
){
    val (height, setHeight) = rememberSaveable{
        mutableStateOf("") //???????????? ???????????? ??????
    }
    val (weight, setWeight) = rememberSaveable{
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("????????? ?????????")}
            )
        }
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            OutlinedTextField(
                value = height,
                onValueChange = setHeight,
                label = { Text("???") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            OutlinedTextField(
                value = weight,
                onValueChange = setWeight,
                label = { Text("?????????") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if(height.isNotEmpty() && weight.isNotEmpty()){
                        onResultClicked(height.toDouble(), weight.toDouble())
                    }
                },
                modifier = Modifier.align(Alignment.End),
            ){
                Text("??????")
            }
        }
    }
}

@Composable
fun ResultScreen(
    navController: NavController,
    bmi : Double
){
    val text = when{
        bmi >= 35 -> "?????? ??????"
        bmi >= 30 -> "2?????? ??????"
        bmi >= 25 -> "1?????? ??????"
        bmi >= 23 -> "?????????"
        bmi >= 18.5 -> "??????"
        else -> "?????????"
    }

    val imageRes = when {
        bmi >= 23 -> R.drawable.ic_baseline_sentiment_very_dissatisfied_24
        bmi >= 18.5 -> R.drawable.ic_baseline_sentiment_satisfied_24
        else -> R.drawable.ic_baseline_sentiment_dissatisfied_24
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("????????? ?????????")},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "home",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(50.dp))
            Image(painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(
                    color = Color.Black
                )
            )
        }
    }
}

class BmiViewModel : ViewModel(){
    private val _bmi = mutableStateOf(0.0)
    val bmi : State<Double> = _bmi

    fun bmiCalculate(height : Double, weight : Double){
        _bmi.value = weight / (height / 100.0).pow(2.0)
    }
}


























