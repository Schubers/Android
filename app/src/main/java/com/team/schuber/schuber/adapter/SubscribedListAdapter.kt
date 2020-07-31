package com.team.schuber.schuber.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.team.schuber.schuber.R
import com.team.schuber.schuber.ServiceDetailDialogFragment
import com.team.schuber.schuber.model.Service
import com.team.schuber.schuber.model.SubscribedService

class SubscribedListAdapter(val items: ArrayList<SubscribedService>, val fm: FragmentManager): RecyclerView.Adapter<SubscribedListAdapter.SubscribedListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribedListViewHolder {
        return SubscribedListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subscribe_service, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubscribedListViewHolder, position: Int) = holder.bind(items[position])

    inner class SubscribedListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: SubscribedService){
            with(item) {
                itemView.findViewById<View>(R.id.subscribe_background_v).setBackgroundColor(
                    Color.parseColor(themeColor)
                )
                itemView.findViewById<TextView>(R.id.subscribe_name_tv).text = name
                itemView.findViewById<TextView>(R.id.subscribe_kinds_tv).text = category
                itemView.findViewById<TextView>(R.id.subscribe_value_tv).text =
                    amount.toFloat().toInt().toString() + when(currencyType) {
                        "KRW" -> "원/월"
                        "$" -> "달러/월"
                        else -> "/월"
                    }
                Glide.with(
                    itemView.findViewById<ImageView>(R.id.subscribe_imv)
                ).load(iconUrl).into(itemView.findViewById(R.id.subscribe_imv))
                itemView.findViewById<TextView>(R.id.subscribe_payment_date_content_tv).text = "${nextPurchaseAt.split("-")[2]}일"
                itemView.findViewById<TextView>(R.id.subscribe_whole_payment_date_content_tv).text = "${durationMonth}개월"

                itemView.setOnClickListener {
                    ServiceDetailDialogFragment(serviceId, true).show(fm, "dialog")
                }
            }
        }
    }
}