### Resources shared during Demo Sessions
[Document notes](https://docs.google.com/document/d/1CUSueUD6oA05FXFBGEPfF5otseunELamWQkriU3vv0Y/edit?tab=t.0#heading=h.7idzviv2phqd)  
[Join the Whatsapp Group here](https://chat.whatsapp.com/JeXSs2yTYRs6VWyhgTDFOS)

[see the remaining kotlin fundamentals notes here](/kotlinfunda.md)

### UI Components
- [Constraint Layout](https://developer.android.com/develop/ui/views/layout/constraint-layout)
- EditText (to read input from the user)
- [RadioGroup & RadioButton](https://developer.android.com/develop/ui/views/components/radiobutton) (to allow the user to choose one option from the available options)
- CheckBoxes (to allow the user to choose multiple options from the available options)
- [Spinner](https://developer.android.com/develop/ui/views/components/spinner#:~:text=Spinners%20provide%20a%20quick%20way,values%20the%20user%20can%20select.) (to display the items in dropdown - Dropdown menu)
- Switch (To let the user toggle between on and off states)'
- Button

#### Assignment
Extend the logic of the Register me app, by adding an email address edittext box to read the email address of the user. Please validate the email address by using reg ex. 

After completing the assignment drop an email to `pavankreddy.t@gmail.com`

### ListView
Listview is a view in android that displays vertically scrollable list of items. It's one of the most commonly used UI Components for displaying a collection of data in an organized manner. 

**Key Features**
- Displays a scrollable list of items
- Optimizes memory usage to a certain extent, but not fully. 
- Requires an Adapter to bind data to the list. 
**Components of a listview**
1. ListView
2. ArrayAdapter / SingleAdapter / BaseAdapter/ CursorAdapter

[Official Document Link](https://developer.android.com/reference/android/widget/ListView)

### Activity Lifecycle

In Android, an activity is a single screen within an application. It's like a single window in a desktop application, responsible for displaying a user interface and handling user interactions. 

The activity lifecycle refers to the various states that an activity can go through during its lifetime. To manage the resources efficiently, it's important to understand the activity lifecycle. 

[Official Documentation Link](https://developer.android.com/guide/components/activities/activity-lifecycle)

![Image](/activity_lifecycle.png)

**Key Life cycle Methods**
- `onCreate(...)` is a method that gets called when the activity is first created. This is where you initialize the activity and set up its UI.
- `onStart()` is called when the activity becomes visible to the user. 
- `onResume()` called when the activity is in the foreground and is interactable by the user. 
- `onPause()` called when the system is about to keep the activity in background or before destorying it. Here you will release the resources hold by the activity. You can also save data that might be lost if the activity is killed. 
- `onStop()` called when the activity is no longer visible to the user.
- `onDestroy()` called before the activity is destroyed. This is where you release any remaining resources. 
- `onRestart()` called after the activity has been stopped and is about to be started again.

### Intents

***What are Intents?**
- Intents are messages that allow different components of an android application to interact with each other. 
- They can also be used to interact with components of other applications
- Think of them as messengers that carry information and instructions between app components. 

***Types of Intents**
- Explicit Intents
  - You explicitly specify the target component.
  - Used when you know exactly which component you want to start.
- Implicit Intents
  - You specify the action you want to perform.
  - The android system finds the appropriate component (from your app or another app) to handle that action. 

[Official Documentation](https://developer.android.com/guide/components/intents-filters)

Here's a detailed explaination on how to use `ActivityResultLauncher` to send data using an intent and receive a reply:

---
---

### **Step 1: Set Up the Launcher in the Main Activity**
We will use `ActivityResultLauncher` to start the second activity and handle the result asynchronously.

#### **MainActivity.kt**

```kotlin
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendDataButton: Button = findViewById(R.id.sendDataButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Register the launcher
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val replyData = result.data?.getStringExtra("replyKey") ?: "No reply received"
                resultTextView.text = replyData
            }
        }

        // Set onClickListener to launch SecondActivity
        sendDataButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("dataKey", "Hello from MainActivity!")
            resultLauncher.launch(intent)
        }
    }
}
```

---

### **Step 2: Set Up the Second Activity**
This activity receives the data sent by `MainActivity` and sends back a reply.

#### **SecondActivity.kt**

```kotlin
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val receivedData = intent.getStringExtra("dataKey")
        val replyEditText: EditText = findViewById(R.id.replyEditText)
        val replyButton: Button = findViewById(R.id.replyButton)

        // Set received data to a TextView (optional)
        findViewById<TextView>(R.id.receivedDataTextView).text = receivedData

        // Send reply back to MainActivity
        replyButton.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("replyKey", replyEditText.text.toString())
            setResult(RESULT_OK, replyIntent)
            finish() // Close the activity
        }
    }
}
```

---

### **Step 3: Layout Files**

#### **activity_main.xml**

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <Button
        android:id="@+id/sendDataButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Send Data to SecondActivity" />

    <TextView
        android:id="@+id/resultTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Result will be shown here"
        android:textSize="16sp" />
</LinearLayout>
```

#### **activity_second.xml**

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/receivedDataTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Received data will appear here"
        android:textSize="16sp"
        android:layout_marginBottom="16dp" />

    <EditText
        android:id="@+id/replyEditText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Type your reply here" />

    <Button
        android:id="@+id/replyButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Send Reply" />
</LinearLayout>
```

---

### **How It Works**
1. **MainActivity**
   - Launches `SecondActivity` with some initial data using `ActivityResultLauncher`.
   - Handles the reply asynchronously when `SecondActivity` finishes.

2. **SecondActivity**
   - Receives the data and displays it.
   - Sends back a reply using `setResult`.

---

### **Output**
- **MainActivity:** Displays the reply received from `SecondActivity`.
- **SecondActivity:** Shows the data sent by `MainActivity` and lets the user type and send a reply.
---



[For Common Intents](https://developer.android.com/guide/components/intents-common)

#### Assignment
Please try workig with two more implicit intents (from the common intents) section. 

### Alert Dialogs in Android
[official Documentation](https://developer.android.com/develop/ui/views/components/dialogs)

- Basically used to throw alerts to the user
- Consists of the following components 
  - Icon
  - Title
  - messages
  - Three buttons
    - Positive
    - Negative
    - Neutral
- We use AlertDialog.Builder class to set up the aforestated items on the alert dialog box. 

#### Assignment 
- TASK in Android. 
- Try to override the default backbutton behavior. When the back button on your application is tapped, do not close the app. Instead, show an alert asking the user if the user really wants to exit your application or not. 

### RecyclerView in Android

Please visit the presentation [here](https://docs.google.com/presentation/d/1nFJqH0OSSZmjaycRzEGE6vvsm6jlxghQyoO15KKbkwc/edit?usp=sharing)

Refer to the official documentation [here](https://developer.android.com/develop/ui/views/layout/recyclerview)

--

## Step-by-Step Implementation of RecyclerView

---

### **Step 1: Prepare Data**
We start by creating a data class to define the structure of our data. For this example, we'll display a list of actors with their images, names, and years of birth.

#### Code:
```kotlin
data class Actor(val image: Int, val name: String, val yob: Int)

val actors: MutableList<Actor> = mutableListOf()

actors.add(Actor(R.drawable.alluarjun, "Allu Arjun", 1982))
actors.add(Actor(R.drawable.chiranjeevi, "Chiranjeevi", 1955))
actors.add(Actor(R.drawable.amitabh, "Amitabh Bachchan", 1942))
actors.add(Actor(R.drawable.kamal, "Kamal Hassan", 1954))
actors.add(Actor(R.drawable.rana, "Rana Daggubati", 1984))
actors.add(Actor(R.drawable.prabhas, "Prabhas", 1979))
actors.add(Actor(R.drawable.ranbir, "Ranbir Kapoor", 1982))
actors.add(Actor(R.drawable.salman, "Salman Khan", 1965))
actors.add(Actor(R.drawable.charan, "Ram Charan", 1985))
actors.add(Actor(R.drawable.vijay, "Vijay", 1975))
```

---

### **Step 2: Prepare the Layout Design for Each Item**
Create an XML layout for how each item in the RecyclerView should look. 

#### File: `res/layout/item_actor.xml`
```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="8dp">

    <ImageView
        android:id="@+id/imgActor"
        android:layout_width="100dp"
        android:layout_height="150dp"
        android:scaleType="centerCrop"
        android:src="@drawable/alluarjun"
        android:layout_marginEnd="8dp" />

    <LinearLayout
        android:gravity="center"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:id="@+id/tvActorName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textStyle="bold"
            android:text="Allu Arjun"
            android:textSize="16sp" />

        <TextView
            android:id="@+id/tvActorYOB"
            android:layout_width="wrap_content"
            android:text="1982"
            android:layout_height="wrap_content"
            android:textSize="14sp" />
    </LinearLayout>
</LinearLayout>
```

---

### **Step 3: Add RecyclerView to the Main Layout**
Add a RecyclerView in your activity or fragment layout file.

#### File: `res/layout/activity_main.xml`
```xml
<androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

---

### **Step 4: Create the Adapter**
The adapter binds the data to the views defined in the `item_actor.xml` file.

#### File: `ActorAdapter.kt`
```kotlin
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

/** Why do we need an Adapter ?
 * - We have multiple items to show on the recyclerview
 * - We are supposed to use an adapter to populate these items on the recyclerview
 * **/
class ActorAdapter(private val context:Context, private val actors:MutableList<MainActivity.Actors>) :
    Adapter<ActorAdapter.ActorViewHolder>() {

    /**
     * Why do we need a ViewHolder ?
     * For each item on the recyclerview, we have three more views. ViewHolder is a class that holds these three views.*/
    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.imgActor)
        val textViewName:TextView = itemView.findViewById(R.id.tvActorName)
        val textViewYob:TextView = itemView.findViewById(R.id.tvActorYOB)
    }

    /**
     * This method is called when the recyclerview needs a new ViewHolder.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.one_item_design, parent, false)
        return ActorViewHolder(v)
    }

    /**
     * This method is called when the recyclerview needs to know the size of the data set.*/
    override fun getItemCount(): Int {
        return actors.size
    }

    /** This method is called when the recyclerview needs to populate the data on the views.*/
    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.imageView.setImageResource(actors[position].image)
        holder.textViewName.text = actors[position].name
        holder.textViewYob.text = "${actors[position].yob}"
    }

}
```

---

### **Step 5: Set Up the Adapter**
Initialize the RecyclerView and set the adapter.

#### File: `MainActivity.kt`
```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Sample Data
        val actors = mutableListOf(
            Actor(R.drawable.alluarjun, "Allu Arjun", 1982),
            // Add remaining actors
        )

        // Set Adapter
        recyclerView.adapter = ActorAdapter(actors) { actor ->
            Toast.makeText(this, "Clicked: ${actor.name}", Toast.LENGTH_SHORT).show()
        }

        // Set Layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
```

---

### **Step 6: Set Up the Layout Manager**
The layout manager is responsible for positioning the items. Common layout managers include:

- **LinearLayoutManager** (vertical or horizontal list)
- **GridLayoutManager** (grid of items)
- **StaggeredGridLayoutManager** (staggered grids)

#### Code:
```kotlin
recyclerView.layoutManager = LinearLayoutManager(this)
// For grid layout:
// recyclerView.layoutManager = GridLayoutManager(this, 2)
```

---

### **Step 7: Add Click Listeners**
The `onItemClick` lambda in the adapter handles item click events. This allows you to respond to user interactions dynamically.

#### Example:
```kotlin
recyclerView.adapter = ActorAdapter(actors) { actor ->
    Toast.makeText(this, "You selected ${actor.name}, born in ${actor.yob}.", Toast.LENGTH_SHORT).show()
}
```

---

### Final Notes:
- **Performance Tip:** Use `setHasFixedSize(true)` on the RecyclerView for better performance if the list size does not change.
- **Customization:** You can enhance the adapter for more complex layouts or features like filtering or animations.
- **Practice:** add more functionality, such as long clicks, swiping to delete, or loading data from a JSON file.

#### Assignment:
- Work with cardview to elevate the design of each item on the recyclerview
- Work with click listeners (when an item is clicked on the recyclerview do something)
- Create another application that shows the list of your favorite food items along with number of calories per serving. Use ChatGpt or gemini to get the data. 

### Card View 
Cardview enhances the design of your UI

[Official Documentation](https://developer.android.com/develop/ui/views/layout/cardview)

#### Assignment:
- Implement google custom chrom tabs for android in the recyclerview application to show the wikipedia links in the same app.

### Android Networking

#### Async TASK
- Async task is deprecated in api level 30 in android. However, we can still work with Async Task. 
- [Slides here](https://docs.google.com/presentation/d/1Yom6grVVEJVGp8IaB_a01uCNjBuUToHja9tzcTjZEl8/edit#slide=id.g116d7d9d49_3_13)
- [Official Doc here](https://developer.android.com/reference/android/os/AsyncTask)


Explore the [Public apis here](https://github.com/public-apis/public-apis)

### Executors

#### What are Executors ?

Executors are part of java Concurrent framework and provide a high-level replacement for Managing threads. They abstract thread creation and management, allowing developers to focus on task execution rather than the underlying thread management. 

In Android, Executors are used to offload work from the main thread (UI thread) to background threads, ensuring smooth ui performance. 

**Types of Executors**  
1. SingleThreadExecutor
   - Ensures tasks are executed sequentially in a single background thread.
   - Example: Processing a sequence of file operations.
2. FixedThreadPool
   - Contains a fixed number of threads.
   - Example: Executing mutliple tasks with a known number of threads. 
3. CachedThreadPool
   - Creates threads as needed and resuses the existing threads if available. 
   - Example: Handling large number of short lived asynchronous tasks. 
4. ScheduledThreadPool
   - Used to schedule tasks to run after a delay or periodically. 
   - Example: Periodic synchronization with a server. 
5. WorkStealingPool
   - Optimized for a large number of tasks where threads can "steal" work from each other.
   - available from Java 8.

**Advantages of an Executor**
- Simplifies the thread management. 
- Handles pooling and reuse of threads. 
- Prevents common threading issues like resource starvation & memory leaks.
- Easier to scale and maintain. 

Explore Google Books API [here](https://developers.google.com/books/docs/v1/getting_started)

Our URL is [this](https://www.googleapis.com/books/v1/volumes?q=quilting)


### Volley Netowrking Library

https://api.open-meteo.com/v1/forecast?latitude=17.3616&longitude=78.4747&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&timezone=IST

Click [here](https://google.github.io/volley/) for official documentation

- Volley is a networking library, created by Google for making the network requests easy and efficient. 
- It is an Http Library that makes the networking for android apps easier and most importantly faster. 

#### GSON Library
- This is a library developed by Google to convert java objects to JSON and vice versa.
- [Official Documentation](https://github.com/google/gson)
- Provide simple toJson() and fromJson() methods to convert Java objects to JSON and vice-versa
- Allow pre-existing unmodifiable objects to be converted to and from JSON
- Extensive support of Java Generics
- Allow custom representations for objects
- Support arbitrarily complex objects (with deep inheritance hierarchies and extensive use of generic types)

#### Task is to Convert JSON Data to Kotlin Objects. 
1. We need the Kotlin classes created keeping JSON in mind. 
   1. Use [Json2Kt](https://json2kt.com/) website to do this easily 
2. GSON to convert the data into the objects of the classes that we created. 



### Android Notifications

- [Official Documentation](https://developer.android.com/develop/ui/views/notifications/build-notification)
- [Google Slides](https://docs.google.com/presentation/d/1D2n0-V0qG7H0YV5ZWx4rtJpjuHDBJ6m7vcvEmdYR8Ew/edit?resourcekey=0-NjY_l12AwzTN0Znqt7KY6w)
  
#### **1. What are Android Notifications?**
- Notifications are messages displayed outside an app’s UI to provide updates, reminders, or alerts to the user.
- They allow apps to communicate with users without interrupting their current activity.

---

#### **2. Key Components of a Notification**
1. **Title**: The bold text at the top of the notification.
2. **Text**: The main content of the notification.
3. **Icon**: Small image representing the app or type of notification.
4. **Timestamp**: Optional time information, often indicating when the notification was sent.
5. **Action Buttons**: Provide quick actions the user can take without opening the app.

---

#### **3. Notification Channels (Android 8.0 and above)**
- Group notifications into categories for better user control.
- Each channel can have its own importance level, sound, and vibration settings.
- Developers must create channels using the `NotificationChannel` class.
  ```kotlin
  val channel = NotificationChannel(
      CHANNEL_ID,
      "Channel Name",
      NotificationManager.IMPORTANCE_DEFAULT
  )
  notificationManager.createNotificationChannel(channel)
  ```

---

#### **4. Importance Levels**
- **HIGH**: Alerts the user with a sound and appears as a heads-up notification.
- **DEFAULT**: Shows in the notification drawer without sound.
- **LOW**: Silent notification in the drawer.
- **MIN**: Does not appear in the status bar.

---

#### **5. Building a Notification**
- Use the `NotificationCompat.Builder` to construct notifications.
- Example:
  ```kotlin
  val builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setSmallIcon(R.drawable.notification_icon)
      .setContentTitle("Notification Title")
      .setContentText("This is the content of the notification.")
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
  ```

---

#### **6. Displaying a Notification**
- Use `NotificationManager` to issue the notification:
  ```kotlin
  val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  notificationManager.notify(NOTIFICATION_ID, builder.build())
  ```

---

#### **7. Types of Notifications**
1. **Foreground Service Notifications**:
   - Required for background tasks like music players or location tracking.
   - Persistent and cannot be dismissed.
2. **Heads-up Notifications**:
   - Temporarily displayed on top of the screen.
3. **Expandable Notifications**:
   - Include images, a detailed text view, or a list.
4. **Actionable Notifications**:
   - Allow users to respond directly, e.g., reply to messages.
5. **Grouped Notifications**:
   - Combine multiple notifications into a single group for better organization.

---

#### **8. Notification Styles**
- **BigTextStyle**: For long text content.
- **InboxStyle**: For a list of items.
- **BigPictureStyle**: For displaying an image.
- **MessagingStyle**: For conversations or chat apps.

---

#### **9. Best Practices**
- Use concise, meaningful content.
- Avoid spamming users with too many notifications.
- Allow users to customize notification settings.
- Use notification badges to indicate unread notifications.
- Test notifications on different Android versions and device sizes.

---

#### **10. Debugging Notifications**
- Ensure proper permissions are granted.
- Test behavior with and without the app in the background.
- Use `adb shell dumpsys notification` to debug notification behavior.

---


### Pending Intents in Android
- [official document](https://developer.android.com/reference/android/app/PendingIntent)
  
**What are pending Intents ?**
- A `PendingIntent` is a token that grants a foreign application (eg., Android system or another app) permission to perform a predefined action on behalf of your app. 
- It is essentially a wrapper around an `Intent` that  
  - Allows another app (Or system) to execute the `Intent`
  - Retains the `Intent` even if the app that created it is no longer running. 

**Why do use pending intent ?**
- Used in scenarios where your app delegates an action to another process or app. 
- Commonly used with   
  - Notifications: to launch an activity when the user taps the notification. 
  - Alarms: To Perform scheduled tasks with `AlarmManager`
  - Widgets: To Handle user interactions like button clicks. 

**Types of PendingIntent**
1. PendingIntent.getActivity(...)
   1. Used to start an Activity.
   2. Example: Opening the app when the user taps the notification. 
   3. 
   ```kotlin
   val intent = Intent(context, MainActivity::class.java)
   val pendingIntnet = PendingIntent.getActivity(
    context,
    REQUEST_CODE,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT
   )
   ```
2. PendingIntent.getService(...)
   1. Used to start a Service.
   2. Example: Start a background task 
   3. 
   ```kotlin
   val intent = Intent(context, MyService::class.java)
   val pendingIntnet = PendingIntent.getActivity(
    context,
    REQUEST_CODE,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT
   )
3. PendingIntent.getBroadcast(...)
   1. 1. Used to send a Broadcast.
   2. Example: Trigger an Intent when an alarm goes off. 
   3. 
   ```kotlin
   val intent = Intent(context, MyBroadcastReceiver::class.java)
   val pendingIntnet = PendingIntent.getActivity(
    context,
    REQUEST_CODE,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT
   )


**Steps to follow to show an image in the notification when the notificaiton is expanded**
- Download an image and put it in the drawable folder.
- Convert this image into bitmap format using BitmapFactory. 
- set the image using the **BigPictureStyle**.

***Explore [Media Style Notifications](https://developer.android.com/develop/ui/views/notifications/expanded#media-style) and [remote reply action](https://developer.android.com/develop/ui/views/notifications/build-notification#reply-action) implementation***

---
### Broadcast Receiver 

**What is a BroadcastReceiver ?**
- It is a component that responds to broadcast messages (Intents) sent by the android system or other applications. 
- It is commonly used to listen for the system events (e.g., battery low, connectivity changes) or custom events triggered by the app itself. 

**Types of Broadcasts**
1. System Broadcasts
   - Sent by the android system to notify about system events. 
   - Examples:
     - `android.intent.action.BOOT_COMPLETED` (Device is booted)
     - `android.net.conn.CONNECTIVITY_CHANGE` (Network connectivity changes)
2. Custom Broadcasts
   - Sent by apps to notify other components or apps of custom events.
   - Example:
     - Sending a broadcast when data is updated. 

[Slides](https://docs.google.com/presentation/d/1qF9Yeau7uHIP7_aOHWgPU_RnfxACZzGyAZIzcJWz0R0/edit#slide=id.g116d7d9d49_3_13)  
[Official Documentation](https://developer.android.com/develop/background-work/background-tasks/broadcasts)

**Broadcast receivers can be registered in two ways:**
- **Static receivers** 
  - Registered in your AndroidManifest.xml, also called as Manifest-declared receivers.
- **Dynamic receivers**
  - Registered using app or activities' context in your Java files, also called as Context-registered receivers. 

**Important Note**
- Starting from Android 8.0 (API level 26), static receivers can't receive most of the system broadcasts.
- Use a dynamic receiver to register for these broadcasts. 
- If you register for the system broadcasts in the manifest, the Android system won't deliver them to your app.
- A few broadcasts, are excepted from this restriction. See the complete list of [implicit broadcast exceptions](https://developer.android.com/develop/background-work/background-tasks/broadcasts/broadcast-exceptions).

### Retrofit Networking Library (Kotlin)

Retrofit is a popular networking library in Android that simplifies API communication by allowing developers to define endpoints and handle responses efficiently. Below is a detailed guide to using Retrofit with Kotlin, along with relevant resources for further learning.

---

#### 1. **What is Retrofit?**
Retrofit is a type-safe HTTP client for Android and Java, developed by Square. It abstracts the complexities of making HTTP requests, parsing responses, and error handling.

##### Key Features:
- Converts HTTP API into a Kotlin interface.
- Supports various data formats (JSON, XML, etc.).
- Handles API requests and responses with ease.
- Integrated support for popular serialization libraries like Gson, Moshi, and Kotlin Serialization.
- Supports synchronous and asynchronous calls.

---

#### 2. **Setup and Configuration**

##### Add Dependencies:
In your `build.gradle` (Module-level):
```groovy
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson Converter (Optional)
```
For Kotlin Serialization:
```groovy
implementation 'com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0'
```

Sync the project to download the dependencies.

##### Create a Retrofit Instance:
```kotlin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

---

#### 3. **Define API Endpoints**

Create an interface to define your API endpoints using annotations like `@GET`, `@POST`, etc.

```kotlin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<List<User>>
}
```

- **@GET**: Defines a GET request.
- **@POST**: Defines a POST request.
- **@Query**: Adds query parameters to the request.
- **@Body**: Sends a request body (used with POST/PUT).

##### Create API Interface Instance:
```kotlin
val apiService = retrofit.create(ApiService::class.java)
```

---

#### 4. **Model Classes**

Define data classes to represent JSON responses:

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```
Ensure the property names match the JSON response fields. Use Gson annotations like `@SerializedName` if the names differ.

```kotlin
data class User(
    @SerializedName("user_id") val id: Int,
    @SerializedName("user_name") val name: String,
    val email: String
)
```

---

#### 5. **Making API Calls**

Use Retrofit's `Call` object to make API requests asynchronously:

```kotlin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val call = apiService.getUsers(page = 1)
call.enqueue(object : Callback<List<User>> {
    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        if (response.isSuccessful) {
            response.body()?.let { users ->
                users.forEach {
                    println(it.name)
                }
            }
        } else {
            println("Error: ${response.errorBody()?.string()}")
        }
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        t.printStackTrace()
    }
})
```

---

#### 6. **Error Handling**

Handle errors using Retrofit’s `Response` wrapper or exception handling. Example shown above with `onResponse` and `onFailure` methods.

---

#### 7. **Common Annotations**

| Annotation     | Description                                    |
|----------------|------------------------------------------------|
| `@GET`        | Defines a GET request.                        |
| `@POST`       | Defines a POST request.                       |
| `@PUT`        | Defines a PUT request.                        |
| `@DELETE`     | Defines a DELETE request.                     |
| `@Query`      | Appends query parameters to the URL.          |
| `@Path`       | Replaces parts of the URL with values.        |
| `@Body`       | Sends a request body.                         |
| `@Header`     | Adds a custom header to the request.          |

---

#### 8. **Useful Resources**

- **Official Documentation:** [Retrofit Documentation](https://square.github.io/retrofit/)
- **Gson:** [Gson Library](https://github.com/google/gson)
- **Kotlin Serialization:** [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **API Testing:** [Postman](https://www.postman.com/)
- **Fake REST API:** [JsonPlaceHolder](https://jsonplaceholder.typicode.com/)
---

# AlarmManager in Android Using Kotlin

AlarmManager is a system service in Android that allows you to schedule operations to be executed at a specific time, even if the app is not running. It is commonly used for setting alarms, reminders, or background tasks that must occur at precise intervals.

---

## 1. **What is AlarmManager?**
AlarmManager lets you schedule tasks to run at a specific time or after a delay. It works even when your app is not in the foreground or when the device is idle, depending on the type of alarm used.

### Key Features:
- Schedule operations to run at a precise time.
- Works in the background, even when the app is killed.
- Supports both exact and inexact timing.

---

## 2. **Types of Alarms**
AlarmManager provides different types of alarms depending on the use case:

- **RTC (Real-Time Clock):** Triggered based on the current system time.
  - `RTC_WAKEUP`: Wakes the device to trigger the alarm.
  - `RTC`: Triggers the alarm only if the device is already awake.

- **ELAPSED_REALTIME:** Triggered after a specified time relative to the device boot time.
  - `ELAPSED_REALTIME_WAKEUP`: Wakes the device to trigger the alarm.
  - `ELAPSED_REALTIME`: Triggers the alarm only if the device is already awake.

---

## 3. **Setup and Configuration**

### Add Permissions in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

---

## 4. **Basic Usage**
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

# Job Scheduler and Work Manager in Android (Kotlin)

Android provides robust APIs for background tasks, and two widely used solutions for this purpose are **JobScheduler** and **WorkManager**. Below is an overview, along with usage notes and examples for each.

---

## 1. **Job Scheduler**

[**JobScheduler**](https://docs.google.com/presentation/d/1UILCEnzR1vurX0XaFV71Ke_yyIhEJ9--iRzwVpYKLwc/edit?resourcekey=0-RTKA4Q5ubz5BcdHZ6gRt-Q#slide=id.g18e75634d0_0_172) is an API introduced in Android 5.0 (API level 21) that allows scheduling tasks that need to be executed even if the app is not running.

### Use Cases:
- Periodic data sync.
- Uploading logs or files when on Wi-Fi.
- Running jobs when charging.

### Setup and Configuration

### Add Permission:
In the `AndroidManifest.xml`, add the following permission:
```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```

### Define a Job Service:
Extend the `JobService` class to perform background work.

```kotlin
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("JobScheduler", "Job started")

        // Perform the task
        Thread {
            Log.d("JobScheduler", "Task running")
            jobFinished(params, false) // Notify when the job is done
        }.start()

        return true // Task is running on another thread
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("JobScheduler", "Job stopped")
        return false // No need to retry the job
    }
}
```

### Schedule a Job:
Use `JobScheduler` to schedule tasks with specified constraints.

```kotlin
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build

fun scheduleJob(context: Context) {
    val componentName = ComponentName(context, MyJobService::class.java)

    val jobInfo = JobInfo.Builder(1, componentName)
        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // Requires Wi-Fi
        .setRequiresCharging(true) // Requires charging
        .setPeriodic(15 * 60 * 1000L) // Minimum interval: 15 minutes
        .build()

    val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    jobScheduler.schedule(jobInfo)
}
```

---

## 2. **WorkManager**

**WorkManager** is a modern and flexible API for background tasks introduced in Jetpack. It supports constraints and is compatible with all API levels (starting from API level 14).

### Key Features:
- Guaranteed task execution.
- Support for constraints like network type, battery status, etc.
- Support for chaining tasks.
- Compatibility with API 14+.

### Add Dependencies:
In your `build.gradle` (Module-level):
```groovy
implementation "androidx.work:work-runtime-ktx:2.8.1"
```

### Define a Worker:
Extend the `Worker` class to define background tasks.

```kotlin
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("WorkManager", "Work started")

        // Perform the background task here
        try {
            Log.d("WorkManager", "Task running")
            return Result.success()
        } catch (e: Exception) {
            Log.e("WorkManager", "Error", e)
            return Result.failure()
        }
    }
}
```

### Schedule a Work Request:
Create and enqueue a `WorkRequest` to execute your worker.

```kotlin
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

fun scheduleWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiresCharging(true) // Requires charging
        .setRequiredNetworkType(androidx.work.NetworkType.UNMETERED) // Requires Wi-Fi
        .build()

    val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}
```

### Observing Work Status:
You can observe the status of your work using `WorkManager`.

```kotlin
WorkManager.getInstance(context).getWorkInfoByIdLiveData(workRequest.id)
    .observe(lifecycleOwner) { workInfo ->
        if (workInfo != null && workInfo.state.isFinished) {
            Log.d("WorkManager", "Work finished")
        }
    }
```

---

## Key Differences Between JobScheduler and WorkManager

| Feature               | JobScheduler                   | WorkManager                  |
|-----------------------|--------------------------------|-----------------------------|
| API Level Support     | API 21+                       | API 14+                     |
| Task Execution        | Not guaranteed after app kill | Guaranteed                  |
| Chaining Tasks        | Not supported                 | Supported                   |
| Library Dependency    | No                            | Requires Jetpack WorkManager|

---

## Useful Links

1. **JobScheduler Documentation:** [JobScheduler](https://developer.android.com/reference/android/app/job/JobScheduler)
2. **WorkManager Documentation:** [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
3. **WorkManager Guide:** [Guide to WorkManager](https://developer.android.com/codelabs/android-workmanager)

### ViewBinding in Kotlin
---

1. **What is ViewBinding?**
   - ViewBinding is a feature introduced in Android that allows you to interact with views in a type-safe way.
   - It replaces `findViewById` and avoids null pointer exceptions by generating a binding class for each XML layout file in your project.

2. **How to Enable ViewBinding?**
   - Add the following to your `build.gradle` file inside the `android` block:
     ```kotlin
     viewBinding {
         enable = true
     }
     ```

3. **How to Use ViewBinding?**
   - A binding class is generated for each layout file, with a name based on the layout file. For example, `activity_main.xml` generates `ActivityMainBinding`.
   - To use it in an Activity:
     ```kotlin
     class MainActivity : AppCompatActivity() {
         private lateinit var binding: ActivityMainBinding

         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             binding = ActivityMainBinding.inflate(layoutInflater)
             setContentView(binding.root)

             // Access views safely
             binding.textView.text = "Hello, ViewBinding!"
         }
     }
     ```
   - To use it in a Fragment:
     ```kotlin
     class MainFragment : Fragment() {
         private var _binding: FragmentMainBinding? = null
         private val binding get() = _binding!!

         override fun onCreateView(
             inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
         ): View? {
             _binding = FragmentMainBinding.inflate(inflater, container, false)
             return binding.root
         }

         override fun onDestroyView() {
             super.onDestroyView()
             _binding = null
         }
     }
     ```

4. **Advantages of ViewBinding:**
   - Type-safe: Directly accesses views without casting.
   - Null-safety: Eliminates runtime null pointer exceptions.
   - Better performance: Skips the need to search views at runtime.

---

### ViewModel and LiveData in Kotlin

1. **What is ViewModel?**
   - ViewModel is a class designed to store and manage UI-related data in a lifecycle-conscious way.
   - It survives configuration changes like screen rotations, preventing the loss of UI data.

2. **How to Implement ViewModel?**
   - Add dependencies to `build.gradle`:
     ```kotlin
     implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:<version>"
     ```
   - Create a ViewModel class:
     ```kotlin
     class MainViewModel : ViewModel() {
         private val _text = MutableLiveData<String>()
         val text: LiveData<String> get() = _text

         fun updateText(newText: String) {
             _text.value = newText
         }
     }
     ```
   - Use ViewModel in an Activity or Fragment:
     ```kotlin
     class MainActivity : AppCompatActivity() {
         private lateinit var viewModel: MainViewModel

         override fun onCreate(savedInstanceState: Bundle?) {
             super.onCreate(savedInstanceState)
             viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

             viewModel.text.observe(this) { newText ->
                 // Update UI
                 binding.textView.text = newText
             }
         }
     }
     ```

3. **What is LiveData?**
   - LiveData is a lifecycle-aware observable data holder class.
   - It automatically updates the UI when the.3
   -  underlying data changes.

4. **How to Use LiveData?**
   - Define LiveData in ViewModel:
     ```kotlin
     private val _counter = MutableLiveData<Int>()
     val counter: LiveData<Int> get() = _counter

     fun incrementCounter() {
         _counter.value = (_counter.value ?: 0) + 1
     }
     ```
   - Observe LiveData in an Activity or Fragment:
     ```kotlin
     viewModel.counter.observe(this) { count ->
         binding.counterTextView.text = count.toString()
     }
     ```

5. **Advantages of ViewModel and LiveData:**
   - **Separation of Concerns:** Keeps UI-related data separate from UI controllers like Activities and Fragments.
   - **Lifecycle Awareness:** Updates UI only when the associated lifecycle is active.
   - **Improved Testability:** Logic in ViewModel can be tested independently of the UI.

6. **Best Practices:**
   - Use `ViewModelProvider` to instantiate ViewModels.
   - Use `LiveData` to expose data to the UI and `MutableLiveData` for internal data modification.
   - Avoid storing references to Views or Context in ViewModel to prevent memory leaks.

### MVC & MVVM architecture patterns

#### MVC - Model, View & Controller
#### MVVM - Model, View & ViewModel

MVC & MVVM are achitectural patterns used in software development to seperate concerns and manage code organization. Both are popular in Android Development. However, they differ in how they manage the flow of data and the user interaction. 

Architectural patterns provide a template or stucturing and organizing code and components in a way that improve system's maintainability.

**MVC**  
***Components***  
- Model
  - Represents the data and the business logic of the application. 
  - It is also responsible for handling the data-related operations, such as fetching data from the server or API or Database. 
- View
  - The UI that displays data to the user is called the view. 
  - It is responsible for rendering the UI Components and displaying data to the world from the Model. 
- Controller
  - Acts as an Intermediary between the model and the view. 
  - It processes the user inputs, updates the model, and determines which view should be displayed. 

![MVC Overview](/mvc.png)

Example in an android app, the view could be an activity or a fragment, the model could be a data class or a repository managing the data and the controller could be a class that handles the user input and is reponsible for updating the UI. 

**MVVM**
- Model
  - Same as that of the MVC - It represents the data and the business logic of the app. 
- View 
  - The user Interface, which is typically an activity or a fragment in the app. 
- ViewModel 
  - An intermediary that holds the UI Logic and the data needed for the View. It communicates with the model and prepares observable data for the views. 
![MVVM](/mvvm.png)

Example in an android app, the view could be your activity or fragment, the model is the data repo or the data classes or other data sources. The view model is a class that holds livedata and other observable types, these view observers observe if there are any changes in the data and if there are any, it notifies the view and updates it. 

***Key Differences***   

Difference|MVC|MVVM
---|---|---
Seperation of Concern | The controller often contains a mid of UI and Business logic, making it less seperation| The view Model contains the UI Logic, and the model contains the business logic, providing a clearer seperation
DataBinding| Not supported in MVC, the view and controller must manually update each other|Often uses data binding libraries like android Data Binding / ViewBinding or Jetpack's ViewModel and LiveData which allow automatic updates to the UI when data changes
Testability|Testing can be challenging because the UI and business logic are not clearly seperated| Relatively easier to test as the ViewModel can be tested independently of the UI.
Observer Pattern| Changes in the model are often propagated to the View Manually| The view model can use observable data to automatically notify the view of changes happening to the data. 

**Conclusion:** MVC and MVVM are both effective in managing the complexity of the application. MVC is simpler and more straight forward, but MVVM offers better seperation of concerns and support for data binding making it more modern and flexible.

### Fragments In Android
A Fragment represents a reusable portion of your App's UI. A Fragment defines and manages it's own layout, has its own lifecycle, and can handle it's own input events. 
- Fragments cannot live on their own. They must be hosted by an activity or an other fragment.
- The fragment's view hierarchy becomes part of, or attaches to, the host's view hierarchy. 

[Official Link](https://developer.android.com/guide/fragments)

**Primary Usecases**
- **Modular UI Components**   
  - Fragments allow developers to break down an activity's user interface into smaller, managable pieces. It makes it easier to reuse the code and UI components across different parts of the applicaiton or even in different activities. 
- **flexible layouts for mutliple screen sizes**
  - Fragments are ideal for creating responsive layouts. In devices with larger screens (Like tablets), you can show multiple fragments side by side, while on smaller screen (Like Phones) you can display them one after the other. 
- **Reusability**
  - Since framents are self contained UI Components, they can be reused in different activities or even within different parts of the same activity. This reduces the amount of boilerplate code and can also speed up the development process.

**Imp Note:**
For a fragment to be shown to the user, there must be an activity or another fragment running and that should act as a host. A Frament cannot be used independently.

**Fragment Lifecycle**  
[Link to doc](https://developer.android.com/guide/fragments/lifecycle)  
![Fragment Lifecycle](/fragment-view-lifecycle.png)

- onAttach()
  - **When it happens?**: It is called when the fragment is first attached to its parent activity. 
  - **What can you do ?**: This is where you can access the parent activity or set up any intial resources.
- onCreate()
  - **When it happens?**: It is called when the fragment is first created, right after being attached to the activity. 
  - **What can you do ?**: You can initialize non UI Components, like setting up of data, ViewModels etc.,
- onCreateView()
  - **When it happens?**: Called when the fragment's UI needs to be created (ie., that the layout is inflated)
  - **What can you do ?**: Inflate the fragment's UI.
- onActivityCreated()
  - **When it happens?**: It is called after the activity's onCreate() method finishes.
  - **What can you do ?**: This is a good place to interact with the activity's views and complete the finial initialization. 
- onStart()
  - **When it happens?**: Called when the fragment is visible to the user. 
  - **What can you do ?**: Start things that need to happen when the fragment is visible (Ex: Starting animations or loading data)
- onResume()
  - **When it happens?**: It is called when the fragment is now interacting with the user
  - **What can you do ?**: Start things that need to happen when the fragment is actively used. 
- onStop()
  - **When it happens?**: It is called when the fragment is no longer visible to the user. 
  - **What can you do ?**: release resources, stop animations, and save any necessary data. 
- onDestroyView()
  - **When it happens?**: Called when the viewhierarchy of the fragment is destroyed( but the fragment itself may still exist)
  - **What can you do ?**: Clean up references, remove any binding or free resources related to the UI. 
- onDestroy()
  - **When it happens?**: called when the fragment is no longer needed. 
  - **What can you do ?**: stop background tasks. 
- onDetach()
  - **When it happens?**: Called when the fragment is detached from the parents activity. 
  - **What can you do ?**: Final Cleanup. 

**Fragment lifecycle Flow**  
1. Created -> onAttach() -> onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()
2. When the fragment is no longer visible, it follows this flow:
   1. onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()

### Fragment Communication
Fragment Communication in android involves enabling communication between different fragments or between a fragment and it's parent activity. Kotlin Simplifies the fragment communication using `interfaces`, `ViewModel`,or fragment-specific communication methods like `setFragmentResult` API.

- Using Interfaces (Traditional Approach)
  - [Visit this app](/FragCommunication/)
- Using SharedViewModel
  - [Visit this app](/FragCommunication%20-%20Shared%20ViewModel/)
- Using `setFragmentResult` API
  - [Visit this app](/FragmentCommunicationsetFragmentResult/)
- Using Direct Fragment to Fragment Communication.

#### Comparision of Methods

Methods|Use Case |Pros | Cons
---|---|---|---
interface|Fragment to Activity Communication|Simple for small apps | verbose, Tightly coupled
SharedViewModel|Sharing the data between fragments in the same activity | Decoupled, Lifecycle aware | slightly complex
setFragmentResult| Direct fragment to fragment communication (Jetpack Recommended way)|Modern, Light weight|Requires API 26+
Direct Communication|Tightly Couple fragments|Simple for closely related fragments|Not scalable.

### Lateral Navigation in Android: View Pager & Tab Navigation
Lateral navigation in android refers to navigating between fragments or activities by simply swiping on the screen or selecting tabs. 
The most common ways to implement lateral navigation are
- [ViewPager2 (latest ViewPager)](https://developer.android.com/develop/ui/views/animations/screen-slide-2)
- [TabLayout with ViewPager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
- BottomNavigationView with ViewPager2 (Assignment)
- Navigation Component (Advanced navigation)

**ViewPager2 allows users to swipe between the fragments**  

### Navigation Components (Android)
- core components
  - Navigation Graph
    - The Navigation Graph is an xml file that defines all the destinations (Fragment / activity) and actions (transition) in the app.
  - Nav controller
    - Manages Navigation within an app, controlling back stack & executing navigation actions. 
  - NavHostFragment
    - A Container that hosts the fragments and manages the navigation within it.

- [Link to the official Documentation](https://developer.android.com/guide/navigation)
- [Link to Youtube Video](https://www.youtube.com/watch?v=NqpyCKlYLVU)

**Assignment**
- Try to trasfer data using safe args in the navigation components example. 

### Shared Preferences

SharedPreferences is a light weight storage mechanism in android that allows storing small key-value pairs persistently. It is mainly used for storing simple data like user preferences, settings, or session data. 

**Key Features**
- Stores data in XML format
- Data persists even after the app is closed or restarted. 
- Besit suited for small amounts of primitive data (e.g., strings, integers, Boolean)
- Not Suitable for large or complex data structures like lists or images. 

### Jetpack DataStore in Android 
Jetpack datastore is Google's Recommended replacement for `SharedPreferences`. It provides a more efficient, safer and asynchronous way to store key-value data using kotlin Coroutines and flow. 

**Why use DataStore instead of SharedPreferences ?**
- **Asynchronous:** uses `Coroutines` and `Flow`, avoiding UI Thread blocking.
- **Safer Storage:** uses `Proto Datastore` for type safety
- **No Performance Issues:** SharedPreferences blocks I/O operations, but datastore doesn't.

**Types of DataStore**
- **Preference DataStore:** key-value storages (Very similar to SharedPreferences)
- **Proto datastore:** Stores structured objects with type safety using [protocol buffers](https://protobuf.dev/) (protobuf)


### SQLite Database in Android

SQLite is a lightweight, embedded database that comes with Android. It is used for local data storage in applications. 

**Features:**
- Self-Contained, serverless, and zero- configuration required. 
- Supports most of SQL syntax
- Stores Data in a Single File. 

**How do we create SQLiteDatabase ?**
- By the help of `SQLiteOpenHelper` class

Inorder to work with Basic queries - Please goto [this website](sqliteonline.com)

**SQLite Queries**  
- Create a Table
  - CREATE TABLE person(
  person_id integer PRIMARY key AUTOINCREMENT,
  person_name text,
  person_age integer
);

- Insert Data 
  - INSERT INTO person (person_name,person_age) VALUES ('Rama',22), ('Pavan', 32) ;

- Retrieve The Data
  - select * from person;

- Update the data
  - UPDATE person set person_age = 34 WHERE person_id = 2;

- Delete an entry
  - DELETE from person where person_id = 2;

- DROP the table
  - DROP TABLE person;

#### Assignment
- Please complete update, Delete queries on SqliteDatabase app.

### Content Providers 

Content Providers manage and share data between applications securely. They enable apps to access data stored in other applications (e.g., Contacts, Media)

***Key Components***
- `ContentProvider` class
- URIs (Uniform Resource Identifiers)
- `ContentResolver` for accessing data.
- CRUD Operations. 

### Room Database
The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. In particular, Room provides the following benefits:

- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.

[Official Documentation](https://developer.android.com/training/data-storage/room)

#### Implementing Room Database
Step 1: Add Dependencies - in your build.gradle(module:app)
```kotlin
plugins {
    ...
    id("kotlin-kapt")
}

android {
    ...
}

dependencies {
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    ...
}
```

Step 2: Create Entity
- Each entity class is a table where
  - class name = table name
  - fields = column names

```kotlin
package com.nareshtech.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val person_id:Int,
    
    @ColumnInfo(name = "personName")
    val person_name:String,
    val person_age:Int
)
```

Step 3: Create a DAO 
- `PersonDao` provides the methods that the rest of the app uses to interact with data in the '`person`' table.

```kotlin
package com.nareshtech.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDAO {

    @Insert
    fun insertData(person: Person):Unit

    @Query("select * from person")
    fun getAllData():List<Person>

    @Update
    fun updateRow(person: Person)
}
```

Step 4: Create a Database class
The following code defines an AppDatabase class to hold the database. AppDatabase defines the database configuration and serves as the app's main access point to the persisted data. The database class must satisfy the following conditions:

- The class must be annotated with a `@Database` annotation that includes an entities array that lists all of the data entities associated with the database.
- The class must be an abstract class that extends `RoomDatabase`.
- For each DAO class that is associated with the database, the database class must define an abstract method that has zero arguments and returns an instance of the DAO class.

```kotlin
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase:RoomDatabase() {
    abstract fun personDao():PersonDAO
}
```
Step 5: Begin communication with the database by creating the roomdatabase. 

```kotlin
class MainActivity : AppCompatActivity() {
  
    lateinit var personDatabase: PersonDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
       ...
        personDatabase = Room.databaseBuilder(this, PersonDatabase::class.java, "pavan")
            .allowMainThreadQueries()
            .build()
    }
}
```
