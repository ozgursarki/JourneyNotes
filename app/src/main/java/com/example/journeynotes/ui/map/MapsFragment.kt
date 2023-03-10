package com.example.journeynotes.ui.map

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.journeynotes.R
import com.example.journeynotes.databinding.FragmentMapsBinding
import com.example.journeynotes.domain.model.Note
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback, OnMarkerClickListener,OnMapClickListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher : ActivityResultLauncher<String>
    private var trackBoolean : Boolean = false
    private val viewModel: MapsViewModel by viewModels()
    private var lastMarker : Marker?=null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        registerLauncher()
        trackBoolean = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = binding.map
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)

        viewModel.note.observe(viewLifecycleOwner) { note ->
            NoteBottomSheetFragment.newInstance(note).show(parentFragmentManager,NoteBottomSheetFragment.TAG)

        }

    }

    private fun handleNotes(notes:List<Note>) {
        notes.forEach { note ->
            mMap.addMarker(MarkerOptions().position(LatLng(note.location.latitude!!,note.location.longitude!!)))

        }


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

        viewLifecycleOwner.lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.notes.collect {
                        handleNotes(it)
                    }
                }
            }
        }

        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {

            override fun onProviderDisabled(provider: String) {

            }

            override fun onProviderEnabled(provider: String) {

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }
            override fun onLocationChanged(location: Location) {
                if (!trackBoolean) {
                    val userLocation = LatLng(location.latitude,location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15f))
                    trackBoolean = true
                }


            }
        }

        if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),Manifest.permission.ACCESS_FINE_LOCATION)) {
                Snackbar.make(binding.root,"Permission needed",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission") {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }.show()
            }else {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                val lastUserLocation = LatLng(lastLocation.latitude,lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15f))
            }
            mMap.isMyLocationEnabled = true
        }

        mMap.setOnMapClickListener(this)
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        if(marker.id != lastMarker?.id) {
            viewModel.getNoteByLocation(com.example.journeynotes.domain.model.Location(
                latitude = marker.position.latitude,
                longitude = marker.position.longitude
            ))
        }else {
            val location = com.example.journeynotes.domain.model.Location(marker.position.latitude,marker.position.longitude)
            val balloon = Balloon.Builder(requireContext())
                .setWidth(BalloonSizeSpec.WRAP)
                .setHeight(BalloonSizeSpec.WRAP)
                .setText(getString(R.string.balloon_title))
                .setTextSize(15f)
                .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                .setArrowSize(10)
                .setTextTypeface(Typeface.BOLD)
                .setArrowPosition(0.5f)
                .setPadding(12)
                .setCornerRadius(8f)
                .setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.orange))
                .setBalloonAnimation(BalloonAnimation.ELASTIC)
                .setLifecycleOwner(viewLifecycleOwner)
                .setOnBalloonClickListener {
                    val action = MapsFragmentDirections.actionMapsFragmentToAddNotesFragment(location)
                    findNavController().navigate(action)
                }
                .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                .setBalloonHighlightAnimation(BalloonHighlightAnimation.HEARTBEAT)
                .build()

            binding.root.showAlignTop(balloon)
        }



        return true
    }


    private fun registerLauncher(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null) {
                        val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))
                    }
                    mMap.isMyLocationEnabled = true
                }
            }else {
                Toast.makeText(requireContext(),"Permission needed",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onMapClick(p0: LatLng) {
        lastMarker?.remove()

        lastMarker = mMap.addMarker(MarkerOptions().position(p0))
        lastMarker?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

    }

}
