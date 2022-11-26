import java.io.IOException;
import java.util.*;

public class FruitIntoBaskets {
    public static void main(String[] args) throws IOException {

        int[] fruits = new int[]{1,9,1,8,22,0,22,19,19,2,
                19,6,6,19,2,20,2,9,9,9,
                9,16,19,16,19,11,19,0,19,19};
        int maxFruits = 0;
        int currentFruits = 0;
        ArrayList<Integer> threeDifferentFruits = new ArrayList<Integer>();
        List<Integer> lastIndex = new ArrayList<Integer>();

        //sliding window
        for (int i = 0, j = 0; j< fruits.length; j++){
            System.out.println("start of window " + i);
            //add the fruit type to the three extinct fruit type
            if (!threeDifferentFruits.contains(fruits[j])){
                if (threeDifferentFruits.size() >= 3) {
                    threeDifferentFruits.remove(0);
                }
                threeDifferentFruits.add(fruits[j]);
            } else {
                threeDifferentFruits.remove(threeDifferentFruits.indexOf(fruits[j]));
                threeDifferentFruits.add(fruits[j]);
            }

//            update the last index which the fruit change
            if (j - 1 >= 0 && fruits[j] != fruits[j-1]){
                lastIndex.add(j);
                System.out.println("last index append for fruit "+ fruits[j]+ " index is " +j);
            }

//            if the current fruit is the same as the start of the window, extend the current window length
            if (fruits[i] == fruits[j]){
                currentFruits++;
                System.out.println("adding fruit "+ fruits[j] + " " + currentFruits);
            } else if (j - 1 >= 0 && fruits[j] == fruits[j-1]) {
                //this is when the start of the window is different than j and j+1 in fruits, e.g. 1 2 2
                currentFruits++;
                System.out.println("adding fruit "+ fruits[j] + " " + currentFruits);
            }
//            when the current fruit no longer equal to the start of the window and is already added to the
//            three different fruits queue,
            else if (fruits[i] != fruits[j]){
                System.out.println("three different fruits right now are " + threeDifferentFruits);
//            now if this is the second fruit on the queue, we can still move on to j+1
                if (threeDifferentFruits.size() == 2){
                    currentFruits++;
                    System.out.println("adding fruit "+ fruits[j] + " " + currentFruits);
                }
//            now if this is the third distinctive fruit on the queque, we need to adject the start of the window i,
//            and potentially update maximum window length
                if (threeDifferentFruits.size() == 3){
                    maxFruits = Math.max(currentFruits, maxFruits);
                    //now update i to lastIndex[-2]
                    i = lastIndex.get(lastIndex.size()-2);
                    System.out.println("start of window "+i);
                    //and remove the head of the queue three different fruits
                    threeDifferentFruits.remove(0);
                    //and now there is again only two type of fruits in the window, update current window length
                    currentFruits = j - i + 1;
                    //if some of the three different fruits is no longer in side the window, throw it out
                    for (int k=0; k<threeDifferentFruits.size(); k++){
                        if (threeDifferentFruits.get(0) != fruits[i] && threeDifferentFruits.get(0) != fruits[j]){
                            threeDifferentFruits.remove(0);
                        }
                    }
                }
            }

        }
        System.out.println("answer: " + Math.max(currentFruits, maxFruits));
    }
}

