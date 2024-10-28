package com.buttercms.model

import java.util.Date

data class PageItem(
    val slug: String,
    val name: String,
    val published: Date?,
    val updated: Date?,
    val page_type: String,
    val fields: Any,
    val status: PageStatus,
    val scheduled: Date?
)

data class Pages(
    val meta: Meta,
    val data: List<Any>
)

data class Page(
    val data: Any
)

enum class PageStatus {
    DRAFT,
    PUBLISHED,
    SCHEDULED
}

