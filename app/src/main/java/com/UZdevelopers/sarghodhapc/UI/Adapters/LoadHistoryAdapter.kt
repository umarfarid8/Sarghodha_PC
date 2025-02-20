package com.UZdevelopers.sarghodhapc.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.databinding.ItemLoadHistoryBinding

class LoadHistoryAdapter(private val loadList: List<Load>) :
    RecyclerView.Adapter<LoadHistoryAdapter.LoadViewHolder>() {

    inner class LoadViewHolder(private val binding: ItemLoadHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(load: Load) {
            binding.tvMaterialName.text = load.materialName
            binding.tvVendorName.text = "Vendor: ${load.vendorName}"
            binding.tvDate.text = "Date: ${load.date}"
            binding.tvGrossWeight.text = "Gross Weight: ${load.grossWeight}"
            binding.tvStatus.text = "Status: ${load.status}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadViewHolder {
        val binding = ItemLoadHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LoadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadViewHolder, position: Int) {
        holder.bind(loadList[position])
    }

    override fun getItemCount(): Int = loadList.size
}
