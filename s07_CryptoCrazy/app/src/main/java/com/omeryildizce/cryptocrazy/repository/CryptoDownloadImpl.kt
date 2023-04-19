package com.omeryildizce.cryptocrazy.repository

import com.omeryildizce.cryptocrazy.model.CryptoModel
import com.omeryildizce.cryptocrazy.service.CryptoApi
import com.omeryildizce.cryptocrazy.util.Resource
import java.lang.Exception

class CryptoDownloadImpl(private val api: CryptoApi) : CryptoDownload {
    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {
        return try {
            val response = api.getData()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error" , null)
            }else{
                Resource.error("Error", null)
            }
        }catch (e :Exception){
            Resource.error("No Data!", null)
        }
    }
}