package com.nareshtech.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDAO {

    @Insert
    fun insertData(person: Person):Unit

    @Query("select * from person")
    fun getAllData():List<Person>

    @Update
    fun updateRow(person: Person)
}