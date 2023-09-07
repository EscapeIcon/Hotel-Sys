//房间类
public class RoomObj{
    private int roomId;
    private boolean isEmpty = true;

    //constructor
    public RoomObj(){}
    public RoomObj(int roomId) {
        this.roomId = roomId;
    }

    //getter and setter
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
//大床房类
class DoubleRoom extends RoomObj{
    public final static String ROOMTYPE = "大床房";

    //constructor
    public DoubleRoom() {}
    public DoubleRoom(int roomId){
        super(roomId);
    }
}

//标间类
class SingleRoom extends RoomObj{
    public final static String ROOMTYPE = "标间";

    //constructor
    public SingleRoom() {}
    public SingleRoom(int roomId) {
        super(roomId);
    }
}
