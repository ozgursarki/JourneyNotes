package com.example.journeynotes.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.skydoves.balloon.*


class MapsFragment : Fragment(), OnMapReadyCallback, OnMarkerClickListener,OnMapClickListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher: ActivityResultLauncher<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.map
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)
        registerLauncher()
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)


    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener(this)
        mMap.setOnMarkerClickListener(this)

        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = LocationListener { location -> println("location : $location") }


        if (ContextCompat.checkSelfPermission!!(
                Activity(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Activity(),Manifest.permission.ACCESS_FINE_LOCATION)) {
                Snackbar.make(binding.root,"Permission needed",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission") {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()
            }else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
        }
        mMap.isMyLocationEnabled = true

        val fusedLocationClient: FusedLocationProviderClient =
           LocationServices.getFusedLocationProviderClient(requireContext());
        fusedLocationClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                val latLong = LatLng(it.latitude,it.longitude);
                mMap.addMarker(MarkerOptions().position(latLong))
            }
        }


    }

    override fun onMarkerClick(p0: Marker): Boolean {

        val balloon = Balloon.Builder(requireContext())
            .setWidthRatio(1.0f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(getString(R.string.balloon_title))
            .setTextSize(15f)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setCornerRadius(8f)
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(viewLifecycleOwner)
            .setOnBalloonClickListener {
                val action = MapsFragmentDirections.actionMapsFragmentToAddNotesFragment()
                findNavController().navigate(action)
            }
            .build()

        binding.root.showAlignTop(balloon)

        return true
    }

    override fun onMapClick(location: LatLng) {
        mMap.clear()

        mMap.addMarker(MarkerOptions().position(location).title("You clicked here!"))
    }

    private fun registerLauncher(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                }
            }else {
                Toast.makeText(requireContext(),"Permission needed",Toast.LENGTH_LONG).show()
            }

        }
    }
}