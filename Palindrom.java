/*
 * Check if a word or frase is a Palindrome
 */

import java.util.Arrays;
import java.util.Scanner;

public class Palindrom {
  public static void main ( String[] args) {
    Scanner userInput = new Scanner(System.in);

    System.out.println("This program tests if a word or a phrase is an palindrome.");
    boolean pgmEnd = false;
    while (!pgmEnd) {
      System.out.println("What word or phrase do you want to test?");
      String origStr = userInput.nextLine();
      if ( origStr.isEmpty() ) {
        pgmEnd = true;
        System.out.println("Program end.");
      } else {
        String lowCaseStr = origStr.toLowerCase();
        String noSpaceStr = lowCaseStr.replaceAll("\\s","");
        String reversedStr;
        reversedStr = new StringBuilder(noSpaceStr).reverse().toString();
        boolean isPalin = noSpaceStr.equals(reversedStr);
        if (isPalin) {
           System.out.println(origStr + " is a palindrome.");
         } else {
           System.out.println(origStr + " is not a palindrome.");
         }
       }
     }
   }
 }
