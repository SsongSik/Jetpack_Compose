package com.example.stopwatch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class MainViewModel : ViewModel() {

    private var time = 0
    private var timerTask : Timer? = null
    //현재 진행되고 있는지
    private val _isRunning = mutableStateOf(false)
    val isRunning : State<Boolean> = _isRunning

    private val _sec = mutableStateOf(0)
    val sec : State<Int> = _sec

    private val _milli = mutableStateOf(0)
    val milli : State<Int> = _milli

    private val _lapTimes = mutableStateOf(mutableListOf<String>())
    val lapTimes : State<List<String>> = _lapTimes

    private var lap = 1

    fun start(){
        _isRunning.value = true

        timerTask = timer(period = 10) {
            time++
            _sec.value = time / 100
            _milli.value = time % 100
        }
    }

    fun pause(){
        _isRunning.value = false
        timerTask?.cancel()
    }

    fun reset(){
        //모두 초기화
        timerTask?.cancel()

        time = 0
        _isRunning.value = false
        _sec.value = 0
        _milli.value = 0

        _lapTimes.value.clear()
        lap = 1
    }

    fun recordLapTime(){
        _lapTimes.value.add(0, "$lap Lap : ${sec.value}.${milli.value}")
        lap++
    }
}