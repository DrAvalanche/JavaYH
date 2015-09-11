/*
 * Check if a year input is a leap year.
 */

import java.util.Scanner;

public class LeapYear {
  public static void main (String[] args) {
    Scanner userInput = new Scanner(System.in);

    System.out.println("This program checks for leapyear.");
    boolean pgmEnd = false;
    while (!pgmEnd) {
      System.out.println("What year do you want to test?");
      String origStr = userInput.nextLine();
      if ( origStr.isEmpty() ) {
        pgmEnd = true;
        System.out.println("Program end.");
      } else {
        int year = Integer.parseInt(origStr);
        if ((year % 4) == 0) {
          if ((year % 100) == 0) {
            if ((year % 400) == 0) {
              System.out.println(origStr + " is a leapyear.");
            } else {
              System.out.println(origStr + " is not a leapyear.");
            }
          } else {
            System.out.println(origStr + " is a leapyear.");
          }
        } else {
          System.out.println(origStr + " is not a leapyear.");
        }
      }
    }
  }
}
