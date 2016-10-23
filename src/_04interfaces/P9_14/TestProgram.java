package _04interfaces.P9_14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program computes the average surface area of an array of SodaCan objects
 * Created by michaelmeyer on 10/19/16.
 */
public class TestProgram {

    public static void main(String[] args) {
        List<SodaCan> sodaCans = new ArrayList<>();
        //First create some SodaCans and add to an ArrayList
        SodaCan can1 = new SodaCan(1, 5);
        SodaCan can2 = new SodaCan(3, 15);
        SodaCan can3 = new SodaCan(1, 100);
        SodaCan can4 = new SodaCan(100, 5);
        SodaCan can5 = new SodaCan(33, 5);
        sodaCans.add(can1);
        sodaCans.add(can2);
        sodaCans.add(can3);
        sodaCans.add(can4);
        sodaCans.add(can5);
        double dTotalSurfaceArea = 0;
        int nCanCount = 0;
        for (SodaCan sodaCan : sodaCans) {
            dTotalSurfaceArea = dTotalSurfaceArea + sodaCan.getSurfaceArea();
            nCanCount++;
        }
        double dAvgArea = dTotalSurfaceArea / nCanCount;
        System.out.println("Average can surface area is: " + dAvgArea);
    }

}
