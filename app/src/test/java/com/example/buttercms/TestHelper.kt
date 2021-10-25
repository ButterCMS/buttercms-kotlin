package com.example.buttercms

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.*

object Helper {

    private val moshi = Moshi.Builder()
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
        api.getAuthor("applifting-sample", "include=recent_posts").execute().body()

    fun getAuthors() = api.getAuthors("include=recent_posts").execute().body()

    fun getPost() = api.getPost("example-2").execute().body()
    fun getPosts() =
        api.getPosts(mapOf("page" to "1", "page_size" to "10")).execute().body()

    fun searchPosts() =
        api.searchPosts("example", mapOf("include" to "recent_posts")).execute().body()

    fun getCategory() = api.getCategory("example-category", "include=recent_posts").execute().body()
    fun getCategories() = api.getCategories("include=recent_posts").execute().body()

    fun getTag() = api.getTag("example-tag").execute().body()
    fun getTags() = api.getTags("include=recent_posts").execute().body()

    fun getPage() =
        api.getPage("homepage", "homepage", mapOf("locale" to "en")).execute().body()

    fun getPages() = api.getPages("homepage", mapOf("locale" to "en")).execute().body()

    fun getCollections() = api.getCollections("faq", mapOf("locale" to "en")).execute().body()
}
