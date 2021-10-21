# buttercms-android
Android library for ButterCMS API.
## Documentation
For a comprehensive list of examples, check out the [API documentation][https://buttercms.com/docs/api/].

## Installation
### Gradle

build.gradle

```
dependencies {
    implementation 'com.buttercms:buttercmsandroid: '
}
```

###Usage
To get started with the Butter API, instantiate the ButterCMSClient with the API key found in the [Butter Admin Settings][https://buttercms.com/login/?next=/settings/].

```
// Initialize Butter client
val client = ButterCMS("3606556ecbd4134ea24b8936a829ab9edaddb583")
```

###Sections
- Authors
- Categories
- Collections
- Pages
- Posts
- Tags
