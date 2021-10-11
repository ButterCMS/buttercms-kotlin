package com.example.buttercms.model

data class Author(
    val slug: String,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val bio: String?,
    val title: String?,
    val linkedinUrl: String?,
    val facebookUrl: String?,
    val pinterestUrl: String?,
    val instagramUrl: String?,
    val twitterHandle: String?,
    val profileImage: String?,
    // With query include=recent_posts:
    val recentPosts: List<Post>?
)
