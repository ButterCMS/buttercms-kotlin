package com.example.buttercms.model

import java.util.Date

data class PageItem<T>(
    val slug: String,
    val name: String,
    val published: Date?,
    val updated: Date?,
    val page_type: String,
    val fields: T
)

data class Pages(
    val data: List<PageItem<Any>>
)

data class Page(
    val data: PageItem<Any>
)
