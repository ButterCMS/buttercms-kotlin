package com.buttercms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TagItem(
    val name: String,
    val slug: String,
    val recent_posts: List<Data>?
) : Parcelable

data class Tag(
    val data: TagItem
)

data class Tags(
    val data: List<TagItem>
)
