import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class LicenseKeyFormatting {
    public static void main(String[] args) throws IOException {

            int k = 2;
            String s = "2-5G-3J";
            int charecterCountExDash = 0;
            StringBuilder charecterExDash = new StringBuilder();
            char[] sArray = s.toCharArray();
            for (char i: sArray) {
                if (! (i == '-')){
                    charecterCountExDash += 1;
                    charecterExDash.append(Character.toUpperCase(i));
                }
            }
            int numberOfCharecterLeft = charecterCountExDash % k;
            if (numberOfCharecterLeft != 0){
                // resultString +=
                if (charecterCountExDash / k != 0) {
                    charecterExDash.insert(numberOfCharecterLeft, '-');
                }
                for (int i = 1; i < charecterCountExDash / k; i++){
                    charecterExDash.insert(numberOfCharecterLeft + k * i + i, '-');
                }
            }else{
                for (int i = 1; i < charecterCountExDash / k; i++){
                    charecterExDash.insert(k * i + ( i - 1 ), '-');
                }
            }
        System.out.println(charecterExDash.toString());
    }
}
