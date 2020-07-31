package com.team.schuber.schuber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.schuber.schuber.adapter.SubscribeListForYouAdapter
import com.team.schuber.schuber.model.SubscribeListResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_subscribe_list.*
import kotlinx.android.synthetic.main.activity_subscribe_list_for_you.*
import kotlinx.android.synthetic.main.activity_subscribe_list_for_you.progress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscribeListForYouActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscribe_list_for_you)

        progress.visibility = View.VISIBLE

        Network.getNeedApi(this).listForYou().enqueue(
            object : Callback<SubscribeListResponseModel> {
                override fun onFailure(call: Call<SubscribeListResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SubscribeListResponseModel>,
                    response: Response<SubscribeListResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            progress.visibility = View.GONE
                            list_for_you_rcv.adapter = SubscribeListForYouAdapter(response.body()!!.results, supportFragmentManager)
                            list_for_you_rcv.layoutManager = LinearLayoutManager(this@SubscribeListForYouActivity)
                        }
                    }
                }
            }
        )
    }
}