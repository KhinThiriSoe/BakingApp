package com.khinthirisoe.bakingapp.data.db

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns
import android.provider.BaseColumns._ID

object IngredientsContract {

    const val CONTENT_AUTHORITY = "com.khinthirisoe.bakingapp"
    val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

    const val TYPE_TEXT = "TEXT"
    const val PRIMARY_KEY = " PRIMARY KEY"
    const val NOT_NULL = " NOT NULL"
    const val UNIQUE = " UNIQUE"
    const val TYPE_PRIMARY_KEY_TEXT_UNIQUE = TYPE_TEXT + PRIMARY_KEY + NOT_NULL + UNIQUE
    const val TYPE_TEXT_NOT_NULL = TYPE_TEXT + NOT_NULL

    class Ingredients : BaseColumns {

        companion object {

            val TABLE_NAME = "ingredient"

            val COL_NAME = "name"
            val COL_QUALITY = "quality"
            val COL_MEASURE = "measure"

            val COLUMNS = arrayOf(
                arrayOf(
                    _ID,
                    TYPE_PRIMARY_KEY_TEXT_UNIQUE
                ),
                arrayOf(
                    COL_NAME,
                    TYPE_TEXT_NOT_NULL
                ),
                arrayOf(
                    COL_QUALITY,
                    TYPE_TEXT_NOT_NULL
                ),
                arrayOf(
                    COL_MEASURE,
                    TYPE_TEXT_NOT_NULL
                )
            )

            val CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY + DbConstant.SEPARATOR + TABLE_NAME)
            val CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + DbConstant.SEPARATOR + TABLE_NAME
            val ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + DbConstant.SEPARATOR + TABLE_NAME

            fun buildTodoUriWithId(id: Long): Uri {
                return BASE_CONTENT_URI.buildUpon()
                    .appendPath(java.lang.Long.toString(id))
                    .build()
            }
        }
    }
}