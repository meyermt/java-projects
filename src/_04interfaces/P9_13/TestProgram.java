package _04interfaces.P9_13;

/**
 * Testing labeledpoint toString
 * Created by michaelmeyer on 10/18/16.
 */
public class TestProgram {

    public static void main(String[] args) {
        LabeledPoint labeledPoint = new LabeledPoint(3, 4, "my point");
        System.out.println(labeledPoint.toString());
    }
}
