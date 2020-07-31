package com.team.schuber.schuber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notice_image.setOnClickListener {
            startActivity(Intent(this, NoticeActivity::class.java))
        }

        report_card.setOnClickListener {
            startActivity(Intent(this, SubListActivity::class.java).apply {
                putExtra("title", "소비 리포트")
            })
        }

        subscribe_card.setOnClickListener {
            startActivity(Intent(this, SubListActivity::class.java).apply {
                putExtra("title", "구독 서비스 추천")
            })
        }

        subscribe_setting_card.setOnClickListener {
            startActivity(Intent(this, SubListActivity::class.java).apply {
                putExtra("title", "구독 서비스 관리")
            })
        }
    }
}
