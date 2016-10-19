package _04interfaces.P9_8;

/**
 * Test driver program to prove out Person, Student, and Instructor properties, toString, and inheritance.
 * Created by michaelmeyer on 10/18/16.
 */
public class TestProgram {

    public static void main(String[] args) {
        Person person = new Person("Mike Meyer", 1981);
        Student student = new Student("Johnny Boy", 1056, "Comp Sci");
        Instructor inst = new Instructor("Mr. Mann", 1890, 345000.00);
        System.out.println(person.toString());
        System.out.println(person.getStrName() + " is a person: " + (person instanceof Person));
        System.out.println(person.getStrName() + " is a student: " + (person instanceof Student));
        System.out.println(person.getStrName() + " is an instructor: " + (person instanceof Instructor));
        System.out.println(student.toString());
        System.out.println(student.getStrName() + " is a person: " + (student instanceof Person));
        System.out.println(student.getStrName() + " is a student: " + (student instanceof Student));
        System.out.println(inst.toString());
        System.out.println(inst.getStrName() + " is a person: " + (inst instanceof Person));
        System.out.println(inst.getStrName() + " is an instructor: " + (inst instanceof Instructor));
    }


}
