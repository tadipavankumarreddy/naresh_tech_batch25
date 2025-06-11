You got it! This tutorial will guide you through building an Android application using Jetpack Compose, Retrofit, and Dagger Hilt, adhering to a Clean Architecture approach. We'll start with the basics of Retrofit for GET and POST requests, then slowly introduce Dagger Hilt for dependency injection, all within the context of Jetpack Compose UI.

**Core Concepts We'll Cover:**

* **Jetpack Compose:** Android's modern toolkit for building native UI.
* **Retrofit:** A type-safe HTTP client for Android and Java.
* **JSONPlaceholder:** A free fake online REST API for testing (`jsonplaceholder.typicode.com`).
* **Dagger Hilt:** A dependency injection library for Android built on Dagger.
* **Clean Architecture:** A software architecture pattern that separates concerns into distinct layers (Domain, Data, Presentation).

---

## Part 0: Project Setup and Initial Dependencies

Let's begin by setting up our Android Studio project and adding the necessary dependencies.

1.  **Create a New Android Project:**
    * Open Android Studio.
    * Click "New Project."
    * Select "Empty Activity" under the "Phone and Tablet" tab and click "Next."
    * Configure your project:
        * **Name:** `RetrofitHiltComposeExample`
        * **Package name:** `com.example.retrofithiltcomposeexample` (or similar)
        * **Save location:** Choose a suitable directory.
        * **Language:** `Kotlin`
        * **Minimum SDK:** API 21 (or higher, typically for Compose)
        * **Build configuration language:** `Kotlin DSL` (recommended for newer projects)
    * Click "Finish."

2.  **Add Dependencies:**
    Open your `app/build.gradle.kts` file. We need to add dependencies for Compose, Retrofit, OkHttp, Coroutines, Lifecycle, and Hilt.

    ```kotlin
    // app/build.gradle.kts

    plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("com.google.devtools.ksp") // For KSP (Kotlin Symbol Processing) - successor to Kapt for Hilt
        id("com.google.dagger.hilt.android") // Hilt Gradle Plugin
    }

    android {
        namespace = "com.example.retrofithiltcomposeexample"
        compileSdk = 34 // Or your current compile SDK version

        defaultConfig {
            applicationId = "com.example.retrofithiltcomposeexample"
            minSdk = 21
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1" // Or your current Compose compiler version
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
        implementation("androidx.activity:activity-compose:1.9.0")
        implementation(platform("androidx.compose:compose-bom:2024.05.00")) // Or the latest stable BOM
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0") // For JSON parsing

        // OkHttp (Retrofit uses OkHttp internally, good for logging)
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

        // Dagger Hilt
        implementation("com.google.dagger:hilt-android:2.51.1")
        ksp("com.google.dagger:hilt-compiler:2.51.1")

        // Hilt for ViewModel and Compose Integration
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
        ksp("androidx.hilt:hilt-compiler:1.2.0")
    }
    ```

    Also, update your **project-level** `build.gradle.kts` to include the KSP and Hilt plugins in the `plugins` block:

    ```kotlin
    // project/build.gradle.kts

    plugins {
        id("com.android.application") version "8.1.0" apply false // Or your current version
        id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Or your current version
        id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false // KSP plugin
        id("com.google.dagger.hilt.android") version "2.51.1" apply false // Hilt plugin
    }
    ```
    (Note: The KSP version should generally match your Kotlin version's major and minor numbers.)

3.  **Add Internet Permission:**
    Open your `AndroidManifest.xml` file and add the internet permission inside the `<manifest>` tag:

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools">

        <uses-permission android:name="android.permission.INTERNET" />

        <application
            android:allowBackup="true"
            android:dataExtractionRules="@xml/data_extraction_rules"
            android:fullBackupContent="@xml/backup_rules"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@android:style/Theme.Material.Light.NoActionBar" > <activity
                android:name=".MainActivity"
                android:exported="true"
                android:label="@string/app_name"
                android:theme="@style/Theme.RetrofitHiltComposeExample">
                <intent-filter>
                    <action android:name="android.intent.action.MAIN" />
                    <category android:name="android.intent.category.LAUNCHER" />
                </intent-filter>
            </activity>
        </application>
    </manifest>
    ```
    *Note: `android:usesCleartextTraffic="true"` is not explicitly needed here because JSONPlaceholder uses HTTPS. If you were hitting a plain HTTP endpoint, you would need to add `android:usesCleartextTraffic="true"` to your `<application>` tag.*

4.  **Sync Gradle:**
    Sync your project with Gradle files.

---

## Part 1: Retrofit GET and POST with Jetpack Compose (Without Hilt)

Let's build the initial Retrofit integration. We'll keep it simple first, and then refactor with Hilt.

### Clean Architecture Layers

We'll structure our code into the following layers:

* **`data`**: Contains data models, API interfaces, and repository implementations.
    * `model`: Data classes for API requests/responses.
    * `remote`: Retrofit API interface.
    * `repository`: Implementation of data source (e.g., calling API).
* **`domain`**: Contains business logic, use cases, and abstract interfaces.
    * `model`: Domain-specific data classes (if different from data layer).
    * `repository`: Abstract interface for data operations.
    * `usecase`: Business logic for specific operations.
* **`presentation`**: Contains UI-related code (Compose Composables, ViewModels).
    * `ui`: Composable functions.
    * `viewmodel`: ViewModels.
* **`util`**: Utility classes (e.g., `Resource` class for handling network states).

### 1.1 Data Layer

**`data/model/PostDto.kt`** (Data Transfer Object for API response)

```kotlin
package com.example.retrofithiltcomposeexample.data.model

// DTO for response from GET /posts and POST /posts
data class PostDto(
    val userId: Int,
    val id: Int? = null, // ID is generated by the server for new posts, so it can be null on request
    val title: String,
    val body: String
)
```

**`data/remote/JsonPlaceholderApi.kt`** (Retrofit API Interface)

```kotlin
package com.example.retrofithiltcomposeexample.data.remote

import com.example.retrofithiltcomposeexample.data.model.PostDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceholderApi {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @POST("posts")
    suspend fun createPost(@Body post: PostDto): Response<PostDto>
}
```

* `@GET("posts")`: Defines a GET request to the `/posts` endpoint.
* `@POST("posts")`: Defines a POST request to the `/posts` endpoint.
* `suspend`: Marks these functions as suspend functions, allowing them to be called from coroutines.
* `Response<T>`: Retrofit's wrapper for HTTP responses, providing access to body, headers, status codes, etc.
* `@Body PostDto post`: Indicates that the `PostDto` object will be sent as the request body, serialized to JSON by Gson.

**`data/repository/PostRepositoryImpl.kt`** (Implementation of the Repository)

For now, we'll keep the `RetrofitClient` inside this implementation. We'll extract it later with Hilt.

```kotlin
package com.example.retrofithiltcomposeexample.data.repository

import com.example.retrofithiltcomposeexample.data.model.PostDto
import com.example.retrofithiltcomposeexample.data.remote.JsonPlaceholderApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// This will be replaced by Hilt injection later
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: JsonPlaceholderApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())) // Use lenient Gson for some APIs
            .build()
            .create(JsonPlaceholderApi::class.java)
    }
}


// Implements the abstract domain repository
class PostRepositoryImpl {

    private val api = RetrofitClient.api

    suspend fun getPosts(): List<PostDto> {
        val response = api.getPosts()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Failed to fetch posts: ${response.code()}")
        }
    }

    suspend fun createPost(post: PostDto): PostDto {
        val response = api.createPost(post)
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception("Failed to create post: ${response.code()}")
        }
    }
}
```

* `RetrofitClient` is currently a singleton `object`. This is a basic way to manage a single instance of Retrofit, but it's not ideal for testing or larger applications. Hilt will fix this.
* `PostRepositoryImpl`: Directly uses `RetrofitClient.api` to make network calls. It also includes basic error handling by throwing exceptions for unsuccessful responses.

### 1.2 Domain Layer

**`domain/model/Post.kt`** (Domain Model - can be the same as DTO for simple cases)

```kotlin
package com.example.retrofithiltcomposeexample.domain.model

data class Post(
    val userId: Int,
    val id: Int? = null,
    val title: String,
    val body: String
)
```
*Note: In complex applications, `PostDto` and `Post` might differ significantly (e.g., `PostDto` contains raw API fields, `Post` contains processed/transformed data).*

**`domain/repository/PostRepository.kt`** (Abstract Repository Interface)

```kotlin
package com.example.retrofithiltcomposeexample.domain.repository

import com.example.retrofithiltcomposeexample.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(post: Post): Post
}
```
*This interface defines the contract for data operations, abstracting away the underlying data source (whether it's an API, database, etc.).*

**`domain/usecase/GetPostsUseCase.kt`**

```kotlin
package com.example.retrofithiltcomposeexample.domain.usecase

import com.example.retrofithiltcomposeexample.data.repository.PostRepositoryImpl // For now, directly use Impl
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.data.model.PostDto // Temporarily import DTO for mapping

// For now, directly use PostRepositoryImpl. We'll use the interface with Hilt later.
class GetPostsUseCase {
    private val repository = PostRepositoryImpl()

    suspend operator fun invoke(): List<Post> {
        // Map DTO to domain model
        return repository.getPosts().map { dto ->
            Post(
                userId = dto.userId,
                id = dto.id,
                title = dto.title,
                body = dto.body
            )
        }
    }
}
```

**`domain/usecase/CreatePostUseCase.kt`**

```kotlin
package com.example.retrofithiltcomposeexample.domain.usecase

import com.example.retrofithiltcomposeexample.data.repository.PostRepositoryImpl // For now, directly use Impl
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.data.model.PostDto // Temporarily import DTO for mapping

// For now, directly use PostRepositoryImpl. We'll use the interface with Hilt later.
class CreatePostUseCase {
    private val repository = PostRepositoryImpl()

    suspend operator fun invoke(post: Post): Post {
        // Map domain model to DTO for the request
        val postDto = PostDto(
            userId = post.userId,
            title = post.title,
            body = post.body
        )
        val createdDto = repository.createPost(postDto)
        // Map DTO back to domain model for the response
        return Post(
            userId = createdDto.userId,
            id = createdDto.id,
            title = createdDto.title,
            body = createdDto.body
        )
    }
}
```
* `operator fun invoke()`: Allows calling the use case like a function (e.g., `getPostsUseCase()`).
* **Mapping:** We perform mapping between `PostDto` (data layer) and `Post` (domain layer). This is crucial for Clean Architecture, ensuring domain logic doesn't depend on API-specific data structures.

### 1.3 Presentation Layer

**`presentation/util/Resource.kt`** (Utility for State Handling)

```kotlin
package com.example.retrofithiltcomposeexample.presentation.util

// Sealed class to represent different states of a network request
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
```

**`presentation/viewmodel/PostViewModel.kt`**

```kotlin
package com.example.retrofithiltcomposeexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.domain.usecase.CreatePostUseCase
import com.example.retrofithiltcomposeexample.domain.usecase.GetPostsUseCase
import com.example.retrofithiltcomposeexample.presentation.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val getPostsUseCase = GetPostsUseCase() // Direct instantiation for now
    private val createPostUseCase = CreatePostUseCase() // Direct instantiation for now

    private val _postsState = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())
    val postsState: StateFlow<Resource<List<Post>>> = _postsState.asStateFlow()

    private val _createPostState = MutableStateFlow<Resource<Post>>(Resource.Loading())
    val createPostState: StateFlow<Resource<Post>> = _createPostState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            _postsState.value = Resource.Loading()
            try {
                val posts = getPostsUseCase()
                _postsState.value = Resource.Success(posts)
            } catch (e: Exception) {
                _postsState.value = Resource.Error("Error fetching posts: ${e.localizedMessage}")
            }
        }
    }

    fun createPost(title: String, body: String, userId: Int) {
        viewModelScope.launch {
            _createPostState.value = Resource.Loading()
            try {
                val newPost = Post(title = title, body = body, userId = userId)
                val createdPost = createPostUseCase(newPost)
                _createPostState.value = Resource.Success(createdPost)
            } catch (e: Exception) {
                _createPostState.value = Resource.Error("Error creating post: ${e.localizedMessage}")
            }
        }
    }

    // Reset create post state after showing success/error
    fun resetCreatePostState() {
        _createPostState.value = Resource.Loading(null) // Reset to loading with no data
    }
}
```

* `ViewModel`: Part of Android Architecture Components, stores UI-related data and handles business logic.
* `MutableStateFlow`: A hot flow that holds a single latest value and updates collectors. Used here to represent the UI state (loading, success, error).
* `viewModelScope.launch`: Launches a coroutine that is automatically cancelled when the ViewModel is cleared.
* **Direct UseCase Instantiation:** For now, `GetPostsUseCase` and `CreatePostUseCase` are directly instantiated. Hilt will take care of injecting these.
* Error Handling: Catches exceptions from use cases and updates the `_errorMessage` LiveData.

**`presentation/ui/PostScreen.kt`** (Composable UI)

```kotlin
package com.example.retrofithiltcomposeexample.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.presentation.util.Resource
import com.example.retrofithiltcomposeexample.presentation.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
    val postsState by viewModel.postsState.collectAsState()
    val createPostState by viewModel.createPostState.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Observe createPostState for toast messages
    LaunchedEffect(createPostState) {
        when (createPostState) {
            is Resource.Success -> {
                val post = (createPostState as Resource.Success).data
                Toast.makeText(context, "Post created: ID ${post?.id}", Toast.LENGTH_SHORT).show()
                viewModel.resetCreatePostState()
            }
            is Resource.Error -> {
                val errorMessage = (createPostState as Resource.Error).message
                Toast.makeText(context, "Error creating post: $errorMessage", Toast.LENGTH_LONG).show()
                viewModel.resetCreatePostState()
            }
            is Resource.Loading -> {
                // Do nothing
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Retrofit & Compose (No Hilt Yet)") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // GET Posts Section
            Button(
                onClick = { viewModel.getPosts() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Refresh Posts (GET)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (postsState) {
                is Resource.Loading -> CircularProgressIndicator()
                is Resource.Success -> {
                    val posts = (postsState as Resource.Success).data
                    if (!posts.isNullOrEmpty()) {
                        Text("Fetched Posts:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        posts.forEach { post ->
                            Text(
                                text = "ID: ${post.id}, Title: ${post.title}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(Color.LightGray.copy(alpha = 0.2f))
                                    .padding(8.dp)
                            )
                        }
                    } else {
                        Text("No posts fetched yet.")
                    }
                }
                is Resource.Error -> {
                    val errorMessage = (postsState as Resource.Error).message
                    Text("Error: $errorMessage", color = Color.Red)
                }
            }

            // Separator
            Spacer(modifier = Modifier.height(24.dp))
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)
            Spacer(modifier = Modifier.height(24.dp))

            // POST Post Section
            Text("Create New Post (POST)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            var title by remember { mutableStateOf("") }
            var body by remember { mutableStateOf("") }
            var userId by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text("Body") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = userId,
                onValueChange = { userId = it },
                label = { Text("User ID (e.g., 1)") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.foundation.text.KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotEmpty() && body.isNotEmpty() && userId.isNotEmpty()) {
                        val userIdInt = userId.toIntOrNull()
                        if (userIdInt != null) {
                            viewModel.createPost(title, body, userIdInt)
                        } else {
                            Toast.makeText(context, "Please enter a valid User ID", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = createPostState !is Resource.Loading // Disable button while loading
            ) {
                if (createPostState is Resource.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Create Post")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            when (createPostState) {
                is Resource.Loading -> if (createPostState.data == null) Text("Creating post...", color = Color.Gray)
                is Resource.Success -> {
                    val post = (createPostState as Resource.Success).data
                    if (post != null) {
                        Text(
                            text = "Created Post (ID: ${post.id}):\nTitle: ${post.title}\nBody: ${post.body}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green.copy(alpha = 0.2f))
                                .padding(8.dp)
                        )
                    }
                }
                is Resource.Error -> {
                    val errorMessage = (createPostState as Resource.Error).message
                    Text("Creation Error: $errorMessage", color = Color.Red)
                }
            }
        }
    }
}
```

* `@Composable`: Marks a function as a Composable, the building block of Compose UI.
* `viewModel()`: A convenient way to get a ViewModel instance in Compose.
* `collectAsState()`: Collects values from a `StateFlow` and represents the latest value as a `State` object.
* `LaunchedEffect`: A composable that runs a side effect when its key (e.g., `createPostState`) changes. Used here to show `Toast` messages.
* UI elements: `Button`, `Text`, `OutlinedTextField`, `CircularProgressIndicator`, `Column`, `Spacer`, `Divider`, `Scaffold`, `TopAppBar`.
* State management: `remember { mutableStateOf("") }` for text field values.

**`MainActivity.kt`**

```kotlin
package com.example.retrofithiltcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.retrofithiltcomposeexample.presentation.ui.PostScreen
import com.example.retrofithiltcomposeexample.ui.theme.RetrofitHiltComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitHiltComposeExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PostScreen()
                }
            }
        }
    }
}
```

**Run the Application:**

At this point, you should be able to run the app.
* Click "Refresh Posts (GET)" to see a list of titles.
* Enter values for "Title," "Body," and "User ID" (e.g., 1), then click "Create Post." You should see the new post's details and a toast message.
* Observe the `Logcat` for network requests if `HttpLoggingInterceptor` is set to `BODY`.

---

## Part 2: Integrating Dagger Hilt

Now, let's refactor our project to use Dagger Hilt for dependency injection, making our code more modular, testable, and maintainable.

### 2.1 Hilt Application Class

We need to tell Hilt where to generate its components.

**`RetrofitHiltComposeApplication.kt`** (Create this file in your root package)

```kotlin
package com.example.retrofithiltcomposeexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RetrofitHiltComposeApplication : Application() {
    // Hilt will generate necessary code here.
    // You can add other application-wide initializations if needed.
}
```

**Update `AndroidManifest.xml`:**

Change the `android:name` attribute in the `<application>` tag to point to your new Hilt Application class.

```xml
<application
    android:name=".RetrofitHiltComposeApplication" // <--- Change this line
    android:allowBackup="true"
    android:dataExtractionRules="@xml/data_extraction_rules"
    android:fullBackupContent="@xml/backup_rules"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@android:style/Theme.Material.Light.NoActionBar">
    </application>
```

### 2.2 Hilt Modules for Data Layer

We'll create Hilt modules to provide instances of our `JsonPlaceholderApi`, `OkHttpClient`, `Retrofit`, and `PostRepository`.

**`di/AppModule.kt`** (Create a new package `di`)

```kotlin
package com.example.retrofithiltcomposeexample.di

import com.example.retrofithiltcomposeexample.data.remote.JsonPlaceholderApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // This module provides dependencies that live as long as the application
object AppModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton // Ensures only one instance of HttpLoggingInterceptor is created
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton // Ensures only one instance of OkHttpClient is created
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton // Ensures only one instance of Retrofit is created
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Provides
    @Singleton // Ensures only one instance of JsonPlaceholderApi is created
    fun provideJsonPlaceholderApi(retrofit: Retrofit): JsonPlaceholderApi {
        return retrofit.create(JsonPlaceholderApi::class.java)
    }
}
```

* `@Module`: Declares that this object is a Dagger module.
* `@InstallIn(SingletonComponent::class)`: Specifies the Hilt component where these bindings are installed. `SingletonComponent` means the provided instances will be singletons throughout the application's lifetime.
* `@Provides`: Annotates methods that tell Hilt how to create an instance of a particular type.
* `@Singleton`: Ensures that only one instance of the provided dependency is created and reused within its component scope.
* **Dependency Chain:** Hilt automatically resolves the dependencies: `Retrofit` needs `OkHttpClient`, which needs `HttpLoggingInterceptor`. Dagger Hilt figures out the order of provision.

### 2.3 Hilt Modules for Domain Layer (Repository and Use Cases)

Now we will bind our abstract `PostRepository` interface to its concrete `PostRepositoryImpl` and provide the use cases.

**`data/repository/PostRepositoryImpl.kt` (Updated for Hilt)**

Remove the `RetrofitClient` object and inject `JsonPlaceholderApi` directly into the constructor.

```kotlin
package com.example.retrofithiltcomposeexample.data.repository

import com.example.retrofithiltcomposeexample.data.model.PostDto
import com.example.retrofithiltcomposeexample.data.remote.JsonPlaceholderApi
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.domain.repository.PostRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // Make PostRepositoryImpl a singleton as it depends on a singleton API
class PostRepositoryImpl @Inject constructor( // Hilt will inject JsonPlaceholderApi here
    private val api: JsonPlaceholderApi
) : PostRepository { // Implement the domain interface

    override suspend fun getPosts(): List<Post> {
        val response = api.getPosts()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.map { dto -> // Map DTO to Domain Post
                Post(
                    userId = dto.userId,
                    id = dto.id,
                    title = dto.title,
                    body = dto.body
                )
            }
        } else {
            throw Exception("Failed to fetch posts: ${response.code()}")
        }
    }

    override suspend fun createPost(post: Post): Post {
        val postDto = PostDto( // Map Domain Post to DTO for request
            userId = post.userId,
            title = post.title,
            body = post.body
        )
        val response = api.createPost(postDto)
        if (response.isSuccessful && response.body() != null) {
            val createdDto = response.body()!!
            return Post( // Map DTO back to Domain Post for response
                userId = createdDto.userId,
                id = createdDto.id,
                title = createdDto.title,
                body = createdDto.body
            )
        } else {
            throw Exception("Failed to create post: ${response.code()}")
        }
    }
}
```

* `@Inject constructor()`: Hilt knows how to create `PostRepositoryImpl` instances because `JsonPlaceholderApi` is already provided by `AppModule`.
* `: PostRepository`: The implementation now explicitly implements the `PostRepository` interface from the domain layer.

**`di/RepositoryModule.kt`**

```kotlin
package com.example.retrofithiltcomposeexample.di

import com.example.retrofithiltcomposeexample.data.repository.PostRepositoryImpl
import com.example.retrofithiltcomposeexample.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Binds the concrete implementation to its interface
    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
}
```

* `@Binds`: Used in abstract modules to tell Hilt which implementation to use for an interface. This is more performant than `@Provides` for interface bindings.
* `abstract class RepositoryModule`: Modules using `@Binds` must be abstract classes.

**`di/UseCaseModule.kt`**

```kotlin
package com.example.retrofithiltcomposeexample.di

import com.example.retrofithiltcomposeexample.domain.repository.PostRepository
import com.example.retrofithiltcomposeexample.domain.usecase.CreatePostUseCase
import com.example.retrofithiltcomposeexample.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: PostRepository): GetPostsUseCase {
        return GetPostsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCreatePostUseCase(repository: PostRepository): CreatePostUseCase {
        return CreatePostUseCase(repository)
    }
}
```

* Hilt now knows how to provide `PostRepository` (from `RepositoryModule`), so it can inject it into the Use Cases.

**`domain/usecase/GetPostsUseCase.kt` (Updated for Hilt)**

```kotlin
package com.example.retrofithiltcomposeexample.domain.usecase

import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.domain.repository.PostRepository // Use the interface
import javax.inject.Inject // Inject annotation

class GetPostsUseCase @Inject constructor( // Hilt will inject PostRepository here
    private val repository: PostRepository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getPosts() // Repository now returns domain models
    }
}
```

**`domain/usecase/CreatePostUseCase.kt` (Updated for Hilt)**

```kotlin
package com.example.retrofithiltcomposeexample.domain.usecase

import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.domain.repository.PostRepository // Use the interface
import javax.inject.Inject // Inject annotation

class CreatePostUseCase @Inject constructor( // Hilt will inject PostRepository here
    private val repository: PostRepository
) {
    suspend operator fun invoke(post: Post): Post {
        return repository.createPost(post) // Repository now takes/returns domain models
    }
}
```
* `@Inject constructor()`: Hilt will now provide the `PostRepository` implementation.
* **No more manual mapping in Use Cases:** The mapping from DTO to Domain Model (and vice-versa) is now handled within the `PostRepositoryImpl`, keeping the Use Cases cleaner and focused purely on business rules.

### 2.4 Hilt-enabled ViewModel and Activity

**`presentation/viewmodel/PostViewModel.kt` (Updated for Hilt)**

```kotlin
package com.example.retrofithiltcomposeexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofithiltcomposeexample.domain.model.Post
import com.example.retrofithiltcomposeexample.domain.usecase.CreatePostUseCase
import com.example.retrofithiltcomposeexample.domain.usecase.GetPostsUseCase
import com.example.retrofithiltcomposeexample.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel // Hilt ViewModel annotation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject // Inject annotation

@HiltViewModel // Annotate ViewModel with @HiltViewModel
class PostViewModel @Inject constructor( // Use @Inject for constructor
    private val getPostsUseCase: GetPostsUseCase, // Injected by Hilt
    private val createPostUseCase: CreatePostUseCase // Injected by Hilt
) : ViewModel() {

    private val _postsState = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())
    val postsState: StateFlow<Resource<List<Post>>> = _postsState.asStateFlow()

    private val _createPostState = MutableStateFlow<Resource<Post>>(Resource.Loading())
    val createPostState: StateFlow<Resource<Post>> = _createPostState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            _postsState.value = Resource.Loading()
            try {
                val posts = getPostsUseCase()
                _postsState.value = Resource.Success(posts)
            } catch (e: Exception) {
                _postsState.value = Resource.Error("Error fetching posts: ${e.localizedMessage}")
            }
        }
    }

    fun createPost(title: String, body: String, userId: Int) {
        viewModelScope.launch {
            _createPostState.value = Resource.Loading()
            try {
                val newPost = Post(title = title, body = body, userId = userId)
                val createdPost = createPostUseCase(newPost)
                _createPostState.value = Resource.Success(createdPost)
            } catch (e: Exception) {
                _createPostState.value = Resource.Error("Error creating post: ${e.localizedMessage}")
            }
        }
    }

    fun resetCreatePostState() {
        _createPostState.value = Resource.Loading(null)
    }
}
```

* `@HiltViewModel`: This annotation tells Hilt to generate a ViewModel Factory for this ViewModel, allowing its dependencies to be injected.
* `@Inject constructor()`: Hilt will automatically provide `GetPostsUseCase` and `CreatePostUseCase` because we defined how to provide them in `UseCaseModule`.

**`MainActivity.kt` (Updated for Hilt)**

```kotlin
package com.example.retrofithiltcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.retrofithiltcomposeexample.presentation.ui.PostScreen
import com.example.retrofithiltcomposeexample.ui.theme.RetrofitHiltComposeExampleTheme
import dagger.hilt.android.AndroidEntryPoint // Hilt Activity annotation

@AndroidEntryPoint // Annotate Activity with @AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitHiltComposeExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PostScreen()
                }
            }
        }
    }
}
```

* `@AndroidEntryPoint`: This annotation generates a Hilt component for this Android class (Activity, Fragment, Service, BroadcastReceiver), allowing Hilt to inject dependencies into it (or into its `ViewModel`).

**`presentation/ui/PostScreen.kt` (No changes needed, but confirm import)**

The `PostScreen` Composable uses `viewModel()`. When Hilt is properly set up, `viewModel()` will automatically use the Hilt-generated ViewModel Factory to provide the `PostViewModel` with its injected dependencies. No explicit changes are needed in `PostScreen.kt` itself.

```kotlin
// Ensure this import is correct:
import androidx.lifecycle.viewmodel.compose.viewModel // This automatically integrates with Hilt when @HiltViewModel is used
```

### 2.5 Rebuild and Run

Clean the project (`Build -> Clean Project`) and then Rebuild (`Build -> Rebuild Project`). This ensures that Dagger Hilt's annotation processors run and generate the necessary code.

Run the application again. The functionality should be identical to Part 1, but now the entire Retrofit and Use Case setup is managed by Dagger Hilt, providing the benefits of dependency injection:

* **Testability:** You can easily provide mock implementations of `JsonPlaceholderApi` or `PostRepository` during unit tests.
* **Modularity:** Components are loosely coupled. Changes in how `Retrofit` is built don't affect the `ViewModel` or `UseCases`.
* **Maintainability:** Easier to understand the dependencies and how objects are created.
* **Scalability:** As your app grows, Hilt helps manage a complex dependency graph.

---

## Conclusion and Further Considerations

You've successfully implemented GET and POST requests using Retrofit with JSONPlaceholder, and then integrated Dagger Hilt to manage your dependencies following a Clean Architecture pattern with Jetpack Compose.

**Key Takeaways:**

* **Retrofit** simplifies network requests with intuitive annotations and coroutine support.
* **Clean Architecture** separates concerns, making your app more robust and easier to maintain.
* **Dagger Hilt** automates dependency injection, reducing boilerplate and improving testability.
* **Jetpack Compose** provides a declarative way to build UI that integrates well with reactive streams (like `StateFlow` from ViewModels).

**Next Steps and Enhancements:**

* **Error Handling Refinements:** Implement more granular error handling (e.g., distinguishing between network errors, API errors, bad requests). You could return specific error objects or use a custom sealed class for `Resource` to carry error codes or messages.
* **Pagination:** For `getPosts`, you might want to fetch posts in chunks.
* **Authentication:** Integrate authentication tokens for protected APIs.
* **Caching:** Implement data caching strategies (e.g., using Room database).
* **UI/UX:** Add more sophisticated UI elements, loading indicators, empty states, and better error display.
* **Testing:** Write unit tests for your Use Cases, Repositories, and ViewModels (which Hilt makes much easier).
* **Kotlin Flow for Repository:** Instead of `suspend` functions directly returning `List<Post>`, your repository could return `Flow<List<Post>>` to provide continuous updates.
* **Mapper Objects:** For more complex DTO to Domain model mappings, create dedicated mapper classes.

This detailed tutorial provides a solid foundation for building robust and well-architected Android applications. Happy coding!
