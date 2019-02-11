package com.khinthirisoe.bakingapp.ui

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Binder
import android.widget.AdapterView
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.db.IngredientsContract

class BakingWidgetRemoteViewsFactory(private val mContext: Context, intent: Intent) :
    RemoteViewsService.RemoteViewsFactory {
    private var mCursor: Cursor? = null

    override fun onCreate() {

    }

    override fun onDataSetChanged() {

        if (mCursor != null) {
            mCursor!!.close()
        }

        val identityToken = Binder.clearCallingIdentity()
        val uri = IngredientsContract.Ingredients.CONTENT_URI
        mCursor = mContext.contentResolver.query(
            uri, null, null, null
        )

        Binder.restoreCallingIdentity(identityToken)

    }

    override fun onDestroy() {
        if (mCursor != null) {
            mCursor!!.close()
        }
    }

    override fun getCount(): Int {
        return if (mCursor == null) 0 else mCursor!!.count
    }

    override fun getViewAt(position: Int): RemoteViews? {
        if (position == AdapterView.INVALID_POSITION ||
            mCursor == null || !mCursor!!.moveToPosition(position)
        ) {
            return null
        }

        val rv = RemoteViews(mContext.packageName, R.layout.list_baking_app_widget)
        rv.setTextViewText(
            R.id.widget_ingredient,
            mCursor!!.getString(mCursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_NAME))
        )
        rv.setTextViewText(
            R.id.widget_quality,
            mCursor!!.getString(mCursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_QUALITY))
        )
        rv.setTextViewText(
            R.id.widget_measure,
            mCursor!!.getString(mCursor!!.getColumnIndex(IngredientsContract.Ingredients.COL_MEASURE))
        )

//        val fillInIntent = Intent()
//        fillInIntent.putExtra(BakingAppWidget.EXTRA_LABEL, mCursor!!.getString(1))
//        rv.setOnClickFillInIntent(R.id.widgetItemContainer, fillInIntent)

        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return if (mCursor!!.moveToPosition(position)) mCursor!!.getLong(0) else position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}
