package com.judrummer.androidreadermonadplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kittinunf.result.Result
import com.judrummer.androidreadermonadplayground.data.AppPreference
import com.judrummer.androidreadermonadplayground.data.AppPreferenceImpl
import com.judrummer.androidreadermonadplayground.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

sealed class MainState {
    object Initialize : MainState()
    object Loading : MainState()
    data class Success(val userProfile: UserProfile) : MainState()
    data class Error(val throwable: Throwable) : MainState()
}

class LoginUseCaseDependenciesImpl : LoginUseCaseDependencies {
    override val userProfileMapper: UserProfileMapper = UserProfileMapperImpl()
    override val appPreference: AppPreference = AppPreferenceImpl()
}

class MainViewModel(
        private val loginUseCaseDependencies: LoginUseCaseDependencies = LoginUseCaseDependenciesImpl()
) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun login(username: String, password: String) {
        _state.value = MainState.Loading
        launch {
            launch {
                val result = loginUseCase(username, password)
                        .runReader(loginUseCaseDependencies.appPreference)
                        .runReader(loginUseCaseDependencies.userProfileMapper)

                when (result) {
                    is Result.Success -> {
                        _state.value = MainState.Success(result.value)
                    }
                    is Result.Failure -> {

                        _state.value = MainState.Error(result.error)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        job.cancel()
    }
}
