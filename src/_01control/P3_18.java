package _01control;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program takes numeric month and day inputs and outputs the season.
 */
public class P3_18 {

//    If month is 1, 2, or 3, season = "Winter"
//    Else if month is 4, 5, or 6, season = "Spring"
//    Else if month is 7, 8, or 9, season = "Summer"
//    Else if month is 10, 11, or 12, season = "Fall"
//    If month is divisible by 3 and day >= 21
//      If season is "Winter", season = "Spring"
//      Else if season is "Spring", season = "Summer"
//      Else if season is "Summer", season = "Fall"
//      Else season = "Winter"

    private static final String WINTER = "Winter";
    private static final String SPRING = "Spring";
    private static final String SUMMER = "Summer";
    private static final String FALL = "Fall";

    public static void main(String[] args) {
        System.out.print("Enter a numeric month and day (separated by space) to get season: ");
        Scanner scanner = new Scanner(System.in);
        int nMonth = scanner.nextInt();
        int nDay = scanner.nextInt();
        String strSeason = "";
        if (nMonth == 1 || nMonth == 2 || nMonth == 3) {
            strSeason = WINTER;
        } else if (nMonth == 4 || nMonth == 5 || nMonth == 6) {
            strSeason = SPRING;
        } else if (nMonth == 7 || nMonth == 8 || nMonth == 9) {
            strSeason = SUMMER;
        } else if (nMonth == 10 || nMonth == 11 || nMonth == 12) {
            strSeason = FALL;
        }
        if (nMonth % 3 == 0 && nDay >= 21) {
            if (strSeason.equals(WINTER)) {
                strSeason = SPRING;
            } else if (strSeason.equals(SPRING)) {
                strSeason = SUMMER;
            } else if (strSeason.equals(SUMMER)) {
                strSeason = FALL;
            } else {
                strSeason = WINTER;
            }
        }
        System.out.println(strSeason);
    }
}
