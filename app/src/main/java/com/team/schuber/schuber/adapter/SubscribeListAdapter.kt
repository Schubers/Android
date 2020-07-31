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

class SubscribeListAdapter(val items: ArrayList<Service>, val fm: FragmentManager): RecyclerView.Adapter<SubscribeListAdapter.SubscribeListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribeListViewHolder {
        return SubscribeListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subscribe_service_rank, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubscribeListViewHolder, position: Int) = holder.bind(items[position])

    inner class SubscribeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Service){
            with(item) {
                itemView.findViewById<View>(R.id.subscribe_rank_background_v).setBackgroundColor(
                    Color.parseColor(themeColor)
                )
                itemView.findViewById<TextView>(R.id.subscribe_rank_name_tv).text = name
                itemView.findViewById<TextView>(R.id.subscribe_rank_kinds_tv).text = category
                itemView.findViewById<TextView>(R.id.subscribe_rank_value_tv).text =
                    amount.toFloat().toInt().toString() + when(currencyType) {
                        "KRW" -> "원/월"
                        "$" -> "달러/월"
                        else -> "원/월"
                    }
                Glide.with(
                    itemView.findViewById<ImageView>(R.id.subscribe_rank_imv)
                ).load(iconUrl).into(itemView.findViewById(R.id.subscribe_rank_imv))
                itemView.findViewById<TextView>(R.id.subscribe_rank_payment_subscribers_content_tv).text = "${subscribers}명"
                itemView.findViewById<TextView>(R.id.subscribe_rank_tv).text = "#${items.indexOf(item) + 1}"

                itemView.setOnClickListener {
                    ServiceDetailDialogFragment(id).show(fm, "dialog")
                }
            }
        }
    }
}