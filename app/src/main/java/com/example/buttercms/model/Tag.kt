package com.example.buttercms.model

data class Tag(
    val name: String,
    val slug: String,
    val recent_post: List<Post>?
)

data class TagResponse(
    val data: Tag
)

data class TagsResponse(
    val data: List<Tag>
)
