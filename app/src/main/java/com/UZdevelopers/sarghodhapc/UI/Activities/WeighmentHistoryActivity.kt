package com.UZdevelopers.sarghodhapc.UI.Activities

import com.UZdevelopers.sarghodhapc.UI.Adapters.HistoryWeighmentAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.ViewModels.HistoryWeightmentViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityWeighmentHistoryBinding
import kotlinx.coroutines.launch

class WeighmentHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeighmentHistoryBinding
    private lateinit var adapter: HistoryWeighmentAdapter
    private var loadList = ArrayList<Load>()
    lateinit var  viewModel: HistoryWeightmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeighmentHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = HistoryWeightmentViewModel()
        viewModel.readData()
        adapter= HistoryWeighmentAdapter(loadList)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)


        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                it?.let {
                    Toast.makeText(this@WeighmentHistoryActivity, it, Toast.LENGTH_SHORT).show()
                }
            }

        }

        lifecycleScope.launch {
            viewModel.data.collect {
                it?.let {
                    loadList.clear()
                    loadList.addAll(it)
                    adapter.notifyDataSetChanged()
                }

            }
        }
    }
}
