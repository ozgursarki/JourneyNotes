package com.example.journeynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.journeynotes.databinding.ActivityMainBinding
import com.example.journeynotes.ui.AddNotesFragment
import com.example.journeynotes.ui.MapsFragment

import com.google.android.gms.maps.GoogleMap

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MapsFragment())

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mapsFragment -> replaceFragment(MapsFragment())
                R.id.addNotesFragment -> replaceFragment(AddNotesFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
         val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
         fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction.commit()
    }



}