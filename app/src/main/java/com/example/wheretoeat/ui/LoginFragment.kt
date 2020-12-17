package com.example.wheretoeat.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wheretoeat.R
import com.example.wheretoeat.databinding.FragmentLoginBinding
import com.example.wheretoeat.room.User
import com.example.wheretoeat.viewmodels.UserViewModel
import java.math.BigInteger
import java.security.MessageDigest


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        sharedPreferences =
            context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.loginButton.setOnClickListener {
            mUserViewModel.user.observe(viewLifecycleOwner, dataObserver)

            mUserViewModel.getUserByEmail(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString().toMd5()
            )

            binding.emailInput.text!!.clear()
            binding.passwordInput.text!!.clear()
            binding.passwordInput.clearFocus()
            binding.emailInput.clearFocus()
        }


        return binding.root
    }

    private fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    private val dataObserver = Observer<User> {
        if (it == null) {
            Toast.makeText(activity, "Invalid email or password", Toast.LENGTH_SHORT).show()
        } else {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.putString("email", binding.emailInput.text.toString())
            editor.putString("name", it.name)
            editor.putString("phone", it.phone)
            editor.putString("address", it.address)
            editor.putString("password", it.password)
            editor.apply()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}