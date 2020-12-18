package com.example.wheretoeat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentDetailsBinding
import com.example.wheretoeat.viewmodels.RestaurantViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mRestaurantViewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        mRestaurantViewModel = requireActivity().run {  ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)}
        binding.restaurantName.text= mRestaurantViewModel.currentRestaurant.value!!.name
        binding.restaurantAddress.text = mRestaurantViewModel.currentRestaurant.value!!.address
        binding.restaurantArea.text=mRestaurantViewModel.currentRestaurant.value!!.area
        binding.restaurantCity.text=mRestaurantViewModel.currentRestaurant.value!!.city
        binding.restaurantPhone.text=mRestaurantViewModel.currentRestaurant.value!!.phone
        binding.restaurantPrice.text=mRestaurantViewModel.currentRestaurant.value!!.price.toString()

        return binding.root
    }


}