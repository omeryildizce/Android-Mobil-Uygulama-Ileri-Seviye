package com.omeryildizce.artbooktesting.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omeryildizce.artbooktesting.R
import com.omeryildizce.artbooktesting.api.RetrofitApi
import com.omeryildizce.artbooktesting.repo.ArtRepository
import com.omeryildizce.artbooktesting.repo.ArtRepositoryInterface
import com.omeryildizce.artbooktesting.roomdb.ArtDao
import com.omeryildizce.artbooktesting.roomdb.ArtDatabase
import com.omeryildizce.artbooktesting.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ArtDatabase::class.java, "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi() : RetrofitApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }

/*
    @Singleton
    @Provides
    fun provideArtRepository(
        artDao: ArtDao,
        retrofitApi: RetrofitApi
    ): ArtRepositoryInterface {
        return ArtRepository(artDao, retrofitApi)
    }

 */
    @Singleton
    @Provides
    fun injectNormalRepo(dao: ArtDao, api:RetrofitApi) = ArtRepository(dao, api) as ArtRepositoryInterface
    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}