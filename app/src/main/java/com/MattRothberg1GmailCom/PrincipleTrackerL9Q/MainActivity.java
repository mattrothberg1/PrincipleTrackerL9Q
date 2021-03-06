package com.MattRothberg1GmailCom.PrincipleTrackerL9Q;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote.BeaconID;
import com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote.EstimoteCloudBeaconDetails;
import com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote.EstimoteCloudBeaconDetailsFactory;
import com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote.ProximityContentManager;
import com.estimote.coresdk.cloud.model.Color;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;

import com.google.api.services.sheets.v4.SheetsScopes;

import com.google.api.services.sheets.v4.model.*;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//<<<<<<< HEAD
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
//
// Running into any issues? Drop us an email to: contact@estimote.com
//
//=======

//realm imports

//
// Running into any issues? Drop us an email to: contact@estimote.com
//










// jack and tommy 11.15

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();


    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("D0D3FA86-CA76-45EC-9BD9-6AF4012AA803", 51816, 56249),
                        new BeaconID("D0D3FA86-CA76-45EC-9BD9-6AF40E8162BF", 12110, 55284),
                        new BeaconID("D0D3FA86-CA76-45EC-9BD9-6AF4176B9BC5", 39922, 54118)),
                new EstimoteCloudBeaconDetailsFactory());
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                String text;
                Integer backgroundColor;
                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;

                    //add to realm
                    realm = Realm.getDefaultInstance();
                    addLog(realm);

                    text = "You're in " + beaconDetails.getBeaconName() + "'s range!";
                    backgroundColor = BACKGROUND_COLORS.get(beaconDetails.getBeaconColor());
                } else {
                    text = "No beacons in range.";
                    backgroundColor = null;
                }
                ((TextView) findViewById(R.id.textView)).setText(text);
                findViewById(R.id.relativeLayout).setBackgroundColor(
                        backgroundColor != null ? backgroundColor : BACKGROUND_COLOR_NEUTRAL);
            }
        });
    }








    @Override
    protected void addLog(Realm realm){
        realm.executeTransaction(new Realm.Transaction(){
            Log log = realm.createObject(Log.class);
            log.setTime();
            log.setRoomID();
            log.setStatus();
        });
    }


    @Override
    protected void showLogs(){
        RealmResults<Log> results1 = realm.where(Log.class).findAll();
        for(Log l: results1){
            Log.d(l.time, l.roomID);
        }
    }




    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Starting ProximityContentManager content updates");
            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Stopping ProximityContentManager content updates");
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }
}
