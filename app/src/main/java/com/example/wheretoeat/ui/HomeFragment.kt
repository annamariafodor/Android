package com.example.wheretoeat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheretoeat.R
import com.example.wheretoeat.adapters.RestaurantAdapter
import com.example.wheretoeat.databinding.FragmentHomeBinding
import com.example.wheretoeat.databinding.RestaurantListItemBinding
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel
import java.util.*

class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingCardView: RestaurantListItemBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestaurantViewModel: RestaurantViewModel
    private lateinit var adapter: RestaurantAdapter
    private lateinit var restaurantList: List<Restaurant>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        bindingCardView =
            DataBindingUtil.inflate(inflater, R.layout.restaurant_list_item, container, false)
        mUserViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(UserViewModel::class.java) }
        mRestaurantViewModel =
            requireActivity().run { ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java) }
        restaurantList = mRestaurantViewModel.restaurants.value!!
        adapter = RestaurantAdapter(mRestaurantViewModel.restaurants.value!!, this)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = mutableListOf<Restaurant>()
                if (newText!!.isNotEmpty()) {
                    newList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    mRestaurantViewModel.restaurants.value!!.forEach {
                        if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                            newList.add(it)
                        }
                    }
                    adapter.setData(newList)
                } else {
                    newList.addAll(mRestaurantViewModel.restaurants.value!!)
                    adapter.setData(newList)
                }

                return true
            }

        })

        val arrayAdapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    mRestaurantViewModel.cities.value!!.cities
                )
            }
        arrayAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mRestaurantViewModel.restaurants.observe(viewLifecycleOwner, Observer {
                    adapter.setData(mRestaurantViewModel.restaurants.value!!)
                })
                mRestaurantViewModel.currentCity.observe(viewLifecycleOwner, Observer {
                    mRestaurantViewModel.getRestaurantByCity(binding.spinner.selectedItem.toString())
                })
                mRestaurantViewModel.currentCity.value = binding.spinner.selectedItem.toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        return binding.root
    }

    override fun onItemClick(item: Restaurant) {
        mRestaurantViewModel.currentRestaurant.value = item
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_homeFragment_to_detailsFragment)
    }


}