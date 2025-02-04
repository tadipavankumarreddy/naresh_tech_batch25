package com.nareshtech.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {

    lateinit var databaseHelper: DatabaseHelper

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        databaseHelper.insertData(values!!)
        return uri
    }

    override fun onCreate(): Boolean {
        databaseHelper = DatabaseHelper(context!!,"person",1)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return databaseHelper.getData()
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }
}