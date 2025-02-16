package com.UZdevelopers.sarghodhapc.UI.Activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Load
import com.UZdevelopers.sarghodhapc.databinding.ActivityAddQualityFormBinding
import com.google.gson.Gson

class Add_Quality_form : AppCompatActivity() {
    lateinit var binding: ActivityAddQualityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddQualityFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val LoadData = Gson().fromJson(intent.getStringExtra("data"), Load::class.java)
        binding.epNumber.text = LoadData.entryPermit
        binding.selectedMaterial.text = LoadData.materialName
        binding.gorssweight.text = LoadData.grossWeight.toString() + " Tones"





    }
}