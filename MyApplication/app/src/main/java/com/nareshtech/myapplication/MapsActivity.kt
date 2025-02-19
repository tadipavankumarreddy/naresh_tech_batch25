package com.nareshtech.myapplication

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.nareshtech.myapplication.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedlocationProviderClient: FusedLocationProviderClient

    // TODO 1: Request the sensitive permissions (Dangerous Permissins)
    companion object{
        private val REQUIRED_PERMISSIONS = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ).toTypedArray()
    }

    val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        permissions ->
        var permissionGranted = true
        permissions.entries.forEach{
            if(it.key in REQUIRED_PERMISSIONS && !it.value==false){
                permissionGranted = false
            }

            if(permissionGranted){
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
            }

            if(!permissionGranted){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO 2: Request the sensitive permissions (Dangerous Permissins)
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)

        // TODO 3: Inorder to get the last known location intialize fusedLocationProviderClient
        fusedlocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val charminar = LatLng(17.3616, 78.4747)
        mMap.addMarker(MarkerOptions().position(charminar).title("Marker in Charminar"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(charminar,13f))

        //  17.2259,78.2404

        val golconda = LatLng(17.2259, 78.2404)
        mMap.addMarker(MarkerOptions().position(golconda).title("Marker in Golconda"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(golconda,13f))

        if(checkCallingOrSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // you can get the locaiton from fusedLocationProviderClient
            val la: Task<Location> = fusedlocationProviderClient.getLastLocation()
            la.addOnSuccessListener {
                location ->
                val myloc = LatLng(location.latitude,location.longitude)
                mMap.addMarker(MarkerOptions().position(myloc).title("Marker in My Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 13f))
            }
        }

    }
}
