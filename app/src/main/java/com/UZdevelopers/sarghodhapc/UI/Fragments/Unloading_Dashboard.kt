package com.UZdevelopers.sarghodhapc.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.R
import com.UZdevelopers.sarghodhapc.UI.Activities.Add_Unloading_Form
import com.UZdevelopers.sarghodhapc.UI.Activities.Search_Quailty_Activity
import com.UZdevelopers.sarghodhapc.UI.Activities.Search_Unloading_Activity
import com.UZdevelopers.sarghodhapc.databinding.FragmentQualityDashboardBinding
import com.UZdevelopers.sarghodhapc.databinding.FragmentUnloadingDashboardBinding

class Unloading_Dashboard : Fragment() {
    lateinit var binding: FragmentUnloadingDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnloadingDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewEntry.setOnClickListener() {
            startActivity(
                Intent(
                    this@Unloading_Dashboard.requireContext(),
                   Search_Unloading_Activity::class.java
                )
            )
        }

    }
}