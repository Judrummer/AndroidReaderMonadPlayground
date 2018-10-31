package com.judrummer.androidreadermonadplayground.domain

import com.judrummer.androidreadermonadplayground.data.AppPreference
import com.judrummer.androidreadermonadplayground.data.LoginResponseEntity
import com.judrummer.androidreadermonadplayground.reader.Reader
import  com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
import com.judrummer.androidreadermonadplayground.data.loginApi
import com.judrummer.androidreadermonadplayground.reader.map

fun saveLoginEntity(entity: Result<LoginResponseEntity, Exception>) = Reader<AppPreference, Result<LoginResponseEntity, Exception>> { appPreference ->
    if (entity is Result.Success) {
        appPreference.userProfileEntity = entity.value.mockUser
        appPreference.userToken = entity.value.token
    }
    entity
}

fun mapUserProfile(entity: Result<LoginResponseEntity, Exception>) = Reader<UserProfileMapper, Result<UserProfile, Exception>> { mapper ->
    entity.map { mapper.map(it.mockUser) }
}

interface LoginUseCaseDependencies {
    val userProfileMapper: UserProfileMapper
    val appPreference: AppPreference
}

suspend fun loginUseCase(username: String, password: String) =
        loginApi(username, password)
                .let { saveLoginEntity(it) }
                .map { mapUserProfile(it) }