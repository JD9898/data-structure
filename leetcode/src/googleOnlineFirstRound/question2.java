package googleOnlineFirstRound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//*
// Question One
//
//Question Two
//Answer all questions
//You are given an array segments consisting of N integers denoting the lengths of several segments. Your task is to find among them four segments from which a rectangle can be constructed. What is the minimum absolute difference between the side lengths of the constructed rectangle?
//
//Write a function:
//
//int solution(int[] segments);
//
//that, given an array segments, returns the minimum absolute difference between the side lengths of the constructed rectangle or −1 if no rectangle can be constructed.
//
//Examples:
//
//For segments = [2, 2, 2, 2, 2], we can construct only a 2 x 2 rectangle out of the given segments. The function should return 0.
//For segments = [911, 1, 3, 1000, 1000, 2, 2, 999, 1000, 911], we can construct three rectangles: 2 x 911, 2 x 1000, and 911 x 1000. Out of those three possibilities, the best one is 911 x 1000. The function should return 89.
//For segments = [4, 1, 1, 1, 3], we cannot construct any rectangle out of the given segments. The function should return −1.
//For segments = [1, 1, 1], we cannot construct any rectangle out of the given segments. The function should return −1.
//Assume that:
//
//N is an integer within the range [1..30];
//each element of array segments is an integer within the range [1..1,000].
//In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
//
// *//
public class question2 {

    //time complexity O()
    public static int solution(int[] A) {
        HashMap<Integer, Integer> mapOfSideCounts = new HashMap<>();

        for (int i: A) {
            if (mapOfSideCounts.containsKey(i)) {
                int count = mapOfSideCounts.get(i) + 1;
                mapOfSideCounts.put(i, count);
            } else {//the integer not in the map yet
                mapOfSideCounts.put(i, 1);
                ;
            }
        }
//        System.out.println(mapOfSideCounts);
        //now loop through the map and make a new map with all sides that have a count > 1
        HashMap<Integer, Integer> mapOfSidesForRectangles = new HashMap<>();
        ArrayList<Integer> sides = new ArrayList<>();
        for (int j : mapOfSideCounts.keySet()) {
            int count = mapOfSideCounts.get(j);
            if (count > 1) {
                mapOfSidesForRectangles.put(j, count);
                sides.add(j);
            }
        }
//        System.out.println(mapOfSidesForRectangles);
//        System.out.println(sides);
        if(mapOfSidesForRectangles.size()==1 && mapOfSidesForRectangles.get(sides.get(0))>=4){
            return 0;
        }

        Collections.sort(sides);

//        System.out.println(sides);
        int minDifference = Integer.MAX_VALUE;
        for(int i=0; i<sides.size()-1; i++){
            int currentDifferent = Math.abs(sides.get(i) - sides.get(i + 1));
            if(currentDifferent<minDifference){
                minDifference = currentDifferent;
            }
        }

//        System.out.println(minDifference);
//        System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        return minDifference;
    }

    public static void main(String[] args) throws IOException {
        int[] segments = {911, 1, 3, 1000, 1000, 2, 2, 999, 1000, 911};
        int sol = solution(segments);
//        System.out.println("solution: "+sol);
    }
}
