package com.omeryildizce.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPrefeferences {
    companion object {
        private val PREFERENCES_TIME = "prefernces_time"
        private var sharedPrefeferences: SharedPreferences? = null
        @Volatile
        private var instance: CustomSharedPrefeferences? = null

        private val lock = Any()
        operator fun invoke(context: Context): CustomSharedPrefeferences = instance ?: synchronized(
            lock
        ) {
            instance ?: makeCustomSharedPrefeferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPrefeferences(context: Context): CustomSharedPrefeferences {
            sharedPrefeferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPrefeferences()
        }
    }

    fun saveTime(time:Long){
        sharedPrefeferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime() = sharedPrefeferences?.getLong(PREFERENCES_TIME, 0)
}