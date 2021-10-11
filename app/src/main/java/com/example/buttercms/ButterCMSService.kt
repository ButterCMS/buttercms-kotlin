package com.example.buttercms

import com.example.buttercms.ButterCmsService.Companion.BASE_URL
import com.example.buttercms.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class ApiCallService(apiToken: String) {

    val data = ButterCmsRepository().retrofitClient(BASE_URL, apiToken)
        .create(ButterCmsService::class.java)
}

interface ButterCmsService {

    companion object {
        const val BASE_URL = "https://api.buttercms.com/v2/"
        const val POSTS = "posts/"
    }

    @GET(BASE_URL + POSTS)
    fun getPost(@Query("slug") slug: String): Call<Post?>
}
