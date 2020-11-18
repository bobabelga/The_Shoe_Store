package com.bobabelga.theshoestore.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bobabelga.theshoestore.R
import com.bobabelga.theshoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val sharedPref: SharedPreferences? = context?.getSharedPreferences("Login", MODE_PRIVATE)
        if (sharedPref!!.getBoolean("Login", false)) {
            findNavController().navigate(R.id.action_loginFragment_to_shoeListingFragment)
        }


        binding.loginBtn.setOnClickListener { view: View ->
            val editor = sharedPref.edit()
            editor?.putBoolean("Login", true)
            editor?.apply()
            view.findNavController()
                .navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                )
        }
        binding.registerBtn.setOnClickListener { view: View ->
            val editor = sharedPref.edit()
            editor?.putBoolean("Login", true)
            editor?.apply()
            view.findNavController()
                .navigate(
                    LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
                )
        }

        return binding.root
    }

}