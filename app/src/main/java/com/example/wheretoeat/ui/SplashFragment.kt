package com.example.wheretoeat.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentSplashBinding
import com.example.wheretoeat.viewmodels.UserViewModel

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.GONE
        sharedPreferences =
            requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val credentials = sharedPreferences.all
        if (credentials!!.containsKey("email") && credentials.containsKey("password")) {
            Log.d("log", "szia ${sharedPreferences.all}")
            Log.d("log", "szia2 ${sharedPreferences.getString("email","").toString()}")
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        } else {
            Log.d("log", "hello")
            sharedPreferences.edit().clear().apply()
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }




        return binding.root
    }

}