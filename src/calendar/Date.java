package calendar;

import java.io.IOException;

class Date implements Comparable<Date>{

  private int day;
  private final Year year;
  private final Month month;
  private final String[] WEEKDAY = {"poniedzialek", "wtorek", "sroda",
      "czwartek", "piatek", "sobota", "niedziela"};

  private final String[] MONTHSNUMBERROME = {"I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII"};

  private final String[] WEEKDAYSHORT = {"Pon.","Wt.","Sr.","Czw.","Pt.","Sob.","Niedz."};

  private final int[] NUMOFDAYS = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

  Date(int day, int monthNumber, int year) throws IOException {
    this.year = new Year(year);
    this.month = new Month(monthNumber, this.year.isLeapYear());
    if(day > this.month.getDays() || day <= 0) {
      throw new WrongData("Dzien wychodzi poza zakres dni miesiaca");
    }
    this.day = day;
    FileClass log = new FileClass("Test.txt");
    log.write("Object has been created");
  }

  Date(String day, String month, String year) throws IOException {
    this.day = Integer.parseInt(day);
    this.year = new Year(Integer.parseInt(year));
    this.month = new Month(Integer.parseInt(month), this.year.isLeapYear());
    FileClass log = new FileClass("Test2.txt");
    log.write("Object has been created");
  }

  public static Date parse(String obj) throws IOException {
    String[] delimiter = obj.split("-");
    return new Date(delimiter[0], delimiter[1], delimiter[2]);
  }

  void backOneWeek() {
    this.day -= 7;
    if(this.day <= 0) {
      this.month.subMonth(this.year.isLeapYear());
      this.day += this.month.getDays();
      if(this.month.getOtherYear()) {
        this.year.subYear();
        this.month.setOtherYear(false);
      }
    }
  }

  void nextOneWeek() {
    this.day += 7;
    if(this.day > this.month.getDays()) {
      this.day -= this.month.getDays();
      this.month.addMonth(this.year.isLeapYear());
      if(this.month.getOtherYear()) {
        this.year.addYear();
        this.month.setOtherYear(false);
      }
    }
  }

  String getDayName() {
    int day = this.day;
    int month = this.month.getNumberOfMonth();
    Year year = this.year;
    int yy, c, g;
    int result;

    day = day + NUMOFDAYS[month-1];
    if((month > 2) && (year.isLeapYear())) {
      day ++;
    }
    yy = (year.getYear() - 1) % 100;
    c = (year.getYear() - 1) - yy;
    g = yy + (yy/4);
    result = (((((c/100) % 4) * 5) + g) % 7);
    result += day - 1;
    result %= 7;

    return WEEKDAY[result];
  }

  @Override
  public String toString() {
    return this.day + "." + this.month.getMonthsName() + "." + this.year.getYear();
  }

  @Override
  public int compareTo(Date obj) {
    final int LARGER = 1;
    final int SMALLER = -1;
    final int EQUAL = 0;

    if(this.year.getYear() > obj.year.getYear()) {
      return LARGER;
    } else {
      if(this.year.getYear() == obj.year.getYear()) {
        if(this.month.getNumberOfMonth() > obj.month.getNumberOfMonth()) {
          return LARGER;
        }
        else if(this.month.getNumberOfMonth() == obj.month.getNumberOfMonth()) {
          if (this.day > obj.day) {
            return LARGER;
          }

          else if(this.day == obj.day) {
            return EQUAL;
          }

          else {
            return SMALLER;
          }
        }

        else if(this.month.getNumberOfMonth() < obj.month.getNumberOfMonth()) {
          return SMALLER;
        }
      }
    }
    return SMALLER;
  }

  public String dayWeekName() throws IOException {
    Date temp = new Date(20, 1, 2020);
    Date temp2 = new Date(this.day, this.month.getNumberOfMonth(), this.year.getYear());
    while(true) {
      if(temp2.compareTo(temp) == 1) {
        temp2.backOneWeek();
        if(temp2.compareTo(temp) == -1) {
          return WEEKDAY[7 - (temp.day - temp2.day)];
        }
      }
      else if(temp2.compareTo(temp) == -1) {
        temp2.nextOneWeek();
        if(temp2.compareTo(temp) == 1) {
          System.out.println(temp2);
          return WEEKDAY[temp2.day - temp.day];
        }
      }
      else {
        return WEEKDAY[0];
      }
    }
  }

  String otherFormat(int number) {
    return switch (number) {
      case 1 -> this.day + "-" + this.month.getNumberOfMonth() + "-" + this.year.getYear();
      case 2 -> this.day + "." + MONTHSNUMBERROME[this.month.getNumberOfMonth() - 1] + "." + this.year.getYear();
      case 3 -> getDayName() + "," + this.day + "-" + this.month.getNumberOfMonth() + "-" + this.year.getYear();
      default -> toString();
    };
  }
}