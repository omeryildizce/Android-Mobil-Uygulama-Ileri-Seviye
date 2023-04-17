package com.omeryildizce.cryptocrazy.viewmodel

import Crypto
import androidx.lifecycle.ViewModel
import com.omeryildizce.cryptocrazy.repository.CryptoRepository
import com.omeryildizce.cryptocrazy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {

    suspend fun getCrypto(id: String): Resource<Crypto> {
        return repository.getCrypto(id)
    }
}