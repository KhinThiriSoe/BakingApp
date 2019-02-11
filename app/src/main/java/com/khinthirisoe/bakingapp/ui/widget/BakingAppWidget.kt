package com.khinthirisoe.bakingapp.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.ui.baking.view.BakingActivity

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [BakingAppWidgetConfigureActivity]
 */
class BakingAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(
                context.packageName,
                R.layout.baking_app_widget
            )

            // click event handler for the title, launches the app when the user clicks on title
            val titleIntent = Intent(context, BakingActivity::class.java)
            val titlePendingIntent = PendingIntent.getActivity(context, 0, titleIntent, 0)
            views.setOnClickPendingIntent(R.id.widgetTitleLabel, titlePendingIntent)


            val intent = Intent(context, BakingWidgetRemoteViewsService::class.java)
            views.setRemoteAdapter(R.id.widgetListView, intent)

            // template to handle the click listener for each item
//            val clickIntentTemplate = Intent(context, IngredientsActivity::class.java)
//            val clickPendingIntentTemplate = TaskStackBuilder.create(context)
//                .addNextIntentWithParentStack(clickIntentTemplate)
//                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//            views.setPendingIntentTemplate(R.id.widgetListView, clickPendingIntentTemplate)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            BakingAppWidgetConfigureActivity.deleteTitlePref(
                context,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            // refresh all your widgets
            val mgr = AppWidgetManager.getInstance(context)
            val cn = ComponentName(context, BakingAppWidget::class.java)
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widgetListView)
        }
        super.onReceive(context, intent)
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

            val widgetText =
                BakingAppWidgetConfigureActivity.loadTitlePref(
                    context,
                    appWidgetId
                )
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.baking_app_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun sendRefreshBroadcast(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(context, BakingAppWidget::class.java)
            context.sendBroadcast(intent)
        }
    }
}

