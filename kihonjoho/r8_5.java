import java.util.*;

public class r8_5 {
    public static ArrayList<ArrayList<Integer>> oneHotEncoding(String[] colors){
        int i, j, k;
        ArrayList<String> colorVector = new ArrayList<>();
        ArrayList<ArrayList<Integer>> oneHotVector = new ArrayList<>();

        for(i = 0; i < colors.length; i++){
            if(!colorVector.contains(colors[i])){
                colorVector.add(colors[i]);
            }
        }

        for(j = 0; j <= colors.length; j++){
            ArrayList<Integer> tempVector = new ArrayList<>();
            for(k = 0; k < colorVector.size(); k++){
                if(colors[j].equals(colorVector.get(k))){
                    tempVector.add(1);
                } else {
                    tempVector.add(0);
                }
            }
            oneHotVector.add(tempVector);
        }
        return oneHotVector;
    }
}
