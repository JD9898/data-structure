package recursion;

import java.io.IOException;
import java.util.*;

public class WordSquares {

    public static class Solution {
        public List<Integer> indexesWhenInsertingAbove = new ArrayList<>();
        public List<Integer> indexesWhenInsertingBelow = new ArrayList<>();
        public Map<Integer, List<Integer>> indexWordMap = new HashMap<>();

        public Set<Integer> insertWordAbove(String[] words, String originWord){
            char[] oldWord = originWord.toCharArray();
            Set<Integer> sol = new HashSet<>();
            indexesWhenInsertingAbove.clear();
            for (int i=0; i<oldWord.length-1; i++){//i is the index of the highlighted letter
                for (int j=0; j<words.length; j++){
                    char[] newWord = words[j].toCharArray();
                    if(oldWord[i] == newWord[i+1]){
                        sol.add(j);//sol is a list of word index that can be inserted before the current word
                        indexesWhenInsertingAbove.add(i+1);
                        System.out.println("adding to index ABOVE: "+i+ " old word below, new word above: "+new String(oldWord)+", "+new String(newWord));
                    }
                }
            }
            return sol;
        }

        public Set<Integer> insertWordBelow(String[] words, String originWord){
            char[] oldWord = originWord.toCharArray();
            Set sol = new HashSet();
            indexesWhenInsertingBelow.clear();
            for (int i=1; i<oldWord.length; i++){
                for (int j=0; j<words.length; j++){
                    char[] newWord = words[j].toCharArray();
                    if(oldWord[i] == newWord[i-1]){
                        sol.add(j); //sol is a list of word index that can be inserted after the word
                        indexesWhenInsertingBelow.add(i-1);
                        System.out.println("adding to index BELOW: "+i+ " old word above, new word below: "+new String(oldWord)+", "+new String(newWord));
                    }
                }
            }
            return sol;
        }

        public List insertWordWithIndex(String[] words, String originWord, int index, String insertAboveOrBelow){
            char[] oldWord = originWord.toCharArray();
            List<String> solAbove = new ArrayList<>();
            List<String> solBelow = new ArrayList<>();
            if (index != 0){
                Set solfromAbove = insertWordAbove(words,originWord);
                solAbove.addAll(solfromAbove);
            } else if (index != originWord.length()-1){
                Set solFromBelow = insertWordBelow(words,originWord);
                solBelow.addAll(solFromBelow);
            }
            if (insertAboveOrBelow == "a"){return solAbove;}
            else if (insertAboveOrBelow == "b"){return solBelow;}
            else{
                List<List<String>> sol = new ArrayList();
                sol.add(solAbove);
                sol.add(solBelow);
                return sol;
            }
        }


        public List<List<String>> wordSquares(String[] words) {
            List<List<String>> solutions = new ArrayList<>();
            List<String> solution = new ArrayList<>(words[0].length());

            System.out.println("word list: "+ Arrays.stream(words).toList());
            System.out.println("current word: "+words[0]);
            Set solAbove = insertWordAbove(words,words[0]); //list of word index above the current word
            Set solBelow = insertWordBelow(words,words[0]); //list of word index after the current word
            System.out.println("list of word index above the current word: "+solAbove);
            System.out.println("list of word index after the current word: "+solBelow);

            System.out.println("indexes above: "+indexesWhenInsertingAbove);
            System.out.println("indexes below: "+indexesWhenInsertingBelow);

            List<Integer> indexes = new ArrayList();
//            if (!solAbove.isEmpty() && !solBelow.isEmpty()){//this mean the current word is not the first or the last row
//                for (int indexBelow: indexesWhenInsertingBelow){
//                    if(indexBelow == 1) {indexes.add(0);}
//                    if (indexesWhenInsertingAbove.contains(indexBelow-2)){
//                        indexes.add(indexBelow-1);
//                        indexesWhenInsertingAbove.remove(indexBelow-2);
//                        indexesWhenInsertingBelow.remove(indexBelow);
//                    }
//                }
//            }
//            if (indexesWhenInsertingAbove.contains(words[0].length()-2)){
//                indexes.add(words[0].length()-1);
//            }
//            for (int i: indexes){
//                System.out.println("i: "+i);
//                    if (indexWordMap.get(i).isEmpty()) {
//                        indexWordMap.put(i, List.of(0));
//                        while (indexWordMap.get(i-1).isEmpty()){
//                            indexWordMap.put(i-1, List.of(indexesWhenInsertingAbove.get(0)));
//                            indexesWhenInsertingAbove.remove(0);
//                        }
////                        if (){
////                            for (int j: indexesWhenInsertingAbove){
////                                indexWordMap.get(i-1).add(j);
////                            }
////                        }
//                    } else {
//                        indexWordMap.get(i).add(0);
//                    }
//            }
//            for (int j: indexesWhenInsertingAbove){
//                if(indexWordMap.get(i).isEmpty()){
//                    indexWordMap.put(i, List.of(0));
//                }else{indexWordMap.get(i).add(0);}
//            }

            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] words = new String[]{"abat","baba","atan","atal"};
//        Solution solution = new Solution();
        Solution solution = new Solution();
        List<List<String>> res = solution.wordSquares(words);
//        System.out.println(res);

    }
}
