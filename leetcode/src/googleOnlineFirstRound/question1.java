package googleOnlineFirstRound;

import java.io.IOException;

//*
// We are given a string text of length N consisting of the letters 'a', 'b' or 'c'. We can insert any of those letters before or after any letter in the string.
//
//The goal is to insert letters into text so that it will follow the pattern "abcabca..."; i.e. it should start with a letter 'a', letter 'a' should be followed by 'b', letter 'b' should be followed by 'c', and letter 'c' by 'a'. The string may end with any of those three letters.
//
//What is the minimum number of letters we need to insert into text?
//
//Write a function:
//
//int solution(String text);
//
//that, given a string text of length N, returns the minimum number of insertions needed to make text follow the described pattern.
//
//Examples:
//
//For text = "aabcc" we need to insert letters 'b' and 'c' between the pair of letters 'a', and then insert letters 'a' and 'b' between the two letters 'c'. This way we obtain the string "abcabcabc" and the function should return 4.
//For text = "abcabcabca", we do not need to insert any letters. The string already follows the required pattern, so the function should return 0. Note that text does not need to end with letter 'c'.
//For text = "bcaaa", letter 'a' should be inserted at the beginning of text, and then letters 'b' and 'c' should be inserted between the two pairs of letters 'a'. This way we obtain the string "abcabcabca" and the function should return 5.
//Assume that:
//
//N is an integer within the range [1..100];
//string text consists only of the following characters: "a", "b" and/or "c".
//In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
//
// *//
public class question1 {
    
    public static int solution(String S) {
        int currentLetter = 2;//a:0, b:1, c:2
        char[] chars = S.toCharArray();
        int numberInserted = 0;
        for (int i=0; i<chars.length; i++){
//            System.out.println("i="+i+", chars[i]="+chars[i]);
            if (chars[i] == 'a' && currentLetter != 2){
//                System.out.println("chars[i]=a");
                //before a, if not c(2),
                //1)if it is a(currentLetter = 0), need to insert b,c, 2
                //2) if it is b(currentLetter = 1), need to inset c, 1
                numberInserted += 2 - currentLetter;
            } else if (chars[i] == 'b' && currentLetter != 0){
//                System.out.println("chars[i]=b");
                //before b, if not a(0),
                //1)if it is b(currentLetter = 1), need to insert c,a, 2
                //2) if it is c(currentLetter = 2), need to inset a, 1
                numberInserted += 3 - currentLetter;
            } else if (chars[i] == 'c' && currentLetter != 1){
//                System.out.println("chars[i]=c");
                //before c, if not b(1),
                //1)if it is c(currentLetter = 2), need to insert a,b, 2
                //2)if it is a(currentLetter = 0), need to insert b, 1
                if (currentLetter==2){
                    numberInserted += 2;
                } else if (currentLetter==0){
                    numberInserted += 1;
                }
            }
            if(chars[i]=='a'){
                currentLetter=0;
            }else if (chars[i] =='b'){
                currentLetter=1;
            }else {
                currentLetter=2;
            }
        }
//        System.out.println(numberInserted);
//        System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        return numberInserted;
    }

    public static void main(String[] args) throws IOException {
        String string = "aabcc";
        int sol = solution(string);
    }
}
