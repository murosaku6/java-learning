public class r8_2 {
    public static int complement(int x){
        return ~x + 1;
    }
    public static void main(String[] args) {
        int x = 5;
        System.out.println(complement(x));
    }
}
