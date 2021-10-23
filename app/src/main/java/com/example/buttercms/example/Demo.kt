package com.example.buttercms.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.buttercms.*

class Demo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Butter client
        val client = ButterCMS("3606556ecbd4134ea24b8936a829ab9edaddb583")

        Thread {
            try {
                // Authors
                val response =
                    client.data.getAuthor("applifting-sample", includeRecentPosts(true)).execute()
//                val response = client.data.getAuthors(includeRecentPosts(false)).execute()

                // Categories
//                val response = client.data.getCategory("example-category", includeRecentPosts(false)).execute()
//                val response = client.data.getCategories(includeRecentPosts(true)).execute()

                // Collections
//                val response = collectionWrapper(
//                    client, "faq",
//                    convertCollection(
//                        hashMapOf(
//                            Collection.LOCALE to "en", Collection.PAGE to "1"
//                        )
//                    ),
//                    myCollection = DemoData::class.java
//                )

//                val response = collectionWrapper(
//                    client, "faq",
//                    convertCollection(
//                        hashMapOf(
//                            Collection.KEYS to ("home,title")
//                        )
//                    ),
//                    myCollection = DemoData::class.java
//                )

//                val response = collectionWrapper(
//                    client, "faq",
//                    convertCollectionField(
//                        hashMapOf(
//                            Collection.FIELDS to Pair("question", "question")
//                        )
//                    ),
//                    myCollection = DemoData::class.java
//                )

                // Page
//                val response = client.data.getPage(
//                    "homepage", "homepage",
//                    convertPage(hashMapOf(Page.LOCALE to "en", Page.PREVIEW to "1"))
//                ).execute()

//                val response = client.data.getPages("homepage", convertPage(hashMapOf(
//                    Page.PAGE to "1", Page.PAGESIZE to "1", Page.LOCALE to "en"
//                ))
//                ).execute()

                // Post
//                val response = client.data.getPost("example-2").execute()
//                val response = client.data.getPosts(convertPost(hashMapOf(Post.PAGE to "1"))).execute()
//                val response = client.data.searchPosts(
//                    "example",
//                    convertPost(
//                            hashMapOf(Post.PAGE to "1", Post.PREVIEW to "1")
//                    )
//                ).execute()
                // Tags
//                val response = client.data.getTag("example-tag").execute()
//                val response = client.data.getTags(includeRecentPosts(false)).execute()

                println(response)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()
    }
}
