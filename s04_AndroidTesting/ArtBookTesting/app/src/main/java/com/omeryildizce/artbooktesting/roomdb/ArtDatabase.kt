package com.omeryildizce.artbooktesting.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omeryildizce.artbooktesting.model.Art

@Database(entities =  arrayOf(Art::class)   , version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao(): ArtDao
}