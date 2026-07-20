import java.util.ArrayList;

public class r8_4 {
    static Integer[] dataList = {10, 30, 20, 40, null};
    static Integer[] pointerList = {3, 4, 2, null, null};

    public static int[] orderList(){
        int p = 0;
        ArrayList<Integer> linearList = new ArrayList<>();
        for(int i = 0; i < dataList.length; i++){
            linearList.add(dataList[p]);

            if(pointerList[p] == null){
                break;
            }

            p = pointerList[p] - 1;
        }


        int[] result = new int[linearList.size()];
        for(int i = 0; i < linearList.size(); i++){
            result[i] = linearList.get(i);
        }

        return result;
    }
}