package com.khinthirisoe.bakingapp.ui.widget

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Binder
import android.os.Build
import android.widget.AdapterView
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.annotation.RequiresApi
import com.khinthirisoe.bakingapp.data.db.IngredientsContract


class BakingWidgetRemoteViewsFactory(private val context: Context, private val intent: Intent) :
    RemoteViewsService.RemoteViewsFactory {
    private var cursor: Cursor? = null

    override fun onCreate() {

        initCursor()

    }

    private fun initCursor() {
        if (cursor != null) {
            cursor!!.close()
        }
        val identityToken = Binder.clearCallingIdentity()

        cursor = context.contentResolver.query(
            IngredientsContract.Ingredients.CONTENT_URI, null, null, null, null
        )
        Binder.restoreCallingIdentity(identityToken)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDataSetChanged() {

        initCursor()

    }

    override fun onDestroy() {
        if (cursor != null) {
            cursor!!.close()
        }
    }

    override fun getCount(): Int {
        return if (cursor == null) 0 else cursor!!.count
    }

    override fun getViewAt(position: Int): RemoteViews? {
        if (position == AdapterView.INVALID_POSITION ||
            cursor == null || !cursor!!.moveToPosition(position)
        ) {
            return null
        }

        val rv = RemoteViews(context.packageName, com.khinthirisoe.bakingapp.R.layout.list_baking_app_widget)
        rv.setTextViewText(
            com.khinthirisoe.bakingapp.R.id.widget_ingredient,
            cursor!!.getString(cursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_NAME))
        )
        rv.setTextViewText(
            com.khinthirisoe.bakingapp.R.id.widget_quality,
            cursor!!.getString(cursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_QUALITY))
        )
        rv.setTextViewText(
            com.khinthirisoe.bakingapp.R.id.widget_measure,
            cursor!!.getString(cursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_MEASURE))
        )

        val fillInIntent = Intent()
        rv.setOnClickFillInIntent(com.khinthirisoe.bakingapp.R.id.widgetItemContainer, fillInIntent)

        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return if (cursor!!.moveToPosition(position)) cursor!!.getLong(0) else position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}
