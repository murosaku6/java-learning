import java.util.ArrayList;

public class r5_1 {

    // maxNum以下の素数を全て返す
    public static ArrayList<Integer> findPrimeNumbers(int maxNum) {

        ArrayList<Integer> pnList = new ArrayList<>();

        for (int i = 2; i <= maxNum; i++) {
            boolean divideFlag = true;
            // iの平方根まで調べる
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                // iがjで割り切れたら素数ではない
                if (i % j == 0) {
                    divideFlag = false;
                    break;
                }
            }
            // 割り切れなかったら素数
            if (divideFlag) {
                pnList.add(i);
            }
        }
        return pnList;
    }

    public static void main(String[] args) {
        int maxNum = 30;
        ArrayList<Integer> primeNumbers = findPrimeNumbers(maxNum);
        System.out.println(primeNumbers);
    }
}