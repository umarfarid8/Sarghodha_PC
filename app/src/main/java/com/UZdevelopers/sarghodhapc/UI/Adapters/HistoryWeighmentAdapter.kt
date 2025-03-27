package com.UZdevelopers.sarghodhapc.UI.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ViewHolders.HistoryWeighmentViewHolder
import com.UZdevelopers.sarghodhapc.databinding.ItemWeighmentHistoryBinding
import java.text.SimpleDateFormat
import java.util.Locale

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


        holder.binding.textView16.text = item.date
        holder.binding.textView24.text = item.entryPermit


        holder.binding.textView34.text = item.vendorName
        holder.binding.textView36.text = item.vendorNumber

        holder.binding.textView38.text = item.vechileNumber
        holder.binding.textView40.text = item.vechileType

        holder.binding.textView13.text = item.materialName
        holder.binding.textView30.text = item.grossWeight + " Kg's"

        holder.binding.textView17.text =  if (item.tareWeight == "-1") {
            "N/A"
        } else {
            item.tareWeight.toString() + " Kg's"
        }
        holder.binding.textView42.text =  if (item.netWeight == "-1") {
            "N/A"
        } else {
            item.netWeight.toString() + " Kg's"
        }
        holder.binding.textView52.text =  if (item.deductionP == "-1") {
            "N/A"
        } else {
            item.deductionP.toString() + " %"
        }

        holder.binding.textView44.text =  if (item.payableWeight == "-1") {
            "N/A"
        } else {
            item.payableWeight.toString() + " Kg's"
        }

        holder.binding.textView20.text =  if (item.stackNumber == "-1") {
            "N/A"
        } else {
            item.stackNumber.toString()
        }

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        val startTime = formatter.parse(item.date!!)!!.time
        val endTime = formatter.parse(item.tareWeightTime!!)!!.time

        val difference = endTime - startTime
        val minutes = (difference / (1000 * 60)) % 60
        val hours = (difference / (1000 * 60 * 60)) % 24

//        println("$hours:$minutes ")
        holder.binding.textView56.text = "$hours:$minutes"

        holder.binding.textView22.text = if (item.processStatus == "1") {
            "Waiting For Quality..."
        } else if( item.processStatus == "2"){
            "Waiting For Unloading..."
        } else if (item.processStatus == "3"){
            "Waiting For Tare Wait"
        }
        else {
            "Completed"
        }

        holder.binding.textView25.text =  if (item.tareWeightTime == "-1") {
            "N/A"
        } else {
            item.tareWeightTime.toString()
        }
    }
}
