package com.example.wheretoeat.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.MapsActivity
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentDetailsBinding
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.User
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mRestaurantViewModel: RestaurantViewModel
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var lisOfFavourites: List<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        mUserViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(UserViewModel::class.java) }
        mRestaurantViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java) }
        sharedPreferences =
            context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        binding.restaurantName.text = mRestaurantViewModel.currentRestaurant.value!!.name
        binding.restaurantAddress.text = mRestaurantViewModel.currentRestaurant.value!!.address
        binding.restaurantArea.text = mRestaurantViewModel.currentRestaurant.value!!.area
        binding.restaurantCity.text = mRestaurantViewModel.currentRestaurant.value!!.city
        binding.restaurantPhone.text = mRestaurantViewModel.currentRestaurant.value!!.phone
        binding.restaurantPrice.text =
            mRestaurantViewModel.currentRestaurant.value!!.price.toString()


        mUserViewModel.favourites.observe(viewLifecycleOwner, Observer {
            lisOfFavourites = mUserViewModel.favourites.value!!
        })
        mUserViewModel.getFavourites(sharedPreferences.getString("email", "").toString())
        checkFavourites()

        binding.favouriteIcon.setOnClickListener {
            val newFavList = mutableListOf<Restaurant>()
            if (lisOfFavourites.contains(mRestaurantViewModel.currentRestaurant.value)) {
                newFavList.addAll(lisOfFavourites)
                newFavList.remove(mRestaurantViewModel.currentRestaurant.value)
                binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_star_outline_24)
                sharedPreferences.getString("email", "")?.let { it1 ->
                    mUserViewModel.updateFavourites(
                        newFavList,
                        it1
                    )
                }
            } else {
                newFavList.addAll(lisOfFavourites)
                newFavList.add(mRestaurantViewModel.currentRestaurant.value!!)
                binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_star_yellow)
                sharedPreferences.getString("email", "")?.let { it1 ->
                    mUserViewModel.updateFavourites(
                        newFavList,
                        it1
                    )
                }
            }
        }

        binding.mapButton.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java).apply {
                putExtra("lat", mRestaurantViewModel.currentRestaurant.value!!.lat)
                putExtra("long", mRestaurantViewModel.currentRestaurant.value!!.lng)
                putExtra("name", mRestaurantViewModel.currentRestaurant.value!!.name)
            }
            startActivity(intent)
        }

        return binding.root
    }

    fun checkFavourites() {
        mUserViewModel.favourites.observe(viewLifecycleOwner, Observer {
            if (mUserViewModel.favourites.value!!.contains(mRestaurantViewModel.currentRestaurant.value)) {
                binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_star_yellow)
            } else {
                binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }
        })
        mUserViewModel.getFavourites(sharedPreferences.getString("email", "").toString())
    }

}