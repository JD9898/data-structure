package recursion;

import java.io.IOException;
import java.util.*;

public class WordSquaresCopyTrie {
    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        List<Integer> wordList = new ArrayList<Integer>();

        public TrieNode() {}

    }

    public static class Solution {
        int N = 0;
        String[] words = null;
        TrieNode trie = null;

        public List<List<String>> wordSquares(String[] words) {
//            System.out.println("start solving the question:");
            this.words = words;
            this.N = words[0].length();

            List<List<String>> results = new ArrayList<List<String>>();
            this.buildTrie(words);

            for (String word : words) {
                LinkedList<String> wordSquares = new LinkedList<String>();
                wordSquares.addLast(word);
                this.backtracking(1, wordSquares, results);
            }
            return results;
        }

        protected void backtracking(int step, LinkedList<String> wordSquares,
                                    List<List<String>> results) {
//            System.out.println("start backtracking, N="+N+", step="+step);
            if (step == N) {
                results.add((List<String>) wordSquares.clone());
//                System.out.println("results: "+results);
                return;
            }

            StringBuilder prefix = new StringBuilder();
            for (String word : wordSquares) {
//                System.out.println("wordSquares contain: "+wordSquares);
                prefix.append(word.charAt(step));
//                System.out.println("prefix is "+prefix+", word: "+word+", word.charAt(step): "+word.charAt(step));
            }

            for (Integer wordIndex : this.getWordsWithPrefix(prefix.toString())) {
//                System.out.println("print get words with prefix: "+this.getWordsWithPrefix(prefix.toString()));
//                System.out.println("\nprefix: "+ prefix.toString()+", this.getWordsWithPrefix(prefix.toString()): "+this.getWordsWithPrefix(prefix.toString())+"\n");
                wordSquares.addLast(this.words[wordIndex]);
                this.backtracking(step + 1, wordSquares, results);
//                System.out.println("the last word in wordsquares right now is: "+wordSquares.getLast());
//                System.out.println("remove last, "+"prefix: "+ prefix.toString()+", this.getWordsWithPrefix(prefix.toString()): "+this.getWordsWithPrefix(prefix.toString()));
//                System.out.println("wordSquare: "+wordSquares+", prefix: "+prefix+", step: "+step+", wordIndex: "+wordIndex);
                wordSquares.removeLast();
                System.out.println("wordSquare: "+wordSquares+", prefix: "+prefix+", step: "+step+", wordIndex: "+wordIndex);
            }
        }

        protected void buildTrie(String[] words) {
//            System.out.println("build trie with the wordList:");
            this.trie = new TrieNode();
//            System.out.println("current trieNode this.trie, children:"+this.trie.children + ", wordList: "+this.trie.wordList);

            for (int wordIndex = 0; wordIndex < words.length; ++wordIndex) {
                String word = words[wordIndex];
//                System.out.println("\nlooping through words, current word is: " + words[wordIndex]);

                TrieNode node = this.trie;
//                System.out.println("initiate a TrieNode, this.trie, children:"+this.trie.children.keySet() + ", wordList: "+this.trie.wordList);
                for (Character letter : word.toCharArray()) {
//                    System.out.println(">??????????"+node.children.keySet());
//                    System.out.println("check each letter in this word: "+letter+ "; currently at node.children.keySet(): "+ node.children.keySet());
                    if (node.children.containsKey(letter)) {
//                        System.out.println("HERE");
                        node = node.children.get(letter); // only change node here
//                        System.out.println("what is node.children.get(letter): "+node.children.get(letter));
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
//                        System.out.println("!!!!!!!!!!!!!!!"+letter+"    "+node.children.keySet());
                        node = newNode;
//                        System.out.println("clear the current node, and create new node with key: "+node.children.keySet());
                    }
//                    while(this.trie.children("a")){
//                        System.out.println("dwjkhkwfuwfiw");
//                    }
                    node.wordList.add(wordIndex);
//                    System.out.println("add wordIndex to node.wordList: "+node.wordList);
                }
            }
        }

        protected List<Integer> getWordsWithPrefix(String prefix) {
            TrieNode node = this.trie;
//            System.out.println("\nright now the trie is: "+trie.children.keySet());

            for (Character letter : prefix.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    // return an empty list.
                    return new ArrayList<Integer>();
                }
            }
//            System.out.println("the wordlist of the current node: "+node.wordList.toString());
            return node.wordList;
        }
    }


    //*
    // M: length of each word, N: length of the words
    // Solution 2:
    // time complexity: O()
    // space complexity: O()
    // *//
    public static class SolutionTwo {
        int N = 0;
        String[] words = null;
        HashMap<String, List<String>> prefixHashTable = null;

        public List<List<String>> wordSquares(String[] words) {
            this.words = words;
            this.N = words[0].length();

            List<List<String>> results = new ArrayList<List<String>>();
            this.buildPrefixHashTable(words);

            for (String word : words) {
                LinkedList<String> wordSquares = new LinkedList<String>();
                wordSquares.addLast(word);
                this.backtracking(1, wordSquares, results);
            }
            return results;
        }

        protected void backtracking(int step, LinkedList<String> wordSquares,
                                    List<List<String>> results) {
            if (step == N) {
                results.add((List<String>) wordSquares.clone());
                return;
            }

            StringBuilder prefix = new StringBuilder();
            for (String word : wordSquares) {
                prefix.append(word.charAt(step));
            }

            for (String candidate : this.getWordsWithPrefix(prefix.toString())) {
                wordSquares.addLast(candidate);
                this.backtracking(step + 1, wordSquares, results);
                wordSquares.removeLast();
            }
        }

        protected void buildPrefixHashTable(String[] words) {
            this.prefixHashTable = new HashMap<String, List<String>>();

            for (String word : words) {
                for (int i = 1; i < this.N; ++i) {
                    String prefix = word.substring(0, i);
                    List<String> wordList = this.prefixHashTable.get(prefix);
                    if (wordList == null) {
                        wordList = new ArrayList<String>();
                        wordList.add(word);
                        this.prefixHashTable.put(prefix, wordList);
                    } else {
                        wordList.add(word);
                    }
                }
            }
        }

        protected List<String> getWordsWithPrefix(String prefix) {
            List<String> wordList = this.prefixHashTable.get(prefix);
            return (wordList != null ? wordList : new ArrayList<String>());
        }
    }

    public static void main(String[] args) throws IOException {
        String[] words = new String[]{"abat","baba","atan","atal"};
        Solution solution = new Solution();
//        SolutionTwo solution = new SolutionTwo();
        List<List<String>> res = solution.wordSquares(words);
        System.out.println(res);

    }
}
