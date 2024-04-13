/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// this is a date class that will use for employee to make attendance

public class Date {
    
    private int month;
    private int day;
    private int year;
    // date must after 01/01/2023
    public Date(int month, int day, int year) {
        // set date after 01/01/2023
        try {
            if (year > 2023 || (year == 2023 && month > 1) || (year == 2023 && month == 1 && day > 1)) {
                this.month = month;
                this.day = day;
                this.year = year;
            } else {
                throw new IllegalArgumentException("Date must be after 01/01/2023");
            }
        } catch (IllegalArgumentException e) {
            // handle the exception
            System.err.println("$Date must be after 01/01/2023");
        }
    }

    public int getMonth() {
        return month;
    }
    // set month with condition check
    public void setMonth(int month) {
        try {
            if (month <= 12 && month >= 1) {
                this.month = month;
            }else {
                throw new IllegalArgumentException("Date must be after 01/01/2023");
        }
        } catch (IllegalArgumentException e) {
            // handle the exception
            System.err.println("$Date must be after 01/01/2023");
        }
    }

    public int getDay() {
        return day;
    }
    // set day with condition check
    public void setDay(int day) {
        try {
            if (day <= 31 && day >= 1) {
                this.day = day;
            }else {
                throw new IllegalArgumentException("Date must be after 01/01/2023");
        }
        } catch (IllegalArgumentException e) {
            // handle the exception
            System.err.println("$Date must be after 01/01/2023");
        }
    }

    public int getYear() {
        return year;
    }
    // set year with condition check
    public void setYear(int year) {
        try {
            if (year <= 9999 && year >= 2023) {
                this.year = year;
            }else {
                throw new IllegalArgumentException("Date must be after 01/01/2023");
        }
        } catch (IllegalArgumentException e) {
            // handle the exception
            System.err.println("$Date must be after 01/01/2023");
        }
    }

    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}
