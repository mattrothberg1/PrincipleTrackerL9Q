public class Log extends RealmObject {
    @PrimaryKey
    private String time;
    private int room_id;
    private String status;


    public TimeEntered getTimeEntered() {
        return date;
    }

    public void setRoomID(int id){
        this.room_id = id;
    }

    public int getID() {
        return room_id;
    }

    public setStatus(String status) {
        this.status = status;
    }

    public getStatus() {
        return status;
    }

}


