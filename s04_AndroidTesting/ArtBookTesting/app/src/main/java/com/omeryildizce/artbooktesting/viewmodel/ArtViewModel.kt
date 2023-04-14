package com.omeryildizce.artbooktesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omeryildizce.artbooktesting.api.ImageResponse
import com.omeryildizce.artbooktesting.model.Art
import com.omeryildizce.artbooktesting.repo.ArtRepositoryInterface
import com.omeryildizce.artbooktesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository : ArtRepositoryInterface
): ViewModel() {
    // Art fragment
    val artList = repository.getArt()

    // Image Api Fragment
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList: LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl:LiveData<String>
        get() = selectedImage

    // Art details
    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage :LiveData<Resource<Art>>
        get() = insertArtMsg

    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url:String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art:Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art:Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun makeArt(name:String, artistName:String,year: String){
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter Name, Artist Name and Year",null))
            return
        }
        val yearInt = try {
            year.toInt()
        }catch (e:Exception){
            insertArtMsg.postValue(Resource.error("Year should be number", null))
            return
        }
        val art = Art(name,artistName , yearInt, selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchforImage(searchString:String){
        if (searchString.isEmpty()){
            return
        }else{
            images.value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.searchImage(searchString)
                images.value = response
            }
        }
    }
}