package com.khinthirisoe.bakingapp.data.db

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import androidx.annotation.NonNull
import com.khinthirisoe.bakingapp.data.db.IngredientsContract.CONTENT_AUTHORITY
import com.khinthirisoe.bakingapp.data.db.IngredientsContract.Ingredients

class IngredientsProvider : ContentProvider() {

    private var mDbHelper: IngredientsDbHelper? = null

    companion object {
        const val URI_INGREDIENT = 1

        val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {

            URI_MATCHER.addURI(
                CONTENT_AUTHORITY,
                IngredientsContract.Ingredients.TABLE_NAME, URI_INGREDIENT
            )
        }
    }

    override fun onCreate(): Boolean {
        mDbHelper = IngredientsDbHelper(context)
        return true
    }

    override fun query(
        @NonNull uri: Uri, projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val queryBuilder = SQLiteQueryBuilder()
        val mDatabase = mDbHelper!!.readableDatabase
        val matchCode = URI_MATCHER.match(uri)

        when (matchCode) {
            URI_INGREDIENT -> {
                queryBuilder.tables = Ingredients.TABLE_NAME
            }

            else -> throw IllegalArgumentException("Illegal query. Match code=$matchCode; uri=$uri")
        }

        val cursor = queryBuilder.query(mDatabase, projection, selection, selectionArgs, null, null, sortOrder)
        if (context != null)
            cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun bulkInsert(uri: Uri, values: Array<ContentValues>): Int {
        val db = mDbHelper?.writableDatabase
        when (URI_MATCHER.match(uri)) {
            URI_INGREDIENT -> {
                db?.beginTransaction()
                var rowsInserted = 0
                try {
                    for (value in values) {
                        val _id = db?.insert(Ingredients.TABLE_NAME, null, value)
                        if (_id != (-1).toLong()) {
                            rowsInserted++
                        }
                    }
                    db?.setTransactionSuccessful()
                } finally {
                    db?.endTransaction()
                }
                if (rowsInserted > 0) {
                    context!!.contentResolver.notifyChange(uri, null)
                }
                return rowsInserted
            }
            else -> return super.bulkInsert(uri, values)
        }
    }

    override fun insert(@NonNull uri: Uri, contentValues: ContentValues?): Uri? {
        val db = mDbHelper?.writableDatabase

        return when (URI_MATCHER.match(uri)) {
            URI_INGREDIENT -> {
                val _id = db?.insert(Ingredients.TABLE_NAME, null, contentValues)
                if (_id != (-1).toLong()) {
                    context!!.contentResolver.notifyChange(uri, null)
                }

                Ingredients.buildTodoUriWithId(_id!!)
            }
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = mDbHelper?.writableDatabase
        val numDeleted: Int
        when (URI_MATCHER.match(uri)) {
            URI_INGREDIENT -> {
                numDeleted = db!!.delete(
                    Ingredients.TABLE_NAME, selection, selectionArgs
                )
            }
            else -> throw UnsupportedOperationException("Unknown uri: $uri")
        }

        context.contentResolver.notifyChange(uri, null)
        return numDeleted
    }

    override fun update(@NonNull uri: Uri, contentValues: ContentValues?, s: String?, strings: Array<String>?): Int {
        return 0
    }

    override fun getType(@NonNull uri: Uri): String? {
        val matchCode = URI_MATCHER.match(uri)

        return when (matchCode) {
            URI_INGREDIENT -> Ingredients.CONTENT_TYPE
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}