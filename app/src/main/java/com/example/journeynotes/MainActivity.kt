package com.example.journeynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journeynotes.databinding.ActivityMainBinding

import com.google.android.gms.maps.GoogleMap

class MainActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}