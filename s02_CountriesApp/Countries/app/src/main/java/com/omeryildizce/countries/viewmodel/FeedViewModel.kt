package com.omeryildizce.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omeryildizce.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Ankara","Asia","TR","Turkish","")
        val country1 = Country("Usa","Ankara","Asia","TR","Turkish","")
        val country2 = Country("UK","Ankara","Asia","TR","Turkish","")
        val country3 = Country("Germany","Ankara","Asia","TR","Turkish","")

        val countryList = arrayListOf<Country>(country,country1,country2,country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}