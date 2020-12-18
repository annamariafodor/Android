package com.example.wheretoeat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheretoeat.R
import com.example.wheretoeat.adapters.RestaurantAdapter
import com.example.wheretoeat.databinding.FragmentHomeBinding
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel

class HomeFragment : Fragment(),RestaurantAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestaurantViewModel: RestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mRestaurantViewModel = requireActivity().run {  ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)}

        val adapter = RestaurantAdapter(mRestaurantViewModel.restaurants.value!!,this)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onItemClick(item: Restaurant) {
        mRestaurantViewModel.currentRestaurant.value = item
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_homeFragment_to_detailsFragment)
    }


}