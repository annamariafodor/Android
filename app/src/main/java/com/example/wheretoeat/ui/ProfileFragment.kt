package com.example.wheretoeat.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentProfileBinding
import com.example.wheretoeat.databinding.FragmentRegistrationBinding
import com.example.wheretoeat.viewmodels.UserViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        sharedPreferences = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!

        binding.signOutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        binding.nameText.text=sharedPreferences.getString("name","")
        binding.addressText.text=sharedPreferences.getString("address","")
        binding.phoneText.text=sharedPreferences.getString("phone","")
        binding.emailText.text=sharedPreferences.getString("email","")

        return binding.root
    }

}