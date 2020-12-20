package com.example.wheretoeat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.adapters.RestaurantAdapter
import com.example.wheretoeat.databinding.FragmentFavouritesBinding
import com.example.wheretoeat.databinding.FragmentHomeBinding
import com.example.wheretoeat.databinding.RestaurantListItemBinding
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var bindingCardView: RestaurantListItemBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mRestaurantViewModel: RestaurantViewModel
    private lateinit var adapter: RestaurantAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)



        return binding.root
    }


}