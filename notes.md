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

