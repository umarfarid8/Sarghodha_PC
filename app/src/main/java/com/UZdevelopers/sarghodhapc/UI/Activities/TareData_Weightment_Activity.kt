package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.ViewModels.AddWeighmentViewModel
import com.UZdevelopers.sarghodhapc.ViewModels.TareWeightViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddWeighmentBinding
import com.UZdevelopers.sarghodhapc.databinding.ActivityTareDataWeightmentBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class TareData_Weightment_Activity : AppCompatActivity() {
    lateinit var  binding: ActivityTareDataWeightmentBinding
    lateinit var  viewModel: TareWeightViewModel
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Updating Information")
        progressDialog.setCancelable(false)
        binding= ActivityTareDataWeightmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = TareWeightViewModel()
        val LoadData = Gson().fromJson(intent.getStringExtra("data"), Load::class.java)

        binding.tvStackNumberValue.text = LoadData.stackNumber
        binding.tvDeductionValue.text = LoadData.deductionP

        binding.button.setOnClickListener(){
            if(binding.etTareWeight.text.isEmpty()){
                binding.etTareWeight.error = "Tare Weight is required"
                return@setOnClickListener
            }

            progressDialog.show()
            LoadData.processStatus = (LoadData.processStatus!!.toInt() + 1).toString()
            viewModel.updateLoad(LoadData)
        }

        lifecycleScope.launch {
            viewModel.failureMessage.collect {
                if (it != null) {
                    progressDialog.dismiss()
                    Toast.makeText(this@TareData_Weightment_Activity, it, Toast.LENGTH_SHORT).show()
                }

            }
        }

        lifecycleScope.launch {
            viewModel.isUpdated.collect {
                if (it) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        this@TareData_Weightment_Activity,
                        "Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@TareData_Weightment_Activity, MainActivity::class.java))
                    finish()
                }
            }
        }

        binding.etTareWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                     var netWeight = LoadData.grossWeight!!.toDouble() - s.toString().toDouble()
                     binding.tvNetWeightValue.text = netWeight.toString()
                       //deduction kgs =(net wt * ded. %) /100
                    var decution = (netWeight * LoadData.deductionP!!.toDouble()) / 100

                    // pay wt=net wt-ded kgs
                    var payableWeight = netWeight - decution
                    binding.tvPayableWeightValue.text = payableWeight.toString()
                    LoadData.netWeight = netWeight.toString()
                    LoadData.payableWeight = payableWeight.toString()
                    LoadData.tareWeight = s.toString()

                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


}