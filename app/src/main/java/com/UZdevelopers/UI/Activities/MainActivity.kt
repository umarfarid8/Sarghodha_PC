package com.UZdevelopers.UI.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.UZdevelopers.UI.ModelClass.Users
import com.UZdevelopers.sarghodhapc.R

 class MainActivity : AppCompatActivity() {
    companion object {
        var user: Users? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}