package com.buttercms.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.Date

data class Post(
    val meta: MetaPost,
    val data: Data
)

data class Posts(
    val meta: Meta,
    val data: List<Data>
)

data class MetaPost(
    val next_post: String?,
    val previous_post: PreviousPost
)

data class Meta(
    val next_page: Int?,
    val previous_page: Int?,
    val count: Int
)

data class PreviousPost(
    val slug: String,
    val title: String,
    val featured_image: String?
)

@Parcelize
data class Data(
    val status: Status?,
    val created: Date?,
    val updated: Date?,
    val published: Date?,
    val scheduled: Date?,
    val title: String,
    val slug: String,
    val body: String?,
    val summary: String?,
    val seo_title: String?,
    val meta_description: String?,
    val featured_image_alt: String?,
    val url: String?,
    val featured_image: String?,
    val author: AuthorItem?,
    val tags: List<TagItem>?,
    val categories: List<CategoryItem>?,
) : Parcelable

enum class Status {
    @Json(name = "draft")
    DRAFT,

    @Json(name = "published")
    PUBLISHED,

    @Json(name = "scheduled")
    SCHEDULED
}

