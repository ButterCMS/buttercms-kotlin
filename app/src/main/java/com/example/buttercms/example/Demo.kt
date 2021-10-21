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
                    client.data.getAuthor("applifting-sample", includeRecentPosts()).execute()
//                val response = client.data.getAuthors(includeRecentPosts()).execute()
                // QueryMap does not take null

                // Categories
//                val response = client.data.getCategory("example-category", includeRecentPosts()).execute()
//                val response = client.data.getCategories(includeRecentPosts()).execute()

                // Collections
//                val response = collectionWrapper(
//                    client, "faq",
//                    convertCollection(
//                        hashMapOf(
//                            Collection.LOCALE to "en"
//                        )
//                    ),
//                    myCollection = DemoData::class.java
//                )

//                val response = collectionWrapper(
//                    client, "faq",
//                    convertCollection(
//                        hashMapOf(
//                            "fields.question" to "Can"
//                        )
//                    ),
//                    myCollection = DemoData::class.java
//                )

                // Page
//                val response = client.data.getPage(
//                    "homepage", "homepage",
//                    convertPage(hashMapOf(Page.LOCALE to "en"))
//                ).execute()

//                val response = client.data.getPages("homepage", convertPage(hashMapOf(
//                    Page.LOCALE to "en"
//                ))
//                ).execute()

                // Post
//                val response = client.data.getPost("example-2").execute()
//                val response = client.data.getPosts(convertPost(hashMapOf(Post.PAGE to "1"))).execute()
//                val response = client.data.searchPosts("example", convertPost(hashMapOf(Post.PAGE to "1"))).execute()

                // Tags
//                val response = client.data.getTag("example-tag").execute()
//                val response = client.data.getTags(includeRecentPosts()).execute()

                println(response)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }.start()
    }
}
