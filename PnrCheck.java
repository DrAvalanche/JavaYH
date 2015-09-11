/*
 * Pnr sanity check
 */

import java.util.Arrays;
import java.util.Scanner;
import java.time.DateTimeException;


public class PnrCheck {
  public static void main( String[] args) {
    Scanner userInput = new Scanner(System.in);

    System.out.println("This program checks your pnr.");
    Pnr myPnr;
    boolean pgmEnd = false;
    while (!pgmEnd) {
      System.out.print("Please enter your pnr: ");
      String pnrStr = userInput.nextLine();
      if ( pnrStr.isEmpty() ) {
        pgmEnd = true;
        System.out.println("Program end.");
      } else if (!Pnr.okPnrInput(pnrStr)) {
        System.out.println("Something is wrong with your input, please try again.");
      } else {
        try {
          myPnr = new Pnr(pnrStr);
          myPnr.pnrInfoTxt();
        } catch (DateTimeException exc) {
          System.out.println(pnrStr + " is not a valid date.");
        }
      }
    }
  }
}
