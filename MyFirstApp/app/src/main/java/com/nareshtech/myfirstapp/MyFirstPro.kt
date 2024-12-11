package com.nareshtech.myfirstapp

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