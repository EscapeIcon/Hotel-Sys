public class Front {
    public static void main(String[] args) {
        RoomObj[] obj = new RoomObj[10];
        for(int i = 0;i < 10;i++){
            if(i % 2 == 0)obj[i] = new DoubleRoom(110+i);
            else obj[i] = new SingleRoom(110+i);
        }
        RoomManage roomManage = new RoomManage(obj);

        System.out.println(roomManage.toString());
        roomManage.checkState();
        roomManage.orderRoom(112);
        roomManage.checkState();
        roomManage.checkOutRoom(112);
        roomManage.checkState();
    }
}
