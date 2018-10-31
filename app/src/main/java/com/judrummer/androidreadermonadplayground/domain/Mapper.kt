package com.judrummer.androidreadermonadplayground.domain

import com.judrummer.androidreadermonadplayground.data.UserProfileEntity

interface UserProfileMapper {
    fun map(userProfileEntity: UserProfileEntity): UserProfile
}

class UserProfileMapperImpl : UserProfileMapper {

    override fun map(userProfileEntity: UserProfileEntity): UserProfile = userProfileEntity.run {
        UserProfile(id, username, firstName, lastName, displayName, image)
    }

}