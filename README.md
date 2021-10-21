# buttercms-android
Android library for ButterCMS API.
## Documentation
For a comprehensive list of examples, check out the [API documentation](https://buttercms.com/docs/api/).

## Installation
### Gradle

build.gradle

```
dependencies {
    implementation 'com.buttercms:buttercmsandroid: '
}
```

### Sections
- Authors
- Categories
- Collections
- Pages
- Posts
- Tags

### Usage
To get started with the Butter API, instantiate the ButterCMSClient with the API key found in the [Butter Admin Settings](https://buttercms.com/login/?next=/settings/).

```
// Initialize Butter client
val client = ButterCMS("your_api_key")
```

### Calling APIs

Every endpoint expects list of parameters. 
In Helpers.kt file you can find help functions and defined enum parameters.

- For Author, Categories, Tags: for including recent post call help function
```
fun includeRecentPosts(): Map<String, String> {
    return mapOf(Pair("include", "recent_posts"))
}
```
and then code looks like this:
```
val client = ButterCMS("your_api_key")
val response = client.data.getAuthor("your_author", includeRecentPosts()).execute()
```

- For Collections, Page, Post: 
e.g. Page - choose function called convertPage, put hashmap of chosen Page enum as parameter and value

```
enum class Page(val value: String) {
    PREVIEW("preview"),
    LOCALE("locale"),
    LEVELS("levels"),

    // pages
    PAGE("page"),
    PAGESIZE("page_size");
}
```

then code will look like this:

```
val client = ButterCMS("your_api_key")
client.data.getPage(
                    "page_type", "page_slug",
                    convertPage(hashMapOf(Page.LOCALE to "en"))
                ).execute()
```
## Blog Posts
### Posts

```
@GET("$POSTS{slug}/")
fun getPost(@Path("slug") slug: String): Call<Post>
```
```
@GET(POSTS)
fun getPosts(@QueryMap queryParameters: Map<String, String>?): Call<Posts>
```

```
@GET(SEARCH) 
fun searchPosts(@Query("query") query: String, @QueryMap queryParameters: Map<String, String>?): Call<Posts>
```

### Authors

```
@GET("$AUTHORS{author}")
fun getAuthor(@Path("author") author: String, @QueryMap queryParameters: Map<String, String>?): Call<Author>
```

```
@GET(AUTHORS)
fun getAuthors(@QueryMap queryParameters: Map<String, String>?): Call<Authors>
```

### Categories

```
@GET("$CATEGORIES{category}/")
fun getCategory(@Path("category") category: String,@QueryMap queryParameters: Map<String, String>?): Call<Category>
```

```
@GET(CATEGORIES)
fun getCategories(@QueryMap queryParameters: Map<String, String>?): Call<Categories>
```

### Tags

```
@GET("$TAGS{tag}/")
fun getTag(@Path("tag") tag: String): Call<TagResponse>
```

```
@GET(TAGS)
fun getTags(@QueryMap queryParameters: Map<String, String>?): Call<TagsResponse>
```

### Pages

The Pages in Butter CMS has configurable schema. See [ButterCMS](https://buttercms.com/kb/creating-editing-and-deleting-pages-and-page-types#creatingapage) doc for details how to create page. The schema defines fileds which can exist on the page. 

ButerCMS also provides a concept of Page types and you can get all pages of the same type by getPages() API. To read more about the Page types refer to [ButterCMS doc](https://buttercms.com/kb/creating-editing-and-deleting-pages-and-page-types#creatingapagetype).

```
@GET("$PAGES{page_type_slug}/{page_slug}/")
fun getPage(@Path("page_type_slug") page_type: String,@Path("page_slug") page_slug: String,@QueryMap queryParameters: Map<String, String>): Call<Page>
```

```
@GET("$PAGES{page_type}/")
fun getPages(@Path("page_type") page_type: String,@QueryMap queryParameters: Map<String, String>?): Call<Pages>
```

### Collection

imilar as Pages the Collections has also configurable schema. See [ButterCMS](https://buttercms.com/kb/creating-editing-and-deleting-collections#creatingacollection) doc for more details. The schema again define fields which composes the collection item. The SDK requires you to model the collection data as a data class an provide it as a parameter to the API. For converting receiving data to your prepared data class, you need to call helper function "collectionWrapper" from Helpers.kt and provide your prepared data class.(with your slug and map of Collection parameters)

```
@GET("$COLLECTIONS{slug}/")
fun getCollections(@Path("slug") slug: String,@QueryMap queryParameters: Map<String, String>?): Call<Collections<Any>>
```


code will looks like this:
```
val response = collectionWrapper(
   client, "your_slug",
   convertCollection(
         hashMapOf(
              Collection.LOCALE to "en"
         )
     ),
     myCollection = YOUR_DATA_CLASS::class.java
)

