package com.omeryildizce.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.omeryildizce.artbooktesting.api.ImageResponse
import com.omeryildizce.artbooktesting.model.Art
import com.omeryildizce.artbooktesting.util.Resource

interface ArtRepositoryInterface {
    suspend fun insertArt(art: Art)
    suspend fun deleteArt(art:Art)
    fun getArt() : LiveData<List<Art>>
    suspend fun searchImage(imageString:String) : Resource<ImageResponse>
}