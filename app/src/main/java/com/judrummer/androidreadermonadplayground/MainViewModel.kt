package com.judrummer.androidreadermonadplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

sealed class MainState {
    object Initialize : MainState()
    object Loading : MainState()
    data class Success(val userProfile: String) : MainState()
    data class Error(val throwable: Throwable) : MainState()
}

class MainViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun login(username: String, password: String) {
        launch {

        }
    }

    override fun onCleared() {
        job.cancel()
    }
}
