package com.fyt.myapplication

import android.os.Build
import java.util.*

class Constant {

    companion object{

        //登陆授权
        const val CLIENT_ID = "88678c33614166b75d8e"
        const val CLIENT_SECRET = "974d81b62ba4ec274bc65698e4a9b86b7ff27554"
        const val NOTE = "fytGithubApp"
        const val NOTE_URL = "https://github.com/Tkorn"
        val SCOPE = listOf("user", "repo", "notifications", "gist")

        val FINGERPRINT: String by lazy { Build.FINGERPRINT + UUID.randomUUID().toString() }

    }
}