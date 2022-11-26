package treesAndGraphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//time complexity O(M^2*N)
//space complexity O(26*M*N) -> O(M*N)
public class WordLadderCopy {

//    public static List<String> wordList = constructWordList(List.of("hot","hot","dot","dog","lot","log","cog"));
//    public static String beginWord = "hit";
//    public static String endWord = "cog";

//    public static List<String> wordList = constructWordList(List.of("hot","dog"));
//    public static String beginWord = "hot";
//    public static String endWord = "dog";

//    public static List<String> wordList = constructWordList(List.of("hot", "dot", "dog"));
//    public static String beginWord = "hot";
//    public static String endWord = "dot";

//    public static List<String> wordList = constructWordList(List.of("a", "b", "c"));
//    public static String beginWord = "a";
//    public static String endWord = "c";

    public static List<String> wordList = constructWordList(List.of("hot","cog","dog","tot","hog","hop","pot","dot"));
    public static String beginWord = "hot";
    public static String endWord = "dog";

    public static List<String> constructWordList(List<String> originalWordList){
        List<String> wordList = new ArrayList<>();
        for (int i=0; i< originalWordList.size(); i++){
            wordList.add(originalWordList.get(i));
        }
        return wordList;
    }

    public static List<String> findAllNeighbors(String string){
        List<String> neighbors = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (int i=0; i<chars.length; i++){
            char temp = chars[i];
            for (char l='a'; l<='z'; l++){
                chars[i] = l;
                String neighbor = new String(chars);
                neighbors.add(neighbor);
            }
            chars[i] = temp;
        }
        return neighbors;
    }

    public static int LadderLength(String beginWord, String endWord, List<String> wordList){
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int length = 1;
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size() > endSet.size()){
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> newBeginSet = new HashSet<>();
            for (String word: beginSet){
                List<String> neighbors = findAllNeighbors(word);
                for (String neighbor: neighbors){
                    if(endSet.contains(neighbor)) return length + 1;
                    if (words.contains(neighbor)){
                        newBeginSet.add(neighbor);
                        words.remove(neighbor);
                    }
                }
            }
            beginSet = newBeginSet;
            length++;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        int steps = LadderLength(beginWord, endWord, wordList);
        System.out.println(steps);
    }

}