package com.example.buttercms.model

data class Category(
    val name: String,
    val slug: String,
    val recentPost: List<Post>?
)

data class Categories(
    val data: List<Category>
)
