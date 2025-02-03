package com.nareshtech.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(val context:Context, val name:String,val version:Int):SQLiteOpenHelper(context,name,null,version){
    // Requires you to override two methods
        // 1. onCreate() -> This is where you write queries that creates your table(s)
        // 2. onUpgrade() -> Initially you will be working on version 1 of the database - if new version is available - then this method is invoked by the system.
    companion object{
        val TABLE_NAME = "person"
        val COL_0 = "person_id"
        val COL_1 = "person_name"
        val COL_2 = "person_age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table $TABLE_NAME($COL_0 integer primary key autoincrement, $COL_1 text, $COL_2 integer);"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table $TABLE_NAME")
        onCreate(db)
    }

    // Insert Data into the Database
    fun insertData(values:ContentValues){
        // get the writable database
        val db:SQLiteDatabase = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
    }

    fun getData():Cursor{
        // get the readable database
        val db:SQLiteDatabase = this.readableDatabase
        return db.rawQuery("select * from $TABLE_NAME", null)
    }
}