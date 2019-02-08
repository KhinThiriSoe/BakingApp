package com.khinthirisoe.bakingapp.ui;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class BakingWidgetService extends RemoteViewsService {
    private static final String TAG = "WidgetService";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "onGetViewFactory: " + "Service called");
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
