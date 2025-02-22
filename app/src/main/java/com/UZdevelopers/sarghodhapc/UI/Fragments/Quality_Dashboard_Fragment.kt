package com.UZdevelopers.sarghodhapc.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.Activities.Add_Quality_form
import com.UZdevelopers.sarghodhapc.UI.Activities.History_quality_Activity
import com.UZdevelopers.sarghodhapc.UI.Activities.Search_Quailty_Activity
import com.UZdevelopers.sarghodhapc.UI.Activities.SelectMaterial
import com.UZdevelopers.sarghodhapc.UI.Adapters.HistoryQualityAdapter
import com.UZdevelopers.sarghodhapc.databinding.ActivityHistoryQualityBinding
import com.UZdevelopers.sarghodhapc.databinding.FragmentQualityDashboardBinding
import com.UZdevelopers.sarghodhapc.databinding.FragmentWeightmentDashboardBinding

class Quality_Dashboard_Fragment : Fragment() {
    lateinit var binding: FragmentQualityDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQualityDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewQualityEntry.setOnClickListener() {
            startActivity(
                Intent(
                    this@Quality_Dashboard_Fragment.requireContext(),
                    Search_Quailty_Activity::class.java
                )
            )
        }

        binding.cardViewInspectionHistory.setOnClickListener() {
            startActivity(
                Intent(
                    this@Quality_Dashboard_Fragment.requireContext(), History_quality_Activity::class.java))
        }

    }
}