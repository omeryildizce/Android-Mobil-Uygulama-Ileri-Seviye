package com.omeryildizce.cryptocrazy.service

import Crypto
import com.omeryildizce.cryptocrazy.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoAPI {
    //https://api.coingecko.com/api/v3/coins/list?include_platform=false
    @GET("coins/list?include_platform=false")
    suspend fun getCryptoList() : CryptoList

    // coins/{coin_id}?localization=false&tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false
    @GET("coins/{coin_id}?localization=false&tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCrypto(
        @Path("coin_id") coin_id : String
    ) : Crypto

}