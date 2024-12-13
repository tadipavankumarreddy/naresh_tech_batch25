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
### Inheritance in kotlin

Inheritance is the process of acquring the properties and behaviors of one class into another class. 

Inheritance is primarily used for Re-Usability of the code

**Important Point to Note:**

- All classes in kotlin have a common super class, `Any`, which is the default super class for a class with no super class defined. 

```kotlin
class ABCD{
    // The default super class will be ANY
}
```

`Any` class has got three methods
- equals()
- hashCode()
- toString()

```koltin
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
    val d1 = Dog("Tommy", 18, 18.45)

    println(d.equals(d1))
    println(d.toString())
    println(d1.toString())
    println(d.hashCode())
    println(d1.hashCode())
}
```

**Important Point to Note:**
In kotlin, all classes are `final` by default.If you want to subclass a class you use `open` keyword to make the class subclassable. 

```Kotlin'
class Pavan{
    // This class is final which cannot be subclassed. 
}
```

```kotlin
open class Pavan{
    // This class can be extended by subclasses. 
}
```
In Java, we use `extends` keyword to create a subclass. In kotlin we use `:` instead of `extends`.

```kotlin
open class Pavan{

}

class Kumar:Pavan(){

}
```
**Example**

```kotlin
open class A(var name:String?, var age:Int?){
    fun display(){
        print("$name $age ")
    }
}

class B(var n:String?, var a:Int?, var salary:Int?):A(n,a){
    fun display2(){
        display()
        println("$salary")
    }
}

fun main(){
    val b = B("Pavan",18,10000)
    b.display2()
}
```

### Encapsulation

Encapsulation refers to the bundling of data and methods that operate on data with in a single unit, is called as class. 

Encapsulation is a way to hide the implementation details of a class from outside access and only exposing public interfaces that can be used to interact with the class. 

***Modifiers in Kotlin:***
- **private** - the element that is declared private cannot be accessed from outside the kotlin file that is defined in. These elements can only be accessed in the same place where they are defined. 
- **public** - Elements can be accessed from anywhere. 
- **protected** - Same as private except that subclasses can access the class/interface elements. 
- **internal** - Anything in the module (folder) can be accessed. 

```kotlin
class Internals{
    internal  var a = 10
}

fun main(){
    val i = Internals()
    println(i.a)
}
```

```kotlin
class BankAccount(private var balance:Double){
    fun deposit(amount:Double){
        if(amount>0){
            balance += amount
            println("Deposited $amount in your account")
            println("The new Balance is $balance")
        }else{
            println("The amount should be a positive for deposit")
        }
    }
    
    fun withdraw(amount:Double){
        if(amount>0 && amount<=balance){
            balance -= amount
            println("Withdrawal successful, the current balance is $balance")
        }else{
            println("Insufficient balance")
        }
    }
    
    fun getBalance():Double{
        return balance
    }
}
```

By using encapsulation, the internal state of the `BankAccount` class is protected from direct access and mofifications from outside the class. 
Only the methods provided by the class can modify the balance, ensuring that the class maintains control over how the balance is accessed and changed. This help maintain integrity of the data and prevents unintended side effects. 

If a class with a certain functionality is defined in a different package and the elements in the class are accessible either directly (if they are declared public) or with the help of their helper methods (incase of private or protected modifiers), you can write the import statements to import that module or a specific class. 

If a class in the same module(Package) needs to be accessed, you can directly access it. No need of Import statements. 


### Polymorphism
Existence in multiple forms is called polymorphism

- Overloading (compile time polymorphism)
- Overriding (runtime Polymorphism)
  
**Overloading**
```kotlin
fun sum(a:Int, b:Int):Int{
    return a+b
}

fun sum(a:Int, b:Int, c:Int):Int{
    return a+b+c
}

fun sum(a:Int, b:Double):Double{
    return a+b
}
fun main() {
    println(sum(10,20))
    println(sum(10,20,30))
    println(sum(10,30.45))
}
```

**Overriding**

The function that has `open` keyword can only overridden. 

```koltin
open class First{
    open fun sum(a:Int,b:Int) = a+b
    fun sum(a:Int, b:Int, c:Int) = sum(a,b)+c
}

class Second:First(){
    override fun sum(a:Int, b:Int) = a*b
}

fun main() {
    var s = Second()
    println(s.sum(10,20))
    println(s.sum(10,20,30))
}
```

### Abstract Classes in Kotlin
Abstract classes are those classes defined with abstract keyword. In an abstract class you can have methods with body and methods without a body.

Abstract classes cannot be instantiated on its own and must be subclassed.
- It can have Abstract methods (unimplemented)
- It can have concrete methods (defined)

```kotlin
abstract class RBI{
    /***This function is not open to override - so all the banks that
     * are undertaken by RBI should be implementing the same
     * interst rate***/
    fun homeLoanInterestRate():Double{
        return 7.65
    }

    abstract fun personalLoan():Double
}

class SBI:RBI(){
    override fun personalLoan():Double{
        return 6.2
    }
}

class ICICI:RBI(){
    override fun personalLoan():Double{
        return 7.8
    }
}

fun main(){
    val s:SBI = SBI()
    println(s.homeLoanInterestRate())
    println(s.personalLoan())
}
```


### Interfaces in Kotlin

Interfaces are similar to interfaces in java, but with some additonal features and more concise syntax.

**Declare an Interface**

```kotlin
interface MyInterface{
    fun myMethod()
    val myProperty:String
}
```
**Implementing an Inteface**

Classes in kotlin can implement one or other interfaces using `:` symbol.

```koltin
interface MyInterface{
    fun myMethod()
    val myProperty:String
}

class MyClass:MyInterface{
    override val myProperty:String = "Hello"
    override fun myMethod(){
        println("My Method Implementation")
    }
}
fun main() {
    val m = MyClass()
    m.myMethod()
}
```

#### Default Implementation
Kotlin interfaces can provide default implementation for Methods. 

```kotlin
interface MyInterface{
    fun myMethod(){
        println("This is default implementation")
    }
}
```

With interfaces, we can implement Multiple Inheritance

```kotlin
interface Animal{
    val name:String
    fun sound():String
    
    //default Implementation
    fun printDetails(){
        println("Animal: $name, sound: ${sound()}")
    }
}

class Dog(override val name:String):Animal{
    override fun sound():String = "Bark"
}

class Cat(override val name:String):Animal{
    override fun sound():String = "Meow"
}	
fun main() {
	val dog = Dog("Buddy")
    dog.printDetails()
}
```

### **Lambdas** and **Higher-Order Functions** in Kotlin.

---

### **What are Lambdas?**
A **lambda expression** is a function that can be passed as a parameter and doesn't need to be declared explicitly.

#### **Syntax of a Lambda**
```kotlin
val lambdaName: (InputType) -> ReturnType = { arguments -> body }
```

#### **Example**
```kotlin
val greet: (String) -> Unit = { name -> println("Hello, $name!") }

greet("Kotlin")  // Output: Hello, Kotlin!
```

---

### **Simplified Lambda Syntax**
Kotlin allows us to simplify lambdas further.

#### **Remove Explicit Types**
```kotlin
val greet = { name: String -> println("Hello, $name!") }
greet("Kotlin")  // Output: Hello, Kotlin!
```

#### **Single Parameter Shortcut (`it`)**
When there's only one parameter, you can use `it`:
```kotlin
val square = { it: Int -> it * it }
println(square(5))  // Output: 25
```

---

### **Higher-Order Functions**
A **higher-order function** is a function that takes another function as a parameter or returns a function.

#### **Example 1: Passing a Lambda as a Parameter**
```kotlin
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

val sum = calculate(5, 3) { x, y -> x + y }
println(sum)  // Output: 8
```

**Explanation**:
1. `calculate` takes two integers (`a` and `b`) and a lambda function `operation`.
2. `operation` performs an action on `a` and `b`.

---

### **Returning a Function**
A function can return another function.

#### **Example**
```kotlin
fun createMultiplier(multiplier: Int): (Int) -> Int {
    return { number -> number * multiplier }
}

val timesThree = createMultiplier(3)
println(timesThree(5))  // Output: 15
```

**Explanation**:
1. `createMultiplier` returns a lambda that multiplies its input by `multiplier`.
2. `timesThree` becomes a function that multiplies any number by 3.

---

### **Common Use Cases**
#### **Example 1: Filtering a List**
```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)
val evenNumbers = numbers.filter { it % 2 == 0 }
println(evenNumbers)  // Output: [2, 4, 6]
```

#### **Example 2: Mapping a List**
```kotlin
val doubled = numbers.map { it * 2 }
println(doubled)  // Output: [2, 4, 6, 8, 10, 12]
```

#### **Example 3: Sorting a List**
```kotlin
val names = listOf("Alice", "Bob", "Charlie")
val sortedNames = names.sortedBy { it.length }
println(sortedNames)  // Output: [Bob, Alice, Charlie]
```

---

### **Inline Higher-Order Functions**
Kotlin allows you to use `inline` for higher-order functions to improve performance by avoiding function object creation.

#### **Example**
```kotlin
inline fun inlineCalculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

val result = inlineCalculate(10, 5) { x, y -> x - y }
println(result)  // Output: 5
```

---

### **Working with `let`, `run`, `apply`, and `also`**
Kotlin provides several higher-order functions like `let`, `run`, `apply`, and `also` to make your code concise.

#### **Using `let`**
```kotlin
val name = "Kotlin"
name.let { println("Hello, $it!") }
```

#### **Using `apply`**
```kotlin
val person = Person().apply {
    name = "John"
    age = 30
}
```

---

### **Practical Example**
Here’s a complete example using lambdas and higher-order functions:
```kotlin
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)

    // Filtering even numbers
    val evenNumbers = numbers.filter { it % 2 == 0 }

    // Mapping to squares
    val squares = evenNumbers.map { it * it }

    println(squares)  // Output: [4, 16]
}
```

---
END
---
