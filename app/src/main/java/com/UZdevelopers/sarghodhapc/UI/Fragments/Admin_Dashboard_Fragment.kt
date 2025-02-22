package com.UZdevelopers.sarghodhapc.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.UI.Activities.SelectMaterial
import com.UZdevelopers.sarghodhapc.databinding.FragmentAdminDashboardBinding

class Admin_Dashboard_Fragment : Fragment() {
    lateinit var binding: FragmentAdminDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewAssignRoles.setOnClickListener(){
            startActivity(Intent(this@Admin_Dashboard_Fragment.requireContext(), SelectMaterial::class.java).putExtra("user", "Centre In-Charge"))
        }

    }
}