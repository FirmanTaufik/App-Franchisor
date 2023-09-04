package com.appfranchisor.app.retrofit

import com.appfranchisor.app.BuildConfig
import com.appfranchisor.app.helper.App
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hasNetwork
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient (  ) {

    private val TAG = "ApiClient"
    private var mRetrofit: Retrofit? = null
    private val app = App
    val context = app.getContext()
    val client: Retrofit
        get() {
            val cacheSize = (5 * 1024 * 1024).toLong()

            val myCache = context?.let { Cache(it.cacheDir, cacheSize) }
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    okHttp(myCache,logging)

                )
                .build()
            return mRetrofit!!
        }

    private fun okHttp(myCache: Cache?, logging: HttpLoggingInterceptor): OkHttpClient {
          val okHttpClient  = OkHttpClient.Builder()
            .cache(myCache)
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                if (hasNetwork(context!!)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else  request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()

                chain.proceed(request)
            })
            .addInterceptor(logging)

           /* if (username!=null && password!=null) {
                okHttpClient.addInterceptor(BasicAuthInterceptor(username!!, password!!))
            }
*/
            return okHttpClient.build()
    }

}