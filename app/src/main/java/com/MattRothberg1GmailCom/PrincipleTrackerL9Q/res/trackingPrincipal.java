package com.MattRothberg1GmailCom.PrincipleTrackerL9Q.res;
import java.util.*;
import io.realm.RealmObject;

public class trackingPrincipal extends RealmObject  {
    private Date time;
    private int room_id;
    private String status;
    private String date;
}

public void setDate(String date) {this.date = date;}
public Date getDate() {
    return date;
    }

public void setRoomID (int id) {
    this.room_id = id;
    }

public int getID() {
    return room_id;
    }

public setStatus (String status) {
    this.status = status;
    }

public getStatus () {
    return status;
}


