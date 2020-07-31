package com.team.schuber.schuber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.schuber.schuber.adapter.SubscribedListAdapter
import com.team.schuber.schuber.model.SubscribedListResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_subscribed_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscribedListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribed_list)

        progress.visibility = View.VISIBLE

        Network.getNeedApi(this).getSubscribedList().enqueue(
            object : Callback<SubscribedListResponseModel> {
                override fun onFailure(call: Call<SubscribedListResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SubscribedListResponseModel>,
                    response: Response<SubscribedListResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            with(response.body()!!) {
                                progress.visibility = View.GONE
                                title_tv.text = "이번 달 결제예정 금액은\n${futurePaymentTotal}원 입니다."
                                subscribed_list_rcv.adapter = SubscribedListAdapter(results, supportFragmentManager)
                                subscribed_list_rcv.layoutManager = LinearLayoutManager(this@SubscribedListActivity)
                            }
                        }
                    }
                }
            }
        )

    }
}