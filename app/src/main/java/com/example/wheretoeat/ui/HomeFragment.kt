package com.example.wheretoeat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.ActivityMainBinding
import com.example.wheretoeat.databinding.FragmentHomeBinding
import com.example.wheretoeat.viewmodels.DBViewModel

class HomeFragment : Fragment() {

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
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)

        viewModel.response.observe(viewLifecycleOwner, Observer {
            binding.textView3.text = viewModel.response.value!!.restaurants.toString()
        })

        return binding.root
    }

}