package _04interfaces.P9_23;

import javafx.util.converter.LocalDateStringConverter;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/19/16.
 */
public class AppointmentDriver {

    private static final String MONTHLY = "monthly";
    private static final String ONETIME = "onetime";
    private static final String DAILY = "daily";
    private static final String[] APPT_TYPES = {DAILY, MONTHLY, ONETIME};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstOptComplete = false;
        openingMessage();
        while (!isFirstOptComplete) {
            String strFirstOpt = scanner.nextLine();
            if (strFirstOpt.equalsIgnoreCase("new")) {
                //allow to add new appts
                isFirstOptComplete = true;
                addSequence(scanner);
            } else if (strFirstOpt.equalsIgnoreCase("load")) {
                //load sequence
                isFirstOptComplete = true;
                //loadSequence(scanner);
            } else {
                System.out.println("That option is not available. Please try again.");
                openingMessage();

            }
        }
    }

    private static void openingMessage() {
        System.out.println("Welcome to appointment book.");
        System.out.println("Please read options and descriptions below:");
        System.out.println("  new - create a new appointment book");
        System.out.println("  load - load and existing appointment book");
        System.out.print("Please type in which option you'd like [new|load]: ");
    }

    private static List<Appointment> addSequence(Scanner scanner) {
        List<Appointment> appointmemts = new ArrayList<>();
        String strType = getValidType(scanner);
        System.out.print("Please enter a description for your appointment: ");
        String strDescription = scanner.nextLine();
        LocalDate date = getValidDate(scanner, strType);
        Appointment appt = createNewAppointment(strType, strDescription, date);
        System.out.print("Would you like to add another appointment? [y/N] :");
        String strAnswer = scanner.nextLine();
        if (strAnswer.equalsIgnoreCase("y")) {
            appointmemts.addAll(addSequence(scanner));
        }
        return appointmemts;
    }

    private static Appointment createNewAppointment(String strType, String strDescription, LocalDate date) {
        if (strType.equalsIgnoreCase(ONETIME)) {
            return new Onetime(strDescription, date);
        } else if (strType.equalsIgnoreCase(MONTHLY)) {
            return new Monthly(strDescription, date);
        } else if (strType.equalsIgnoreCase(DAILY)) {
            return new Daily(strDescription, date);
        } else {
            throw new RuntimeException("Unable to create new appointment for type: " + strType + " and date: " + date);
        }
    }

    private static LocalDate getValidDate(Scanner scanner, String strType) {
        LocalDate date = LocalDate.now();
        if (strType.equalsIgnoreCase(ONETIME)) {
            System.out.print("Please enter a date for this appointment in the format YYYY-MM-DD");
            String strDate = scanner.nextLine();
            if (strDate.length() == 10 && strDate.charAt(4) == '-' && strDate.charAt(7) == '-')
            date = stringConverter.fromString(strDate);
        } else if (strType.equalsIgnoreCase(MONTHLY)) {
            System.out.print("Please enter an integer day of the month for this appointment: ");
            int nDay = Integer.valueOf(scanner.nextLine());
            if (nDay > 0 && nDay <= 31) {
                LocalDate.of(2016, 7, nDay);
            } else {
                System.out.println("Invalid day entered. Please try again: ");
                date = getValidDate(scanner, strType);
            }
        // The last two else if and else are not technically needed at this time, but will keep them to preserve
        // modularity
        } else if (strType.equalsIgnoreCase(DAILY)) {
        } else {
            throw new RuntimeException("Unexpected type not accounted for when creating date: " + strType);
        }
        return date;
    }

    private static String getValidType(Scanner scanner) {
        System.out.print("Please enter the type of appointment [daily|monthly|onetime]");
        String strType = scanner.nextLine();
        if (isValidType(strType)) {
            return strType;
        } else {
            return getValidType(scanner);
        }
    }

    private static boolean isValidType(String strType) {
        for (String apptType : APPT_TYPES) {
            if (strType.equalsIgnoreCase(apptType)) {
                return true;
            }
        }
        return false;
    }

//    private static Appointment

}
