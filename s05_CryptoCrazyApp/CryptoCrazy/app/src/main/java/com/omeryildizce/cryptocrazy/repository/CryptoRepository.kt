package com.omeryildizce.cryptocrazy.repository

import Crypto
import com.omeryildizce.cryptocrazy.model.CryptoList
import com.omeryildizce.cryptocrazy.service.CryptoAPI
import com.omeryildizce.cryptocrazy.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
) {
    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            api.getCryptoList()
        }catch (e :Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(response)

    }

    suspend fun getCrypto(id:String) : Resource<Crypto>{
        val response = try {
            api.getCrypto(id)
        }catch (e:Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}