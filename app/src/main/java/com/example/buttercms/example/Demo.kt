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
            val authorParameters = HashMap<String, String>()
            authorParameters["include"] = "recent_posts"

            client.data.getAuthor(
                "applifting-sample", authorParameters,
                callback = object :
                    Callback<Author, RestCallError> {
                    override fun success(t: Author) {
                        Log.w("success", t.toString())
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
//                authorParameters,
//                callback = object :
//                    Callback<Authors, RestCallError> {
//                    override fun success(t: Authors) {
//                        Log.w("success", t.toString())
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
//            val categoryParameters = HashMap<String, String>()
//            categoryParameters["include"] = "recent_posts"
//
//            client.data.getCategory(
//                "example-category", categoryParameters,
//                callback = object :
//                    Callback<Category, RestCallError> {
//                    override fun success(t: Category) {
//                        Log.w("success", t.toString())
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
//                categoryParameters,
//                callback = object :
//                    Callback<Categories, RestCallError> {
//                    override fun success(t: Categories) {
//                        Log.w("success", t.toString())
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
//            val collectionParameters = HashMap<String, String>()
//            collectionParameters["locale"] = "en"
//            client.data.getCollection(
//                "faq",
//                collectionParameters,
//                DemoData::class.java,
//                callback = object :
//                    Callback<Collections, RestCallError> {
//                    override fun success(t: Collections) {
//                        Log.w("success", t.toString())
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
//            val pageParameters = HashMap<String, String>()
//            pageParameters["locale"] = "en"
//            pageParameters["preview"] = "1"
//            client.data.getPage(
//                "homepage",
//                "homepage",
//                pageParameters,
//                DemoPageItem::class.java,
//                callback = object : Callback<Page, RestCallError> {
//                    override fun success(t: Page) {
//                        Log.w("success", t.toString())
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

//            val pagesParameters = HashMap<String, String>()
//            pagesParameters["locale"] = "en"
//            pagesParameters["preview"] = "1"
//            client.data.getPages(
//                "homepage",
//                pagesParameters,
//                DemoPageItem::class.java,
//                callback = object : Callback<Pages, RestCallError> {
//                    override fun success(t: Pages) {
//                        Log.w("success", t.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )

            // Post
//            val postParameters = HashMap<String, String>()
//            postParameters["locale"] = "en"
//            postParameters["preview"] = "1"
//            client.data.getPost(
//                "example-2",
//                callback = object : Callback<Post, RestCallError> {
//                    override fun success(t: Post) {
//                        Log.w("success", t.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )

//            client.data.getPosts(
//                postParameters,
//                callback = object : Callback<Posts, RestCallError> {
//                    override fun success(t: Posts) {
//                        Log.w("success", t.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )

//            client.data.searchPosts(
//                "example",
//                postParameters,
//                callback = object : Callback<Posts, RestCallError> {
//                    override fun success(t: Posts) {
//                        Log.w("success", t.toString())
//                    }
//
//                    override fun failure(error: RestCallError) {
//                        Log.w("DemoError", error.errorMessage.toString() + error.errorBody.toString())
//                    }
//                }
//            )
            // Tags
//            val tagParameters = HashMap<String, String>()
//            tagParameters["include"] = "recent_posts"
//            client.data.getTag(
//                "example-tag",
//                callback = object : Callback<Tag, RestCallError> {
//                    override fun success(t: Tag) {
//                        Log.w("success", t.toString())
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
//                tagParameters,
//                callback = object : Callback<Tags, RestCallError> {
//                    override fun success(t: Tags) {
//                        Log.w("success", t.toString())
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
