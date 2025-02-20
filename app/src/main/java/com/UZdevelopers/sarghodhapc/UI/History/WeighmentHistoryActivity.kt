package com.UZdevelopers.sarghodhapc.UI.Activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.UZdevelopers.sarghodhapc.UI.Adapters.LoadHistoryAdapter
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.databinding.ActivityWeighmentHistoryBinding
import com.google.firebase.database.*

class WeighmentHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeighmentHistoryBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var historyAdapter: LoadHistoryAdapter
    private var loadList = mutableListOf<Load>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeighmentHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("WeighmentEntries")

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = LoadHistoryAdapter(loadList)
        binding.recyclerView.adapter = historyAdapter

        // Load data from Firebase
        loadHistory()
    }

    private fun loadHistory() {
        binding.progressBar.visibility = View.VISIBLE
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                loadList.clear()
                for (data in snapshot.children) {
                    val load = data.getValue(Load::class.java)
                    if (load != null) {
                        loadList.add(load)
                    }
                }
                historyAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@WeighmentHistoryActivity, "Failed to load history", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}
