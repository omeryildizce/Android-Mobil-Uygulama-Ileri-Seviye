package com.omeryildizce.cryptocrazy.service

 import com.omeryildizce.cryptocrazy.model.CryptoModel
 import retrofit2.Response
 import retrofit2.http.GET

interface CryptoApi {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData() : Response<List<CryptoModel>>
}