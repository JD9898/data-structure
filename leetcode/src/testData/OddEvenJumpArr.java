package testData;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class OddEvenJumpArr {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("src/testData/OEArray.txt"));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        String[] arr = lines.toArray(new String[0]);
        for (String i: arr){
            System.out.println(i);
        }
    }
}