package com.bobabelga.theshoestore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bobabelga.theshoestore.R
import com.bobabelga.theshoestore.databinding.FragmentShoeDetailBinding
import com.bobabelga.theshoestore.models.SheoList
import com.bobabelga.theshoestore.models.Shoe


class ShoeDetailFragment : Fragment() {
    lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        var shoe = ShoeDetailFragmentArgs.fromBundle(requireArguments()).shoe
        var newShoe = Shoe("", 0.0, "", "")
        binding.shoe = newShoe
        binding.sizeEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) binding.sizeEditText.text.clear()
        }
        binding.saveBtn.setOnClickListener { view: View ->
            if (binding.nameEditText.text.toString().isEmpty())
                Toast.makeText(context, "Name is empty", Toast.LENGTH_SHORT).show()
            else if (binding.companyEditText.text.toString().isEmpty())
                Toast.makeText(context, "Company is empty", Toast.LENGTH_SHORT).show()
            else if (binding.sizeEditText.text.toString().isEmpty())
                Toast.makeText(context, "Size is empty", Toast.LENGTH_SHORT).show()
            else if (binding.descEditText.text.toString().isEmpty())
                Toast.makeText(context, "Descriptin is empty", Toast.LENGTH_SHORT).show()
            else {
                newShoe = binding.shoe as Shoe
                if (shoe != null) {
                    shoe!!.shoeArrayList.add(newShoe)
                } else {
                    shoe = SheoList(ArrayList())
                    shoe!!.shoeArrayList.add(newShoe)
                }
                view.findNavController()
                    .navigate(
                        ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment(
                            shoe
                        )
                    )
            }

        }
        binding.cancelBtn.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment(shoe)
                )
        }
        return binding.root
    }
}