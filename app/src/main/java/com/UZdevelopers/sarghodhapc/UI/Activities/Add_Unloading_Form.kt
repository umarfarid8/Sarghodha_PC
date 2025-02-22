package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Material
import com.UZdevelopers.sarghodhapc.ViewModels.AddQualityDataViewModel
import com.UZdevelopers.sarghodhapc.ViewModels.AddUnloadingDataViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddQualityFormBinding
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddUnloadingFormBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch

class Add_Unloading_Form : AppCompatActivity() {
    lateinit var binding: ActivityAddUnloadingFormBinding
    lateinit var viewModel: AddUnloadingDataViewModel

    lateinit var  progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddUnloadingFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel= AddUnloadingDataViewModel()

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Updating Information")
        progressDialog.setCancelable(false)

        val LoadData = Gson().fromJson(intent.getStringExtra("data"), Load::class.java)
        binding.etEntryPermit.isEnabled = false

        binding.etEntryPermit.setText(LoadData.entryPermit)
        binding.tvDeductionValue.text = LoadData.deductionP.toString()
        binding.tvSandValue.text = LoadData.sandP.toString()
        binding.tvMoistureValue.text = LoadData.mositureP.toString()
        binding.tvGrossWeightValue.text = LoadData.grossWeight.toString()

        binding.btnSubmit.setOnClickListener{

            if(binding.etStackNumber.text.toString().isEmpty()){
                binding.etStackNumber.error = "Stack Number is required"
                return@setOnClickListener
            }
            progressDialog.show()
            LoadData.processStatus = (LoadData.processStatus!!.toInt() + 1).toString()
            LoadData.stackNumber = binding.etStackNumber.text.toString()
            viewModel.updateLoad(LoadData)
        }

        lifecycleScope.launch {
            viewModel.failureMessage.collect {
                if (it != null) {
                    progressDialog.dismiss()
                    Toast.makeText(this@Add_Unloading_Form, it, Toast.LENGTH_SHORT).show()
                }

            }
        }

        lifecycleScope.launch {
            viewModel.isUpdated.collect {
                if (it) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        this@Add_Unloading_Form,
                        "Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@Add_Unloading_Form, MainActivity::class.java))
                    finish()
                }
            }
        }
   }
}