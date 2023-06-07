package com.vasyancoder.hackapp

import android.content.SharedPreferences
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
import com.vasyancoder.core.Utils
import com.vasyancoder.feature_login.presentation.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


        // read
        val sharedPrefRead: SharedPreferences = getPreferences(MODE_PRIVATE)
        val logined = sharedPrefRead.getString(LoginFragment.SHARED_PREF_LOGIN, "")

        val graph = navHostFragment.navController
            .navInflater.inflate(R.navigation.navigation)

        if (logined != "") {
            if (logined != null) {
                Utils.userRole = logined
            }
            graph.setStartDestination(R.id.hackathonsListFragment)
            navHostFragment.navController.graph = graph
        } else {
            graph.setStartDestination(R.id.greetingFragment)
            navHostFragment.navController.graph = graph
        }


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