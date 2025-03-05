package com.ameer.timerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _timers = MutableStateFlow(MutableList(1000) { -1 })
    val timers = _timers.asStateFlow()

    fun startTimer(index: Int) {
        if (_timers.value[index] == -1) {
            _timers.value = _timers.value.toMutableList().also { it[index] = 0 }
            viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _timers.value = _timers.value.toMutableList().also { it[index]++ }
                }
            }
        }
    }
}