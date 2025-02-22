package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Material
import com.UZdevelopers.sarghodhapc.ViewModels.LimitViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddAssumptionHoldBinding
import kotlinx.coroutines.launch

class Add_Exemption_HoldActivity : AppCompatActivity() {
    lateinit var  binding: ActivityAddAssumptionHoldBinding
    lateinit var  progressDialog: ProgressDialog
    lateinit var viewModel:LimitViewModel
    lateinit var  materialData: Material
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddAssumptionHoldBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Checking In Database")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val material = intent.getStringExtra("material")
        binding.selectedMaterial2.text = material

        var moistureAssumptionL = binding.etMoistureAssumptionL
        var moistureHoldL = binding.etMoistureHoldL
        var sandAssumptionL = binding.etSandAssumptionL
        var sandHoldL = binding.etSandHoldL
        var mudPieceAssumptionL = binding.etMudPieceAssumptionL
        var mudPieceHoldL = binding.etMudPieceHoldL
        var burraysAssumptionL = binding.etBurraysAssumptionL
        var burraysHoldL = binding.etBurraysHoldL
        var finesAssumptionL = binding.etFinesAssumptionL
        var finesHoldL = binding.etFinesHoldL



        viewModel=LimitViewModel()

        if(material != ""){
            viewModel.getMaterial(material!!)
            showRelvantFields(material)
        } else {
            Toast.makeText(this, "Material Name Not Found", Toast.LENGTH_SHORT).show()
        }

        binding.buttonupdate.setOnClickListener(){

            materialData.moistureHoldL = binding.etMoistureHoldL.text.toString().toFloatOrNull()
            materialData.moistureExemption = binding.etMoistureAssumptionL.text.toString().toFloatOrNull()

            materialData.sandHoldL = binding.etSandHoldL.text.toString().toFloatOrNull()
            materialData.sandExemption = binding.etSandAssumptionL.text.toString().toFloatOrNull()

            materialData.mudPieceHoldL = binding.etMudPieceHoldL.text.toString().toFloatOrNull()
            materialData.mudPieceExemption = binding.etMudPieceAssumptionL.text.toString().toFloatOrNull()

            materialData.burraysHoldL = binding.etBurraysHoldL.text.toString().toFloatOrNull()
            materialData.burraysExemption = binding.etBurraysAssumptionL.text.toString().toFloatOrNull()

            materialData.finesHoldL = binding.etFinesHoldL.text.toString().toFloatOrNull()
            materialData.finesExemption = binding.etFinesAssumptionL.text.toString().toFloatOrNull()

            viewModel.updateMaterial(materialData)
            progressDialog.setMessage("Updaing Material")
            progressDialog.show()

        }


        binding.buttonSave.setOnClickListener{
            materialData = Material()
             if(valideForm(material)){
                 materialData.material = material
                 materialData.moistureHoldL = binding.etMoistureHoldL.text.toString().toFloatOrNull()
                 materialData.moistureExemption = binding.etMoistureAssumptionL.text.toString().toFloatOrNull()

                 materialData.sandHoldL = binding.etSandHoldL.text.toString().toFloatOrNull()
                 materialData.sandExemption = binding.etSandAssumptionL.text.toString().toFloatOrNull()

                 materialData.mudPieceHoldL = binding.etMudPieceHoldL.text.toString().toFloatOrNull()
                 materialData.mudPieceExemption = binding.etMudPieceAssumptionL.text.toString().toFloatOrNull()

                 materialData.burraysHoldL = binding.etBurraysHoldL.text.toString().toFloatOrNull()
                 materialData.burraysExemption = binding.etBurraysAssumptionL.text.toString().toFloatOrNull()

                 materialData.finesHoldL = binding.etFinesHoldL.text.toString().toFloatOrNull()
                 materialData.finesExemption = binding.etFinesAssumptionL.text.toString().toFloatOrNull()

                 viewModel.saveMaterial(materialData)
                 progressDialog.setMessage("Saving Data")
                 progressDialog.show()
             }
        }

        lifecycleScope.launch {
            viewModel.isSaved.collect{
                if (it){
                    progressDialog.show()
                    Toast.makeText(this@Add_Exemption_HoldActivity, "Updated/Added Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Add_Exemption_HoldActivity, MainActivity::class.java))
                    finish()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.isEmtpy.collect{
                if (it) {
                    progressDialog.dismiss()
                    Toast.makeText(this@Add_Exemption_HoldActivity, "No Existing Data Found", Toast.LENGTH_SHORT
                    ).show()
                    binding.buttonupdate.visibility =  View.GONE
                }
            }
        }


        lifecycleScope.launch {
            viewModel.material.collect{
                if (it != null) {
                    progressDialog.dismiss()
                    binding.buttonSave.visibility = View.GONE
                    materialData = it
                    updateInformation(it)
                }
            }
        }
    }

    fun updateInformation(material:Material){
        binding.etMoistureAssumptionL.setText(material.moistureExemption.toString())
        binding.etMoistureHoldL.setText(material.moistureHoldL.toString())

        binding.etSandAssumptionL.setText(material.sandExemption.toString())
        binding.etSandHoldL.setText(material.sandHoldL.toString())

        binding.etMudPieceAssumptionL.setText(material.mudPieceExemption.toString())
        binding.etMudPieceHoldL.setText(material.mudPieceHoldL.toString())

        if( material.material != "Brassica Straw Loose" ){

            binding.etBurraysAssumptionL.setText(material.burraysExemption.toString())
            binding.etBurraysHoldL.setText(material.burraysHoldL.toString())

            binding.etFinesAssumptionL.setText(material.finesExemption.toString())
            binding.etFinesHoldL.setText(material.finesHoldL.toString())
        }

    }

    fun showRelvantFields(material: String){

        if (material == "Brassica Straw Loose"){
            binding.etBurraysAssumptionL.visibility = View.GONE
            binding.etBurraysHoldL.visibility = View.GONE
            binding.etFinesAssumptionL.visibility = View.GONE
            binding.etFinesHoldL.visibility = View.GONE

        }
    }

    fun valideForm(material: String):Boolean{

            if (binding.etMoistureAssumptionL.text.toString().isEmpty()){
                binding.etMoistureAssumptionL.error = "Required"
                return false
            }
            if (binding.etMoistureHoldL.text.toString().isEmpty()){
                binding.etMoistureHoldL.error = "Required"
                return  false
            }
            if (binding.etSandAssumptionL.text.toString().isEmpty()){
                binding.etSandAssumptionL.error = "Required"
                return false
            }
            if (binding.etSandHoldL.text.toString().isEmpty()){
                binding.etSandHoldL.error = "Required"
                return false
            }
            if (binding.etMudPieceAssumptionL.text.toString().isEmpty()){
                binding.etMudPieceAssumptionL.error = "Required"
                return false
            }
            if (binding.etMudPieceHoldL.text.toString().isEmpty()){
                binding.etMudPieceHoldL.error = "Required"
                return false
            }

           if(material != "Brassica Straw Loose"){
               // if selected material is not brassica straw loose then it means we need to check all the fields

               if(binding.etBurraysAssumptionL.text.toString().isEmpty()){
                  binding.etBurraysAssumptionL.error = "Required"
                   return false
               }
               if(binding.etBurraysHoldL.text.toString().isEmpty()){
                   binding.etBurraysHoldL.error = "Required"
                   return false
               }
               if(binding.etFinesAssumptionL.text.toString().isEmpty()){
                   binding.etFinesAssumptionL.error = "Required"
                   return false
               }
               if(binding.etFinesHoldL.text.toString().isEmpty()){
                   binding.etFinesHoldL.error = "Required"
                   return false
               }
            }

        return true
    }
}