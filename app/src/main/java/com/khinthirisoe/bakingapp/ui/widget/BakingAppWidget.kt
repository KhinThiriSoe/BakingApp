package com.khinthirisoe.bakingapp.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.baking.view.BakingActivity

class BakingAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(

                context.packageName,
                R.layout.baking_app_widget

            )

            val pref = AppPreferencesHelper(context)
            var recipeName : String
            if (pref.recipeName.isNotEmpty()){
                recipeName = pref.recipeName
                views.setTextViewText(R.id.widgetTitleLabel, recipeName)
            }

            val titleIntent = Intent(context, BakingActivity::class.java)
            val titlePendingIntent = PendingIntent.getActivity(context, 0, titleIntent, 0)
            views.setOnClickPendingIntent(R.id.widgetTitleLabel, titlePendingIntent)


            val intent = Intent(context, BakingWidgetRemoteViewsService::class.java)
            views.setRemoteAdapter(R.id.widgetListView, intent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    companion object {

        fun sendRefreshBroadcast(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(context, BakingAppWidget::class.java)
            context.sendBroadcast(intent)
        }

    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            // refresh all your widgets
            val mgr = AppWidgetManager.getInstance(context)
            val cn = ComponentName(context, BakingAppWidget::class.java)
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widgetListView)
        }
        super.onReceive(context, intent)
    }
}

