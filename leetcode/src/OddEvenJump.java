import java.io.*;
import java.util.*;

public class OddEvenJump {
    public static void main(String[] args) throws IOException {


//        int[] arr = new int[]{1,2,3,4,5};
        int[] arr = new int[]{10,13,12,14,15};

        int n  = arr.length;
        int res = 1;
        boolean[] higher = new boolean[n], lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
//        System.out.println("higher: " + higher);
//        System.out.println("lower: " + lower);
        TreeMap<Integer, Integer> map = new TreeMap<>();
//        System.out.println("hey i am an empty treemap: " + map);
        map.put(arr[n - 1], n - 1);

        System.out.println("after putting the last digit in the array into the map:" + map);
        for (int i = n - 2; i >= 0; --i) {
            System.out.println("=================== i= " + i +" ===================================");
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(arr[i]), lo = map.floorEntry(arr[i]);
            System.out.println("1. Map.Entry<Integer, Integer>, hi: " + hi + "; lo: " + lo);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            System.out.println("2. lower[(int)hi.getValue()]: " + lower[(int)hi.getValue()] + "; (int)hi.getValue(): " + (int)hi.getValue() + "; and higher[" + i + "] = " + lower[(int)hi.getValue()]);
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (lo != null){
                System.out.println("3. higher[(int)lo.getValue()]: " + higher[(int)lo.getValue()] + "; (int)lo.getValue(): " + (int)lo.getValue() + "; and lower[" + i + "] = " + higher[(int)lo.getValue()]);
            }
            else {
                System.out.println("3. lo is null");
            }
            if (higher[i]) res++;
            map.put(arr[i], i);
            System.out.println("4. right now higher[i] = " + higher[i] + "; map current is: " + map);
        }
        System.out.println("there are " + res + " solutions");

//        FileReader input = new FileReader("src/testData/OEArray.txt");
//        BufferedReader bufRead = new BufferedReader(input);
//        String myLine = null;
//
////        int[] arr = new int[];
//
//        while ((myLine = bufRead.readLine()) != null) {
//            int[] vals = Stream.of(myLine.split(",")).mapToInt(Integer::parseInt).toArray();
//            System.out.println(vals);
//        }

//        int numberOfSolutions = 1; //when jumping from the end of the arr
//        Map<Integer, Set> masterRoutes = new HashMap<>();
//        for (int i = 0; i < arr.length-1; i++) { //loop through each number in arr in order
////            System.out.println("jumping from index " + i);
//            int numberOfJumpsMade = 0;
//            int currentIndex = i;
//            if (masterRoutes.get(i) != null && masterRoutes.get(i).contains(0)) {
//                numberOfSolutions += 1;
//                currentIndex = -1;
//            }
//            Map<Integer, Set> routes = new HashMap<>(); //key: index; value: value
//            Set<Integer> oddOrEven = new HashSet<>(Arrays.asList(0));
//            routes.put(i, oddOrEven);
////            System.out.println("at this time, route is: " + routes);
//            while (currentIndex != arr.length - 1 && currentIndex != -1) { //when not reaching the end
//                if (numberOfJumpsMade % 2 == 0) { //about to make an odd jump
//                    if (oddJump(arr,currentIndex) != -1) {
//                        int nextIndex = oddJump(arr, currentIndex);
////                        System.out.println(currentIndex + " odd jump to " + nextIndex);
//                        if (masterRoutes.get(nextIndex) != null && masterRoutes.get(nextIndex).contains(1)) {
//                            numberOfSolutions += 1;
//                            break;
//                        }
//                        currentIndex = nextIndex;
//                        numberOfJumpsMade += 1;
//                        Set<Integer> currentSet = routes.get(nextIndex);
//                        if (currentSet == null) {
//                            currentSet = new HashSet<>();
//                            currentSet.add(1);
//                        }
//                        else {currentSet.add(1);}
//                        routes.put(nextIndex, currentSet);
//                    }
//                    else {
//                        break;
//                    }
//                }
//                if (numberOfJumpsMade % 2 == 1) {
//                    if (evenJump(arr, currentIndex) != -1) {
//                        int nextIndex = evenJump(arr, currentIndex);
////                        System.out.println(currentIndex + " even jump to " + nextIndex);
//                        if (masterRoutes.get(nextIndex) != null && masterRoutes.get(nextIndex).contains(0)) {
//                            numberOfSolutions += 1;
//                            break;
//                        }
//                        currentIndex = nextIndex;
//                        numberOfJumpsMade += 1;
//                        Set<Integer> currentSet = routes.get(nextIndex);
//                        if (currentSet == null) {
//                            currentSet = new HashSet<>();
//                            currentSet.add(0);
//                        }
//                        else {currentSet.add(0);}
//                        routes.put(nextIndex, currentSet);
//                    }
//                    else {
//                        break;
//                    }
//                }
//            }
//            if (currentIndex == arr.length-1) {
//                numberOfSolutions += 1;
////                System.out.println("old master routes: " + masterRoutes);
////                System.out.println("merged in routes: " + routes);
//                routes.entrySet()
//                        .forEach(entry -> masterRoutes.merge(
//                                entry.getKey(),
//                                entry.getValue(),
//                                (key, value) -> entry.getValue()));
//                System.out.println("Reached the end of arr from " + i + ", solutions found so far: " + numberOfSolutions);
//                System.out.println("new master routes: " + masterRoutes + " of size " + masterRoutes.size());
//            }
//        }
//        System.out.println(numberOfSolutions);
//    }
//
//    static int oddJump(int[] arr, int currentIndex) { //jump to smallest bigger
//        int smallestBiggerValue = arr[currentIndex];
//        int indexToJumpTo = currentIndex;
////        System.out.println("start odd jump, current index and value are: " + currentIndex + ", " + arr[currentIndex]);
//        for (int i = currentIndex + 1; i < arr.length; i++){ //loop through the rest of the arr
////            System.out.println("checking the next possible jump, index: " + i + ", value: " + arr[i]);
//            if (arr[i] == arr[currentIndex]){
////                System.out.println("find the same value");
//                if(i > currentIndex){
//                    return i;
//                }
//            }
//            if (arr[i] > arr[currentIndex] && arr[currentIndex] == smallestBiggerValue) {//S.B. has not been updated
//                smallestBiggerValue = arr[i];
//                indexToJumpTo = i;
////                System.out.println("updating the current candidate to jump to, index: " + indexToJumpTo + ", value: " + smallestBiggerValue);
//            }
//            if (arr[i] > arr[currentIndex] && arr[currentIndex] != smallestBiggerValue) {//S.B. has been updated
//                if (arr[i] < smallestBiggerValue) {
//                    smallestBiggerValue = arr[i];
//                    indexToJumpTo = i;
//                }
//            }
//        }
//        if (indexToJumpTo > currentIndex) {
//            return indexToJumpTo;
//        }
////        System.out.println("no more jumps possible");
//        return -1;
//    }
//
//    static int evenJump(int[] arr, int currentIndex) { //jump to biggest smaller
//        int biggestSmaller = arr[currentIndex];
//        int indexToJumpTo = currentIndex;
////        System.out.println("start even jump, current index and value are: " + currentIndex + ", " + arr[currentIndex]);
//        for (int i = currentIndex + 1; i < arr.length; i++) {
////            System.out.println("checking the next possible jump, index: " + i + ", value: " + arr[i]);
//            if (arr[i] <= arr[currentIndex] && indexToJumpTo == currentIndex) {//have not jumped
//                biggestSmaller = arr[i];
//                indexToJumpTo = i;
//            }
//            if (arr[i] <= arr[currentIndex] && biggestSmaller < arr[i]) {//have jumped but find bigger value smaller than the jump from value
//                biggestSmaller = arr[i];
//                indexToJumpTo = i;
//            }
//        }
//        if (indexToJumpTo > currentIndex) {
//            return indexToJumpTo;
//        }
////        System.out.println("no more jumps possible");
//        return -1;
    }
}
