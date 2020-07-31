package com.team.schuber.schuber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.schuber.schuber.adapter.SubscribeListForYouAdapter
import com.team.schuber.schuber.model.SearchServiceResponseModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        progress.visibility = View.GONE
    }

    fun search(view: View) {
        progress.visibility = View.VISIBLE
        Network.getNeedApi(this).searchService(search_edit.text.toString()).enqueue(
            object : Callback<SearchServiceResponseModel> {
                override fun onFailure(call: Call<SearchServiceResponseModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<SearchServiceResponseModel>,
                    response: Response<SearchServiceResponseModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            with(response.body()!!) {
                                list_rcv.adapter = SubscribeListForYouAdapter(results, supportFragmentManager)
                                list_rcv.layoutManager = LinearLayoutManager(this@SearchActivity)
                                progress.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        )
    }
}