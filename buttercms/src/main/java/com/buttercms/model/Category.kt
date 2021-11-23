package com.buttercms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Category(
    val data: CategoryItem?
) : Parcelable

data class Categories(
    val data: List<CategoryItem>
)

@Parcelize
data class CategoryItem(
    val name: String,
    val slug: String,
    val recent_posts: List<CategoryPost>?
) : Parcelable

@Parcelize
data class CategoryPost(
    val status: Status?,
    val created: Date?,
    val updated: Date?,
    val published: Date?,
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
    val categories: List<Category>?,
) : Parcelable
