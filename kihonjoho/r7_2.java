public class r7_2{
    public static int search(String[] data, String[] key){
        int i;
        int j;
        int lenData;
        int lenKey;
        int result = -1;

        lenData = data.length;
        lenKey = key.length;

            for(i = 0; i < lenData - lenKey + 1; i++){
                for(j = 0; j < lenKey; j++){
                    if(data[i + j].equals(key[j])){
                        if(j == lenKey - 1){
                            result = i;
                        }
                    } else {
                        break;
                    }
                }
            }
            return result;
        }

    public static void main(String[] args) {
        
    }
}