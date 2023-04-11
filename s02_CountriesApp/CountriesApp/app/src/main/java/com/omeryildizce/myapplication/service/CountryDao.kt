package com.omeryildizce.myapplication.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.omeryildizce.myapplication.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>
    // suspend -> coroutinei pause & resume

    @Query("select * from country")
    suspend fun getAllCountries() : List<Country>

    @Query("select * from country where uuid = :countryId")
    suspend fun getCountry(countryId :Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}