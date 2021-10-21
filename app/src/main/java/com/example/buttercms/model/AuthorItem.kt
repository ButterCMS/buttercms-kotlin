package com.example.buttercms.model

data class AuthorItem(
    val first_name: String,
    val last_name: String?,
    val email: String?,
    val slug: String,
    val bio: String?,
    val title: String?,
    val linkedin_url: String?,
    val facebook_url: String?,
    val instagram_url: String?,
    val pinterest_url: String?,
    val twitter_handle: String?,
    val profile_image: String?,
    // With query include=recent_posts:
    val recent_posts: List<Data>?
)

data class Author(
    val data: AuthorItem
)

data class Authors(
    val data: List<AuthorItem>
)
