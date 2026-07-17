public class r7_4 {
    public static int change(int n){
        int count = 0;
        int rest = n;
        while(rest >= 0){
            count = count + (rest / 5) + 1;
            rest = rest - 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(change(0));
        System.out.println(change(5));
        System.out.println(change(10));
        System.out.println(change(20));
    }
}
