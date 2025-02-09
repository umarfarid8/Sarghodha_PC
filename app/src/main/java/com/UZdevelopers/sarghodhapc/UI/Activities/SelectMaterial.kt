package com.UZdevelopers.sarghodhapc.UI.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.databinding.ActivitySelectMaterialBinding

class SelectMaterial : AppCompatActivity() {
    lateinit var  binding: ActivitySelectMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           binding= ActivitySelectMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardView1.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Brassica Straw Loose")
            startActivity(intent)
        }

        binding.cardView.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Wheat Straw Loose CTMP")
            startActivity(intent)
        }
        binding.cardView3.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Wheat Straw Loose Biomass")
            startActivity(intent)
        }
        binding.cardView4.setOnClickListener{
            val intent= Intent(this,Add_Weighment_Form::class.java)
            intent.putExtra("material","Linseed Straw Loose")
            startActivity(intent)
        }
        binding.cardView5.setOnClickListener{
            val intent= Intent(this,Add_Quality_form::class.java)
            intent.putExtra("material","Corn")
            startActivity(intent)
        }


    }
}