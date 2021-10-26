package com.example.buttercms

import com.example.buttercms.model.Collections
import com.example.buttercms.model.Meta
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun <T> collectionWrapper(
    client: ButterCMS,
    slug: String,
    queryParameters: Map<String, String>?,
    myCollection: Class<T>
): Collections {
    val response = client.data.getCollections(slug, queryParameters).execute().body()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter<Any>(Collections::class.java)
    val jsonElement = adapter.toJson(response)
    val mapper = ObjectMapper().registerModule(KotlinModule())
    val root: JsonNode = mapper.readTree(jsonElement)
    return Collections(
        meta = mapper.convertValue(root.get("meta"), Meta::class.java),
        data = mapper.convertValue(
            root.get("data"),
            mapper.typeFactory.constructType(myCollection)
        )
    )
}

fun includeRecentPosts(includeRecentPosts: Boolean): String? {
    return if (includeRecentPosts) {
        "recent_posts"
    } else null
}

enum class Collection(val value: String) {
    KEYS("keys"),
    FIELDS("fields."),
    PAGE("page"),
    PAGESIZE("page_size"),
    LOCALE("locale"),
    LEVELS("levels"),
    TEST("test"),
    ORDER("order");
}

enum class Page(val value: String) {
    PREVIEW("preview"),
    LOCALE("locale"),
    LEVELS("levels"),

    // pages
    PAGE("page"),
    PAGESIZE("page_size");
}

enum class Post(val value: String) {
    PAGE("page"),
    PAGESIZE("page_size"),
    PREVIEW("preview"),
    EXCLUDEBODY("exclude_body"),
    AUTHORSLUG("author_slug"),
    CATEGORYSLUG("category_slug"),
    TAGSLUG("tag_slug");
}

fun convertCollection(map: HashMap<Any, String>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (paramName, paramValue) = when (it.key) {
            // Collection
            Collection.KEYS -> useRequestContentList(Collection.KEYS.value, it.value)
            Collection.PAGE -> useRequestContentList(Collection.PAGE.value, it.value)
            Collection.PAGESIZE -> useRequestContentList(Collection.PAGESIZE.value, it.value)
            Collection.LOCALE -> useRequestContentList(Collection.LOCALE.value, it.value)
            Collection.LEVELS -> useRequestContentList(Collection.LEVELS.value, it.value)
            Collection.TEST -> useRequestContentList(Collection.TEST.value, it.value)
            Collection.ORDER -> useRequestContentList(Collection.ORDER.value, it.value)
            else -> Pair(it.key, it.value)
        }
        final[paramName as String] = paramValue
    }
    return final
}

fun convertCollectionField(map: HashMap<Any, Pair<String, String>>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (paramName, paramValue) = when (it.key) {
            Collection.FIELDS -> useRequestContentField(Collection.FIELDS.value, it.value)
            else -> Pair(it.key, it.value)
        }
        final[paramName as String] = paramValue.toString()
    }
    return final
}

fun useRequestContentField(
    paramName: String,
    paramValue: Pair<String, String>
): Pair<String, String> {
    val newParamName = paramName + paramValue.first
    val newParamValue = paramValue.second
    return Pair(newParamName, newParamValue)
}

fun useRequestContentList(value: String, key: String): Pair<String, String> {
    return Pair(value, key)
}

fun convertPage(map: HashMap<Page, String>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (paramName, paramValue) = when (it.key) {
            Page.PREVIEW -> useRequestContentList(Page.PREVIEW.value, it.value)
            Page.LOCALE -> useRequestContentList(Page.LOCALE.value, it.value)
            Page.LEVELS -> useRequestContentList(Page.LEVELS.value, it.value)
            Page.PAGE -> useRequestContentList(Page.PAGE.value, it.value)
            Page.PAGESIZE -> useRequestContentList(Page.PAGESIZE.value, it.value)
        }
        final[paramName] = paramValue
    }

    return final
}

fun convertPost(map: HashMap<Post, String>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (paramName, paramValue) = when (it.key) {
            Post.AUTHORSLUG -> useRequestContentList(Post.AUTHORSLUG.value, it.value)
            Post.CATEGORYSLUG -> useRequestContentList(Post.CATEGORYSLUG.value, it.value)
            Post.EXCLUDEBODY -> useRequestContentList(Post.EXCLUDEBODY.value, it.value)
            Post.PAGE -> useRequestContentList(Post.PAGE.value, it.value)
            Post.PAGESIZE -> useRequestContentList(Post.CATEGORYSLUG.value, it.value)
            Post.PREVIEW -> useRequestContentList(Post.PREVIEW.value, it.value)
            Post.TAGSLUG -> useRequestContentList(Post.TAGSLUG.value, it.value)
        }
        final[paramName] = paramValue
    }
    return final
}
