package com.UZdevelopers.sarghodhapc.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
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
        binding.etDeduction.isEnabled = false

        val etMoisture = binding.etMoisture
        val etSand = binding.etSand
        val etFines = binding.etFines
        val etMudPieces = binding.etMudPieces
        val etBurrays = binding.etBurrays

        fun validateRelatedFields():Boolean {

            if(etMoisture.text.isEmpty()){
                etMoisture.error = "Moisture is required"
                return false
            }
            if(etSand.text.isEmpty()){
                etSand.error = "Sand is required"
                return false
            }
            if(etMudPieces.text.isEmpty()){
                etMudPieces.error = "Mud Pieces is required"
                return false
            }

            if(LoadData.materialName != "Brassica Straw Loose"){
                if(etFines.text.isEmpty()){
                    etFines.error = "Fines is required"
                    return false
                }

                if(etBurrays.text.isEmpty()){
                    etBurrays.error = "Burrays is required"
                    return false
                }


            }
            return  true
        }

        // Function to add TextWatcher to an EditText
        fun addTextWatcher(editText: EditText, fieldName: String) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val input = s.toString()
                    if (input.isNotEmpty()) {
                        val value = input.toFloatOrNull()
                        value?.let {
                            // Do something with the new value
                            println("$fieldName changed to: $it")
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if(validateRelatedFields()){
                        var totalDeduction = 0.0
                        if(material.finesAssumptionL != null && material.burraysAssumptionL != null){ // checking if we need to check this field or not
                           if( etFines.text.toString().toFloat() > material.finesHoldL!! ||
                               etBurrays.text.toString().toFloat() > material.burraysHoldL!! ||
                               etMoisture.text.toString().toFloat() > material.moistureHoldL!! ||
                               etSand.text.toString().toFloat() > material.sandHoldL!! ||
                               etMudPieces.text.toString().toFloat() > material.mudPieceHoldL!!
                               ){ // if limit is higher than above it will be rejected
                                // add logic for rejected
                                Log.i("INfo", "Rejected")
                                binding.rbHold.isChecked = true

                           } else {
                               var moistureV = (binding.etMoisture.text.toString().toFloat()) - material.moistureAssumptionL!!
                               var sandV = (binding.etSand.text.toString().toFloat()) - material.sandAssumptionL!!
                               var mudv = (binding.etMudPieces.text.toString().toFloat()) - material.mudPieceAssumptionL!!
                               var finesV = (binding.etFines.text.toString().toFloat()) - material.finesAssumptionL!!
                               var burraysV = (binding.etBurrays.text.toString().toFloat()) - material.burraysAssumptionL!!


                               if(moistureV > 0){
                                   totalDeduction += moistureV
                               }
                               if (sandV > 0){
                                   totalDeduction += sandV
                               }
                               if (mudv > 0){
                                   totalDeduction += mudv
                               }
                               if (finesV > 0){
                                   totalDeduction += finesV
                               }
                               if (burraysV > 0) {
                                   totalDeduction += burraysV
                               }

                                binding.etDeduction.setText(totalDeduction.toString())


                           }
                        } else {
                            // check only 3
                            if( etMoisture.text.toString().toFloat() > material.moistureHoldL!! ||
                                etSand.text.toString().toFloat() > material.sandHoldL!! ||
                                etMudPieces.text.toString().toFloat() > material.mudPieceHoldL!!
                            ){ // if limit is higher than above it will be rejected
                                // add logic for rejected
                                Log.i("INfo", "Rejected")
                                binding.rbHold.isChecked = true

                            }
                             else{
                                var moistureV = (binding.etMoisture.text.toString().toFloat()) - material.moistureAssumptionL!!
                                var sandV = (binding.etSand.text.toString().toFloat()) - material.sandAssumptionL!!
                                var mudv = (binding.etMudPieces.text.toString().toFloat()) - material.mudPieceAssumptionL!!

                                if(moistureV > 0){
                                    totalDeduction += moistureV
                                }
                                if (sandV > 0){
                                    totalDeduction += sandV
                                }
                                if (mudv > 0)
                                    totalDeduction += mudv

                                binding.etDeduction.setText(totalDeduction.toString())
                            }
                        }


                    }
                }
            })
        }

// Apply the TextWatcher to all EditTexts

        addTextWatcher(etMoisture, "Moisture")
        addTextWatcher(etSand, "Sand")
        addTextWatcher(etFines, "Fines")
        addTextWatcher(etMudPieces, "Mud Pieces")
        addTextWatcher(etBurrays, "Burrays")


        if (LoadData.materialName == "Brassica Straw Loose") {
            binding.etBurrays.visibility = View.GONE
            binding.tvBurrays.visibility = View.GONE

            binding.etFines.visibility = View.GONE
            binding.tvFines.visibility = View.GONE

        }

        binding.buttonSave.setOnClickListener {
            if(validateRelatedFields()){
              if(!binding.rbDarkBrown.isChecked && !binding.rbLightBrown.isChecked){
                  Toast.makeText(this, "Select Color", Toast.LENGTH_SHORT).show()
                  return@setOnClickListener
              }

              if(!binding.rbAccepted.isChecked && !binding.rbBalanceHold.isChecked && !binding.rbHold.isChecked){
                  Toast.makeText(this, "Select Status", Toast.LENGTH_SHORT).show()
                  return@setOnClickListener
              }
              if(binding.etTestedBy.text.isEmpty()){
               binding.etTestedBy.error = "Add Your name"
               return@setOnClickListener
              }

              if (binding.etRemarks.text.isEmpty()){
                  binding.etRemarks.error = "Add Remarks"
                  return@setOnClickListener
              }

                progressDialog.show()
                LoadData.mositureP = binding.etMoisture.text.toString()
                LoadData.sandP = binding.etSand.text.toString()
                LoadData.mudPieceP = binding.etMudPieces.text.toString()

                if(LoadData.materialName != "Brassica Straw Loose"){
                    LoadData.burraysP = binding.etBurrays.text.toString()
                    LoadData.finesP = binding.etFines.text.toString()
                }

                LoadData.colorr = if(binding.rbLightBrown.isChecked) "Light Brown" else "Dark Brown"
                 var status = "Accepted"
                if(binding.rbBalanceHold.isChecked){
                    status = "Balance Hold"
                } else if(binding.rbHold.isChecked){
                 status = "Rejected"
                }
                LoadData.status = status
                LoadData.processStatus = (LoadData.processStatus!!.toInt() + 1).toString()
                LoadData.remarks = binding.etRemarks.text.toString()
                LoadData.deductionP = binding.etDeduction.text.toString()
                LoadData.testedBy = binding.etTestedBy.text.toString()

                viewModel.updateLoad(LoadData)


            }
        }

        lifecycleScope.launch {
            viewModel.material.collect {
                if (it != null) {
                    material = it
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