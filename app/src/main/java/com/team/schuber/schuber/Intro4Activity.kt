package com.team.schuber.schuber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Intro4Activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro4)
        Handler().postDelayed(
            {
                startActivity(Intent(this, Intro5Activity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            },2000
        )
    }
}