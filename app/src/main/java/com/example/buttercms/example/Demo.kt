package com.example.buttercms.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.buttercms.*
import com.example.buttercms.error.Callback
import com.example.buttercms.error.RestCallError
import com.example.buttercms.model.*

class Demo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Butter client
        val client = ButterCMS("3606556ecbd4134ea24b8936a829ab9edaddb583")

        Thread {
            // Authors
            val queryParameters = HashMap<String, String>()
            queryParameters["include"] = "recent_posts"

            client.data.getAuthor(
                "applifting-sample", queryParameters,
                callback = object :
                    Callback<Author, RestCallError> {
                    override fun success(response: Author) {
                        Log.w("success", response.toString())
                    }

                    override fun failure(error: RestCallError) {
                        Log.w(
                            "DemoError",
                            error.errorMessage.toString() + error.errorBody.toString()
                        )
                    }
                }
            )

//            client.data.getAuthors(
//                queryParameters,
//                callback = object :
//                    Callback<Authors, RestCallError> {
//                    override fun success(response: Authors) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

            // Categories
//            val queryParameters = HashMap<String, String>()
//            queryParameters["include"] = "recent_posts"
////
//            client.data.getCategory(
//                "example-category", queryParameters,
//                callback = object :
//                    Callback<Category, RestCallError> {
//                    override fun success(response: Category) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )
//
//            client.data.getCategories(
//                queryParameters,
//                callback = object :
//                    Callback<Categories, RestCallError> {
//                    override fun success(response: Categories) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

            // Collections
//            val queryParameters = HashMap<String, String>()
//            queryParameters["locale"] = "en"
//            client.data.getCollection(
//                "faq",
//                queryParameters,
//                DemoData::class.java,
//                callback = object :
//                    Callback<Collections, RestCallError> {
//                    override fun success(response: Collections) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

            // Page
//            val queryParameters = HashMap<String, String>()
//            queryParameters["locale"] = "en"
//            queryParameters["preview"] = "1"
//            client.data.getPage(
//                "homepage",
//                "homepage",
//                queryParameters,
//                DemoPageItem::class.java,
//                callback = object : Callback<Page, RestCallError> {
//                    override fun success(response: Page) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

//            val queryParameters = HashMap<String, String>()
//            queryParameters["locale"] = "en"
//            queryParameters["preview"] = "1"
//            client.data.getPages(
//                "homepage",
//                queryParameters,
//                DemoPageItem::class.java,
//                callback = object : Callback<Pages, RestCallError> {
//                    override fun success(response: Pages) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )

            // Post
//            val queryParameters = HashMap<String, String>()
//            queryParameters["locale"] = "en"
//            queryParameters["preview"] = "1"
//            client.data.getPost(
//                "example-2",
//                callback = object : Callback<Post, RestCallError> {
//                    override fun success(response: Post) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

//            client.data.getPosts(
//                queryParameters,
//                callback = object : Callback<Posts, RestCallError> {
//                    override fun success(response: Posts) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )

//            client.data.searchPosts(
//                "example",
//                queryParameters,
//                callback = object : Callback<Posts, RestCallError> {
//                    override fun success(response: Posts) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )
            // Tags
//            val queryParameters = HashMap<String, String>()
//            queryParameters["include"] = "recent_posts"
//            client.data.getTag(
//                "example-tag",
//                callback = object : Callback<Tag, RestCallError> {
//                    override fun success(response: Tag) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )

//            client.data.getTags(
//                queryParameters,
//                callback = object : Callback<Tags, RestCallError> {
//                    override fun success(response: Tags) {
//                        Log.w("success", response.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w(
//                            "DemoError",
//                            error.errorMessage.toString() + error.errorBody.toString()
//                        )
//                    }
//                }
//            )
        }.start()
    }
}
