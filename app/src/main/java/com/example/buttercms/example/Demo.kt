package com.example.buttercms.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buttercms.ApiCallService

interface GetParameters {
    fun getParameter(): Map<String, String>
}

enum class AuthorParameters : GetParameters {
    INCLUDE {
        override fun getParameter(): Map<String, String> {
            return mapOf(Pair("include", "recent_post"))
        }
    }
}

enum class CategoryParameters : GetParameters {
    INCLUDE {
        override fun getParameter(): Map<String, String> {
            return mapOf(Pair("include", "recent_post"))
        }
    }
}

enum class TagParameters : GetParameters {
    INCLUDE {
        override fun getParameter(): Map<String, String> {
            return mapOf(Pair("include", "recent_post"))
        }
    }
}

class Demo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = ApiCallService("3606556ecbd4134ea24b8936a829ab9edaddb583")

        // Post
        val parameters: MutableMap<String, String> = HashMap()
        parameters["preview"] = "1"
        parameters["page"] = "1"
        parameters["page_size"] = "10"
        parameters["exclude_body"] = "false"
        parameters["author_slug"] = "test-api"
        parameters["category_slug"] = "test-category"
        parameters["tag_slug"] = "test-tag"

        // SinglePage
        val singlePageParameters: MutableMap<String, String> = HashMap()
        singlePageParameters["locale"] = "en"
        singlePageParameters["preview"] = "1"
        singlePageParameters["levels"] = "2"

        // Pages
        val pageParameters: MutableMap<String, String> = HashMap()
        pageParameters["preview"] = "1"
        pageParameters["page"] = "1"
        pageParameters["page_size"] = "10"
        pageParameters["locale"] = "en"
        pageParameters["levels"] = "2"

        // Collections
        val collectionParameters: MutableMap<String, String> = HashMap()
        collectionParameters["locale"] = "en"
        collectionParameters["test"] = "1"
        collectionParameters["fields.genre"] = "asc"
        collectionParameters["order"] = "name"
        collectionParameters["page"] = "1"
        collectionParameters["page_size"] = "1"
        collectionParameters["locale"] = "en"
        collectionParameters["levels"] = "2"

        Thread {
            try {
                // Post
//                val l = client.data.getPost("example-2").execute()
//                val l = client.data.getPosts(parameters).execute()
//                val l = client.data.searchPosts("example").execute()

                // Authors
//                val l = client.data.getAuthor("Applifting", AuthorParameters.INCLUDE.getParameter()).execute()
//                val l = client.data.getAuthors(HashMap()).execute()
                // send empty Hashmap as QueryMap does not take null

                // Categories
//                val l = client.data.getCategory("example-category").execute()
//                val l = client.data.getCategories(CategoryParameters.INCLUDE.getParameter()).execute()

                // Tags
//                val l = client.data.getTag("example-tag").execute()
//                val l = client.data.getTags(TagParameters.INCLUDE.getParameter()).execute()

                // Page
//                val l = client.data.getPage("homepage", "homepage", singlePageParameters).execute()
//                val l = client.data.getPages("homepage", pageParameters).execute()

                // collections
                val l = client.data.getCollections("faq", collectionParameters).execute()
                println(l)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()
    }
}
