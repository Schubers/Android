package com.team.schuber.schuber.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team.schuber.schuber.R
import com.team.schuber.schuber.model.Payment


class MonthReportPaymentTableAdapter(val items: ArrayList<Payment>): RecyclerView.Adapter<MonthReportPaymentTableAdapter.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        return PaymentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service_value, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) = holder.bind(items[position])

    inner class PaymentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Payment){
            itemView.findViewById<TextView>(R.id.ratio_value_tv).text = "${(item.percent * 100).toInt()}%"
            itemView.findViewById<TextView>(R.id.service_name_value_tv).text = item.serviceName
            Glide.with(
                itemView.findViewById<ImageView>(R.id.service_name_value_imv)
            ).load(item.iconUrl).into(itemView.findViewById(R.id.service_name_value_imv))
            itemView.findViewById<TextView>(R.id.value_value_tv).text = "${item.amount}원"
        }
    }
}