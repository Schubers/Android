package com.team.schuber.schuber

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.team.schuber.schuber.model.Service
import com.team.schuber.schuber.model.SubscribeRequestModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.fragment_service_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceDetailDialogFragment(val serviceId: Int, var isSubscribed: Boolean = false): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout.visibility = View.INVISIBLE
        progress.visibility = View.VISIBLE

        if (isSubscribed)
            subscribe_button.visibility = View.GONE


        Network.getNeedApi(this.requireContext()).serviceDetail(serviceId).enqueue(
            object : Callback<Service> {
                override fun onFailure(call: Call<Service>, t: Throwable) {

                }

                override fun onResponse(call: Call<Service>, response: Response<Service>) {
                    when(response.code()) {
                        200 -> {
                            progress.visibility = View.GONE
                            layout.visibility = View.VISIBLE
                            with(response.body()!!) {
                                Glide.with(service_detail_imv).load(iconUrl)
                                    .into(service_detail_imv)
                                service_detail_background_v.setBackgroundColor(
                                    Color.parseColor(themeColor)
                                )
                                service_detail_kinds_tv.text = category
                                service_detail_name_tv.text = name
                                service_detail_value_tv.text =
                                    amount + when(currencyType) {
                                        "KRW" -> "원/월"
                                        "$" -> "달러/월"
                                        else -> "원/월"
                                    }
                                service_introduce_content_tv.text = description
                                service_subscriber_content_tv.text = "${subscribers}명"
                                service_company_content_tv.text = company
                            }
                        }
                    }
                }
            }
        )

        subscribe_button.setOnClickListener {
            subscribe_button.onLoad("로딩 중")

            Network.getNeedApi(this.requireContext()).subscribe(SubscribeRequestModel(serviceId)).enqueue(
                object : Callback<Unit> {
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        subscribe_button.onError("구독 완료")
                    }

                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        when(response.code()) {
                            200 -> {
                                subscribe_button.onSuccess("구독 완료")
                                Handler().postDelayed(
                                    {
                                        dismiss()
                                    },
                                    2000
                                )
                            }
                            else -> subscribe_button.onError("에러")
                        }
                    }
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}