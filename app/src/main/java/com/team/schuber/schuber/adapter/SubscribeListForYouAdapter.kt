package com.team.schuber.schuber.adapter

import android.graphics.Color
import android.util.Log
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

class SubscribeListForYouAdapter(val items: ArrayList<Service>, val fm: FragmentManager): RecyclerView.Adapter<SubscribeListForYouAdapter.SubscribeListForYouViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribeListForYouViewHolder {
        return SubscribeListForYouViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubscribeListForYouViewHolder, position: Int) = holder.bind(items[position])

    inner class SubscribeListForYouViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Service){
            with(item) {
                itemView.findViewById<View>(R.id.service_background_v).setBackgroundColor(
                    Color.parseColor(themeColor)
                )
                itemView.findViewById<TextView>(R.id.service_name_tv).text = name
                itemView.findViewById<TextView>(R.id.service_kinds_tv).text = category
                itemView.findViewById<TextView>(R.id.service_value_tv).text =
                    amount.toFloat().toInt().toString() + when(currencyType) {
                        "KRW" -> "원/월"
                        "$" -> "달러/월"
                        else -> "원/월"
                    }
                Glide.with(
                    itemView.findViewById<ImageView>(R.id.service_imv)
                ).load(iconUrl).into(itemView.findViewById(R.id.service_imv))

                itemView.setOnClickListener {
                    ServiceDetailDialogFragment(id).show(fm, "dialog")
                }
            }
        }
    }
}