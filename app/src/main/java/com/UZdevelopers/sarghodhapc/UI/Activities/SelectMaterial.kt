package com.UZdevelopers.sarghodhapc.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.UZdevelopers.sarghodhapc.databinding.ActivitySelectMaterialBinding

class SelectMaterial : AppCompatActivity() {
    lateinit var  binding: ActivitySelectMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.material1.setOnClickListener{
            Toast.makeText(this, "Brassica Straw Loose", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Brassica Straw Loose")
            startActivity(intent)
        }

        binding.material2.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Wheat Straw Loose CTMP")
            startActivity(intent)
        }
        binding.material3.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Wheat Straw Loose Biomass")
            startActivity(intent)
        }
        binding.material4.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Linseed Straw Loose")
            startActivity(intent)
        }
        binding.material5.setOnClickListener{
            val intent= Intent(this,Add_Quality_form::class.java)
            intent.putExtra("material","Corn")
            startActivity(intent)
        }


    }
}