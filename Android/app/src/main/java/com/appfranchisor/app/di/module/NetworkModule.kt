package com.appfranchisor.app.di.module

import com.appfranchisor.app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.appfranchisor.app.api.ApiService
import com.appfranchisor.app.helper.App
import com.appfranchisor.app.helper.PreferenceHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent ::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val BASE_URL_API ="${App.getContext()?.resources?.getString(R.string.base_url)}/api/"

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .client(OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                val request = if (PreferenceHelper.getToken(App.getContext()!!)==null) {
                    chain.request().newBuilder()
                        .build()
                } else {
                    val token = PreferenceHelper.getToken(App.getContext()!!)
                    chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                }
                chain.proceed(request)
            }.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun providesApiService( retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java);
    }
}