package com.buttercms

import com.buttercms.Helper.generateRetrofit
import com.buttercms.Helper.setResponse
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class Tests {
    var mockWebServer = MockWebServer()
    val client = ButterCMS("3606556ecbd4134ea24b8936a829ab9edaddb583")

    // create instance of retrofit using our fake mockserver
    val api: ButterCmsService by lazy {
        generateRetrofit(mockWebServer).create(
            ButterCmsService::class.java
        )
    }

    @Before
    fun setUp() {
        mockWebServer.start(8080)
    }

    // compare mock response with real butter response
    @Test
    fun fetchAuthorAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("author_response.json")
        val mockResponse = interactor.getAuthor()

        val authorName = "applifting-sample"
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val actualResponse =
            client.data.getAuthorResponse(
                authorName,
                queryParam
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Check that response is success
    @Test
    fun fetchAuthorAndCheckSuccessCode() {
        val authorName = "applifting-sample"
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val response =
            client.data.getAuthorResponse(
                authorName,
                queryParam
            ).execute()

        assertEquals(200, response.code())
    }

    @Test
    fun fetchAuthorAndCheckFailedCode() {
        val authorName = "applifting"
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val response = client.data.getAuthorResponse(
            authorName,
            queryParam
        ).execute()

        assertEquals(404, response.code())
    }

    @Test
    fun fetchAuthorsAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("authors_response.json")
        val mockResponse = interactor.getAuthors()

        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val actualResponse =
            client.data.getAuthorsResponse(
                queryParam
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Category
    @Test
    fun fetchCategoryAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("category_response.json")
        val mockResponse = interactor.getCategory()
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val actualResponse =
            client.data.getCategoryResponse(
                "example-category", queryParam
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @Test
    fun fetchCategoriesAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("categories_response.json")
        val mockResponse = interactor.getCategories()
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val actualResponse =
            client.data.getCategoriesResponse(
                queryParam
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Collections
    @Test
    fun fetchCollectionAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("collections_response.json")
        val mockResponse = interactor.getCollections()

        val actualResponse =
            client.data.getCollectionsResponse(
                "faq", mapOf("locale" to "en")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Page
    @Test
    fun fetchPageAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("page_response.json")
        val mockResponse = interactor.getPage()

        val actualResponse =
            client.data.getPageResponse(
                "homepage", "homepage", mapOf("locale" to "en")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @Test
    fun fetchPagesAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("pages_response.json")
        val mockResponse = interactor.getPages()

        val actualResponse =
            client.data.getPagesResponse(
                "homepage", mapOf("locale" to "en")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Post
    @Test
    fun fetchPostAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("post_response.json")
        val mockResponse = interactor.getPost()

        val actualResponse =
            client.data.getPostResponse(
                "example-2"
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @Test
    fun fetchPostsAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("posts_response.json")
        val mockResponse = interactor.getPosts()

        val actualResponse =
            client.data.getPostsResponse(
                mapOf("page" to "1", "page_size" to "10")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @Test
    fun searchPostAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("post_search_response.json")
        val mockResponse = interactor.searchPosts()

        val actualResponse =
            client.data.searchPostsResponse(
                "example", mapOf("include" to "recent_post")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    // Tag
    @Test
    fun fetchTagAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("tag_response.json")
        val mockResponse = interactor.getTag()

        val actualResponse =
            client.data.getTagResponse(
                "example-tag", mapOf("include" to "recent_posts")
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @Test
    fun fetchTagsAndCheckResponse() {
        // set mock response
        val interactor = TestInteractor(api)
        mockWebServer.setResponse("tags_response.json")
        val mockResponse = interactor.getTags()
        val queryParam = HashMap<String, String>()
        queryParam["include"] = "recent_posts"

        val actualResponse =
            client.data.getTagsResponse(
                queryParam
            ).execute()
        assertEquals(mockResponse, actualResponse.body())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
