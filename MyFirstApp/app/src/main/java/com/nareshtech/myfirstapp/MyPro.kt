package com.nareshtech.myfirstapp

import java.util.Scanner


fun main(){
   // filter, map & sortedBy they are especially useful for a collection of items.
    val numbers = listOf(5,6,45,1,0,36)
    val sorted = numbers.sortedByDescending { it }
    println(sorted)
}
