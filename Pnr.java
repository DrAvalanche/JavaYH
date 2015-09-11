import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;


public class Pnr {

  String personnummer;
  int century;
  LocalDate birthDate;

  Pnr(String pnrStr) {

    personnummer = pnrStr;
    LocalDate presentDate = LocalDate.now();

    int presentYear = presentDate.getYear();
    int presentCentury = (presentYear / 100) * 100;
    int presentYearInCent = presentYear % 100;
    int birthYearInCent = Integer.parseInt(personnummer.substring(0,2));

    if (personnummer.charAt(6) == '+') {
      // Person is 100+ years old.
      if (birthYearInCent <= presentYearInCent) {
        century = presentCentury - 100;
      } else {
        century = presentCentury - 200;
      }
    } else {
      if (birthYearInCent <= presentYearInCent) {
        century = presentCentury;
      } else {
        century = presentCentury - 100;
      }
    }

    int year = Integer.parseInt(personnummer.substring(0,2)) + century;
    int month = Integer.parseInt(personnummer.substring(2,4));
    int day = Integer.parseInt(personnummer.substring(4,6));
    birthDate = LocalDate.of(year,month,day);


  }

  public void pnrInfoTxt () {
    System.out.println("Info om personnummer " + personnummer);
    System.out.println("Födelsedatum " + birthDate.toString());
    LocalDate presentDate = LocalDate.now();
    long age = birthDate.until(presentDate, ChronoUnit.YEARS);
    System.out.println("Ålder " + age);

    String localStr = new StringBuilder(personnummer).deleteCharAt(6).toString();
    System.out.println("Siffersumma " + getDigitSum(localStr));
    if (okPnrChecksum(personnummer)) {
      System.out.println("Personnummret verkar stämma.");
    } else {
      System.out.println("Personnummret verkar inte stämma.");
    }
  }

  public static boolean okPnrChecksum(String pnrStr) {
    boolean isOkChecksum = false;

    String localStr = new StringBuilder(pnrStr).deleteCharAt(6).toString();
    char[] pnrChArr = localStr.toCharArray();

    int[] pnrIntArr = new int[10];
    for (int i = 0; i < 10; i++) {
      pnrIntArr[i] = Character.getNumericValue(pnrChArr[i]);
    }

    int tempSum = 0;
    for (int i = 0; i < 9; i++){
      if ( i % 2 == 0) {
        tempSum += getDigitSum(pnrIntArr[i]*2);
      } else {
        tempSum += getDigitSum(pnrIntArr[i]);
      }
    }
    tempSum += pnrIntArr[9];
    if (tempSum % 10 == 0) {
      isOkChecksum = true;
    } else {
      isOkChecksum = false;
    }
    return isOkChecksum;
  }

  public static int getDigitSum (int digits) {
    int sum = 0;
    char[] digitChArr = Integer.toString(digits).toCharArray();
    for (char c : digitChArr) {
      sum += Character.getNumericValue(c);
    }
    return sum;
  }

  public static int getDigitSum (String digits) {
    int sum = 0;
    char[] digitChArr = digits.toCharArray();
    for (char c : digitChArr) {
      sum += Character.getNumericValue(c);
    }
    return sum;
  }

  public static boolean okPnrInput(String pnrInput) {
    boolean isPnrOk;
    if (pnrInput.length() == 11 &&
        (pnrInput.charAt(6) == '-' || pnrInput.charAt(6) == '+')) {
      // Last check, all but char 6 should be digits, sets return boolean.
      String localStr = new StringBuilder(pnrInput).deleteCharAt(6).toString();
      isPnrOk = Pnr.onlyDigits(localStr);
    } else {
      isPnrOk = false;
    }
    return isPnrOk;
  }

  public static boolean onlyDigits(String str) {
    // isOnlyDigits is true until non digit char found.
    boolean isOnlyDigits = true;
    char[] chArr = str.toCharArray();
    for (char c : chArr) {
      if (!Character.isDigit(c)) {
        isOnlyDigits = false;
      }
    }
    return isOnlyDigits;
  }
}
