package com.UZdevelopers.sarghodhapc.UI.Activities

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.Repositories.AuthRepository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Users
import com.UZdevelopers.sarghodhapc.UI.Fragments.Quality_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.R
import androidx.fragment.app.FragmentManager
import com.UZdevelopers.sarghodhapc.UI.Fragments.Admin_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Assistant_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Error_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Unloading_Dashboard
import com.UZdevelopers.sarghodhapc.UI.Fragments.Weightment_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        var user: Users? = null
    }
      lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(user != null){
            Toast.makeText(this, "Welcome ${user!!.role}", Toast.LENGTH_SHORT).show()
            loadHomeFragment(user!!.role!!)  //loading Dashboard based on user role.
        }


    }
    private fun loadHomeFragment(role: String) {
        val fragment: Fragment = when (role) {
            "Quality Inspector" -> Quality_Dashboard_Fragment()
            "Weighment Assistant" -> Weightment_Dashboard_Fragment()
            "Unloading Supervisor" -> Unloading_Dashboard()
            "Assistant In-charge" -> Assistant_Dashboard_Fragment()
            "Centre In-Charge" -> Admin_Dashboard_Fragment()
             else -> Error_Dashboard_Fragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
