package com.bobabelga.theshoestore.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bobabelga.theshoestore.models.Shoe

class ShoeViewModel : ViewModel() {
     val shoeListLiveData = MutableLiveData<ArrayList<Shoe>>()
     var shoeList = ArrayList<Shoe>()

     fun addshoe (shoe:Shoe){
          shoeList.add(shoe)
          shoeListLiveData.value = shoeList
     }
}