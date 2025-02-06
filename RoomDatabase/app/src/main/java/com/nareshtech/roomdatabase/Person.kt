package com.nareshtech.roomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "human")
data class Human(
    @PrimaryKey(autoGenerate = true)
    val person_id:Int,

    @ColumnInfo(name = "personName")
    val person_name:String,
    val person_age:Int
)
