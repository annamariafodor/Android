package com.example.wheretoeat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheretoeat.R
import com.example.wheretoeat.adapters.RestaurantAdapter
import com.example.wheretoeat.databinding.FragmentHomeBinding
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.viewmodels.DBViewModel

class HomeFragment : Fragment(),RestaurantAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: DBViewModel by lazy {
        ViewModelProvider(this).get(DBViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        var dummyList: List<Restaurant> = listOf()
        for (i in 1..10) {
            dummyList += Restaurant(
                i, "nameOfRestaurant", "addressOfRestaurant",
                "city", "state", "area", 8230, "country", "+0123465",
                5.2, 3.4, 49.99, "reserveUrl", "mobileResUrl", "imgUrl"
            )
        }
        Log.d("log","dummylist: ${dummyList.toString()}")

        val adapter = RestaurantAdapter(dummyList,this)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.response.observe(viewLifecycleOwner, Observer {
//            binding.textView3.text = viewModel.response.value!!.restaurants.toString()
        })

        return binding.root
    }

    override fun onItemClick(item: Restaurant) {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_home_to_detailsFragment)
    }


}