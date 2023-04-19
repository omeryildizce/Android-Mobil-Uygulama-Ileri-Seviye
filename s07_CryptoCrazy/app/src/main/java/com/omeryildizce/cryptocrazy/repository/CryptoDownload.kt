package com.omeryildizce.cryptocrazy.repository

import com.omeryildizce.cryptocrazy.model.CryptoModel
import com.omeryildizce.cryptocrazy.util.Resource

interface CryptoDownload {
    suspend fun downloadCryptos() : Resource< List<CryptoModel>>
}