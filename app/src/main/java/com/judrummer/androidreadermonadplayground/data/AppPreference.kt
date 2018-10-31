package com.judrummer.androidreadermonadplayground.data

import com.judrummer.androidreadermonadplayground.delegate.PaperDelegate

interface AppPreference {
    var userProfileEntity: UserProfileEntity?
    var userToken: String?
}

class AppPreferenceImpl : AppPreference {
    override var userProfileEntity: UserProfileEntity? by PaperDelegate()
    override var userToken: String? by PaperDelegate()
}