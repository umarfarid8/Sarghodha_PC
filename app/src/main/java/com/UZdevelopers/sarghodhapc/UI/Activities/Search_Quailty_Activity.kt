package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.ViewModels.QualitySearchModelClass
import com.UZdevelopers.sarghodhapc.databinding.ActivitySearchQuailtyBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class Search_Quailty_Activity : AppCompatActivity() {
    lateinit var binding: ActivitySearchQuailtyBinding
    lateinit var viewModel: QualitySearchModelClass
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySearchQuailtyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        viewModel = QualitySearchModelClass()

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Matching EP Number...")
        progressDialog.setCancelable(false)

        setContentView(binding.root)
        binding.buttonSave2.setOnClickListener {
            val searchText = binding.etSearch.text.toString()

            if (searchText.isNotEmpty()) {
                progressDialog.show()
                viewModel.searchRecord(searchText)
            } else {
                Toast.makeText(this, "Please enter a search text", Toast.LENGTH_SHORT).show()
                binding.etSearch.error = "Please enter a search EP Number"
            }


            lifecycleScope.launch {
                viewModel.failureMessage.collect {
                    if (it != null) {
                        Toast.makeText(this@Search_Quailty_Activity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            lifecycleScope.launch {
                viewModel.loadData.collect {
                    if (it != null) {
                        progressDialog.dismiss()
                        val intent =
                            Intent(this@Search_Quailty_Activity, Add_Quality_form::class.java)
                        intent.putExtra("data", Gson().toJson(it))
                        startActivity(intent)
                    }
                }
            }

            lifecycleScope.launch {
                viewModel.dataEmpty.collect {
                    if (it) {
                        progressDialog.dismiss()
                        Toast.makeText(
                            this@Search_Quailty_Activity,
                            "No Data Found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}