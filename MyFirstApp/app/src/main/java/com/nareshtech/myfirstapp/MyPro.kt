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