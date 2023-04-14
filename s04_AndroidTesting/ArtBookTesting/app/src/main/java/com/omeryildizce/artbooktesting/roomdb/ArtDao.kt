package com.omeryildizce.artbooktesting.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omeryildizce.artbooktesting.model.Art

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)

    @Delete
    suspend fun deleteArt(art: Art)

    @Query("select * from arts")
    fun observeArts() : LiveData<List<Art>>
}