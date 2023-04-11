package com.omeryildizce.myapplication.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.omeryildizce.myapplication.model.Country
import com.omeryildizce.myapplication.service.CountryAPIService
import com.omeryildizce.myapplication.service.CountryDatabase
import com.omeryildizce.myapplication.util.CustomSharedPrefeferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPrefeferences(getApplication())
    //              10 minute mm : ss :
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }

    }
    fun refreshFromApi(){
        getDataFromAPI()
    }
    private fun getDataFromSQLite() {
        countryLoading.value = true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Veriler Veritabanından geldi", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI() {
        countryLoading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {

                        stroreInSQLite(t)

                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false

                    }

                })
        )

    }

    private fun showCountries(t: List<Country>) {
        countries.value = t
        countryError.value = false
        countryLoading.value = false
    }

    private fun stroreInSQLite(list: List<Country>) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountries(list)
        }
        Toast.makeText(getApplication(), "Veriler İnternetten geldi", Toast.LENGTH_LONG).show()

        customPreferences.saveTime(System.nanoTime())


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}