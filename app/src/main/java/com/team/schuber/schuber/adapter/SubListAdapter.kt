package com.team.schuber.schuber.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.schuber.schuber.*
import com.team.schuber.schuber.model.SubListModel

class SubListAdapter(val items: ArrayList<SubListModel>): RecyclerView.Adapter<SubListAdapter.SubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubListViewHolder {
        return SubListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_list, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubListViewHolder, position: Int) = holder.bind(items[position])

    inner class SubListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: SubListModel){
            itemView.findViewById<TextView>(R.id.item_sub_list_tv).text = item.name
            itemView.findViewById<ImageView>(R.id.item_sub_list_imv).setImageResource(item.src)

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,
                    when(item.name) {
                        "월간 소비 동향 리포트" -> MonthConsumptionReportActivity::class.java
                        "이용자 평균과 비교" -> CompareUsersActivity::class.java
                        "실시간 인기 급상승 서비스" -> RealTimeSubscribeListActivity::class.java
                        "서비스 랭킹" -> RankSubscribeListActivity::class.java
                        "맞춤형 서비스 추천" -> SubscribeListForYouActivity::class.java
                        "구독중인 서비스" -> SubscribedListActivity::class.java
                        "구독 서비스 검색" -> SearchActivity::class.java
                        else -> MainActivity::class.java
                    }
                ))
            }
        }
    }
}