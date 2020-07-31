package com.team.schuber.schuber

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.schuber.schuber.adapter.SubListAdapter
import com.team.schuber.schuber.model.SubListModel
import kotlinx.android.synthetic.main.activity_sub_list.*

class SubListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_list)

        title_tv.text = intent.getStringExtra("title")

        back_imv.setOnClickListener {
            finish()
        }

        when(title_tv.text) {
            "소비 리포트" -> {
                list_rcv.adapter = SubListAdapter(
                    arrayListOf(
                        SubListModel("월간 소비 동향 리포트", R.drawable.sub_report),
                        SubListModel("이용자 평균과 비교", R.drawable.sub_card)
                    )
                )
            }
            "구독 서비스 추천" -> {
                list_rcv.adapter = SubListAdapter(
                    arrayListOf(
                        SubListModel("실시간 인기 급상승 서비스", R.drawable.sub_graph2),
                        SubListModel("서비스 랭킹", R.drawable.sub_graph),
                        SubListModel("맞춤형 서비스 추천", R.drawable.sub_good)
                    )
                )
            }
            "구독 서비스 관리" -> {
                list_rcv.adapter = SubListAdapter(
                    arrayListOf(
                        SubListModel("구독중인 서비스", R.drawable.sub_pocket),
                        SubListModel("구독 서비스 검색", R.drawable.sub_search)
                    )
                )
            }
        }

        list_rcv.layoutManager = LinearLayoutManager(this)

    }
}