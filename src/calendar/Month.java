package calendar;

import java.util.Arrays;

class Month {
  private static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private int days;
  private int numberOfMonth;
  private String monthsName;
  private boolean otherYear;
  static private final String[] MONTHNAMES = {"Styczen", "Luty", "Marzec",
      "Kwiecien", "Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien",
      "Pazdziernik", "Listopad", "Grudzien"};

  Month(int monthNumber, boolean isLeapYear) {
    if (monthNumber > 12 || monthNumber < 1) {
      throw new WrongData("Podany numer miesiaca jest poza zakresem");
    }
    this.numberOfMonth = monthNumber;
    setMonthsName();
    setMonthDays(isLeapYear, monthNumber);
    this.otherYear = false;
  }

  void setMonthsName() {
    String name = MONTHNAMES[getNumberOfMonth() - 1];
    if(Arrays.asList(MONTHNAMES).contains(name)) {
      this.monthsName = name;
    } else {
      throw new WrongData("Podana nazwa miesiaca jest nieprawidlowa");
    }
  }

  String getMonthsName() {
    return this.monthsName;
  }

  int getNumberOfMonth() {
    return this.numberOfMonth;
  }

  int getDays() {
    return this.days;
  }

  void setOtherYear(boolean otherYear) {
    this.otherYear = otherYear;
  }

  boolean getOtherYear() {
    return this.otherYear;
  }

  void setMonthDays(boolean isLeapYear, int monthNumber) {
    if(isFebruaryInLeapYear(isLeapYear, monthNumber)) {
      this.days = 29;
    } else {
      this.days = MONTH_DAYS[monthNumber - 1];
    }
  }

  private boolean isFebruaryInLeapYear(boolean isLeapYear, int monthNumber) {
    return isLeapYear && monthNumber == 2;
  }

  void subMonth(Boolean isLeapYear) {
    this.numberOfMonth --;
    if(this.numberOfMonth <= 0) {
      this.numberOfMonth = 12;
      this.otherYear = true;
    }
    setMonthsName();
    setMonthDays(isLeapYear, this.numberOfMonth);
  }

  void addMonth(Boolean isLeapYear) {
    this.numberOfMonth ++;
    if(this.numberOfMonth > 12) {
      this.numberOfMonth = 1;
      this.otherYear = true;
    }
    setMonthsName();
    setMonthDays(isLeapYear, this.numberOfMonth);
  }
}
