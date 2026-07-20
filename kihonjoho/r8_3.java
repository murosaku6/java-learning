public class r8_3 {
    public static int func1(int n){
        if(n <=2){
            return 1;
        }
        return 2 * func1(n - 2) + func1(n - 1);
    }

    public static int func2(int n){
        int[] data = {1, 1, 1};
        int i;

        if(n > 3){
            for(i = 3; i <= n; i++){
                data[0] = data[1];
                data[1] = data[2];
                data[2] = 2 * data[0] + data[1];
            } 
        }
        return data[2];
    }
}
