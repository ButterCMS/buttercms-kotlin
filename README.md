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

API calls take map of parameters. Parameters are specified by user according to documentation - check [Butter API doc](https://buttercms.com/docs/api/)
Api Calls are asynchronous and expects Callback as a parameter. Callback provides result when API call is finished.  


Example of calling API:

Your code:
```
val client = ButterCMS("your_api_key")

val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"

 client.data.getAuthor(
 "author", queryParameters,  
 callback = object :
     Callback<Author, RestCallError> {
     override fun success(response: Author) {
     //do something on success
     Log.w("success", response.toString())
     }
     
     override fun failure(error: RestCallError) {
     //do something on failure
     Log.w("error", error.errorMessage.toString() + error.errorBody.toString())
     }
 })
```
----------------------------------------------------------------------------------------------------------------------
Behind the scene:
Helping function (which is called by user above) calls getAuthor retrofit call, wait for result and provides it to user
```
//helping function
@Throws(RestCallError::class)
fun ButterCmsService.getAuthor(
    slug: String,
    queryParameters: Map<String, String>,
    callback: Callback<Author, RestCallError>
) {
    val call = getAuthor(slug, queryParameters)
    call.enqueueCall {
        onSuccess = { body ->
            callback.success(body)
        }
        onFailure = {
            callback.failure(it)
        }
    }
}

//retrofit call
@GET("$AUTHORS{author}")
fun getAuthor(
    @Path("slug") slug: String,
    @QueryMap queryParameters: Map<String, String>? = emptyMap()
): Call<Author>
```

-------------------------------------------------------------------------------------------------------------------------------------

## Posts
### Retrieving Posts 
    
getPosts() parameters:
    
| Parameter        | Description                        |
| -----------------|:----------------------------------:|
| queryParameters  | Map of additional query parameters | 
    
    
getPosts() queryParameters
    
| QueryParameter                 | Default                        | Description.                                                      |
| -------------------------------|:------------------------------:|:-----------------------------------------------------------------:|
| page(optional)                 | 1                              | Used to paginate through older posts.                             |
| page_size(optional)            | 10                             | Used to set the number of blog posts shown per page.              |
| exclude_body(optional)         | false                          | When true, does not return the full post body.                    |
| author_slug (optional)         |                                | Filter posts by an author’s slug.                                 | 
| category_slug(optional)        |                                | Filter posts by a category’s slug.                                |     
| query                          |                                | Search query                                                      |    

    
Example:    
```
val queryParameters = HashMap<String, String>()
queryParameters["page"] = "1"
queryParameters["page_size"] = "10"
    
client.data.getPosts(
    queryParameters,
        callback = object : Callback<Posts, RestCallError> {
            override fun success(response: Posts) {}
            override fun failure(error: RestCallError) {}
        })

```

### Retrieving a Single Post
    
getPost() parameters

| Parameter        | Description                          |
| -----------------|:------------------------------------:|
| slug             | The slug of the post to be retrieved.|     

Example:    
```
client.data.getPost( "example-2",
               callback = object : Callback<Post, RestCallError> {
                   override fun success(response: Post) {}
                   override fun failure(error: RestCallError) {}
               }
           )    
```    
    
## Authors    
### Retrieving Authors

getAuthors() parameters

| Parameter        | Description                        |
| -----------------|:----------------------------------:|
| queryParameters  | Map of additional query parameters | 
    
getAuthors() queryParameters
| QueryParameter      | Description                                                             |
| --------------------|:-----------------------------------------------------------------------:|
| include             | If value is recent_posts, will return author's recent posts in response |     
    
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"   
    
client.data.getAuthors(
    queryParameters,
    callback = object :
        Callback<Authors, RestCallError> {
        override fun success(response: Authors) {}
        override fun failure(error: RestCallError) {}
    }
)    
```

### Retrieving Single Author     
    
getAuthor() parameters

| Parameter        | Description                        |
| -----------------|:----------------------------------:|
| slug             | Slug of author to be retrieved.    |  
| queryParameters  | Map of additional query parameters |  
    
getAuthor() queryParameters
| QueryParameter      | Description                                                             |
| --------------------|:-----------------------------------------------------------------------:|
| include             | If value is recent_posts, will return author's recent posts in response |  

Example: 
 
```
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"   
    
client.data.getAuthors(
    "john",
    queryParameters,
    callback = object :
        Callback<Authors, RestCallError> {
        override fun success(response: Authors) {}
        override fun failure(error: RestCallError) {}
    }
)  
```    
    
## Categories
### List Categories

getCategories() parameters

| Parameter        | Description                        |
| -----------------|:----------------------------------:| 
| queryParameters  | Map of additional query parameters |  
    
getCategories() queryParameters
| QueryParameter      | Description                                                              |
| --------------------|:------------------------------------------------------------------------:|
| include             | If value is recent_posts, will return recent posts along with categories | 
 
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts" 
    
client.data.getCategories(
                queryParameters,
                callback = object :
                    Callback<Categories, RestCallError> {
                    override fun success(response: Categories) {}
                    override fun failure(error: RestCallError) {}
                }
            )
``` 
    
### Retrieving Single Category
    
getCategory() parameters

| Parameter        | Description                           |
| -----------------|:-------------------------------------:| 
| slug             | Slug of the category to be retrieved. | 
| queryParameters  | Map of additional query parameters    |    
    
getCategory() queryParameters
| QueryParameter      | Description                                                               |
| --------------------|:-------------------------------------------------------------------------:|
| include             | If value is recent_posts, will return recent posts along with categories  | 

    
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"   
    
client.data.getCategory(
                "example-category", queryParameters,
                callback = object :
                    Callback<Category, RestCallError> {
                    override fun success(response: Category) {}
                    override fun failure(error: RestCallError) {}
                }
            )    
```
## Tags
### List Tags
    
getTags() parameters

| Parameter        | Description                         |
| -----------------|:-----------------------------------:| 
| queryParameters  | Map of additional query parameters  |    
    
getTags() queryParameters
| QueryParameter      | Description                                                               |
| --------------------|:-------------------------------------------------------------------------:|
| include             | If value is recent_posts, will return recent posts along with categories  | 

    
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"   
    
client.data.getTags(
                queryParameters,
                callback = object : Callback<Tags, RestCallError> {
                    override fun success(response: Tags) {}
                    override fun failure(error: RestCallError) {}
                }
            )   
```  
    
### Tag
  
getTag() parameters

| Parameter        | Description                         |
| -----------------|:-----------------------------------:|
| slug             | Slug of the tag to be retrieved     |     
| queryParameters  | Map of additional query parameters  |    
    
getTag() queryParameters
    
| QueryParameter      | Description                                                               |
| --------------------|:-------------------------------------------------------------------------:|
| include             | If value is recent_posts, will return recent posts along with categories  |     
    
Example:    
``` 
val queryParameters = HashMap<String, String>()
queryParameters["include"] = "recent_posts"
    
client.data.getTag(
                "example-tag", queryParameters,
                callback = object : Callback<Tag, RestCallError> {
                    override fun success(response: Tag) {}
                    override fun failure(error: RestCallError) {}
                }
            )    
```     
 

## Pages

The Pages in Butter CMS has configurable schema. See [ButterCMS](https://buttercms.com/kb/creating-editing-and-deleting-pages-and-page-types#creatingapage) doc for details how to create page. The schema defines fields which can exist on the page.    

ButerCMS also provides a concept of Page types and you can get all pages of the same type by getPages() API. To read more about the Page types refer to [ButterCMS doc](https://buttercms.com/kb/creating-editing-and-deleting-pages-and-page-types#creatingapagetype).

SDK requires you to model Page schema as data class and pass as parameter to getPages() func. Data class needs to have schema as PageItem. This allows SDK deserialize page data for you. [check example - DemoPage.kt]    

### List Pages    

getPages() parameters

| Parameter        | Description                         |
| -----------------|:-----------------------------------:|
| pageType         | Type of pages to be retrieved       |     
| queryParameters  | Map of additional query parameters  |
| classType.       | Class Page will be deserialized to  |     
    
getPages() queryParameters
    
| QueryParameter         | Description                                                               |
| -----------------------|:-------------------------------------------------------------------------:|
| preview(optional)      | Set to 1 to return the latest draft version of a page.                    | 
| fields.key(optional)   | Optional param. Filter the result set by the field and value.             |          
| order(optional)        | Order the result set by this field. Defaults to Asc. '-' to sort Desc.    |     
| page(optional)         | Used for Paginating through result set.                                   |     
| page_size (optional)   | Used for Paginating. Defines the number of results returned.              |     
| locale(optional)       | Set to the api slug of your configured locale (i.e. en or fr)             |     
| levels(optional)       | Defaults to 2. Defines the levels of relationships to serialize.          |     
                                  
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["locale"] = "en"
queryParameters["preview"] = "1"
            
client.data.getPages(
                "homepage",
                queryParameters,
                DemoPageItem::class.java,
                callback = object : Callback<Pages, RestCallError> {
                    override fun success(response: Pages) {}
                    override fun failure(error: RestCallError) {}
                }
            )    
```    
### Single Page

getPage() parameters

| Parameter        | Description                         |
| -----------------|:-----------------------------------:|
| pageType         | Type of page to be retrieved       |
| pageSlug         | Slug of page to be retrieved       |     
| queryParameters  | Map of additional query parameters  |
| classType        | Class Page will be deserialized to  |     
    
getPage() queryParameters
    
| QueryParameter         | Description                                                               |
| -----------------------|:-------------------------------------------------------------------------:|
| preview(optional)      | Set to 1 to return the latest draft version of a page.                    |    
| locale(optional)       | Set to the api slug of your configured locale (i.e. en or fr)             | 
    
Example:
    
```
val queryParameters = HashMap<String, String>()
queryParameters["locale"] = "en"
queryParameters["preview"] = "1"
    
client.data.getPage(
                "homepage",
                "homepage",
                queryParameters,
                DemoPageItem::class.java,
                callback = object : Callback<Page, RestCallError> {
                    override fun success(response: Page) {}
                    override fun failure(error: RestCallError) {}
                }
            )    
```    

## Collection

similar as Pages the Collections has also configurable schema. See [ButterCMS](https://buttercms.com/kb/creating-editing-and-deleting-collections#creatingacollection) doc for more details. The schema again define fields which composes the collection item. 
SDK requires you to model the collection data as a data class and pass as parameter to getCollection func. [check example DemoCollection.kt]

### List collection items
    
getCollection() parameters

| Parameter        | Description                                   |
| -----------------|:---------------------------------------------:|
| slug             | collection key                                |    
| queryParameters  | Map of additional query parameters            |
| classType        | Class that Colection will be deserialized to  |     
    
getCollection() queryParameters
    
| QueryParameter         | Description                                                               |
| -----------------------|:-------------------------------------------------------------------------:|
| test(optional)         | Set to 1 to enable Preview mode for viewing draft content.                | 
| fields.key(optional)   | Optional param. Filter the result set by the field and value.             |          
| order(optional)        | Order the result set by this field. Defaults to Asc. '-' to sort Desc.    |     
| page(optional)         | Used for Paginating through result set.                                   |     
| page_size (optional)   | Used for Paginating. Defines the number of results returned.              |     
| locale(optional)       | Set to the api slug of your configured locale (i.e. en or fr)             |     
| levels(optional)       | Defaults to 2. Defines the levels of relationships to serialize.          | 
  
    
Example:
    
```  
val queryParameters = HashMap<String, String>()
queryParameters["locale"] = "en"
            
client.data.getCollection(
                "faq",
                queryParameters,
                DemoData::class.java,
                callback = object :
                    Callback<Collections, RestCallError> {
                    override fun success(response: Collections) {}
                    override fun failure(error: RestCallError) {}
                }
            )    
    
```      
