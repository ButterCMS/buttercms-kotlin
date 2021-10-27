package com.example.buttercms

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ButterCmsRepository {

    private val baseUrl = "https://api.buttercms.com/v2/"
    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun okhttpClient(apiToken: String): OkHttpClient.Builder {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url =
                originalHttpUrl.newBuilder().addQueryParameter("auth_token", apiToken)
                    .build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }
        return okHttpBuilder
    }

    private val moshi = Moshi.Builder()
        .add(CustomDateAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    fun retrofitClient(apiToken: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okhttpClient(apiToken).addInterceptor(interceptor).build())
        .build()
}
