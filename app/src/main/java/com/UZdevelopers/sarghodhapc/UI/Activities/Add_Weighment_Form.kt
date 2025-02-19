package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.ViewModels.AddWeighmentViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddWeighmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.text.isEmpty
import kotlin.text.toDouble
import kotlin.text.toDoubleOrNull
import kotlin.text.trim


class Add_Weighment_Form : AppCompatActivity() {
    lateinit var  binding: ActivityAddWeighmentBinding
    lateinit var  viewModel: AddWeighmentViewModel
    lateinit var progressDialog: ProgressDialog
    lateinit var etVendorName :EditText
    lateinit var etVendorNumber :EditText
    lateinit var  etEntryPermit :EditText
    lateinit var  etVehicleNumber :EditText
    lateinit var  etVehicleType :EditText
    lateinit var etGrossWeight:EditText
    lateinit var etTareWeight :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddWeighmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val material=intent.getStringExtra("material")
        viewModel = AddWeighmentViewModel()

         etVendorName = binding.etVendorName
         etVendorNumber = binding.etVendorNumber
         etEntryPermit = binding.etEntryPermit
         etVehicleNumber =binding.etVehicleNumber
         etVehicleType = binding.etVehicleType
         etGrossWeight = binding.etGrossWeight

        var btnSave = binding.savebtn
        progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Saving your Entry...")
        progressDialog.setCancelable(false)

        // hiding unnecessary fields
        binding.tvTareWeight.visibility = View.GONE
        binding.etTareWeight.visibility = View.GONE
        btnSave.setOnClickListener {
            if (validateForm()) {
                progressDialog.show()
                var load = Load()
                load.date = SimpleDateFormat("yyyy-mm-dd HH:MM a").format(System.currentTimeMillis())
                load.materialName = material
                load.vendorName = etVendorName.text.toString().trim()
                load.vendorNumber = etVendorNumber.text.toString().trim()
                load.entryPermit= etEntryPermit.text.toString().trim()
                load.vechileNumber = etVehicleNumber.text.toString().trim()
                load.vechileType = etVehicleType.text.toString().trim()
                load.grossWeight = etGrossWeight.text.toString().trim()
                viewModel.saveLoad(load)
            }
        }

        lifecycleScope.launch {
            viewModel.isSaved.collect{
                if (it){
                    progressDialog.dismiss()
                    showToast("Entry Saved Successfully")
                    finish()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                it?.let {
                    progressDialog.dismiss()
                    showToast(it)
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        val vendorName = etVendorName.text.toString().trim()
        val vendorNumber = etVendorNumber.text.toString().trim()
        val entryPermit = etEntryPermit.text.toString().trim()
        val vehicleNumber = etVehicleNumber.text.toString().trim()
        val vehicleType = etVehicleType.text.toString().trim()
        val grossWeight = etGrossWeight.text.toString().trim()
//        val tareWeight = etTareWeight.text.toString().trim()

        if (vendorName.isEmpty()) {
            etVendorName.error = "Vendor Name is required"
            showToast("Vendor Name is required")
            return false
        }


        if (vendorNumber.isEmpty()) {
            etVendorNumber.error = "Vendor Number is required"
            showToast("Vendor Number is required")
            return false
        }

        // Entry Permit Validation
        if (entryPermit.isEmpty()) {
            etEntryPermit.error = "Entry Permit is required"
            showToast("Entry Permit is required")
            return false
        }

        // Vehicle Number Validation
        if (vehicleNumber.isEmpty()) {
            etVehicleNumber.error = "Vehicle Number is required"
            showToast("Vehicle Number is required")
            return false
        }

        // Vehicle Type Validation
        if (vehicleType.isEmpty()) {
            etVehicleType.error = "Vehicle Type is required"
            showToast("Vehicle Type is required")
            return false
        }

        // Gross Weight Validation
        if (grossWeight.isEmpty()) {
            etGrossWeight.error = "Gross Weight is required"
            showToast("Gross Weight is required")
            return false
        }
//        else if (grossWeight.toDoubleOrNull() == null) {
//            etGrossWeight.error = "Gross Weight must be a number"
//            showToast("Gross Weight must be a number")
//            return false
//        }

        // Tare Weight Validation
//        if (tareWeight.isEmpty()) {
//            etTareWeight.error = "Tare Weight is required"
//            showToast("Tare Weight is required")
//            return false
//        } else if (tareWeight.toDoubleOrNull() == null) {
//            etTareWeight.error = "Tare Weight must be a number"
//            showToast("Tare Weight must be a number")
//            return false
//        }

        // Additional validation: Gross Weight should be greater than Tare Weight
//        if (grossWeight.toDouble() <= tareWeight.toDouble()) {
//            etGrossWeight.error = "Gross Weight must be greater than Tare Weight"
//            etTareWeight.error = "Tare Weight must be less than Gross Weight"
//            showToast("Gross Weight must be greater than Tare Weight")
//            return false
//        }

        return true
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}