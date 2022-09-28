package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatch.ui.theme.StopWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()

            val sec = viewModel.sec.value
            val milli = viewModel.milli.value
            val isRunning = viewModel.isRunning.value
            val lapTimes = viewModel.lapTimes.value

            MainScreen(
                sec = sec,
                milli = milli,
                isRunning = isRunning,
                lapTimes = lapTimes,
                onReset = { viewModel.reset() },
                onToggle = { running ->
                    if(running) {
                        viewModel.pause()
                    }else{
                        viewModel.start()
                    }
                },
                onLapTime = { viewModel.recordLapTime() }
            )
        }
    }
}

@Composable
fun MainScreen(
    sec : Int,
    milli : Int,
    isRunning : Boolean,
    lapTimes : List<String>,
    onReset : () -> Unit,
    onToggle : (Boolean) -> Unit,
    onLapTime : () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("StopWatch")})
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
            ){
                Text("$sec", fontSize = 100.sp)
                Text("$milli")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f) // 나머지 영역을 모두 차지
                    .verticalScroll(rememberScrollState()),
            ){
                lapTimes.forEach { lapTime ->
                    Text(lapTime)
                }
            }

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                //reset 버튼
                FloatingActionButton(
                    onClick = {
                        onReset()
                    },
                    backgroundColor = Color.Red,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                        contentDescription = "reset"
                    )
                }

                FloatingActionButton(
                    onClick = {
                        onToggle(isRunning)
                    },
                    backgroundColor = Color.Green,
                ) {
                    Image(
                        painter = painterResource(id = if(isRunning) R.drawable.ic_baseline_pause_24 else R.drawable.ic_baseline_play_arrow_24),
                        contentDescription = "toggle"
                    )
                }

                Button(
                    onClick = {
                        onLapTime()
                    }
                ){
                    Text("랩타임")
                }
            }
        }
    }
}
