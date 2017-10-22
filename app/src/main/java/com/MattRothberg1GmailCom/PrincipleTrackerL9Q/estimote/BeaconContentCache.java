package com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

public class BeaconContentCache implements BeaconContentFactory {

    private BeaconContentFactory beaconContentFactory;

    private Map<BeaconID, Object> cachedContent = new HashMap<>();

    public BeaconContentCache(BeaconContentFactory beaconContentFactory) {
        this.beaconContentFactory = beaconContentFactory;
    }

    @Override
    public void getContent(final BeaconID beaconID, final Callback callback) {
        if (cachedContent.containsKey(beaconID)) {
            try {
                callback.onContentReady(cachedContent.get(beaconID));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } else {
            beaconContentFactory.getContent(beaconID, new Callback() {
                @Override
                public void onContentReady(Object content) {
                    cachedContent.put(beaconID, content);
                    try {
                        callback.onContentReady(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
