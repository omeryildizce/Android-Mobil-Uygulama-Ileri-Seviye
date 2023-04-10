package com.omeryildizce.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omeryildizce.myapplication.model.Country

class CountryViewModel : ViewModel(){
    val countryLiveData = MutableLiveData<Country>()

    fun getDataRoom(){
        val country = Country("Turkey", "Ankara", "Asia", "TR", "Turkish","")
        countryLiveData.value = country
    }
}