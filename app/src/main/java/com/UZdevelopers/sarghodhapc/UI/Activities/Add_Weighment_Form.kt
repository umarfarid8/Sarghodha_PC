package com.UZdevelopers.sarghodhapc.UI.Activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddWeighmentBinding
import com.UZdevelopers.sarghodhapc.databinding.ActivitySelectMaterialBinding

class Add_Weighment_Form : AppCompatActivity() {
    lateinit var  binding: ActivityAddWeighmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddWeighmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val material=intent.getStringExtra("material")


    }
}