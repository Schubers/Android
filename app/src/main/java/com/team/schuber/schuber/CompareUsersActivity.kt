package com.team.schuber.schuber

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hadiidbouk.charts.BarData
import com.hadiidbouk.charts.OnBarClickedListener
import com.team.schuber.schuber.model.CompareUsersResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_compare_users.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompareUsersActivity: AppCompatActivity(), OnBarClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_users)

        progress.visibility = View.VISIBLE
        layout.visibility = View.INVISIBLE

        Network.getNeedApi(this).compareUsers().enqueue(
            object : Callback<CompareUsersResponseModel> {
                override fun onFailure(call: Call<CompareUsersResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<CompareUsersResponseModel>,
                    response: Response<CompareUsersResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            with(response.body()!!) {
                                consumption_pattern_tv.text =
                                    "분석 결과\n소비 패턴은\n전체 상위 ${(paymentPattern * 100).toInt()}%입니다."
                                average_consumption_graph.setMaxValue(paymentPerCategories.maxBy { it.amount }!!.amount)
                                average_consumption_graph.setDataList(
                                    ArrayList(
                                        paymentPerCategories.map {
                                            Log.e("asdasd", it.toString())
                                            BarData(
                                                it.category,
                                                it.amount,
                                                "${it.amount.toInt()}"
                                            )
                                        }
                                    )
                                )
                                average_consumption_graph.build()
                                average_consumption_graph.setOnBarClickedListener(this@CompareUsersActivity)

                                with(total) {
                                    save_money_tv.text = "${user}명의 구독자가\n총 ${savedAmount}원 절약"
                                    whole_subscribe_content_tv.text = "${purChasedAmount}원"
                                    save_money_content_tv.text = "${savedAmount}원"
                                }

                                progress.visibility = View.GONE
                                layout.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        )
    }

    override fun onBarClicked(p0: Int) {

    }

}
