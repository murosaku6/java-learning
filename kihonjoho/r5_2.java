public class r5_2{
    public static void main(){
    int[] data = {2, 1, 3, 5, 4};
    int first = data[0];
    int last = data[4];
    int pivot = (first + last) / 2;
    int i = data[0];
    int j = data[5];

    System.out.println(pivot);

    while (true) {
        while(i < pivot){
            i = i + 1;
        }
        while (pivot < j) {
            j = j -1;
        }
        if (i >= j){
            break;
        }
        i = i + 1;
        j = j - 1;
    }
    // if (data[0] < i -1){
    //     sort(data[0], i - 1);
    // } if (j + 1 < data[5]){
    //     sort(j + 1, data[5]);
    // }
    }
}