import java.util.Arrays;

public class RoomManage implements RoomInterface{
    private RoomObj[] room;

    //constructor
    public RoomManage() {}
    public RoomManage(RoomObj[] room) {
        this.room = room;
    }

    //getter and setter
    public Object[] getRoom() {
        return room;
    }
    public void setRoom(RoomObj[] room) {
        this.room = room;
    }

    //订房
    public void orderRoom(int roomId){
        this.room[traverseRoom(roomId,this.room)].setEmpty(false);
        System.out.println("订房成功，您的房间为："+roomId+"，订金1000~");
    };
    //退房
    public void checkOutRoom(int roomId){
        this.room[traverseRoom(roomId,this.room)].setEmpty(true);
        System.out.println("退房成功，定金不退！");
    };

    //查看所有房间的状态
    public void checkState(){
        for(int i = 0;i < this.room.length;i++){
            System.out.println("==========");
            System.out.println("房间类型："+(this.room[i] instanceof DoubleRoom ? DoubleRoom.ROOMTYPE : SingleRoom.ROOMTYPE)+",房间号:"+this.room[i].getRoomId()+",房间是否空闲:"+this.room[i].isEmpty());
        }
    }

    //寻找房间号相同的索引静态方法
    public static int traverseRoom(int roomId,RoomObj[] roomObj){
        for (int i = 0;i < roomObj.length;i++){
            if(roomObj[i].getRoomId() == roomId){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "RoomManage{" +
                "room=" + Arrays.toString(room) +
                '}';
    }
}
