package com.bobabelga.theshoestore.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bobabelga.theshoestore.R
import com.bobabelga.theshoestore.databinding.FragmentShoeListingBinding
import com.bobabelga.theshoestore.databinding.ShoeElementDetailsBinding
import com.bobabelga.theshoestore.ui.ShoeViewModel
import kotlinx.android.synthetic.main.shoe_element_details.*


class ShoeListingFragment : Fragment() {
    lateinit var shoeViewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoeViewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        val binding: FragmentShoeListingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)
        binding.addBtn.setOnClickListener { view: View ->

            view.findNavController()
                .navigate(
                    ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment()
                )
        }

        shoeViewModel.shoeListLiveData.observe(viewLifecycleOwner, { shoeList ->
            for (i in 0 until shoeList.size) {

                val to_add: ShoeElementDetailsBinding = DataBindingUtil.inflate(inflater,
                    R.layout.shoe_element_details,
                    binding.linearLayout, false
                )
                to_add.shoe = shoeList.get(i)
                binding.linearLayout.addView(to_add.root)
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val pref: SharedPreferences? = context?.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
        editor?.commit()
        findNavController().navigate(R.id.action_shoeListingFragment_to_loginFragment)
        return super.onOptionsItemSelected(item)
    }
}