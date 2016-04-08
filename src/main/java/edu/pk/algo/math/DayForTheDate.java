package edu.pk.algo.math;
/**
 * Ref  : https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
 */
public class DayForTheDate {
    static int dayOfWeek(int d, int m, int y) {
        y -= m < 3 ? 1 : 0;
        return (y + y / 4 - y / 100 + y / 400 + month[m - 1] + d) % 7;
    }

    static int month[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
    static String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    /* Driver function to test above function */
    public static void main(String[] args) {
        int day = dayOfWeek(15, 10, 1986);
        System.out.println(days[day]);
    }
}
