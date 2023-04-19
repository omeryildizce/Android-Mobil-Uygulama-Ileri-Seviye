package com.omeryildizce.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val coutDownTimerFlow = flow<Int> {
        val countDownFrom = 10
        var counter = countDownFrom
        emit(countDownFrom)
        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter)
        }
    }

    init {
        collectInViewModel()
    }

    private fun collectInViewModel() {
        viewModelScope.launch {
            coutDownTimerFlow
                .filter {
                    it % 3 == 0
                }.map {
                    it + it
                }
                .collect {
                    println(it)
                }

            coutDownTimerFlow.collectLatest {
                delay(2000)
                print(it)
            }
        }
    }

    // Live data comparision
    private val _liveData = MutableLiveData<String>("Kotlin Live Data")
    val liveData: LiveData<String> = _liveData

    fun changeLiveDataValue() {
        _liveData.value = "Live Data"

    }


    private val _stateFlow = MutableStateFlow("Kotlin State Flow")
    val stateFlow = _stateFlow
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun changeStateFlowValue() {
        _stateFlow.value = "State Flow"
    }

    fun changeSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("Shared Flow")
        }
    }

}

