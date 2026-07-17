public class r7_2{
    public static int search(String[] data, String[] key){
        int i;
        int j;
        int lenData;
        int lenKey;
        int result = -1;
        int count = 0;

        lenData = data.length;
        lenKey = key.length;

            for(i = 0; i < lenData - lenKey + 1; i++){
                for(j = 0; j < lenKey; j++){
                    if(data[i + j].equals(key[j])){
                        count++;

                        System.out.println("真回数：" + count + 
                        "回目 i = " + i + " j = " + j + "(" + 
                    data[i + j] + "=" + key[j] + ")");
                        if(j == lenKey - 1){
                            result = i;
                        }
                    } else {
                        break;
                    }
                }
            }
            System.out.println(count);
            return result;
        }

    public static void main(String[] args) {

    }
}