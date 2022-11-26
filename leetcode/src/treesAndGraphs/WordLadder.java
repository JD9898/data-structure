package treesAndGraphs;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.*;

//time complexity: O(N*(M^2*N))
public class WordLadder {
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

//    public static List<String> wordList = constructWordList(List.of("b"));
//    public static String beginWord = "a";
//    public static String endWord = "z";

//    public static List<String> wordList = constructWordList(List.of("ted","tex","red","tax","tad","den","rex","pee"));
//    public static String beginWord = "red";
//    public static String endWord = "tax";

    //initialize the wordlist tree
    public static NonBinaryTreeNode root = new NonBinaryTreeNode(beginWord, null, 0);

    // initialise root set
    public static List<NonBinaryTreeNode> rootList = List.of(root);
    public static int minSteps = 0;
    public static boolean foundTheWord = false;

    public static class NonBinaryTreeNode {
        String val;
        List<NonBinaryTreeNode> children;
        int level;

        NonBinaryTreeNode(String val, List<NonBinaryTreeNode> children, int level
        ) {
            this.val = val;
            this.children = children;
            this.level = level;
        }

        public String toString() {
            return this.val + this.level;
        }
    }

    public static void addChildrenToNodes (List<String> wordList, List<NonBinaryTreeNode> nodes) throws IOException {
        //adding one child to the tree
        //after this, first layer will be all words that has one different letter as the begin word
        //second layer will be all words with two different letter as the begin word
        List<NonBinaryTreeNode> newLayerChildren = new ArrayList<>();
        List<String> copyWordList = List.copyOf(wordList);
        for (NonBinaryTreeNode node: nodes){
            for (int i=0; i< copyWordList.size(); i++){
                System.out.println(">>>>>>>>>>>"+wordList+" "+nodes+" i: "+i+" wordList.get(i): "+copyWordList.get(i)+" wordList.size(): "+wordList.size()+" newLayerChildren: "+
                        newLayerChildren);
                if (wordList.size() > i){
                    if(wordList.get(i).equals(node.val)){
                    wordList.remove(i);
                }}
                if (foundTheWord==true){break;}
                for (int j=0; j<node.val.length(); j++){
                    String currentWordDeletedJth = new StringBuilder(node.val).deleteCharAt(j).toString();
                    String WordListWordDeletedJth = new
                            StringBuilder(copyWordList.get(i)).deleteCharAt(j).toString();
//                    System.out.println("WordListWordDeletedJth: " + currentWordDeletedJth);

                    if (node.val != copyWordList.get(i) &&
                            WordListWordDeletedJth.equals(currentWordDeletedJth)){
                        if (copyWordList.get(i).equals(endWord)){
                            System.out.println("!!!!!!!!!!!"+copyWordList.get(i)+" "+(node.level+2));
                            foundTheWord = true;
                            minSteps = (node.level+2);
                            break;
                        }
                        NonBinaryTreeNode child = new NonBinaryTreeNode(copyWordList.get(i), null,
                                node.level+1);
//                        System.out.println(">!"+ i+" "+ copyWordList.get(i) +"!<");
                        newLayerChildren.add(child);
                        if(node.children != null){
                            node.children.add(child);
                        }else{
                            List<NonBinaryTreeNode> children = new ArrayList<>();
                            children.add(child);
                            node.children = children;}
                        wordList.remove(copyWordList.get(i));
//                        if (child.val.equals(endWord)){
//                            minSteps = child.level+1;
//                            System.out.println("update to "+minSteps);
//                            break;
//                        }
                    }
                }

                if (i == wordList.size() && newLayerChildren.size() == 0 && foundTheWord==false){
                    minSteps = 0;
                    System.out.println("update to "+minSteps);
                    break;
                }
            }
        }
        rootList = newLayerChildren;
    }

    public static List<String> constructWordList(List<String> originalWordList){
        List<String> wordList = new ArrayList<>();
        for (int i=0; i< originalWordList.size(); i++){
            wordList.add(originalWordList.get(i));
        }
        return wordList;
    }

    public static void main(String[] args) throws IOException {

        while(wordList.size()!=0){
            if(endWord.length()==1 && wordList.contains(endWord)){
                minSteps = 2;
                break;
            }
            if(rootList.size() == 0){
                if(foundTheWord==false){
                    minSteps = 0;
                }
                break;
            }
            addChildrenToNodes(wordList, rootList);
        }
        System.out.println("minimun steps is " + minSteps);
    }

}