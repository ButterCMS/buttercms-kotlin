package com.example.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.Date

data class DemoPageItem(
    val slug: String?,
    val name: String?,
    val published: Date?,
    val updated: Date?,
    val page_type: String?,
    val fields: Fields?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Fields(
    val headline: String,
    val subheadline: String?,
    val documentationUrl: String?
)
