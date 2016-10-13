package _03objects;


import _03objects.P8_5.SodaCan;
import _03objects.P8_6.Car;
import _03objects.P8_8.Student;
import _03objects.P8_9.ComboLock;

/**
 * Created by michaelmeyer on 10/11/16.
 */
public class testmain {

    public static void main(String[] args) {
//        Address address1 = new Address(4, "North Washington St", "New Ulm", "MN", 56076);
//        Address address2 = new Address(119, "North Washington St", 5, "New Ulm", "MN", 56074);
//        address2.print();
//        System.out.println(address1.comesBefore(address2));
//        SodaCan soda = new SodaCan(3.0, 43.1);
//        System.out.println(soda.getSurfaceArea());
//        System.out.println(soda.getVolume());
//        Car myHybrid = new Car(50); // 50 miles per gallon
//        myHybrid.addGas(20); // Tank 20 gallons
//        myHybrid.drive(100); // Drive 100 miles
//        System.out.println(myHybrid.getGasLevel()); // Print fuel remaining
//        Student student = new Student("Michael Meyer", 90, 1);
//        student.addQuiz(80);
//        student.addGrade("B+");
//        student.addGrade("D");
//        System.out.println(student.getTotalGpa());
//        System.out.println(student.getAverageScore());
//        System.out.println(student.getName());
//        System.out.println(student.getTotalScore());
        ComboLock lock = new ComboLock(35, 5, 20);
        System.out.println(lock.open());
        lock.turnRight(5);
        System.out.println(lock.open());
        lock.turnLeft(10);
        System.out.println(lock.open());
        lock.turnRight(24);
        System.out.println(lock.open());
    }

}
