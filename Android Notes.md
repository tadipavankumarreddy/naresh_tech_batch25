## Communication
- fill out this form to keep in touch [link](https://forms.gle/9p8hom1m2eQ4PK669)

- Feel free to explore android app development using Java by referring to this exclusive e-book written by me [Link](https://android-app-development-documentation.readthedocs.io/en/latest/)


## Jetpack UI Compose

Jetpack compose is android's modern toolkit for building native UI. It simplifies and accelerates UI development on android by using declarative approach - you describe what the UI should look like, and compose takes care of the rest. Instead of Modifying the UI elements imperatively (like with XML + `findViewById()`), you simply declare the UI using kotlin code. 

1. Jetpack Compose Basics
- @Composable : An Annotation used on a function that defines UI in a declarative way.
- Recomposition: When state changes, Composables automatically update the UI. 
- Modifier: Used to decorate or add behavior (e.g., padding, size, click listeners)

2. Layouts:
- Column, Row, Box : Used to arrange the UI components. 
- Arragement & Alignment control positioning. 

***Please complete Assignment 1***

***mutableStateOf()***  is a function used to create a state holder that tracks changes to a variable. When the variable changes, it automatically triggers UI updates in Jetpack Compose.

**Specifically:**
- It is often created using mutableStateOf()
- When you change a mutable state, any composables that read the state will automatically recompose (refresh)

**remember{}** -> keep the state alive across recompositions.

## ViewModel
ViewModel in android is a calss that is used to store and manage UI releted Data in a life cycle aware way.

***Why is it important ?***
- In android, things like Activity and Fragment can be destroyed and recreated (for Example, when you rotate the phone. )
- Normally, if you store data inside an activity (say, a list of names), it gets lost when the activity is recreated. 
- ViewModel helps by holding the data seperately  so that even if your activity/fragment is recreated, the data survives. 
- View Model is part of Android Jetpack libraries. 
- Combine ViewModel + mutableStateOf() to make jetpack compose apps powerful.

### Register me App Notes + UI Composables Explained

**What we Designed**  
![What we Designed](/registermephase1.png)

**1. Project Setup**
- MainActivity inherits from ComponentActivity, a standard class in Jetpack Compose for activities.

- enableEdgeToEdge() is called to render content under the system bars (status & navigation bars).

- setContent {} block sets the screen's UI using Compose.

🔗 Reference: [ComponentActivity - Android Docs](https://developer.android.com/reference/androidx/activity/ComponentActivity)  
🔗 Reference: [Edge-to-Edge Design - Android Docs](https://developer.android.com/develop/ui/views/layout/edge-to-edge)  

**2. Registration Screen UI**  
We define a Composable function `RegistrationScreen()` that builds the UI.

**a. State Management**
```kotlin
var name by remember { mutableStateOf("") }
var age by remember { mutableStateOf("") }
var gender by remember { mutableStateOf("") }
```
- Using `remember` + `mutableStateOf` to manage **local state** for user inputs like Name, Age, and Gender.

- These states will auto-trigger recomposition when changed.

🔗 Reference: [State in Compose - Official Docs](https://developer.android.com/jetpack/compose/state)

**b. Column Layout**
```kotlin
Column(
    modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())
)
```
- We use a Column to arrange components vertically.

- We apply:

  - Padding: To create spaces inside.

  - FillMaxSize(): To take full screen.

  - Background(Color.White): White background.

  - WindowInsets: Adjust padding dynamically for status/navigation bars to avoid UI overlaps.

🔗 Reference: [Column Layout - Android Docs](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Column)  
🔗 Reference: [Handling Insets - Compose](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/WindowInsets)

**c. Heading (Title Text)**
```kotlin
Text(
    text = "Register Me!",
    fontFamily = FontFamily.Cursive,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontSize = 30.sp,
    modifier = Modifier
        .fillMaxWidth()
        .rotate(-10f)
        .padding(10.dp)
)
```

- Display a stylish heading:

  - **Font:** Cursive & Bold

  - **Text Size:** 30 sp

  - **Alignment:** Center

  - **Rotation:** Tilted (-10 degrees) for fun visual appeal.

🔗 Reference: [Text Composable - Compose Docs](https://developer.android.com/jetpack/compose/text/text)

**d. Name Input Field**
```kotlin
TextField(
    value = name,
    onValueChange = { name = it },
    label = { Text("Enter your name") },
    modifier = Modifier.fillMaxWidth()
)
```

- Basic `TextField` to capture name input.

- Expands to full width

🔗 Reference: [TextField - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/TextField)

**e. Age Input Field (Outlined)**
```kotlin
OutlinedTextField(
    value = age,
    onValueChange = { age = it },
    label = { Text("Enter your Age") },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    modifier = Modifier.fillMaxWidth()
)
```
- **OutlinedTextField** gives a bordered style.

- Forces **numeric keyboard** using **KeyboardType.Number** for entering Age.

🔗 Reference: [OutlinedTextField - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/OutlinedTextField)

**f. Gender Selection (Radio Buttons)**
```kotlin
Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceAround
) {
    Text(text = "Gender")

    RadioButton(selected = gender == "Male", onClick = { gender = "Male" })
    Text("Male")

    RadioButton(selected = gender == "Female", onClick = { gender = "Female" })
    Text("Female")
}
```
- Row layout used to arrange Gender selection options horizontally.

- Two RadioButton options:

  - Male

  - Female

- On click, gender value gets updated accordingly.
- 
🔗 Reference: [RadioButton - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/material/RadioButton)

**3. Previewing in Android Studio**
```kotlin
@Preview(showBackground = true)
@Composable
private fun RegistrationScreen()
```
- `@Preview` annotation is used to see the layout without running the app.

🔗 Reference: [@Preview - Compose Docs](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/Preview)

## Intents in Android
Intents are messaging objects that are sent to the android OS, the OS then takes a call to address these requests. 

**Intents**  
- Explicit Intents
  - An Explicit intnet directly specifies the target component (Activity, Service, etc.,) it wants to start. You can use this when you know exactly where you want to go in your own app.
  - Eg: Navigating from one activity to another activity

- Implicit Intents
  - An Implicit intent does not name a specific component. Instead, it declares a general action to perform, and the android system find the best app to handle the action.
  - Eg: Opening a webpage in a browser

### What is a context in Android ?
Context is a handle to the system. It gives your app access to everything android provides - like resources, launching activities, accessing databases, systems and services,etc.,

**Types Of Context**  
1. Activity Context (This is inside an Activity)
   - Tied to the Activity's lifecycle
   - Use when dealing with UI or starting other activities.
2. ApplicationContext (getApplicationContext())
   -  Tied to the app's lifecycle.
   -  Use when you don't need the UI or Activity (e.g., from a background service)

**Activity Result Launcher**  
***What is it ?***
ActivityResultLauncher is a modren way to Start an **Activity and get a result back** from it, replacing the older ***startActivityForResult()*** and ***onActivityResult()*** methods. 

***Why use it ?***
- Simpler and lifecycle-aware
- Prevents memory leaks
- Automatically handles configuration changes (like Screen rotations)
- Clean Separation of Logic

***Code Steps to Follow***

**Step 1:**  
```kotlin
val launcher  = registerForActivityResult{(ActivityResultContracts.StartActivityForResult())
{result:ActivityResult->
val data = result.data?.getStringExtra("key")
Toast.makeText(applicationContext,data,Toast.LENGTH_LONG).show()
}
```

**Step 2**
```kotlin
 val i = Intent(applicationContext,SecondActivity::class.java)
i.putExtra("KEY",name)
/*startActivity(i)*/
launcher.launch(i)
```
**Step 3**
```kotlin
val resultIntent = Intent()
resultIntent.putExtra("key","Thank you!")
setResult(RESULT_OK,resultIntent)
finish()
```

### Common Intents (Implicit Intents present in most of the devices)

[Follow this link to use more of them](https://developer.android.com/guide/components/intents-common)

***Complete Assignment 2***

---

## Jetpack Compose Navigation – **Elaborated Notes**

### Introduction to Navigation in Jetpack Compose

Navigation is a fundamental part of Android app development, allowing users to move between different screens (composables). Jetpack Compose provides a robust and modern way to handle navigation using the **Navigation Component for Compose**.

Jetpack Compose uses a **declarative approach** to UI, and its navigation system follows the same principle, using a **NavHost**, **NavController**, and **NavGraph** to manage destinations.

---

### Key Components

#### 1. **NavController**

* Acts as the central API for navigation.
* Keeps track of the back stack of composables.
* Created using:

  ```kotlin
  val navController = rememberNavController()
  ```

#### 2. **NavHost**

* Hosts all the destinations.
* Needs:

  * A `navController`
  * A `startDestination`
* Example:

  ```kotlin
  NavHost(navController = navController, startDestination = "home") {
      composable("home") { HomeScreen() }
      composable("details") { DetailsScreen() }
  }
  ```

#### 3. **composable()**

* Defines a destination in the navigation graph.
* The route string identifies each screen.
* You can pass arguments via route strings or using `navArguments`.

---

### Basic Navigation Example

```kotlin
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}
```

In your `HomeScreen`, you can navigate like this:

```kotlin
Button(onClick = { navController.navigate("profile") }) {
    Text("Go to Profile")
}
```

---

### Passing Data Between Screens

#### 1. **Using Route Parameters**

```kotlin
NavHost(navController, startDestination = "home") {
    composable("details/{userId}") { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId")
        DetailsScreen(userId)
    }
}
```

Navigate using:

```kotlin
navController.navigate("details/123")
```

#### 2. **Using `navArgument()` for type safety**

```kotlin
composable(
    route = "details/{userId}",
    arguments = listOf(navArgument("userId") { type = NavType.StringType })
) { backStackEntry ->
    val userId = backStackEntry.arguments?.getString("userId")
    DetailsScreen(userId)
}
```

---

### Navigating Back

Use `navController.popBackStack()` to go back to the previous screen:

```kotlin
Button(onClick = { navController.popBackStack() }) {
    Text("Back")
}
```

---

### Deep Dive: Navigation with ViewModel

Compose encourages a unidirectional data flow and clean architecture. For passing ViewModels:

* Use `hiltViewModel()` or `viewModel()` in each screen.
* Avoid sharing ViewModels between unrelated composables via navigation.

---

### Navigation with Bottom Navigation Bar

You can integrate navigation with UI elements like bottom navigation:

```kotlin
val navController = rememberNavController()
Scaffold(
    bottomBar = {
        BottomNavigation {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, null) },
                selected = currentRoute == "home",
                onClick = { navController.navigate("home") }
            )
            // Add more items...
        }
    }
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
    }
}
```

---

### Advanced Topics to explore

* **Nested Navigation Graphs**: Managing large apps with modular screen groups.
* **PopUpTo & Inclusive**: Fine-tuning backstack behavior.
* **Safe Args**: Not yet available in Compose. Handle with manual argument parsing.
* **Animated Navigation**: Using `accompanist-navigation-animation` for transitions.
* **Navigation with State Preservation**: Using `rememberSaveable`.

---

### Libraries and Tools

* `androidx.navigation:navigation-compose`
* Optional: `com.google.accompanist:accompanist-navigation-animation`

---

### Summary

| Concept                   | Description                                             |
| ------------------------- | ------------------------------------------------------- |
| `NavController`           | Manages backstack and navigation actions                |
| `NavHost`                 | Declares and hosts all destinations                     |
| `composable`              | Defines a destination/screen                            |
| Passing Arguments         | Via route strings and `navArguments`                    |
| Navigation with ViewModel | Use scoped ViewModels to avoid tight coupling           |
| Bottom Navigation         | Can be integrated with Compose navigation for tabbed UI |

---

### App Flow
1. HomeScreen -> Selecting the brand (Oppo, samsung, Nothing, etc.,)
2. VariantScreen -> Select Variant (8GB|128GB, 8GB|256GB)
3. Summary Screen -> Display Brand + Variant Selected.

***Please complete Assignment 3***
---

### 🏁 Bonus Challenge (Optional):

Add a “Clear Selection” button on the SummaryScreen to:

* Clear the ViewModel state
* Navigate back to HomeScreen

---

### 💡 Tips:

* Use `remember` for temporary state (radio button selection).
* Use `mutableStateOf` and `State` in ViewModel to observe changes.
* Wrap everything in a `NavHost` inside your `MainActivity`.

---

#### Sealed Classes in Kotlin
Imagine that you're designing a system to handle different types of results from an operation. Let's say this operation can either succeed with a valu, fail with an error, or still be in loading.

Without using sealed classes

```Kotlin
class Result {
    var d:String? = null
    var error:String? = null
    var isLoading:Boolean = false
    
    companion object {
        fun success(v:String):Result{
            val result = Result()
            result.d = v
            return result
        }
        
        fun failure(error:String):Result{
            val result = Result()
            result.error = error
            return result
        }
        
        fun loading():Result{
            val result = Result()
            result.isLoading = true
            return result
        }
    }
}


fun processResult(result:Result){
    if(result.isLoading){
        println("Loading...")
    } else if(result.d!=null){
        println("Success: ${result.d}")
    } else if(result.error!=null){
        println("Error: ${result.error}")
    }
}

fun main(){
    val successResult = Result.success("Data Fetched!")
    val errorResult = Result.failure("Network Error")
    val loadingResult = Result.loading()
    
    processResult(successResult)
    processResult(errorResult)
    processResult(loadingResult)
		   
}
```

This approach works, but it has its own drawbacks:
- It's easy to have invalid states

Sealed Classes are those classes that restrict the possible subclasses of a class within the same file. This gives you more control and makes your code safer. 

```kotlin
sealed class Result {
   data class Success(val v:String):Result()
   data class Failure(val error:String):Result()
   object Loading : Result()
}


fun processResult(result:Result){
    when (result){
        is Result.Success -> println("Succes:${result.v}")
        is Result.Failure -> println("Error:${result.error}")
        is Result.Loading -> println("Loading...")
    }
}

fun main(){
    val successResult = Result.Success("Data Fetched!")
    val errorResult = Result.Failure("Network Error")
    val loadingResult = Result.Loading()
    
    processResult(successResult)
    processResult(errorResult)
    processResult(loadingResult)
		   
}
```

**Key things to note:**
- We declare `Result` as `sealed class`.
- The different possible states (`Success`,`Failure`,`Loading`) are defined as subclasses within the `Result` Sealed class.
- `Success` and `Failure` are `data class`es, which automatically provide useful methods like `equals()`, `hashCode()`, and `toString()`.
- `Loading` is an `object` because it doesn't need any additional data - There's only one "Loading" state. 
- In the processResult function, the when expression is now exhaustive. The kotlin compiler knows all the possible subtypes of a `Result` because they are all defined within the same file. This means you don't need an else branch, and the compiler will even warn you if you forget to handle any of the subtypes!

Why Sealed Classes ?
- Type Safety is Enhanced: Sealed classes restrict the possible types, making it impossible to represent invalid or illogical states. 
- Exhaustive When expressions: This is a huge advantage. When you use a when expression with sealed class, the compiler ensures you handle all the possible subtypes. This makes your code more robust and less prone to runtime errors because you won't forget to handle specific case. 
- Improved Code readability and Maintainability
- Better control over inheritance.

### Jetpack Compose : Displying large data sets.

- Jetpack compose provides `LazyColumn`, `LazyRow` and `LazyVerticalGrid` for efficiently displaying large or infinite lists. 
- These components render only the items visible on the screen, improves performance & memory usage - Similar to RecyclerView.

[Official Docs](https://developer.android.com/develop/ui/compose/lists)

**Steps**

1. Prepare Data
2. Create a Design for each item  that you want to display
3. Use LazyColumn, LazyRow and LazyVerticalGrid to show the items. 

[RecyclerView](https://docs.google.com/presentation/d/1nFJqH0OSSZmjaycRzEGE6vvsm6jlxghQyoO15KKbkwc/edit?slide=id.gf0dd569a47_0_129#slide=id.gf0dd569a47_0_129)

### Android Prototyping
To understand the visual representation of our app's functionality, it is a great idea to create prototypes. 

1. Figma
2. Adobe XD
3. Android Studio (XML + Navigtation Component)
4. Balsamiq or Wireframe
5. proto.io/Marvel/inVision

For Shoes app, the data is ready as a [json file](https://raw.githubusercontent.com/tadipavankumarreddy/AndroidUsingKotlin-Batch26/refs/heads/master/shoesdata.json) 

Going to use
- Coroutines 
- HttpsURLConnection
- Kotlinx.seralization
- LazyVerticalGrid

### MVVM (Model, View, ViewModel)
1. Model Layer
   - This Layer is responsible for **Data classes**, **Business Logic**, and the **data Sources (network/local)**
     - `Shoe.kt`
       - Represents the **data model**
     - `ShoeRepository.kt`
       - Responsible for **fetching data** from the network. This acts as a **repositiory**, which is a standard part of Model Layer in MVVM.
     - `NetworkCheck.kt`
       - Contains a helper function `isInternetAvailable()` for network capability checks- a valid utility for data/network access layer.
2. View Layer
   - This is the **Jetpack compose UI** - responsible only for rendering the UI based on data provided by the ViewModel. 
     - `MainActivity.kt`
       - Initializes the `ShoeViewModel` and passes it down.
       - Checks for Internet and shows a dialog.
       - Launches `ShoeApp` which is a composable based on the network availability. 
     - `ShoeApp.kt`
       - uses `NavController` for screen Navigation.
       - Displays loading state or routes to `ShoeGrid` and `ShoeDetailsScreen`.
     - `ShoeGrid.kt`/ `ShoeItem.kt`/`ShoeDetailsScreen.kt`
       - Present the UI logic only.
3. ViewModel Layer
   -  This is a bridge between  Model and View, holding the UI-releated Data in a lifecycle aware manner
      -  `ShoeViewModel.kt`
         -  Holds the state of the shoe list (shoe_list) and loading flag (isLoading)
         -  Indicates data fetching via `ShoeRepository`
         -  Provides business logic `getShoeById()`.

### SQL
***SQL - Structure Query Language***
- SQL is a relational Database where the data is organized in Rows and Coloumns (Table)
- Operations
  - Storing
  - Retrieving
  - Modifying

[Click Here](https://sqliteonline.com/) to practice the commands below.

###### Table Sample used 
***Table name: students_data***
student_id|student_name|student_age
----------|-----------|-----------
1|Pavan Kumar|28
2|Md Naveed|22
3|Chester|21
4|Darshana|20
  
SQLite - Is a Database which is light weight database
- It is embedded in Android by default.

###### Main Classes that we have on android to make use of SLQLite are
- SQLiteDatabase
    - It is used to perform all SQLite operations (database Operations)
- SQLiteOpenHelper
    - It is used for database creation and version Management

###### CRUD - Create, Read, Update, Delete
###### Create a table
```SQL
CREATE table students_data(student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_name TEXT, student_age INTEGER);
```

###### Insert Data into the Table
```SQL
INSERT INTO students_data(student_name, student_age) VALUES("Pavan Kumar", "30");
```

###### Read the table with Select Command
- To read all the values of the table
```SQL
SELECT * FROM students_data;
```
- To read only the specific rows in the table
```SQL
SELECT student_id, student_name from students_data;
```
- To Read database values on a ```where``` condition
```SQL
select student_id,student_name from students_data where student_age>=26;
```

```SQL
SELECT * from students_data ORDER BY student_name ASC;
```

###### Update Command
```SQL
UPDATE students_data set student_name = "TPK Reddy" where student_id = 1;
```

###### Delete Command
```SQL
DELETE from students_data where student_id = 1;
```

### Room Database
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:

- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.

We have built a `Note Taking App`, refer the entire notes [here](/Note%20Taking%20App.md) 

### When we can use ViewModel without needing additional dependencies, why did we add the dependency to our project ?

You can use ViewModel without additional dependencies in "Non-Compose" apps because it is part of Android's Jetpack "androidx.lifecycle" library. 

But When you use jetpack compose, you need `lifecycle-viewmodel-compose` to bridge the gap between ViewModel and Compose. 

**Why do you need this dependency?**

- Compose has no built in knowledge of ViewModel
  - Jetpack compose is UI-First and Declarative, while viewmodel is part of the architecture componenents. To Make them work together - like using viewModel() inside a @composable - You need
```kotlin
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
```

This dependency gives you
- viewModel() function for Composables.
- Seamless lifecycle-aware integration
- Ability to scope viewModel to Naviagtion graphs or composable interactions. 

***Please complete Assignment 4***

---

# 📘 Android Notifications with Jetpack Compose – Complete Notes

---

## 📌 1. **What are Notifications in Android?**

**Notifications** in Android are messages that appear **outside the UI of your app**, alerting the user about **important information**, **updates**, or **actions** they might want to take. They typically appear in the **notification drawer** or as **heads-up popups** (on newer versions).

### 🔍 Why Are They Important?

* Keep users engaged with timely updates.
* Notify about background work, reminders, messages, or downloads.
* Improve user experience by offering actions without opening the app.

---

## 🧱 2. **Structure of a Notification**

A basic notification contains:

| Part             | Description                                  |
| ---------------- | -------------------------------------------- |
| Small Icon       | Required icon shown in status bar and drawer |
| Title            | Main title of the notification               |
| Content Text     | Short message or summary                     |
| Timestamp        | When the notification was posted             |
| Optional Actions | Buttons like Reply, Mark as Read, etc.       |
| Style            | BigTextStyle, InboxStyle, MediaStyle, etc.   |

---

## 🎨 3. **Notification Styles**

Android provides several styles to enhance how notifications appear:

| Style             | Description                       |
| ----------------- | --------------------------------- |
| `BigTextStyle`    | For long messages                 |
| `InboxStyle`      | For multiple lines of content     |
| `BigPictureStyle` | For large images                  |
| `MediaStyle`      | For media playback (music, video) |
| `MessagingStyle`  | For chat apps                     |

📌 *You can customize notifications even more using custom layouts, but most use built-in styles.*

---

## 🔔 4. **What is a Notification Channel? (Android 8.0+)**

Starting from **Android 8.0 (API 26)**, you must **register a Notification Channel** before posting notifications.

### Why?

* To group similar types of notifications (e.g., Chats, Alerts).
* Users can control **sound, vibration, importance** per channel.

### Key Properties:

* `channelId`: Unique ID for the channel
* `name`: Visible to users
* `importance`: Determines interruptiveness (High, Default, Low, Min)

---

## ✅ 5. **Setup in Android Studio**

### Step 1: Add Permission (Android 13+)

```xml
<!-- AndroidManifest.xml -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
```

### Step 2: Request Permission (Runtime - API 33+)

```kotlin
ActivityCompat.requestPermissions(
    this,
    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
    1
)
```

---

## ✅ 6. **Create a Notification Channel**

```kotlin
fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "General Notifications"
        val descriptionText = "Includes all general notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("channel_id", name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
```

📌 Call this inside `MainActivity.onCreate()`.

---

## ✅ 7. **Build a Simple Notification**

```kotlin
fun showSimpleNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Welcome Student!")
        .setContentText("Your first Jetpack Compose notification is here.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        notify(1001, builder.build())
    }
}
```

---

## ✅ 8. **Trigger Notification from Compose UI**

```kotlin
@Composable
fun NotificationButton() {
    val context = LocalContext.current

    Button(onClick = { showSimpleNotification(context) }) {
        Text("Show Notification")
    }
}
```

---

## ✅ 9. **Add Action Button to Notification**

```kotlin
fun showNotificationWithAction(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context, 0, intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Tap to Open")
        .setContentText("This notification has an action.")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    NotificationManagerCompat.from(context).notify(1002, builder.build())
}
```

---

## ✅ 10. **Use BigTextStyle for Expanded Message**

```kotlin
fun showBigTextNotification(context: Context) {
    val bigText = "This is a very long message that will be shown when the notification is expanded. You can use it to show more information to the user."

    val builder = NotificationCompat.Builder(context, "channel_id")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("BigTextStyle Example")
        .setContentText("This is a short preview.")
        .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    NotificationManagerCompat.from(context).notify(1003, builder.build())
}
```

---

## 🛠️ 11. **Full Setup in MainActivity (Jetpack Compose)**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create Notification Channel once
        createNotificationChannel(this)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NotificationButton()
                }
            }
        }
    }
}
```

---

## 📦 12. Optional: Multiple Channels Example

```kotlin
createChannel(context, "ch_chat", "Chat Messages")
createChannel(context, "ch_alert", "Critical Alerts")

fun createChannel(context: Context, id: String, name: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}
```

---

## 📚 Summary Table

| Feature                  | API/Method                              |
| ------------------------ | --------------------------------------- |
| Create Channel           | `NotificationChannel`                   |
| Post Notification        | `NotificationManagerCompat.notify()`    |
| Use from Compose         | `LocalContext.current` + `Button()`     |
| Add Action               | `PendingIntent` + `.setContentIntent()` |
| Expand Text              | `BigTextStyle()`                        |
| Permission (Android 13+) | `POST_NOTIFICATIONS` + Runtime request  |

---

## 🧪 Homework for Students

1. Create a **notification** using `InboxStyle`.
2. Add a **Reply Action** using `RemoteInput` (for chat apps).

---

### Pending Intent
A Pending intent is a token that you give to another applicaiton (like system, AlarmManager, NotificationManager , etc.,) which allows the application to execute **your App's Code** at a later time, on **Your app's behalf**, even if your app is not running. 

**Why is it needed?**
Android apps run in a sandbox. so, one app cannot directly execute code from another app. Pending intent acts as a permission wrapper, allowing system services or other apps to perform operations using your app's identity and permissions. 

**For Example**
- Sending a notification that opens your app when clicked.
- Scheduling an alarm using AlarmManager
- Creating home screen widget that interacts with your app. 

**How does Pending Intent Works ?**

It wraps an Intent, and you specify the operation
- Start Activity
- Start Service
- Start Broadcast

Instead of executing the code now, It gives the system a handle to defer the execution. 

**Types**
- getActivity() - launches an Activity
- getService() - launches a Service
- getBroadcast() - sends a broadcast
- getForegroundService() - Start a foreground service (API 26+)

**Important Flags**
- `FLAG_UPDATE_CURRENT` : Updates the existing pending intent with new extras
- `FLAG_CANCEL_CURRENT`: Cancels the existing pending intent and creates a new one. 
- `FLAG_NO_CREATE`: It returns null if no matching pending intent exists. 
- `FLAG_ONE_SHOT`: can be used only once
- `FLAG_IMMUTABLE` It prevents the intent from the existing pending intent from being modified.
- `FLAG_MUTABLE`: allows the intent to be modified. 


### Broadcast Receiver
[slides](https://docs.google.com/presentation/d/1qF9Yeau7uHIP7_aOHWgPU_RnfxACZzGyAZIzcJWz0R0/edit?slide=id.g3cba478010_0_13#slide=id.g3cba478010_0_13)

[Link to Official Documentation](https://developer.android.com/develop/background-work/background-tasks/broadcasts)


### Firebase

**What is Firebase ?**
It is a Backend-as-a-service (BaaS) platform developed by Google that provides developers with tools to build and scale apps quickly - especially for Web and Mobile Platforms.

**Firebase Core Features**
1. [Firebase Authentication](https://firebase.google.com/docs/auth)
   -   Helps you sign in users securely
   -   Supports: Email/Password, [Google](https://firebase.google.com/docs/auth/android/google-signin), Facebook, Phone, etc.,
2. Cloud Firestore
   - A NoSQL cloud database to store and sync data in real time.
   - Supports Offline access
   - Scales Automatically
   - Stores data in Collections & Documents (like Json Format)
3. Realtime Database
   -   Also a NoSQL database but older than Firestore
   -   Syncs data in realtime across clients
4. Firebase Cloud Storage
   -   For uploading and sharing files (images, videos, documents)
   -   Based on Google Cloud Storage
   -   Secure file uploads/downloads via firebase Auth
5. Firebase Cloud Functions
   -   Run backend code (Node.js) without managing servers.
   -   Useful for sending notifications, auto-processing, triggers etc.,
   -   ex: send an email to the user on signup
6. Firebase Hosting
   -   Free & fast web hosting for static websites (HTML,CSS, JS)
   -   Supports custom domains
   -   Uses CDN for fast global access
7. Firebase Cloud Messaging (FCM)
   - Used to send push notifications to Android, Ios, and web users.
   - Free and Scalable
   - can target topics, specific devices or user groups. 
8. Firebase Analytics
   -   Tracks user behavior and app usage
   -   Integrated with Google Analytics.
   -   Helps improve user engagement and retention. 

[Read about all firebase features](https://firebase.google.com/docs)

### FireStore Database
Cloud firestore is a NoSQL cloud database provided by Firebase (part of Google Cloud Platform). It is designed to store and sync data for client and server side development
- Fully Managed
- Scales automatically
- Real-time synchronization
- Designed for mobile, web, and server apps

#### Firestore vs realtime databases

feature|Firestore|realtime databases
---|---|---
data model|document-collection|JSON Tree
Queries|Indexed, complex queires supported|Shallow Queries Only
Offline Support|Yes (android, web and ios)|yes
Real-time updates|yes|yes
Scalability|more scalable|less scalable
Multi-region support|yes|No

#### Core Concepts
Collections & Documents
- Firestore stores data in Documents, which are organized into Collections
- A Document is a light weight record that contains key-value pairs
- Each Collection can contain multiple documents
- Documents can also contain sub collections.

```javascript
users (collection)
|
--userId (Document)
    |- name: "pavan"
    |- email:"pavan@gmail.com"
    |
    _ orders (subcollection)
        |-item:"mobile"
        |-price:12000
```

No SQL Nature
- Firestore is schema less
- Each document can contain any fields and subcollections

#### data types
- String
- Numbers (Integers or Double)
- Boolean
- Map (nested Objects)
- Array
- Null
- TimeStamp
- GeoPoint
- Reference (another document)

#### Firebase Operations
Basic CRUD
- Create/set : set() - Creates or overwrites a document
- Read: get() - fetch document or collection
- Update: update() - Modify a specific document or a field
- Delete: delete() - removes document or field.

```javascript
//set
db.collection("users").doc("pavan").set({
    name:"pavan",
    age:33
})

// update
db.collection("users").doc("pavan").update({
    age:34
})

// Get
const doc = await db.collection("users").doc("pavan").get();
console.log(doc.data())

// Delete
db.collection("users").doc("pavan").delete()
```

[Read more about Firestore database](https://firebase.google.com/docs/firestore)

### Retrofit
Retrofit turns your HTTP API into a Java (or Kotlin) interface

[Refer Official Documentation](https://square.github.io/retrofit/)

Retrofit is the class through which your API interfaces are turned into callable objects. By default, Retrofit will give you sane defaults for your platform, but it allows for customization.

**Converters**  
By default, Retrofit can only deserialize HTTP bodies into OkHttp’s ResponseBody type, and it can only accept its RequestBody type for @Body.

Converters can be added to support other types. Sibling modules adapt popular serialization libraries for your convenience.

**Built-in converters**

**Gson:** com.squareup.retrofit2:converter-gson  
**Jackson:** com.squareup.retrofit2:converter-jackson
**Moshi:** com.squareup.retrofit2:converter-moshi  
**Protobuf:** com.squareup.retrofit2:converter-protobuf  
**Wire:** com.squareup.retrofit2:converter-wire  
**Simple XML:** com.squareup.retrofit2:converter-simplexml  
**JAXB:** com.squareup.retrofit2:converter-jaxb  
**Kotlin serialization:** com.squareup.retrofit2:converter-kotlinx-serialization  
**Scalars (primitives, boxed, and String):** com.squareup.retrofit2:converter-scalars   

**Dependencies**
```kotlin 
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-gson:3.0.0")

```

**JSONPlaceHolder**  
This is a fake RestFul Webservice that you can use to test if you are able to make netwrok requests.  
[Link](https://jsonplaceholder.typicode.com/)

**Postman**   
Postman is a tool that you can use online or install it on the device to check if the REST APIs are working as per the requirement  
[link](https://www.postman.com/)

**What are POST & GET ?**   
These are fundamental HTTP (Hypertext Transfer Protocol) methods used to communicate with web servers. They are essential for any app that needs to fetch data from an API or send data to server. 

***GET Request***
- Purpose: Primarily used to retrieve data from server. It's like asking a server for information without charging anything on the server. 
- Data Transmission: Data is sent as URL Parameters (Query Strings)
- Visibility : The data is visible in the URL, browser history, server logs and can be bookmarked or cached. 
- Security: Less secure for sensitive data because the data is exposed in the URL. While Https encrypts the entire connection, the data itself is still part of the publicly visible URL.

***When to use GET in Android ?***
- Fetching user profile
- Retrieving a list of products
- Searching for information
- Loading a webpage

***POST Request***
- Purpose: Primarily used to send data to the server to create or update a resource. It's like submitting information to the server that will likely cause a change. 
- Data Transmission: Data is sent in the request body, which is seperate from the URL. 
- Visibility: The data is not visible in the URL, providing a higher level of privacy and security compared to GET. 

***When do we use POST***
- submitting a form (e.g., user registration, login)
- Uploading of files (images, documents)
- Creating a new resource on the server
- Sending sensitive information. 

#### What is lazy{...} and lateinit ?

`lazy` & `lateinit` are both kotlin features designed to defer the initialization of properties, but they cater to different scenarios and have distinct characteristics. Choosing between them depends on how and when you expect the property to be initialized. 


`lateinit`
- Means Late initialization. You are promising the kotlin compiler that you will initialize the property before you use it.
- **Applicability** 
  - Can only be used with `mutable` properties (`var`)
  - can only be used with non-nullable types
  - can only be used with non-primitive types (e.g, `String`,`MyClass`, `Context`, `Retrofit`) you cannot use it for `Int`, `Boolean`, `Double`, etc.,
- **Initialization**
  - must happen externally, usually in lifecycle method like `onCreate` or on a constructor, or a dependency injection framework.
  - It is not `Thread-safe` by default. if multiple threads try to initalize it simultaneously, you will run into issues. 

`lazy`
- Means "lazy initialization". The property will be initialized only on it's first access. 
- **Applicability** 
  - can only be used with immutable properties (`val`)
  - can only be used with non-nullable types
  - can be used with `Any type`, including the primitives.
- **Initialization**
  - The initialization logic is defined with in the `lazy{...}` lambda block. 
  - The initialization only happens `ONCE`(on the first access), and the computed value is then stored (memorized) for all subsequent accesses.
  - It is `Thread safe` by default.

In Essence:
- use `lateinit` when you know the property will be initalized externally at some point before use, and it needs to be mutable. 
- use `lazy` when the initialization logic can be defined right at the declaration, and you want to defer its creation until it's actually needed for a read-only property.

***For JsonTypicode - Retrofit App***
- data: Contains the data models, API Interfaces, and repository implementations
  - model: Data classes for API requests/responses
  - remote: Retrofit API interface
  - Repository: implementation of data sources (e.g., calling API)
- domain: Contains the business logic, use cases, and abstract interfaces
  - model: Domain - specific data classes (if different from data layer)
  - repository - abstract interface for data operations
  - use case - Business logic for specific operations
- presentation: Contains UI related code (Compose Composables, viewmodels)
  - UI: Composable Functions
  - ViewModel
- Util


## Alarm Manager in Android
Alarm manager is a system service in android that allows you to schedule operations to be executed at a specific time, even if the app is not running. It is commonly used for setting alarms, reminders, or background tasks that must occur at precise time intervals. 

### 1. What is Alarm Manager?
Alarm Manager lets you schedule tasks to run at a specific time or after a delay. It works even when your app is not in the foreground or when the device is idle, depending on the type of alarm used

***Key Features***
- Schedule operations to run at a precise time
- Works in the background, even when the app is killed. 
- supports both exact and inexact timings.

### 2. Types of Alarms
- RTC (Real time Clock) : Triggered based on the current system time. 
  - RTC_WAKEUP:Wakes the device to trigger the alarm. 
  - RTC: Triggers the alarm only if the device is already awake. 
- ELAPSED_REALTIME : Triggered after a specified time relative to the device boot time. 
  - ELAPSED_REALTIME_WAKEUP: Wakes the device to trigger the alarm
  - ELAPSED_REALTIME: Triggers the alarm only if the device is already awake. 

### 3. **Setup and Configuration**

### Add Permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

---

### 4. **Basic Usage**
### Example: Setting a One-Time Alarm

1. **Create a BroadcastReceiver**
This will define what happens when the alarm goes off:

```kotlin
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
    }
}
```

2. **Schedule the Alarm**
Use `AlarmManager` to schedule the alarm:

```kotlin
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

fun scheduleAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 8) // Set the hour
        set(Calendar.MINUTE, 30)    // Set the minute
        set(Calendar.SECOND, 0)     // Set the second
    }

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        pendingIntent
    )
}
```

3. **Cancel the Alarm**
Cancel a previously set alarm:

```kotlin
fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    alarmManager.cancel(pendingIntent)
}
```

---

## 5. **Repeating Alarm**
Schedule an alarm to repeat at fixed intervals:

```kotlin
fun scheduleRepeatingAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        System.currentTimeMillis(), // Start time
        AlarmManager.INTERVAL_DAY,  // Repeat interval (e.g., daily)
        pendingIntent
    )
}
```

---

## 6. **Best Practices**
- **Use `setExactAndAllowWhileIdle` for exact alarms:** Ensures alarms work during Doze mode (introduced in Android 6.0).
- **Batch alarms if possible:** Reduce battery usage by combining multiple alarms into a single alarm.
- **Handle device restarts:** Use `BOOT_COMPLETED` broadcast to reschedule alarms after a device reboot.

```xml
<receiver android:name=".AlarmReceiver" />
<receiver android:name=".BootReceiver">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```

---

## 7. **Limitations and Alternatives**
### Limitations:
- Alarms are affected by Doze mode and battery optimizations.
- `setRepeating` may not trigger exactly at the interval due to system optimizations.

### Alternatives:
- **WorkManager:** Use for deferrable and guaranteed background work.
- **JobScheduler:** Use for background tasks that do not require exact timing.

---

## 8. **Useful Resources**
- [AlarmManager Documentation](https://developer.android.com/reference/android/app/AlarmManager)
- [PendingIntent Documentation](https://developer.android.com/reference/android/app/PendingIntent)
- [Doze Mode and App Standby](https://developer.android.com/training/monitoring-device-state/doze-standby)


### Job Scheduler
[Slides](https://docs.google.com/presentation/d/1UILCEnzR1vurX0XaFV71Ke_yyIhEJ9--iRzwVpYKLwc/edit?resourcekey=0-RTKA4Q5ubz5BcdHZ6gRt-Q&slide=id.g18e75634d0_0_172#slide=id.g18e75634d0_0_172)

**JobScheduler** is an intelligent task scheduling algorithm that works based on conditions.
- Intelligent than Alarm Manager
- Has 3 major components
  - JobService
    - This is where we define the task and also cancel a task (Job).
      - onStartJob():Boolean
        - True - When the job is offloaded to a worker thread (When the job runs in the background) - when the job is finished on the worker thread, you are supposed to explicitly call `jobFinished()`.
        - False - When the task is completed. 
        - By Deafult, this method runs on Main Thread. 
      - onStopJob():Boolean
  - JobInfo
    - This is where the conditions / Constraints get set. 
  - JobScheduler
    - Will schedule or cancel jobs.    

**Note**: All JobSchedulers must be registered as a servie in the manifest file 

***Please complete Assignment 5***

### Work Manager
Work Manager is a more modern and flexible API for background Tasks introduced in Jetpack. It supports constraints and is compatible with all API levels >14.

***Key Features***
- Guaranteed Task Execution
- Support for Constraints like network type, battery status etc.,
- Suport for chaining of tasks

***Steps to Work with Work Manager***
1. Add Dependencies

```kotlin
implementation("androidx.work:work-runtime-ktx:2.10.1")
```
2. Define a Worker
```kotlin
// TODO 1: After adding the dependency - define a worker to create your own task
class MyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context,workerParameters){

    override fun doWork(): Result {
        Toast.makeText(applicationContext,"Work Running", Toast.LENGTH_LONG).show()
        Log.v("Main", "Work Running")
        return Result.success()
    }

}
```
3.  Schedule a work request
```kotlin
 val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        // TODO 3: Create a work request
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

WorkManager.getInstance(applicationContext).enqueue(workRequest)

WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)

```


4.  Key Differences between WorkManager & JobScheduler
   
Feature|JobScheduler|WorkManager
---|---|---
API level Support | 21+ | 14+
Task Execution | Not guaranteed after app is killed | Guaranteed
Chaining tasks | Not Supported | Supported
Library Dependency | No | Requires Jetpack WorkManager

[Official Doc](https://developer.android.com/reference/androidx/work/WorkManager)

### Shared Preferences
Shared preferences is a light weight storage mechanism in android that allows storing small key-value pairs persistently. It is mainly used for storing simple data like user preferences, settings, or session data. 

**Key Features**
- Stores data in XML format
- Data persists even after the app is closed or restarted. 
- Best suited for small amounts of primitive data (eg., Strings, Integers, Boolean)
- Not Suitable for large or complex data structures like lists or images. 

### Jetpack Data Store in Android
Jetpack data store is Google's recommended replacement for Shared Preferences. It provides a more efficient, safer and asynchronous way to store key-value data using kotlin coroutines and flow. 

**Why use Data store instead of shared preferences ?**
- Asynchronous : Use coroutines and Flow, avoiding UI Thread blocking
- Safer Storage: Uses `proto Datastore` for type safety.
- No Performance Issues: Shared preferences blocks I/O operations, but data store doesn't

**Types Of DataStore**
- Preference DataStore: Key-value storages (very similar to shared preferences)
- Proto Datastore: Stores structured objects with type safety using [protocol buffers](https://protobuf.dev/) (ProtoBuf)

[Datastore](https://developer.android.com/topic/libraries/architecture/datastore)

### Content Provider
A Contnet Provider is one of the four fundamental Android Application Components (the others being Activities, Services, Broadcast Receivers), whose primary purpose is to Manage access to a structured set of data. It acts as a standarad interface for applications to interact with data, wheather the data is stored in a database (room), files, over the network, or any other storage mechanisms. 

**Core Components**
- ContentProvider class
  - This is the abstract class you will extend to
  - You must implement the following methods
    - onCreate() - Called when the provider is first created. Intilize your data source here (e.g., Get a reference to your room database DAO)
    - query(uri, projection, selection, selectionArgs, sortOrder): Retrieves data. Returns the data in a `Cursor` (Which is like a pointer to a set of rows)
    - insert(uri, values): Insert new rows. returns a URI of the new Row. 
    - update(uri, values, selection, selectionArgs): Updates the existing rows. Returns the number of rows affected. 
    - delete(uri, selection, selectionArgs): Deletes rows. Returns the number of rows affected.
    - getType(uri): Returns the MIME type for the given URI. Useful for client apps to understand the data type
- ContentResolver class
  - This is what client application use
  - You can access it via context.getContentResolver()
- Uri (uniform resource Identifier)
  - The most crucial part for identifying data
  - Its String representing the data you want to access. 
  - format: content://authority/path/id
    - "content://": scheme, always for content providers
    - authority: Uniquely identifies the content provider . This must be defined in the manifest file. 
    - path : Specific type of data
  - UriMatcher: This is a helper class used with your contnet provider to parse the incoming URIs and determine which data they refer to. Makes `switch` statements more manageable. 

***Please complete Assignment 6***

### Android Services

**What is a service ?**
- A Service is an application component that runs in the background to perform long running tasks. 
  - It has no user interface
  - It can continue running even when the user switches to another app
  - Other components (like activities) can bind to a service to interact with it. 

[Official Documentation](https://developer.android.com/develop/background-work/services)

**Why use Service?**  
Services are perfect for tasks that
- Should not be interrupted by user interactions
- Need to run in the background (like playing music, downloading a file, or checking the updates)
- Allow other apps/ components to connect (IPC - Interprocess communication)

**Types of Services**

Type|Description
---|---
Foreground| Runs in the foreground and shows a notification (e.g., music playback, fitness tracking)
Background| Runs in the background without user interaction (more restricted in newer android versions)
Bound| Clients (Activities/Fragments) bind to the service to interact with it and exchange data. 

**Service Lifecycle**
![life-cycle-image](/service_lifecycle.png)


Method | When it's called & What it does
---|---
onCreate()| Called once when the service is first created. Intialize resources here. 
onStartCommand()|Called each time the service is started with `startService(...)` 
onBind(intent)| called when another component binds to the service. Returns an IBinder for communication. 
onDestroy()|called when the service is no longer used and is being destroyed. Cleaning up of resources happens here.

**Key Notes on onStartCommand()**  
- Return values of this method tell Android what to do if the service is killed
  - `START_STICKY` - restart the service after it is killed (no intent data)
  - `START_NOT_STICKY` - Don't restart if the service is killed.
  - `START_REDELIVER_INTENT` - Restart and redeliver the last intent.
- If you are using binding (bound services), you do not need to implement `onStartCommand(...)`

**Important Note**
- By default, a service runs on the main thread. 
- move long tasks to a seperate thread (like HandlerThread or Coroutines or an ExecutorService) to avoid ANR (Application Not Responding) errors. 

**Example Use cases**
- Foreground Service - Music app playing songs with a visible notifications.
- Background Service - Periodically uploading logs or files.
- Bound Service - Weather app fetching current temperature on Demand. 

***Please complete Assignment 7***

Define a **bound service** that returns the **current time**  
Create a **Compose UI** that binds to the service and fetches data

---

### 🟩 **1️⃣ Create the Bound Service**

```kotlin
class TimeService : Service() {

    // Binder to allow clients to call public methods
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): TimeService = this@TimeService
    }

    // Mandatory method to implement
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    // Example method that returns current time
    fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(currentTimeMillis))
    }
}
```

---

### 🟩 **2️⃣ Compose UI + Binding to the Service**

```kotlin
@Composable
fun BoundServiceExample(context: Context) {
    var bound by remember { mutableStateOf(false) }
    var currentTime by remember { mutableStateOf("N/A") }

    // ServiceConnection object
    val serviceConnection = remember {
        object : ServiceConnection {
            var service: TimeService? = null

            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                val localBinder = binder as TimeService.LocalBinder
                service = localBinder.getService()
                bound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                service = null
                bound = false
            }

            fun getTime(): String {
                return service?.getCurrentTime() ?: "Service not bound"
            }
        }
    }

    // UI
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val intent = Intent(context, TimeService::class.java)
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }) {
            Text("Bind to Service")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (bound) {
                currentTime = serviceConnection.getTime()
            } else {
                currentTime = "Service not bound"
            }
        }) {
            Text("Get Current Time from Service")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            context.unbindService(serviceConnection)
            bound = false
        }) {
            Text("Unbind Service")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Current Time: $currentTime")
    }
}
```

---

### 🟩 **3️⃣ Putting It All Together in `MainActivity`**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoundServiceExample(this)
        }
    }
}
```

---

### 🟩 **How it works:**

✅ **`TimeService`** is a **bound service** that exposes a method to get the current time.
✅ The **Compose UI**:

* Binds to the service
* Calls the service method via the **Binder**
* Displays the **current time** fetched from the service.

✅ Buttons:

* **Bind**: Calls `bindService()` to connect.
* **Get Current Time**: Calls the method in the service.
* **Unbind**: Disconnects to release resources.
---

### Dependency Injection (DI)

**What is Dependency Injection ?**  

Dependency Injection is a design pattern in which **an object receives the other objects it depends on from an external source**, rather than creating them itself.

**Dependency** -> The object a class needs to function.  
**Injection** -> Providing the dependency to the class, instead of letting it create it.

**Why use Dependency Injection ?**

- **Decouples Classes** -> Easier to change or extend
- **Improves Testability** -> can replace real dependencies with mocks
- **Promotes code reusability** -> Components are easier to reuse in different contexts

***Traditional Approach (No DI)***

Let's take the example of a `Computer` class that needs `RAM`, `PROCESSOR`, and `HDD`:

```kotlin
class RAM {
    // Implementation
}

class PROCESSOR{
    // Implementation
}

class HDD {
    // Implementation
}

class Computer{
    private val ram = RAM()
    private processor = PROCESSOR()
    private val hdd = HDD()
}
```

**Issue**  
The computer class creates its dependencies itself. If you want to change, upgrade, or test these depenedencies, you have to modify the computer class which is not flexible. 

***With Dependency Injection***

Instead of creating the dependencies internally, we inject them via the constructor

```kotlin
class RAM {
    // Implementation
}

class PROCESSOR{
    // Implementation
}

class HDD {
    // Implementation
}

class Computer(private val ram:RAM, 
private val processor: PROCESSOR,
private val hdd: HDD)
{
    // Implementation
}
```

**What have we changed ?**

- The computer class no longer creates its dependencies
- It receives them from the outside -> Easier to manage and test. 


### Types of DI
1. Constructor Injection
   - Dependencies are provided through the constructor
```kotlin
class Computer(private val ram:RAM, 
private val processor: PROCESSOR,
private val hdd: HDD)
{
    // Implementation
}
```
1. Setter (Property) Injection
   - Dependencies are provided through setters or public properties. 
```kotlin
class Computer{
    lateinit var ram:RAM
    lateinit var processor:PROCESSOR
    lateinit var hdd:HDD

    public fun setRAM(r:RAM){
        ram = r
    }

    // other public methods as such
}
```
3. Interface Injection 
   - Less commonly used.


### DI in Android
In real android apps, managing dependencies manually can get complicated. That's why we use DI frameworks like
- Dagger (Powerful but more boilerplate)
- Hilt (built on Dagger, easier to use in Android)
- Koin (Kotlin-specific, simpler for smaller projects)

[**Hilt Documentation**](https://developer.android.com/training/dependency-injection/hilt-android)

***Set up Hilt with KSP***
- In your Project level build.gradle, under the plugins
```kotlin
id("com.google.devtools.ksp") version "1.9.0-1.0.11" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply fals
```
- Sync the project
- In your app level build.gradle file, enable  the plugins
```kotlin
plugins {
    //...
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}
```
- In your app level build.gradle file, under the dependencies
```kotlin
dependencies {

    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-compiler:2.56.2")

    //...
}
```
- Sync the Project
make sure to use proper ksp version based on your kotlin version

[KSP Github release page](https://github.com/google/ksp/releases?page=3)


### Testing in Android
When you implement your first feature in android app, you ran your application on a physical device and also sometimes on emulator to verify if the code works as expected. This means you already tested an application, albeit a manual test. 

**Types of Tests**
- Functional Tests : Does my app do what it's supposed to do ?
- Performance Testing : Does it do it quickly and efficiently?
- Accessibility testing : Does it work well with the accessibility services ?

**Scope**
- Tests vary depending on the size, or degree of isolation.
  - Unit Testinig: These are called small tests. These tests will only verify a small portion of your application such as method or class. 
  - End to End Testing: We may test the entire screen or userflow. 
  - Integration Testing: If you have more modules and doing integration, these tests are really helpful. These are also called as `Medium` tests. 

- [Mockito Framework](https://site.mockito.org/)
- [Roboelectric by Google](https://developer.android.com/training/testing/local-tests/robolectric)
- [Official Documentation](https://developer.android.com/training/testing/fundamentals)


### UI Testing in Android

***What is UI Testing?***
UI (User Interface) testing checks wheather the UI components of Android app function correctly and display the expected output. It Simulates user interactions like clicks, swipes and typing. 

***Goals of UI Testing***
- Ensure UI behaves as expected
- Detect regressions and bugs early
- Automate repetitive manual tests
- Improve user experience and reliability

***Types of Android Tests***
Type|Layer|Tool
---|---|---
Unit Test | Business logics| Junit, Mockito
Instrumentation| Full app with UI|Espresso, UI Automator

***Tools for UI Testing***
1. Espresso (We cannot record UI tests for JEtpack Compose apps)
2. UI Automator
3. [Compose UI Testing](https://developer.android.com/develop/ui/compose/testing).

### Build Flavors

You can make the same app available in multiple flavors such as (Free & Paid). You do not need to create two different projects and everything can be done under one single project using build flavors

Build flavors allows you to create different versions of your app (like free or paid) from a single codebase
Each flavor can have  
- Different applicaiton IDs
- Different Resources (icons, strings and layouts)
- Different code or feature sets
- Different APIs or Configurations


### Types of Location Access in Android
In Android, you can control location access in different ways, allowing users to choose how and when the app can access their location. 

1. Allow Only while using the app
   1. Also known as "While-in-use" or "Foreground-only" access
   2. Added in Android 10
   3. Allows location access only when the app is actively used. 
2. Allow One-time
   1. Grants location access for single session. when the user closed the app, access is revoked. 
3. Allow All the time
   1. Provides the background access to the users location at any time.
   2. Note: Your app must comply with the [Google Location Policies](https://support.google.com/googleplay/android-developer/answer/9799150?hl=en) for background location access.
4. Deny
   1. The users can deny location access, preventing the app from accessing the location data. 

### Location Providers in Android
Primarily android uses two location providers
1. NETWORK_PROVIDER
   1. Retrieves location based on cell-towers, Wi-Fi and other network signals. 
   2. Require `ACCESS_COARSE_LOCATION` permission
2. GPS_PROVIDER
   1. Provides the location data based on satellite signals for high accuracy. 
   2. require `ACCESS_FINE_LOCATION` Permission. 

Your application can access supported location services using classes in `com.google.android.gms.location` package. 


### Key classes for location in Android
1. FusedLocationProviderClient
   1. The central component of the Location Framework
   2. Use this class to request location update and retrieve last known location.
2. LocationRequest
   1. A data object containing parameters like update intervals, priority and accuracy
   2. Pass this object to `FusedLocationProviderClient` to specify the conditions for location updates. 
3. LocationCallback
   1. Used to retrieve notifications when the device's location changes or becomes unavailable. 
   2. pass this as callback to `LocationResult` to capture and handle location data. 

***Please complete Assignment 8***
---
THE END
---


