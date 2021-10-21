package com.example.buttercms.model

data class Category(
    val data: CategoryItem?
)

data class Categories(
    val data: List<CategoryItem>
)

data class CategoryItem(
    val name: String,
    val slug: String,
    val recent_posts: List<Post>?
)
