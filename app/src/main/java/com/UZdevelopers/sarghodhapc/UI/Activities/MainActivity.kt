package com.UZdevelopers.sarghodhapc.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.UZdevelopers.sarghodhapc.Repositories.AuthRepository
import com.UZdevelopers.sarghodhapc.UI.ModelClass.Users
import com.UZdevelopers.sarghodhapc.UI.Fragments.Quality_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.R
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.UZdevelopers.sarghodhapc.UI.Fragments.Admin_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Assistant_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Error_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.UI.Fragments.Unloading_Dashboard
import com.UZdevelopers.sarghodhapc.UI.Fragments.Weightment_Dashboard_Fragment
import com.UZdevelopers.sarghodhapc.ViewModels.MainViewModel
import com.UZdevelopers.sarghodhapc.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        var user: Users? = null
    }

    lateinit var binding: ActivityMainBinding
    lateinit var  viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val imageView = findViewById<ImageView>(R.id.drawer_icon)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        imageView.setOnClickListener { view: View? ->
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
        if (user != null) {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        when (item.itemId) {
            R.id.nav_home -> {
                if (user != null) {
                    loadHomeFragment(user!!.role!!)
                }
            }

            R.id.nav_logout -> {
                // Handle Logout click
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show()
                viewModel = MainViewModel()
                viewModel.logout()
                finish()
                user = null
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            // Add more cases for other menu items
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
