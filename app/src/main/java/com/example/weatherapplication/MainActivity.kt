package com.example.weatherapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.weatherapplication.compose.PrefManager
import com.example.weatherapplication.compose.SearchBarCompose
import com.example.weatherapplication.compose.WeatherDetailCompose
import com.example.weatherapplication.ui.theme.WeatherApplicationTheme
import com.example.weatherapplication.utils.hideKeyboard
import com.example.weatherapplication.viewmodel.WeatherViewModel
import com.google.android.gms.location.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * @author Kunal Rathod
 * @since 21 march 2023
 * This activity hold the implementation for search the city by name,
 * if location permission is enable the get current location and show weather of that location
 * if permisson denied then search for the city with keywords
 * */
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    var prefManager: PrefManager? = null

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //checking if the prefManager is null then create instance of it
        prefManager ?: kotlin.run {
            prefManager = PrefManager(this@MainActivity)
        }

        //if its not null and we have any last search, then make api call
        prefManager?.getLastSearch()?.let {
            weatherViewModel.fetchWeatherInfo(it)
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        setContent {
            WeatherApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column {
                        SearchBarCompose(
                            hint = "Search By City",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onHideKeyBoard = ::onHideKeyboard
                        ) {
                            //making api call to fetch weather info
                            weatherViewModel.fetchWeatherInfo(it)

                            //after api call, saved that search key to shared preferences
                            prefManager?.saveLastSearch(it)

                        }

                        WeatherDetailCompose(weatherViewModel)

                    }
                }
            }
        }
    }

    /**
     * Hide the keyboard when user search any key
     * */
    private fun onHideKeyboard() {
        hideKeyboard()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(this, Locale.getDefault())

                        val list: List<Address> =
                            geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            ) as List<Address>

                        weatherViewModel.fetchWeatherInfoUsingLatLong(
                            list[0].latitude.toString(),
                            list[0].longitude.toString()
                        )
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    /**
     * this function checks the location is enabled
     * */
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    /**
     * this function check if all the permission are allowed or not
     * */
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    /*runtime permission list*/
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            permissionId
        )
    }


    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }
}
