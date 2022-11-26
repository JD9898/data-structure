package googleOnlineFirstRound;

import google2020example.ASpecialString;

import java.io.IOException;
import java.util.*;

public class LoadBalancing {
    static int solution(Integer[] loads) {
        // put your solution here
        int sum = 0;
        for (int load: loads){
            sum += load;
        }
        System.out.println(sum);

        ArrayList<Integer> listOfLoads = new ArrayList<>();
        for(int i: loads){
            listOfLoads.add(i);
        }
        Collections.sort(listOfLoads);
        System.out.println("sorted array: "+listOfLoads);
//
        int currentSum = 0;
        int currentDifference = 0;
        int currentIndex = 0;
        System.out.println("sum/2="+sum/2);
        for (int i=0; i<listOfLoads.size(); i++) {
            currentSum += loads[i];
            currentIndex = i;
            System.out.println(""+i+"current number: "+loads[i]+", current sum = "+currentSum);
            if (currentSum == sum/2){return 0;}
            if (currentSum > sum/2){break;}
        }
        currentDifference = Math.abs((sum - currentSum) - currentSum);

        int previousNumverInTheArray = listOfLoads.get(currentIndex-1);
        int previousSum = currentSum - previousNumverInTheArray;
        int previousDifference = Math.abs((previousSum) - (sum - previousSum));
        int minDifference = Math.min(previousDifference, currentDifference);

        System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        return minDifference;
    }
    public static void main(String[] args) throws IOException {
        Integer[] loads = {1,2,5,4,3};
        int sol = solution(loads);
        System.out.println("solution: "+sol);
    }
}
