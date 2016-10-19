package _04interfaces.P9_10;

/**
 * Test driver to test the area and perimeter functionality in BetterRectangle
 * Created by michaelmeyer on 10/18/16.
 */
public class TestProgram {

    public static void main(String[] args) {
        BetterRectangle betterRec = new BetterRectangle(0, 0, 10, 15);
        System.out.println("Area of rectangle is: " + betterRec.getArea());
        System.out.println("Perimeter of rectangle is: " + betterRec.getPerimeter());
    }

}
