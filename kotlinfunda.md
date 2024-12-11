### Arrays in Kotlin

Array is one of the fundamental data structures in practically all programming languages. The idea behind an array is to store multiple pieces of the same data type, such as integers or strings under a single name. 

Arrays are used to organize data in programming so that a related set of values can be easily stored and searched for. 

**Important Points to make a note of**
- They are stored in contiguous memory locations
- They can be accessed programmatically through their indices.
- They are mutable. 
- Their size is fixed
- The index of an array starts at 0 and ends at 1 less than the actual size of the array. 

**Create an Array using arrayOf() and arrayOf<`Datatype`>() functions**

```kotlin
package com.nareshtech.myfirstapp


fun main(){
   var a = arrayOf(1,2,3,4,5,6,7,8,9,10)
   // get the size of an array
   println("the size of the array is ${a.size}")


   // Modify the values based on the index
   a[5] = 34


   // set method to change a value
   a.set(5, 43)


   // access the idividual elements through indices
   println(a[5]) // this prints the value present on the sixth index of a




   // traverse the array and print all the values
   for(i in a){
       print("$i ")
   }


   println()
   println(a.get(5))


}
```

**Using Array() constructor & Lambdas**

```kotlin
import java.util.Scanner


fun main()
{
   println("Enter the size of the array")
   val s:Scanner = Scanner(System.`in`)
   val l = s.nextInt()


   var a = Array<Int>(l,{i -> i*2})


   for(i in a){
       print("$i ")
   }
}
```

### OOPs in Kotlin

***OOP - Object Oriented Programming***

Procedural Programming is about writing the procedures or methods that operate on data while, OOP is about creating objects that contain both data and methods that operate on them. 

***Advantages***
1. Faster and easier to execute
2. Provides clear structure of the program
3. OOP helps to keep the koltin code DRY (Dont Repeat yourself)
4. Reusability

***Kotlin Objects & Classes***

- Any Real World entity that shows attributes and behavior can be considered as an object. 
- A class is a blueprint of an Object. 

Unlike Java, Kotlin is null safe. That is the reason, the variables that you declare must be initialized or they can also accept null values if your specify exiplicitly with `?` combination. 

```kotlin
package com.nareshtech.myfirstapp

import java.util.Scanner

class Dog{
    // Dog Attributes -> name, age & Weight
    // Display()
    var name:String? = null
    var age:Int? = null
    var weight:Double? = null

    fun display(){
        println("$name of age $age is of weight $weight")
    }
}

fun main(){
    val d = Dog() // This is the place where you created a copy the class and assigned this with some memory
    d.name = "Tommy"
    d.age = 12
    d.weight = 12.45

    val d2 = Dog() // This is the place where you created a copy the class and assigned this with some memory
    d2.name = "Johnny"
    d2.age = 33
    d2.weight = 89.45
    d.display()
    d2.display()
}
```

***Constructors in Kotlin***

In Kotlin, A Constructor is a special block of code that initializes a new object of a class. There are two types of constructors in kotlin.

**1. Primary Constructor**
- The primary constructor is a part of the class header and is declared after the class name. It is used to initialze the class with basic parameters  
  
**2. Secondary Constructor**
A class can have as many number of constructors as it needs, which are defined inside the class body using `constructor` keyword. They can provide additional initialization logic or different ways to initlize an object. 

```kotlin
package com.nareshtech.myfirstapp

import java.util.Scanner

class Dog(var name:String?, var age:Int?, var weight:Double?){

    constructor(name:String):this(name, 0,0.0){
        println("Remember that you only entered the name of the dog")
    }

    constructor(name:String, age:Int):this(name, age,0.0){
        println("Remember that you only entered the name & age of the dog")
    }

    fun display(){
        println("$name of age $age is of $weight weight")
    }
}

fun main(){
    val d = Dog("Tommy", 18, 18.45)
    d.display()

    val d1 = Dog("Jhonny")
    d1.display()
}
```


