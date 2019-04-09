package com.khinthirisoe.bakingapp.data.db

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns
import com.khinthirisoe.bakingapp.data.db.DbConstant.TYPE_PRIMARY_KEY_TEXT_UNIQUE
import com.khinthirisoe.bakingapp.data.db.DbConstant.TYPE_TEXT

object IngredientsContract {

    const val CONTENT_AUTHORITY = "com.khinthirisoe.bakingapp"
    val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

    class Ingredients : BaseColumns {

        companion object {

            const val TABLE_NAME = "ingredient"

            const val COL_NAME = "name"
            const val COL_QUALITY = "quality"
            const val COL_MEASURE = "measure"

            val COLUMNS = arrayOf(
                arrayOf(
                    COL_QUALITY,
                    TYPE_TEXT
                ),
                arrayOf(
                    COL_MEASURE,
                    TYPE_TEXT
                ),
                arrayOf(
                    COL_NAME,
                    TYPE_PRIMARY_KEY_TEXT_UNIQUE
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