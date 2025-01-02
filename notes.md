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
