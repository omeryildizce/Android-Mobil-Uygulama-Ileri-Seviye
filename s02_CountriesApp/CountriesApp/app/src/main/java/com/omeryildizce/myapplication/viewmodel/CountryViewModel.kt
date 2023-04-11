package com.omeryildizce.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.omeryildizce.myapplication.model.Country
import com.omeryildizce.myapplication.service.CountryDatabase
import kotlinx.coroutines.launch


class CountryViewModel(application: Application) : BaseViewModel(application){
    val countryLiveData = MutableLiveData<Country>()

    fun getDataRoom(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }

    }


}