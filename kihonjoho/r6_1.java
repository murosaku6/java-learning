public class r6_1 {

    public static void merge(){
        int[] data1 = {2, 3};
        int[] data2 = {1, 4};
        int n1 = data1.length;
        int n2 = data2.length;
        int[] work = new int[n1 + n2];
        int i = 0;
        int j = 0;
        int k = 0;

        // 数え用
        int count = 0;

        while (i < n1 && j < n2) {
            if(data1[i] <= data2[j]) {
                work[k] = data1[i];
                i++;
                k++;
            } else {
                work[k] = data2[j];
                j++;
            }
        }

        while (i <= n1) {
            work[k] = data1[i];
            i++;
            k++;
        }

        while (j < n2) {
            work[k] = data2[j];   /*** a ***/
            // 何回通過したか
            count++;
            j++;
            k++;
        }
        // 何回通過したか
        System.out.println("aの実行回数：" + count);
    }

    public static void main(String[] args){
        merge();
    }
}
