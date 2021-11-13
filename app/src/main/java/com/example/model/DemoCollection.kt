package com.example.model

data class DemoData(
    val faq: List<DemoItem>?
)

data class DemoItem(
    val meta: MetaItem,
    val question: String,
    val answer: String
)

data class MetaItem(
    val id: Int
)
