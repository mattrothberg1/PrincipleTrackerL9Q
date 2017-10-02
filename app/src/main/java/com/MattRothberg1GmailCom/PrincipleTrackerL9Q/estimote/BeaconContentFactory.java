package com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote;

public interface BeaconContentFactory {

    void getContent(BeaconID beaconID, Callback callback);

    interface Callback {
        void onContentReady(Object content);
    }
}
