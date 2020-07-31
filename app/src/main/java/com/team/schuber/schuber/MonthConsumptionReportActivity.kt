package com.team.schuber.schuber

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadiidbouk.charts.BarData
import com.hadiidbouk.charts.OnBarClickedListener
import com.team.schuber.schuber.adapter.MonthReportPaymentTableAdapter
import com.team.schuber.schuber.model.MonthReportModel
import com.team.schuber.schuber.network.Network
import kotlinx.android.synthetic.main.activity_month_consumption_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class MonthConsumptionReportActivity: AppCompatActivity(), OnBarClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_consumption_report)

        progress.visibility = View.VISIBLE
        layout.visibility = View.INVISIBLE


        Network.getNeedApi(this).monthReport().enqueue(
            object : Callback<MonthReportModel>{
                override fun onFailure(call: Call<MonthReportModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<MonthReportModel>,
                    response: Response<MonthReportModel>
                ) {
                    when(response.code()) {
                        200 -> {
                            with(response.body()!!) {
                                consumption_whole_value_tv.text =
                                    "이번 달 정기 지출은\n${thisMonthPayment}원 입니다."
                                consumption_whole_value_chart_tv.text =
                                    "${topCategory.category}\n${topCategory.amount.toFloat().toInt()}원"
                                consumption_whole_value_chart.percent =
                                    topCategory.amount.toFloat() / thisMonthPayment.replace(",", "").dropLast(1).toFloat() * 100
                                consumption_value_tv.text = "결제금액이\n지난 달 대비\n${paymentStatus}했어요!"
                                consumption_value_graph.setMaxValue(paymentGraph.maxBy { it.amount }!!.amount)
                                consumption_value_graph.setDataList(
                                    ArrayList(
                                        paymentGraph.map {
                                            BarData(
                                                "${
                                                    SimpleDateFormat("M").format(
                                                        SimpleDateFormat("yyyy-MM").parse(it.month)
                                                    )
                                                }월",
                                                it.amount,
                                                "${it.amount.toInt()}"
                                            )
                                        }.reversed()
                                    )
                                )
                                consumption_value_graph.build()
                                consumption_value_graph.setOnBarClickedListener(this@MonthConsumptionReportActivity)
                                list_tv.text = paymentTable.characterKeyword
                                list_rcv.adapter =
                                    MonthReportPaymentTableAdapter(paymentTable.topPayments)
                                list_rcv.layoutManager = LinearLayoutManager(this@MonthConsumptionReportActivity)

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
