import java.util.Arrays;

public class r8_1 {
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int top;
        int i;
        int len = data.length - 1;
        top = data[len];
        for(i = len; i >= 1; i--){
            data[i] = data[i -1];
        }
        data[0] = top;
        System.out.println(Arrays.toString(data));
    }
}
