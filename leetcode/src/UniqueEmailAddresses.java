import java.util.*;

public class UniqueEmailAddresses {
    public static void main(String[] args) {
        System.out.println("hahaha you are writing bugs again");

        String[] emails = new String[]{"test.email+alex@leetcode.com", "test.email@leetcode.com"};
        List<String> notUniqueEmails = new ArrayList<>();
        for (String originalEmail: emails) {
            String[] emailComponents = originalEmail.split("@");
            String reformattedEmail = "";
            System.out.println(emailComponents[0].indexOf("+"));
            if(emailComponents[0].indexOf("+") == -1){
                String localName = emailComponents[0].replace(".","");
                System.out.println(localName);
                reformattedEmail += localName;
            } else {
                String localName = emailComponents[0].substring(0,emailComponents[0].indexOf("+")).replace(".","");
                System.out.println(localName);
                reformattedEmail += localName;
            }
            reformattedEmail = reformattedEmail + '@' + emailComponents[1];
            notUniqueEmails.add(reformattedEmail);
        }
        Set<String> uniqueEmailsSet = new HashSet<String>(notUniqueEmails);
        String[] uniqueEmails = uniqueEmailsSet.toArray(new String[uniqueEmailsSet.size()]);
        System.out.println(uniqueEmails.length);
    }
}

/**
 *
 * class Solution {
 *     public int numUniqueEmails(String[] emails) {
 *                 List<String> notUniqueEmails = new ArrayList<>();
 *         for (String originalEmail: emails) {
 *             String[] emailComponents = originalEmail.split("@");
 *             String reformattedEmail = "";
 *             System.out.println(emailComponents[0].indexOf("+"));
 *             if(emailComponents[0].indexOf("+") == -1){
 *                 String localName = emailComponents[0].replace(".","");
 *                 System.out.println(localName);
 *                 reformattedEmail += localName;
 *             } else {
 *                 String localName = emailComponents[0].substring(0,emailComponents[0].indexOf("+")).replace(".","");
 *                 System.out.println(localName);
 *                 reformattedEmail += localName;
 *             }
 *             reformattedEmail = reformattedEmail + '@' + emailComponents[1];
 *             notUniqueEmails.add(reformattedEmail);
 *         }
 *         Set<String> uniqueEmailsSet = new HashSet<String>(notUniqueEmails);
 *         String[] uniqueEmails = uniqueEmailsSet.toArray(new String[uniqueEmailsSet.size()]);
 *         return uniqueEmails.length;
 *     }
 * }
 *
 *
 * **/