package google2020example;


//*
//https://www.geeksforgeeks.org/google-internship-2020-google-online-challenge1st-coding-round/
// Recently I Got An E-mail From Google That I’ve been selected from the Resume review round To Google’s Online Challenge round. On the 29th Of August, I’ve given That Challenge and face these two problems, which I want to share with all…
//
//A Special String: You Are given a string S consisting of the lowercase Latin alphabet, a – z. Find the minimum number of characters that must be changed to make S special.
//
//A string S is said to be special if and only if for all (S[i], S[j]) where (1 ≤  i ≤ N/2) and (N/2 + 1 ≤ j ≤ N) one of the following condition is true:
//
//S[i] > S[j]
//S[i] < S[j]
//S[i] = S[j]
//S[i]: Represents the ith character of string S(1-based indexing)
//Input format:
//
//The first line contains an integer T denoting the number of test cases
//The first line of each case contains an integer N denoting the length of S
//The second line of each test case contains a string S
//Output format: Print an integer denoting the minimum number of changes required for each test case in a new line.
//
//Constraints:
//
//1 ≤ T ≤ 5
//1 ≤ N ≤ 103
//N is Even
// *//

import java.io.IOException;
import java.util.*;

public class ASpecialString {

    public static class Solution{
        public Set<Integer> ASpecialString(Integer numberOfTestCases, Integer lengthOfstring, String string) {
            Set<Integer> solutions = new HashSet<>();
            for(int i=0; i<numberOfTestCases; i++){
                if (lengthOfstring != 0) {
                    //construct a hashmap with the original string
                    HashMap<Character, Integer> mapOfLetters = new HashMap<>();
                    //initialize the map
                    for (char c='a'; c<='z'; c++){
                        mapOfLetters.put(c, 0);
                    }
                    //sorting out the original string with hashmap
                    char[] stringToChars = string.toCharArray();
                    for(char c: stringToChars){
                        Integer count = mapOfLetters.get(c)+1;
                        mapOfLetters.put(c, count);
                    }
                    StringBuilder sortedString = new StringBuilder();
                    for (char j='a'; j<='z'; j++){
                        if(mapOfLetters.get(j) != 0){
                            for (int k=0; k<mapOfLetters.get(j); k++){
                                sortedString.append(j);
                            }
                        }
                    }
                    System.out.println(string);
                    System.out.println(new String(sortedString));

                    //define first half and second half of the original string
                    String firstHalf = string.substring(0, lengthOfstring/2);
                    String secondHalf = string.substring(lengthOfstring/2, lengthOfstring);
                    System.out.println(firstHalf);
                    System.out.println(secondHalf);

                    String sortedFirstHalf = sortedString.substring(0, lengthOfstring/2);
                    String sortedSecondHalf = sortedString.substring(lengthOfstring/2, lengthOfstring);
                    System.out.println(sortedFirstHalf);
                    System.out.println(sortedSecondHalf);

                    //compare first half with the first half of the sorted string
                    Integer numberOfFirstHalfLetters = 0;
                    char[] sortedFirstHalfArray = sortedFirstHalf.toCharArray();
                    StringBuilder firstHalfCopy = new StringBuilder(firstHalf);
                    for (char sortedLetter: sortedFirstHalfArray){
                        if(firstHalfCopy.toString().indexOf(sortedLetter) != -1){
                            numberOfFirstHalfLetters++;
                            firstHalfCopy.deleteCharAt(firstHalf.indexOf(sortedLetter));
                        }
                    }
                    System.out.println("firsthalf:" + firstHalfCopy+", numberoffirsthalfletters: "+numberOfFirstHalfLetters);

                    if (numberOfFirstHalfLetters >= lengthOfstring / 4) {//move first half letter in the first half of sorted string
                        Integer numberOfSecondHalfLetter = lengthOfstring / 2 - numberOfFirstHalfLetters; //also = numberofFirstHalfLetter in second half of sorted string
                        solutions.add(numberOfSecondHalfLetter * 2);
//                        System.out.println(numberOfSecondHalfLetter * 2);
                    } else if (numberOfFirstHalfLetters < lengthOfstring / 4) {
                        solutions.add(numberOfFirstHalfLetters * 2);
//                        System.out.println(numberOfFirstHalfLetters * 2);
                    }

                }
                else if (lengthOfstring == 0){solutions.add(0);}
            }
            return solutions;
        }
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        String string = "aababc";
        Set<Integer> sol = solution.ASpecialString(1, 6, string);
//        System.out.println("solution: "+sol);
        System.out.println("solutions:");
        for(int i: sol){
            System.out.println(i);
        }
    }

}
