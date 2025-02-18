package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddQualityFormBinding
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Add_Quality_form : AppCompatActivity() {
    lateinit var binding: ActivityAddQualityFormBinding
    lateinit var viewModel: AddQualityDataViewModel
    lateinit var material: Material

    lateinit var  progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddQualityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = AddQualityDataViewModel()


        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Updating Information")
        progressDialog.setCancelable(false)

        val LoadData = Gson().fromJson(intent.getStringExtra("data"), Load::class.java)
        viewModel.getMaterial(LoadData.materialName!!)


        binding.epNumber.text = LoadData.entryPermit
        binding.selectedMaterial.text = LoadData.materialName
        binding.gorssweight.text = LoadData.grossWeight.toString() + " Tones"

        if (LoadData.materialName == "Brassica Straw Loose") {
            binding.etBurrays.visibility = View.GONE
            binding.tvBurrays.visibility = View.GONE

            binding.etFines.visibility = View.GONE
            binding.tvFines.visibility = View.GONE

        }

        lifecycleScope.launch {
            viewModel.material.collect {
                if (it != null) {
                    material = it
                    Log.i("material", it.sandAssumptionL.toString())
                }
            }
        }
        lifecycleScope.launch {
            viewModel.failureMessage.collect {
                if (it != null) {
                    progressDialog.dismiss()
                    Toast.makeText(this@Add_Quality_form, it, Toast.LENGTH_SHORT).show()
                }

            }
        }
        lifecycleScope.launch {
            viewModel.failureLoadingMaterial.collect {
                if (it != null) {
                    progressDialog.dismiss()
                    Toast.makeText(this@Add_Quality_form, it, Toast.LENGTH_SHORT).show()
                    binding.buttonSave.isEnabled = false
                    binding.buttonSave.isClickable = false

                }

            }
        }
        lifecycleScope.launch {
            viewModel.isUpdated.collect {
                if (it) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        this@Add_Quality_form,
                        "Updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@Add_Quality_form, MainActivity::class.java))
                    finish()
                }

                lifecycleScope.launch {
                    viewModel.isEmtpy.collect {
                        if (it) {
                            binding.buttonSave.isEnabled = false
                            binding.buttonSave.isClickable = false
                            Toast.makeText(
                                this@Add_Quality_form,
                                "Add Assumption/Hold Limits First",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }


            }
        }
    }
}