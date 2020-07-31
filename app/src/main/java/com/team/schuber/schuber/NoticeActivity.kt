package com.team.schuber.schuber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        back_imv.setOnClickListener { finish() }

        apply_radio.setOnClickListener {
            cancel_radio.isChecked = false
            check()
        }

        cancel_radio.setOnClickListener {
            apply_radio.isChecked = false
            check()
        }
    }

    fun check() {
        if (apply_radio.isChecked) {
            apply_radio.isClickable = false
            cancel_radio.isClickable = true
        }
        else if (cancel_radio.isChecked) {
            cancel_radio.isClickable = false
            apply_radio.isClickable = true
        }
    }
}