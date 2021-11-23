package com.buttercms

import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.error.enqueueCall
import com.buttercms.model.*
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import java.io.IOException

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
    @GET("$AUTHORS{slug}")
    fun getAuthorResponse(
        @Path("slug") slug: String,
        @QueryMap queryParameters: Map<String, String>? = emptyMap()
    ): Call<Author>

    @GET(AUTHORS)
    fun getAuthorsResponse(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Authors>

    // Categories
    @GET("$CATEGORIES{slug}/")
    fun getCategoryResponse(
        @Path("slug") slug: String,
        @QueryMap queryParameters: Map<String, String>? = emptyMap()
    ): Call<Category>

    @GET(CATEGORIES)
    fun getCategoriesResponse(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Categories>

    // Collections
    @GET("$COLLECTIONS{slug}/")
    fun getCollectionsResponse(
        @Path("slug") slug: String,
        @QueryMap(encoded = true) queryParameters: Map<String, String>? = emptyMap()
    ): Call<Collections>

    // Pages
    @GET("$PAGES{page_type}/{page_slug}/")
    fun getPageResponse(
        @Path("page_type") page_type: String,
        @Path("page_slug") page_slug: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Page>

    @GET("$PAGES{page_type}/")
    fun getPagesResponse(
        @Path("page_type") page_type: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Pages>

    // Posts
    @GET("$POSTS{slug}/")
    fun getPostResponse(@Path("slug") slug: String): Call<Post>

    @GET(POSTS)
    fun getPostsResponse(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Posts>

    @GET(SEARCH)
    fun searchPostsResponse(
        @Query("query") query: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Posts>

    // Tag
    @GET("$TAGS{slug}/")
    fun getTagResponse(
        @Path("slug") slug: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Tag>

    @GET(TAGS)
    fun getTagsResponse(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Tags>
}

@Throws(RestCallError::class)
fun ButterCmsService.getAuthor(
    slug: String,
    queryParameters: Map<String, String>,
    callback: Callback<Author, RestCallError>
) {
    val call = getAuthorResponse(slug, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getAuthors(
    queryParameters: Map<String, String>,
    callback: Callback<Authors, RestCallError>
) {
    val call = getAuthorsResponse(queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getCategory(
    slug: String,
    queryParameters: Map<String, String>,
    callback: Callback<Category, RestCallError>
) {
    val call = getCategoryResponse(slug, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getCategories(
    queryParameters: Map<String, String>,
    callback: Callback<Categories, RestCallError>
) {
    val call = getCategoriesResponse(queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

fun <T> ButterCmsService.getCollection(
    slug: String,
    queryParameters: Map<String, String>,
    myCollection: Class<T>,
    callback: Callback<Collections, RestCallError>
) {
    val call = getCollectionsResponse(slug, queryParameters)
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter<Any>(Collections::class.java)
    call.enqueueCall {
        onSuccess = { body ->
            val jsonElement = adapter.toJson(body)
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val root: JsonNode = mapper.readTree(jsonElement)
            val collection = Collections(
                meta = mapper.convertValue(root.get("meta"), Meta::class.java),
                data = mapper.convertValue(
                    root.get("data"),
                    mapper.typeFactory.constructType(myCollection)
                )
            )
            callback.success(collection)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun <T> ButterCmsService.getPage(
    pageType: String,
    pageSlug: String,
    queryParameters: Map<String, String>,
    classType: Class<T>,
    callback: Callback<Page, RestCallError>
) {
    val call = getPageResponse(pageType, pageSlug, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val page = Page(
                mapper.convertValue(
                    body.data,
                    mapper.typeFactory.constructType(classType)
                )
            )
            callback.success(page)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(IOException::class)
fun <T> ButterCmsService.getPages(
    pageType: String,
    queryParameters: Map<String, String>,
    classType: Class<T>,
    callback: Callback<Pages, RestCallError>
) {
    val call =
        getPagesResponse(pageType, queryParameters)

    call.enqueueCall {
        onSuccess = { body ->
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val page = Pages(
                mapper.convertValue(body.meta, Meta::class.java),
                mapper.convertValue(
                    body.data,
                    mapper.typeFactory.constructParametricType(List::class.java, classType)
                )
            )
            callback.success(page)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getPost(
    slug: String,
    callback: Callback<Post, RestCallError>
) {
    val call = getPostResponse(slug)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getPosts(
    queryParameters: Map<String, String>,
    callback: Callback<Posts, RestCallError>
) {
    val call = getPostsResponse(queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.searchPosts(
    query: String,
    queryParameters: Map<String, String>,
    callback: Callback<Posts, RestCallError>
) {
    val call = searchPostsResponse(query, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getTag(
    slug: String,
    queryParameters: Map<String, String>,
    callback: Callback<Tag, RestCallError>
) {
    val call = getTagResponse(slug, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}

@Throws(RestCallError::class)
fun ButterCmsService.getTags(
    queryParameters: Map<String, String>,
    callback: Callback<Tags, RestCallError>
) {
    val call = getTagsResponse(queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}
