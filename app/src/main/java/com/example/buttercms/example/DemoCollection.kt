package com.example.buttercms.example

import com.fasterxml.jackson.annotation.JsonProperty

data class DemoData(
    @JsonProperty("faq")
    val faq: List<DemoItem>?
)

data class DemoItem(
    @JsonProperty("meta")
    val meta: MetaItem,
    @JsonProperty("question")
    val question: String,
    @JsonProperty("answer")
    val answer: String
)

data class MetaItem(
    @JsonProperty("id")
    val id: Int
)
