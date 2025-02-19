package com.nareshtech.loginfortesting

import org.junit.Assert.*
import org.junit.Test

// This class checks if 'isLoginValid' function is working properly or not
class LoginFuncTest {
    @Test
    fun validLogin_returnTrue(){
        assertTrue(isLoginValid("admin","password123"))
    }

    @Test
    fun InvalidLogin_returnFalse(){
        assertFalse(isLoginValid("admin","password"))
        assertFalse(isLoginValid("Adming","aae"))
    }

    fun isLoginValid(name:String,psw:String):Boolean{
        return name == "admin" && psw == "password123"
    }
}