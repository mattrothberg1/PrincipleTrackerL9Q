package com.MattRothberg1GmailCom.PrincipleTrackerL9Q.estimote;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface BeaconContentFactory {

    void getContent(BeaconID beaconID, Callback callback);

    interface Callback {
        void onContentReady(Object content) throws IOException, GeneralSecurityException;
    }
}
