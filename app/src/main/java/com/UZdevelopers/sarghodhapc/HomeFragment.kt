package com.UZdevelopers.sarghodhapc.UI.Fragments  // ✅ Package must match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.R

class HomeFragment : Fragment() {  // ✅ Class name should match `nav_graph.xml`

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)  // ✅ Make sure fragment_home.xml exists
    }
}
