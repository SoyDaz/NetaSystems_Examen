package com.neta.systems.places.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.neta.systems.places.R
import com.neta.systems.places.data.model.WeatherResult
import com.neta.systems.places.databinding.FragmentMapsBinding


private const val ARG_PARAM1 = "model"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapsFragment : Fragment(R.layout.fragment_maps) , OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var mWeatherLocation: LatLng? = null
    //(-31.952854, 115.857342)
    private var param1: WeatherResult? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: FragmentMapsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        val root = binding.getRoot()
        if (param1 != null) {
            val lat: Double? = param1?.coord?.lat as? Double
            val log: Double? = param1?.coord?.lon as? Double
            mWeatherLocation = LatLng(lat!!, log!!)
        }
        createMapFragment()
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        if (mapFragment is SupportMapFragment) {
            mapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        if (mWeatherLocation != null) {
            map.setMinZoomPreference(6.0f)
            map.setMaxZoomPreference(25.0f)
            map.addMarker(MarkerOptions().position(mWeatherLocation).title(param1!!.name))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(mWeatherLocation, 25f))
            map.animateCamera(CameraUpdateFactory.zoomIn())
            map.animateCamera(CameraUpdateFactory.zoomTo(25f), 2000, null)
            val cameraPosition = CameraPosition.Builder()
                    .target(mWeatherLocation)
                    .zoom(25f)
                    .bearing(90f)
                    .tilt(30f)
                    .build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }
}