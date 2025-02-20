package com.UZdevelopers.sarghodhapc.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.Activities.Search_weightment_Activity
import com.UZdevelopers.sarghodhapc.UI.Activities.SelectMaterial
import com.UZdevelopers.sarghodhapc.UI.Activities.WeighmentHistoryActivity

import com.UZdevelopers.sarghodhapc.databinding.ActivityMainBinding
import com.UZdevelopers.sarghodhapc.databinding.FragmentWeightmentDashboardBinding

class Weightment_Dashboard_Fragment : Fragment() {

    lateinit var binding:FragmentWeightmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentWeightmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardView.setOnClickListener(){
            startActivity(Intent(this@Weightment_Dashboard_Fragment.requireContext(), SelectMaterial::class.java))
        }

        binding.cardView2.setOnClickListener(){
           startActivity(Intent(this@Weightment_Dashboard_Fragment.requireContext(), Search_weightment_Activity::class.java))
        }
        binding.cardView3.setOnClickListener {
            startActivity(Intent(requireContext(), WeighmentHistoryActivity::class.java))
        }


    }
}