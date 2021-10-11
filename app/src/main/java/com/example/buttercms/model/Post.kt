package com.example.buttercms.model

import com.squareup.moshi.Json

data class Post(
    val meta: Meta,
    val data: List<Data>
)

data class Meta(
    val next_page: Int?,
    val previous_page: Int?,
    val count: Int
)

data class Data(
    val status: Status?,
    val created: String?,
    val updated: String?,
    val published: String?,
    val title: String,
    val slug: String,
    val body: String?,
    val summary: String?,
    val seoTitle: String?,
    val metaDescription: String?,
    val featuredImage: String?,
    val featuredImageAlt: String?,
    val url: String?,
    val author: Author?,
    val tag: List<Tag>?,
    val categories: List<Category>?
)

enum class Status {
    @Json(name = "draft")
    DRAFT,

    @Json(name = "published")
    PUBLISHED
}
