package com.example.buttercms.model

data class Tag(
    val name: String,
    val slug: String,
    val recentPost: List<Post>?
)
