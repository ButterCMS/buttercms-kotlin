package com.example.buttercms.model

data class Category(
    val name: String,
    val slug: String,
    val recentPost: List<Post>?
)
