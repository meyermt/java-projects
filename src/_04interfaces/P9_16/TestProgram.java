package _04interfaces.P9_16;

import _04interfaces.P9_14.Measurable;

import java.util.ArrayList;
import java.util.List;

/**
 * Test driver that will compute the country with the maximum surface area.
 * Created by michaelmeyer on 10/19/16.
 */
public class TestProgram {

    public static void main(String[] args) {
        Measurable[] countries = new Measurable[5];
        Country country1 = new Country("USA", 12300000);
        Country country2 = new Country("Germany", 302340);
        Country country3 = new Country("Russia", 32300000);
        Country country4 = new Country("Borneo", 12300);
        Country country5 = new Country("Japan", 844400);
        countries[0] = country1;
        countries[1] = country2;
        countries[2] = country3;
        countries[3] = country4;
        countries[4] = country5;
        System.out.println("The country with max surface area is: " + MeasureUtils.maximum(countries));

    }

}
