public class r7_1{

    public static int function1(int n, int m){
        int count = 0;
        int i;
        for(i = n; i <= m; i++){
            if(i % 4 == 0){
                count++;
            }
        }
        return count;
    }

    public static int function2(int n, int m){
        int count = 0;
        int tempN = n;
        int i;
        int j;

        for(i = 1; i <= 3; i++){
            if(tempN % 4 == 0){
                break;
            }
            tempN++;
        }
        for(j = tempN; j < m; j = j + 4){
            count++;
        }
        return count;
    }

    public static void main(String[] arg){
    }
}