package com.team.schuber.schuber

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.activity_intro5.*

class Intro5Activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro5)

        layout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
        change_tv.setCharacterLists(
            TickerUtils.provideAlphabeticalList()
        )
        Handler().postDelayed(
            {
                change_tv.text = "간단한"
                Handler().postDelayed(
                    {
                        change_tv.text = "편리한"
                    }, 2000
                )
            }, 2000
        )
    }
}