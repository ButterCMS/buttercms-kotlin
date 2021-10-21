package com.example.buttercms

import com.example.buttercms.model.*
import com.example.buttercms.model.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

class ButterCMS(apiToken: String) {

    val data: ButterCmsService = ButterCmsRepository().retrofitClient(apiToken)
        .create(ButterCmsService::class.java)
}

interface ButterCmsService {

    companion object {
        const val AUTHORS = "authors/"
        const val POSTS = "posts/"
        const val SEARCH = "search/"
        const val CATEGORIES = "categories/"
        const val TAGS = "tags/"
        const val COLLECTIONS = "content/"
        const val PAGES = "pages/"
    }

    // Authors
    @GET("$AUTHORS{author}")
    fun getAuthor(
        @Path("author") author: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Author>

    @GET(AUTHORS)
    fun getAuthors(@QueryMap queryParameters: Map<String, String>?): Call<Authors>

    // Categories
    @GET("$CATEGORIES{category}/")
    fun getCategory(
        @Path("category") category: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Category>

    @GET(CATEGORIES)
    fun getCategories(@QueryMap queryParameters: Map<String, String>?): Call<Categories>

    // Collections
    @GET("$COLLECTIONS{slug}/")
    fun getCollections(
        @Path("slug") slug: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Collections<Any>>

    // Pages
    @GET("$PAGES{page_type_slug}/{page_slug}/")
    fun getPage(
        @Path("page_type_slug") page_type: String,
        @Path("page_slug") page_slug: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Page>

    @GET("$PAGES{page_type}/")
    fun getPages(
        @Path("page_type") page_type: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Pages>

    // Posts
    @GET("$POSTS{slug}/")
    fun getPost(@Path("slug") slug: String): Call<Post>

    @GET(POSTS)
    fun getPosts(@QueryMap queryParameters: Map<String, String>?): Call<Posts>

    @GET(SEARCH)
    fun searchPosts(
        @Query("query") query: String,
        @QueryMap queryParameters: Map<String, String>?
    ): Call<Posts>

    // Tag
    @GET("$TAGS{tag}/")
    fun getTag(@Path("tag") tag: String): Call<TagResponse>

    @GET(TAGS)
    fun getTags(@QueryMap queryParameters: Map<String, String>?): Call<TagsResponse>
}
