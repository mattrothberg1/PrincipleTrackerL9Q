package com.MattRothberg1GmailCom.PrincipleTrackerL9Q;

import android.app.Application;

/**
 * Created by jackaustin on 11/27/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, YourService.class));
    }
}
