package com.example.buttercms

import com.example.buttercms.model.Collections
import com.example.buttercms.model.Meta
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.google.gson.Gson

fun <T> collectionWrapper(
    client: ButterCMS,
    slug: String,
    queryParameters: Map<String, String>?,
    myCollection: Class<T>
): Collections<T> {

    val response = client.data.getCollections(slug, queryParameters).execute().body()
    val gson = Gson()
    val jsonElement = gson.toJson(response)

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
        val (key2, value2) = when (it.key) {
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
        final[key2 as String] = value2
    }
    return final
}

fun convertCollectionField(map: HashMap<Any, Pair<String, String>>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (key2, value2) = when (it.key) {
            Collection.FIELDS -> useRequestContentField(Collection.FIELDS.value, it.value)
            else -> Pair(it.key, it.value)
        }
        final[key2 as String] = value2.toString()
    }
    return final
}

fun useRequestContentField(value: String, key: Pair<String, String>): Pair<String, String> {
    val newValue = value + key.first
    val newKey = key.second
    return Pair(newValue, newKey)
}

fun useRequestContentList(value: String, key: String): Pair<String, String> {
    return Pair(value, key)
}

fun convertPage(map: HashMap<Page, String>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (key2, value2) = when (it.key) {
            Page.PREVIEW -> useRequestContentList(Page.PREVIEW.value, it.value)
            Page.LOCALE -> useRequestContentList(Page.LOCALE.value, it.value)
            Page.LEVELS -> useRequestContentList(Page.LEVELS.value, it.value)
            Page.PAGE -> useRequestContentList(Page.PAGE.value, it.value)
            Page.PAGESIZE -> useRequestContentList(Page.PAGESIZE.value, it.value)
        }
        final[key2] = value2
    }

    return final
}

fun convertPost(map: HashMap<Post, String>): Map<String, String> {
    val final = HashMap<String, String>()
    map.forEach {
        val (key2, value2) = when (it.key) {
            Post.AUTHORSLUG -> useRequestContentList(Post.AUTHORSLUG.value, it.value)
            Post.CATEGORYSLUG -> useRequestContentList(Post.CATEGORYSLUG.value, it.value)
            Post.EXCLUDEBODY -> useRequestContentList(Post.EXCLUDEBODY.value, it.value)
            Post.PAGE -> useRequestContentList(Post.PAGE.value, it.value)
            Post.PAGESIZE -> useRequestContentList(Post.CATEGORYSLUG.value, it.value)
            Post.PREVIEW -> useRequestContentList(Post.PREVIEW.value, it.value)
            Post.TAGSLUG -> useRequestContentList(Post.TAGSLUG.value, it.value)
        }
        final[key2] = value2
    }
    return final
}
