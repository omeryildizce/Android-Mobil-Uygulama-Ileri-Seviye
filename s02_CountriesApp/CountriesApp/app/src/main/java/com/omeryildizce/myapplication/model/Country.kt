package com.omeryildizce.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo("name")
    @SerializedName("name") val name: String,

    @ColumnInfo("capital")
    @SerializedName("capital") val capital: String,

    @ColumnInfo("region")
    @SerializedName("region") val region: String,

    @ColumnInfo("currency")
    @SerializedName("currency") val currency: String,

    @ColumnInfo("language")
    @SerializedName("language") val language: String,

    @ColumnInfo("flag")
    @SerializedName("flag") val imageUrl: String

){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

