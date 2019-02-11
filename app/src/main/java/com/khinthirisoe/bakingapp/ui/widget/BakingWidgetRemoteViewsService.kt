package com.khinthirisoe.bakingapp.ui.widget

import android.content.Intent
import android.widget.RemoteViewsService

class BakingWidgetRemoteViewsService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        return BakingWidgetRemoteViewsFactory(this.applicationContext, intent)
    }
}
