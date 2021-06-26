package com.neta.systems.places.ui.other

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neta.systems.places.R
import com.neta.systems.places.data.adapters.PlacesAdapter
import com.neta.systems.places.data.local.sqlite.RegisterWether
import com.neta.systems.places.data.local.sqlite.SQLiteHelper
import com.neta.systems.places.databinding.FragmentPlacesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlacesFragment : Fragment(R.layout.fragment_maps) {
    private var param1: String? = null
    private var param2: String? = null
    private var listWethers: List<RegisterWether> = ArrayList<RegisterWether>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var postAdapter: PlacesAdapter? = null
    internal lateinit var db: SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding: FragmentPlacesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_places, container, false)
        //val root = binding.getRoot()
        db = SQLiteHelper(requireActivity())


        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.posts.layoutManager = linearLayoutManager
        refreshData()
        binding.posts.adapter = postAdapter
        return binding.root
    }

    fun refreshData() {
        listWethers = db.allWethers
        postAdapter = PlacesAdapter(activity, listWethers)
        Toast.makeText(getContext(), "Existen ${listWethers.size} registros en la Base de Datos", Toast.LENGTH_LONG).show()
        //postAdapter!!.updatePosts(listWethers)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlacesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}