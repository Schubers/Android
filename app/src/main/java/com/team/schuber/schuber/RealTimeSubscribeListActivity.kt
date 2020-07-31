package com.team.schuber.schuber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.schuber.schuber.adapter.SubscribeListAdapter
import com.team.schuber.schuber.model.SubscribeListResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_subscribe_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class RealTimeSubscribeListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe_list)

        time_tv.text = SimpleDateFormat("hh시 mm분 ss초 기준").format(Date())
        getList()


        refresh_imv.setOnClickListener {
            time_tv.text = SimpleDateFormat("hh시 mm분 ss초 기준").format(Date())
            getList()
        }

    }

    fun getList() {
        progress.visibility = View.VISIBLE
        Network.getNeedApi(this).rankRealTime().enqueue(
            object: Callback<SubscribeListResponseModel> {

                override fun onFailure(call: Call<SubscribeListResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SubscribeListResponseModel>,
                    response: Response<SubscribeListResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            progress.visibility = View.GONE
                            subscribe_list_rcv.adapter = SubscribeListAdapter(response.body()!!.results, supportFragmentManager)
                            subscribe_list_rcv.layoutManager = LinearLayoutManager(this@RealTimeSubscribeListActivity)
                        }
                    }
                }

            }
        )
    }
}