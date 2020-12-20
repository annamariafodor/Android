package com.example.wheretoeat.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentRegistrationBinding
import com.example.wheretoeat.models.User
import com.example.wheretoeat.viewmodels.RestaurantViewModel
import com.example.wheretoeat.viewmodels.UserViewModel
import java.math.BigInteger
import java.security.MessageDigest

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        mUserViewModel = requireActivity().run { ViewModelProvider(requireActivity()).get(
            UserViewModel::class.java) }
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.GONE
        sharedPreferences =
            context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!


        binding.registerButton.setOnClickListener {
            if (isValide(
                    binding.emailInput.text.toString(),
                    binding.nameInput.text.toString(),
                    binding.phoneInput.text.toString(),
                    binding.addressInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
            ) {
                mUserViewModel.addUser(
                    User(
                        0, binding.emailInput.text.toString(), binding.nameInput.text.toString(),
                        binding.phoneInput.text.toString(),
                        binding.addressInput.text.toString(),
                        binding.passwordInput.text.toString().toMd5(),
                        mutableListOf()
                    )
                )

                val editor = sharedPreferences.edit()
                editor.clear()
                editor.putString("email", binding.emailInput.text.toString())
                editor.putString("name", binding.nameInput.text.toString())
                editor.putString("phone", binding.phoneInput.text.toString())
                editor.putString("address", binding.addressInput.text.toString())
                editor.putString("password", binding.phoneInput.text.toString().toMd5())
                editor.apply()
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Please input datas!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    private fun isValide(
        emailInput: String,
        nameInput: String,
        phoneInput: String,
        addressInput: String,
        passwordInput: String
    ): Boolean {
        return !(TextUtils.isEmpty(emailInput) || TextUtils.isEmpty(nameInput) || TextUtils.isEmpty(
            phoneInput
        ) || TextUtils.isEmpty(addressInput) || TextUtils.isEmpty(passwordInput))
    }

    private fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}