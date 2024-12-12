package com.nareshtech.myfirstapp

import java.util.Scanner

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