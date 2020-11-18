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
import com.bobabelga.theshoestore.ui.ShoeViewModel


class ShoeListingFragment : Fragment() {
    lateinit var shoeViewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoeViewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)
        val shoeList = ShoeListingFragmentArgs.fromBundle(requireArguments()).shoe
        if (shoeList != null) shoeViewModel.shoeListLiveData.value = shoeList.shoeArrayList

        val binding: FragmentShoeListingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)
        binding.addBtn.setOnClickListener { view: View ->

            view.findNavController()
                .navigate(
                    ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment(
                        shoeList
                    )
                )
        }

        shoeViewModel.shoeListLiveData.observe(viewLifecycleOwner, { shoeList ->
            for (i in 0 until shoeList.size) {
                val to_add: View = inflater.inflate(
                    R.layout.shoe_element_details,
                    binding.linearLayout, false
                )

                val name = to_add.findViewById<View>(R.id.nameTxtValue) as TextView
                name.setText(shoeList.get(i).name)
                val company = to_add.findViewById<View>(R.id.companyTxtValue) as TextView
                company.setText(shoeList.get(i).company)
                val size = to_add.findViewById<View>(R.id.sizeTxtValue) as TextView
                size.setText(shoeList.get(i).size.toString())
                val desc = to_add.findViewById<View>(R.id.descTxtValue) as TextView
                desc.setText(shoeList.get(i).description)

                binding.linearLayout.addView(to_add)
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