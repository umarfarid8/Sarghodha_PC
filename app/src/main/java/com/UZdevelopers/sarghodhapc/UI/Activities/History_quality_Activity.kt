package com.UZdevelopers.sarghodhapc.UI.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.Adapters.HistoryQualityAdapter
import com.UZdevelopers.sarghodhapc.UI.Adapters.HistoryWeighmentAdapter
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ViewHolders.HistoryQualityViewHolder
import com.UZdevelopers.sarghodhapc.ViewModels.HistoryWeightmentViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityHistoryQualityBinding
import com.UZdevelopers.sarghodhapc.databinding.ActivityWeighmentHistoryBinding
import kotlinx.coroutines.launch

class History_quality_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryQualityBinding
    private lateinit var adapter: HistoryQualityAdapter
    private var loadList = ArrayList<Load>()
    lateinit var  viewModel: HistoryWeightmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryQualityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = HistoryWeightmentViewModel()
        viewModel.readData()
        adapter= HistoryQualityAdapter(loadList)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)


        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                it?.let {
                    Toast.makeText(this@History_quality_Activity, it, Toast.LENGTH_SHORT).show()
                }
            }

        }

        lifecycleScope.launch {
            viewModel.data.collect { dataList ->
                dataList?.let {
                    loadList.clear()
                    it.filterTo(loadList) { item ->
                        item.processStatus!!.toInt() > 2
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}