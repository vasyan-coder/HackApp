package com.vasyancoder.hackapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController



        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.greetingFragment -> bottomNavView.visibility = View.GONE
                R.id.loginFragment -> bottomNavView.visibility = View.GONE
                R.id.registrationFragment -> bottomNavView.visibility = View.GONE
                R.id.hackathonsListFragment -> {
                    bottomNavView.setupWithNavController(navController)
                    bottomNavView.visibility = View.VISIBLE
                }
                R.id.calendarFragment -> bottomNavView.visibility = View.VISIBLE
            }
        }
    }
}