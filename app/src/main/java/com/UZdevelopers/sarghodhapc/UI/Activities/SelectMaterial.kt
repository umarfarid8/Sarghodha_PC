package com.UZdevelopers.sarghodhapc.UI.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.UZdevelopers.sarghodhapc.databinding.ActivitySelectMaterialBinding

class SelectMaterial : AppCompatActivity() {
    lateinit var  binding: ActivitySelectMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var  activity:Class<*> = Add_Weighment_Form::class.java

        val role = intent.getStringExtra("user")
        if (role == "Centre In-Charge" ){
                activity = Add_Exemption_HoldActivity::class.java
            }

        binding.material1.setOnClickListener{
            val intent= Intent(this,activity)
            intent.putExtra("material","Brassica Straw Loose")
            startActivity(intent)
        }

        binding.material2.setOnClickListener{
            val intent= Intent(this,activity)
            intent.putExtra("material","Wheat Straw Loose CTMP")
            startActivity(intent)
        }
        binding.material3.setOnClickListener{
            val intent= Intent(this,activity)
            intent.putExtra("material","Wheat Straw Loose Biomass")
            startActivity(intent)
        }
        binding.material4.setOnClickListener{
            val intent= Intent(this,activity)
            intent.putExtra("material","Linseed Straw Loose")
            startActivity(intent)
        }
        binding.material5.setOnClickListener{
            val intent= Intent(this,activity)
            intent.putExtra("material","Corn")
            startActivity(intent)
        }


    }
}