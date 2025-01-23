package com.nareshtech.scoretrackermvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel {
    var count:MutableLiveData<Int>
    constructor(){
        Log.v("Main","ViewModel is created")
        count = MutableLiveData()
        count.value = 0
    }

    fun increment(){
        count.value = count.value?.plus(1)
    }

    fun decrement(){
        count.value = count.value?.minus(1)
    }
    override fun onCleared() {
        super.onCleared()
        Log.v("Main", "ViewModel is destroyed")
    }
}