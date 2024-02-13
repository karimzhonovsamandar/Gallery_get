package com.sammy.sardorapp.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sammy.sardorapp.models.User

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION_CODE),
    MyDbInter {


    companion object {

        const val DB_NAME = "my_db"
        const val VERSION_CODE = 1

        const val TABLE_NAME = "user_table"
        const val ID = "id"
        const val IMAGE = "image"
        const val NAME = "name"


    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query = "create table $TABLE_NAME( $ID integer not null primary key autoincrement," +
                "$IMAGE text not null," +
                "$NAME text not null)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun add(user: User) {
        val database = this.writableDatabase
        var contentValue = ContentValues()
        contentValue.put(IMAGE, user.image)
        contentValue.put(NAME, user.name)
        database.insert(TABLE_NAME,null, contentValue)
        database.close()

    }

    override fun getAllDate(): ArrayList<User> {
        var list = ArrayList<User>()
        val database = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"

        val cursor = database.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val user = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(user)
            }while (cursor.moveToNext())
        }

        return list

    }
}