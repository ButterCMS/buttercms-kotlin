package com.example.buttercms.model

data class Tag(
    val name: String,
    val slug: String,
    val recentPost: List<Post>?
)

data class Tags(
    val data: List<Tag>
)
