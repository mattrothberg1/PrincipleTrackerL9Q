package com.MattRothberg1GmailCom.PrincipleTrackerL9Q;


import io.realm.Realm;

import android.app.Application;

import com.estimote.coresdk.common.config.EstimoteSDK;



//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        realm.init(this);

        EstimoteSDK.initialize(getApplicationContext(), "principle-tracker-l9q", "ff70ea3880224b111e6d76e904f0561c");




    }


}


//to always run in the background
