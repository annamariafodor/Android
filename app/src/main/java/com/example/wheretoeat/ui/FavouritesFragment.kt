package com.example.wheretoeat.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.adapters.RestaurantAdapter
import com.example.wheretoeat.databinding.FragmentFavouritesBinding
import com.example.wheretoeat.databinding.RestaurantListItemBinding
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel

class FavouritesFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestaurantViewModel: RestaurantViewModel
    private lateinit var adapter: RestaurantAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        mUserViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(UserViewModel::class.java) }
        mRestaurantViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java) }
        sharedPreferences =
            context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        val recyclerView = binding.recyclerView


        mUserViewModel.favourites.observe(viewLifecycleOwner, Observer {
            adapter = RestaurantAdapter(mUserViewModel.favourites.value!!, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        })
        mUserViewModel.getFavourites(sharedPreferences.getString("email", "").toString())

        return binding.root
    }

    override fun onItemClick(item: Restaurant) {
        mRestaurantViewModel.currentRestaurant.value = item
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_favouritesFragment_to_detailsFragment)
    }

}