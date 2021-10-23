package com.example.buttercms.model

data class PageItem<T>(
    val slug: String,
    val name: String,
    val published: String,
    val updated: String,
    val page_type: String,
    val fields: T
)

data class Pages(
    val data: List<PageItem<Any>>
)

data class Page(
    val data: PageItem<Any>
)
