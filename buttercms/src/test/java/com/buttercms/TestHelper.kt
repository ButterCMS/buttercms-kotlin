package com.buttercms

import com.buttercms.helper.CustomDateAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

object Helper {

    private val moshi = Moshi.Builder()
        .add(CustomDateAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Create an instance of the Retrofit class
     */
    internal fun generateRetrofit(mockWebServer: MockWebServer): Retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/")) // Dummy url for testing
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * Sets which response the [MockWebServer] should return when a request is made
     */
    fun MockWebServer.setResponse(fileName: String, responseCode: Int = 200) {
        enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getFileAsString(fileName))
        )
    }

    /**
     * The the file in the [filePath] and return its content as a [String]
     */
    private fun getFileAsString(filePath: String): String {
        val uri = ClassLoader.getSystemResource(filePath)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}

class TestInteractor(private val api: ButterCmsService) {
    fun getAuthor() =
        api.getAuthorResponse("applifting-sample", mapOf("include" to "recent_posts")).execute().body()

    fun getAuthors() = api.getAuthorsResponse(mapOf("include" to "recent_posts")).execute().body()

    fun getPost() = api.getPostResponse("example-2").execute().body()
    fun getPosts() =
        api.getPostsResponse(mapOf("page" to "1", "page_size" to "10")).execute().body()

    fun searchPosts() =
        api.searchPostsResponse("example", mapOf("include" to "recent_posts")).execute().body()

    fun getCategory() =
        api.getCategoryResponse("example-category", mapOf("include" to "recent_posts")).execute().body()

    fun getCategories() = api.getCategoriesResponse(mapOf("include" to "recent_posts")).execute().body()

    fun getTag() = api.getTagResponse("example-tag", mapOf("include" to "recent_posts")).execute().body()
    fun getTags() = api.getTagsResponse(mapOf("include" to "recent_posts")).execute().body()

    fun getPage() =
        api.getPageResponse("homepage", "homepage", mapOf("locale" to "en")).execute().body()

    fun getPages() = api.getPagesResponse("homepage", mapOf("locale" to "en")).execute().body()

    fun getCollections() = api.getCollectionsResponse("faq", mapOf("locale" to "en")).execute().body()
}
