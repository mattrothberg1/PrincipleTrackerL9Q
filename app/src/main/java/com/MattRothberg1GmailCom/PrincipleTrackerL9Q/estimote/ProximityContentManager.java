package com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote;

import android.content.Context;

import com.MattRothberg1GmailCom.PrincipleTrackerL9Q.MainActivity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class ProximityContentManager {

    private NearestBeaconManager nearestBeaconManager;

    private Listener listener;

    public ProximityContentManager(MainActivity context,
                                   List<BeaconID> beaconIDs,
                                   BeaconContentFactory beaconContentFactory) {
        final BeaconContentCache beaconContentCache = new BeaconContentCache(beaconContentFactory);

        nearestBeaconManager = new NearestBeaconManager(context, beaconIDs);
        nearestBeaconManager.setListener(new NearestBeaconManager.Listener() {
            @Override
            public void onNearestBeaconChanged(BeaconID beaconID) {
                if (listener == null) {
                    return;
                }

                if (beaconID != null) {
                    beaconContentCache.getContent(beaconID, new BeaconContentFactory.Callback() {
                        @Override
                        public void onContentReady(Object content) throws IOException, GeneralSecurityException {
                            listener.onContentChanged(content);
                        }
                    });
                } else {
                    try {
                        listener.onContentChanged(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }



    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onContentChanged(Object content) throws IOException, GeneralSecurityException;
    }

    public void startContentUpdates() {
        nearestBeaconManager.startNearestBeaconUpdates();
    }

    public void stopContentUpdates() {
        nearestBeaconManager.stopNearestBeaconUpdates();
    }

    public void destroy() {
        nearestBeaconManager.destroy();
    }
}
