package com.example.nigeriancuisine

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        val okkHttpclient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(
                "\n" +
                        "https://worldsrecipe-4a453.firebaseio.com"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

    @Singleton
    @Provides
    fun provideFoodService(retrofit: Retrofit.Builder): ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun buildDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            FoodDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideLogDao(database: FoodDatabase): FoodDao {
        return database.getFoodDao()
    }

    @Singleton
    @Provides
    fun provideRepository(
        foodDao: FoodDao,
        retrofit: ApiService
    ): FoodRepository {
        return FoodRepository(retrofit,foodDao)
    }
}