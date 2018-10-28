package com.judrummer.androidreadermonadplayground.domain

data class UserProfile(
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String,
        val displayName: String,
        val image: String
)