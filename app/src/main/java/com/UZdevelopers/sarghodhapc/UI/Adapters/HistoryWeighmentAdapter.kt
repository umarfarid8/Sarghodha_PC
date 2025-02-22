package com.UZdevelopers.sarghodhapc.UI.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ViewHolders.HistoryWeighmentViewHolder
import com.UZdevelopers.sarghodhapc.databinding.ItemWeighmentHistoryBinding

class HistoryWeighmentAdapter(val items: ArrayList<Load>) : RecyclerView.Adapter<HistoryWeighmentViewHolder>() {

    // Use sizes as a mutable list to hold the data for the adapter
    private var sizes: List<Load> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryWeighmentViewHolder {

        return HistoryWeighmentViewHolder(
            ItemWeighmentHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return sizes.size // Return the size of the sizes list
    }

    override fun onBindViewHolder(holder: HistoryWeighmentViewHolder, position: Int) {
        val item = sizes[position]


        holder.binding.textView30.text = item.grossWeight + " KGS"
        holder.binding.textView17.text = item.stackNumber
        holder.binding.textView20.text =  if (item.stackNumber == "-1") {
            "N/A"
        } else {
            item.stackNumber.toString()
        }
        holder.binding.textView13.text = item.materialName
        holder.binding.textView24.text = item.entryPermit
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



        holder.itemView.setOnClickListener {

//            val intent = Intent(holder.itemView.context, DetailsOfOrder::class.java)
//            intent.putExtra("data", Gson().toJson(item))
//            holder.itemView.context.startActivity(intent)
        }
    }
}
