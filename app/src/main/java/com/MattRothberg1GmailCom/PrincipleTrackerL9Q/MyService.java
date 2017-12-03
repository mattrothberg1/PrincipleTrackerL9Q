package com.MattRothberg1GmailCom.PrincipleTrackerL9Q;

import android.app.Service;

/**
 * Created by jackaustin on 11/27/17.
 */

public class MyService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        return super.onStartCommand(intent, flags, startId);
    }
}
