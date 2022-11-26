package google2020example;

import java.io.IOException;
import java.util.*;


//*
//
// A Special Matrix: You are given an N × N matrix A. The matrix consists of positive integers. In one move, you can apply the following single transformation to the matrix:
//
//Select an arbitrary element of the matrix and increase or decrease it by 1. Each element can be increased or decreased for any arbitrary number of times.
//
//A special number P is a non-negative integer for which the following quadratic equation has at least one negative integer root:
//
//X^2 - 2P + x = 0
//A matrix is called special if at least one of the following conditions is true:
//
//The matrix has a row with special numbers only.
//The matrix has a column with special numbers only.
//Your task is to count the minimum number of moves required to get special matrix A
//
//Input format
//
//The first line contains T denoting the number of test cases.
//The first line of each test case contains an integer N denoting the number of rows and columns.
//Next N lines of each test case contain N integers denoting the initial matrix A.
//Output format
//
//For each test case, print a single integer in a new line denoting the minimum number of moves required to get a special matrix from the provided matrix. if you have already obtained a special matrix, then print 0.
//
//Constraints:
//
//1 ≤ T ≤ 10
//1 ≤ N ≤ 500
//1 ≤ A[i][j] ≤ 1011(1 ≤ i ≤ N, 1 ≤ j ≤ N)
//Sample input:
//
//1
//3
//1 2 3
//4 5 6
//7 8 9
//Sample output:
//
//1
//Explanation: Either the first row or third column can be modified to convert the matrix into a special matrix with a minimum number of moves. The first row can be transformed into [1,3,3] by increasing one time the second element of the first row.
//
// *//
public class ASpecialMatrix {
    public static class Solution{
        //according to definition: x^2+x-2p has at least one negative solution when p>=-1/8 (i.e. 0.125) and
        //p = 0,1,3,6,10,15,...(n*(n+1))2 where n>=0
        public boolean checkIfNumberIsSpecial(Integer number){
            int smallNumber = (int) Math.floor(Math.sqrt(number*2));
            if(smallNumber * (smallNumber+1) / 2 == number) {
//                System.out.println("true");
                return  true;}
//            System.out.println("false");
            return false;
        }

        public Integer ASpecialMatrix(Integer numberOfTestCases, Integer numberOfRowsAndColumns, List<List<Integer>> matrix) {
//            checkIfNumberIsSpecial(5);
            Integer minMoves = matrix.size();
            //initialize N columns
            List<List<Integer>> columns = new ArrayList<>();
            for (int i=0; i<matrix.size(); i++) {
                List<Integer> column = new ArrayList<>();
                columns.add(column);
            }
            //record all columns while looping through all rows
            for (int i=0; i<matrix.size(); i++) {
                int moveNeeded = matrix.size();
                for (int j=0; j<matrix.size(); j++) {
                    int currentNumber = matrix.get(i).get(j);
                    if(checkIfNumberIsSpecial(currentNumber)) {moveNeeded--;}
                    columns.get(j).add(currentNumber);
                }
                if (moveNeeded < minMoves) {minMoves = moveNeeded;}
            }
//            System.out.println(columns);

            //now go thru all columns, can extract this function later
            for (int i=0; i<columns.size(); i++) {
                int moveNeeded = columns.size();
                for (int j=0; j<columns.size(); j++) {
                    int currentNumber = columns.get(i).get(j);
                    if(checkIfNumberIsSpecial(currentNumber)) {moveNeeded--;}
                    columns.get(j).add(currentNumber);
                }
                if (moveNeeded < minMoves) {minMoves = moveNeeded;}
            }
            return minMoves;
        }
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> firstLine = List.of(1,2,3);
        List<Integer> secondLine = List.of(4,5,6);
        List<Integer> thirdLine = List.of(7,8,9);
        matrix.add(firstLine);
        matrix.add(secondLine);
        matrix.add(thirdLine);

        Solution solution = new Solution();
        int sol = solution.ASpecialMatrix(1,3,matrix);
        System.out.println(sol);
        }
}
