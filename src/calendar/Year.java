package calendar;

class Year {
  private int year;
  Year (int year) {
    setYear(year);
  }

  boolean isLeapYear() {
    return this.year % 4 == 0 || this.year % 400 == 0;
  }

  private void setYear(int year) {
    if (year < 0) {
      throw new WrongData("Nieprawidlowy rok");
    }
    this.year = year;
  }

  int getYear() {
    return this.year;
  }

  void subYear() {
    this.year --;
  }

  void addYear() {
    this.year ++;
  }
}
