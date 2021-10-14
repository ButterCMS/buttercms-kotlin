package com.example.buttercms.model

data class Page<T>(
    val slug: String,
    val name: String,
    val published: String,
    val updated: String,
    val page_type: String,
    val fields: T
)

data class Pages(
    val data: List<Page<Any>>
)

data class SinglePage(
    val data: Page<Any>
)
