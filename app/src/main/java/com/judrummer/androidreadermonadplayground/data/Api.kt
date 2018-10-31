package com.judrummer.androidreadermonadplayground.data

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay

suspend fun loginApi(username: String, password: String): Result<LoginResponseEntity, Exception> {
    //Fake Login Api
    delay(2000)
    return Result.of(LoginResponseEntity("fakeToken",
            UserProfileEntity(id = 1L,
                    username = "captain",
                    firstName = "Steve",
                    lastName = "Rogers",
                    displayName = "Captain America",
                    image = "",
                    email = "captain@avengers.com",
                    role = "Leader"
            )))
}