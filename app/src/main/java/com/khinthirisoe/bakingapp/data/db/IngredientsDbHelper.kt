package com.khinthirisoe.bakingapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.khinthirisoe.bakingapp.data.db.IngredientsContract.Ingredients

class IngredientsDbHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    companion object {

        const val DATABASE_VERSION = 4
        const val DATABASE_NAME = "ingredients"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTable(Ingredients.TABLE_NAME, Ingredients.COLUMNS))
    }

    private fun createTable(tableName: String?, columns: Array<Array<String>>?): String {
        if (tableName == null || columns == null || columns.isEmpty()) {
            throw IllegalArgumentException("Invalid parameters for creating table " + tableName!!)
        } else {
            val stringBuilder = StringBuilder("CREATE TABLE ")

            stringBuilder.append(tableName)
            stringBuilder.append(" (")
            var n = 0
            val i = columns.size
            while (n < i) {
                if (n > 0) {
                    stringBuilder.append(", ")
                }
                stringBuilder.append(columns[n][0]).append(' ').append(columns[n][1])
                n++
            }
            return stringBuilder.append(");").toString()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        val drop = "DROP TABLE IF EXISTS "
        db.execSQL(drop + Ingredients.TABLE_NAME)
        onCreate(db)
    }
}

