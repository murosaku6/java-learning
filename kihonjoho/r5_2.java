public class r5_2 {

    // 大域変数
    static int[] data = {2, 1, 3, 5, 4};

    public static void main(String[] args) {

        sort(0, 4);

        System.out.println("最終結果");
        for (int num : data) {
            System.out.print(num + " ");
        }
    }

    public static void sort(int first, int last) {

        int pivot;
        int i;
        int j;

        pivot = data[(first + last) / 2];
        i = first;
        j = last;

        while (true) {

            while (data[i] < pivot) {
                i++;
            }

            while (pivot < data[j]) {
                j--;
            }

            if (i >= j) {
                break;
            }

            // data[i]とdata[j]を入れ替える
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;

            i++;
            j--;
        }

        // *** α ***
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println();

        if (first < i - 1) {
            sort(first, i - 1);
        }

        if (j + 1 < last) {
            sort(j + 1, last);
        }
    }
}