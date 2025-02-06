package com.nareshtech.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDAO {

    @Insert
    fun insertData(person: Human):Unit

    @Query("select * from human")
    fun getAllData():List<Human>

    @Update
    fun updateRow(person: Human)
}