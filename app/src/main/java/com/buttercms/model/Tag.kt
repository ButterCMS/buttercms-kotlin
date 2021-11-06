package com.buttercms.model

data class TagItem(
    val name: String,
    val slug: String,
    val recent_posts: List<Data>?
)

data class Tag(
    val data: TagItem
)

data class Tags(
    val data: List<TagItem>
)
