package com.example.buttercms

import com.example.buttercms.ButterCmsService.Companion.BASE_URL
import com.example.buttercms.model.*
import retrofit2.Call
import retrofit2.http.*

class ApiCallService(apiToken: String) {

    val data: ButterCmsService = ButterCmsRepository().retrofitClient(BASE_URL, apiToken)
        .create(ButterCmsService::class.java)
}

interface ButterCmsService {

    companion object {
        const val BASE_URL = "https://api.buttercms.com/v2/"
        const val AUTHORS = "authors/"
        const val POSTS = "posts/"
        const val SEARCH = "search/"
        const val CATEGORIES = "categories/"
        const val TAGS = "tags"
        const val COLLECTIONS = "content/"
        const val PAGES = "pages/"
    }

    // Authors
    @GET(BASE_URL + AUTHORS)
    fun getAuthor(
        @QueryName authorName: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Authors?>

    @GET(BASE_URL + AUTHORS)
    fun getAuthors(@QueryMap queryParameters: Map<String, String>?): Call<Authors?>

    // Posts
    @GET(BASE_URL + POSTS)
    fun getPost(@Query("slug") slug: String): Call<Post?>

    @GET(BASE_URL + POSTS)
    fun getPosts(@QueryMap queryParameters: Map<String, String>?): Call<Post?>

    @GET(BASE_URL + SEARCH)
    fun searchPosts(@Query("query") query: String): Call<Post?>

    // Categories
    @GET(BASE_URL + CATEGORIES)
    fun getCategory(@QueryName category: String): Call<Categories>

    @GET(BASE_URL + CATEGORIES)
    fun getCategories(@QueryMap queryParameters: Map<String, String>?): Call<Categories>

    // Tag
    @GET(BASE_URL + TAGS)
    fun getTag(@QueryName tag: String): Call<Tags>

    @GET(BASE_URL + TAGS)
    fun getTags(@QueryMap queryParameters: Map<String, String>?): Call<Tags>

    // Pages
    @GET("$BASE_URL$PAGES{page_type}/{page_slug}")
    fun getPage(
        @Path("page_type") page_type: String,
        @Path("page_slug") page_slug: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<SinglePage>

    @GET("$BASE_URL$PAGES{page_type}/")
    fun getPages(
        @Path("page_type") page_type: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Pages>

    // Collections
    @GET("$BASE_URL$COLLECTIONS")
    fun getCollections(
        @Query("keys") keys: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Collections>
}
