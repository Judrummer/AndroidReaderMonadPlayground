package com.judrummer.androidreadermonadplayground.data


data class LoginResponseEntity(
        val token: String,
        val mockUser: UserProfileEntity
)

data class UserProfileEntity(
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String,
        val displayName: String,
        val image: String,
        val email: String,
        val role: String
)