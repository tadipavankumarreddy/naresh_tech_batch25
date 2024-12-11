package com.nareshtech.koltinprograms

// inorder to use the class, import it.

import java.util.Scanner
import kotlin.math.*

fun main() {
    println("Enter your choice\n1.Double the value\n2.Square\n3.Square Root")
    val s:Scanner = Scanner(System.`in`)
    val option = s.nextInt()
    println("Enter the number")
    val a = s.nextInt()

    when(option){
        1 -> {
            println(doubleValue(a))
        }
        2 -> println(powerOfTwo(a))
        3 -> println(squareRoot(a))
        else -> {
            println("Wrong Option! try rerunning the program again")
        }
    }

}

fun doubleValue(a:Int) = 2*a

fun powerOfTwo(a:Int) = a*a

fun squareRoot(a:Int) = sqrt(a.toDouble())


