package com.team.schuber.schuber

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (SharedPrefStorage(this).getToken(true)  == "Bearer ")
                startActivity(Intent(this, Intro1Activity::class.java))
            else
                startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}