package com.UZdevelopers.sarghodhapc.UI.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ViewHolders.HistoryQualityViewHolder
import com.UZdevelopers.sarghodhapc.UI.ViewHolders.HistoryUnlodingViewHolder
import com.UZdevelopers.sarghodhapc.databinding.ItemQualityHistoryBinding
import com.UZdevelopers.sarghodhapc.databinding.ItemUnloadingHistoryBinding

class HistoryUnloadingAdapter(val items: ArrayList<Load>) : RecyclerView.Adapter<HistoryUnlodingViewHolder>() {

    // Use sizes as a mutable list to hold the data for the adapter
    private var sizes: List<Load> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryUnlodingViewHolder {

        return HistoryUnlodingViewHolder(
            ItemUnloadingHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return sizes.size // Return the size of the sizes list
    }

    override fun onBindViewHolder(holder: HistoryUnlodingViewHolder, position: Int) {
        val item = sizes[position]

        holder.binding.namee2.text = item.status
        holder.binding.textView26.text = item.entryPermit
        holder.binding.textView30.text = item.grossWeight + " KGS"

        holder.binding.textView13.text = item.materialName
        holder.binding.textView16.text = item.date
        holder.binding.textView7.text = if (item.processStatus == "1") {
            "Waiting For Quality..."
        } else if( item.processStatus == "2"){
         "Waiting For Unloading..."
        } else if (item.processStatus == "3"){
            "Waiting For Tare Wait"
        }
        else {
            "Completed"
        }
        holder.binding.textView20.text = item.mositureP + " %"
        holder.binding.textView14.text = item.sandP + " %"
        holder.binding.textView22.text = item.mudPieceP + " %"


        holder.itemView.setOnClickListener {

//            val intent = Intent(holder.itemView.context, DetailsOfOrder::class.java)
//            intent.putExtra("data", Gson().toJson(item))
//            holder.itemView.context.startActivity(intent)
        }
    }
}
