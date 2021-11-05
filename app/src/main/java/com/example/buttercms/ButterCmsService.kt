package com.example.buttercms

import com.example.buttercms.error.Callback
import com.example.buttercms.error.RestCallError
import com.example.buttercms.error.enqueueCall
import com.example.buttercms.model.*
import com.example.buttercms.model.Collections
import com.example.buttercms.model.Page
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
    @GET("$AUTHORS{author}")
    fun getAuthor(
        @Path("author") author: String,
        @QueryMap queryParameters: Map<String, String>? = emptyMap()
    ): Call<Author>

    @GET(AUTHORS)
    fun getAuthors(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Authors>

    // Categories
    @GET("$CATEGORIES{category}/")
    fun getCategory(
        @Path("category") category: String,
        @QueryMap queryParameters: Map<String, String>? = emptyMap()
    ): Call<Category>

    @GET(CATEGORIES)
    fun getCategories(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Categories>

    // Collections
    @GET("$COLLECTIONS{slug}/")
    fun getCollections(
        @Path("slug") slug: String,
        @QueryMap(encoded = true) queryParameters: Map<String, String>? = emptyMap()
    ): Call<Collections>

    // Pages
    @GET("$PAGES{page_type_slug}/{page_slug}/")
    fun getPage(
        @Path("page_type_slug") page_type: String,
        @Path("page_slug") page_slug: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Page>

    @GET("$PAGES{page_type_slug}/")
    fun getPages(
        @Path("page_type_slug") page_type: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Pages>

    // Posts
    @GET("$POSTS{slug}/")
    fun getPost(@Path("slug") slug: String): Call<Post>

    @GET(POSTS)
    fun getPosts(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Posts>

    @GET(SEARCH)
    fun searchPosts(
        @Query("query") query: String,
        @QueryMap queryParameters: Map<String, String>
    ): Call<Posts>

    // Tag
    @GET("$TAGS{tag}/")
    fun getTag(@Path("tag") tag: String): Call<Tag>

    @GET(TAGS)
    fun getTags(@QueryMap queryParameters: Map<String, String>? = emptyMap()): Call<Tags>
}

@Throws(RestCallError::class)
fun ButterCmsService.getAuthor(
    author: String,
    queryParameters: Map<String, String>,
    callback: Callback<Author, RestCallError>
) {
    val call = getAuthor(author, queryParameters)
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
    val call = getAuthors(queryParameters)
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
    category: String,
    queryParameters: Map<String, String>,
    callback: Callback<Category, RestCallError>
) {
    val call = getCategory(category, queryParameters)
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
    val call = getCategories(queryParameters)
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
    val call = getCollections(slug, queryParameters)
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
    page_type_slug: String,
    page_slug: String,
    queryParameters: Map<String, String>,
    classType: Class<T>,
    callback: Callback<Page, RestCallError>
) {
    val call = getPage(page_type_slug, page_slug, queryParameters)
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
    slug: String,
    queryParameters: Map<String, String>,
    classType: Class<T>,
    callback: Callback<Pages, RestCallError>
) {
    val call =
        getPages(slug, queryParameters)

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
    val call = getPost(slug)
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
    val call = getPosts(queryParameters)
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
    val call = searchPosts(query, queryParameters)
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
    query: String,
    callback: Callback<Tag, RestCallError>
) {
    val call = getTag(query)
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
    val call = getTags(queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }

        onFailure = {
            callback.failure(it)
        }
    }
}
