package _04interfaces.P9_23;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Driver program for appointmet book.
 * Created by michaelmeyer on 10/19/16.
 */
public class AppointmentDriver {

    private static final String MONTHLY = "monthly";
    private static final String ONETIME = "onetime";
    private static final String DAILY = "daily";
    private static final String[] APPT_TYPES = {DAILY, MONTHLY, ONETIME};

    /**
     * Main method for appointment book.
     * @param args no args taken
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstOptComplete = false;
        openingMessage();
        List<Appointment> appointments = new ArrayList<>();
        while (!isFirstOptComplete) {
            String strFirstOpt = scanner.nextLine();
            if (strFirstOpt.equalsIgnoreCase("new")) {
                //allow to add new appts
                isFirstOptComplete = true;
                appointments = addSequence(scanner);
            } else if (strFirstOpt.equalsIgnoreCase("load")) {
                //load sequence
                isFirstOptComplete = true;
                appointments = loadSequence(scanner);
            } else {
                System.out.println("That option is not available. Please try again.");
                openingMessage();
            }
        }
        secondOption(scanner, appointments);
    }

    /**
     * Processes second group of options - save, check, or quit - for appointment book program.
     * @param scanner used to read in arguments from user
     * @param appointments list of appointments comprising the appointment book
     */
    private static void secondOption(Scanner scanner, List<Appointment> appointments) {
        System.out.println("Appointments loaded. Options available:");
        System.out.println("  save - Save an appointment book to a file and quit.");
        System.out.println("  check - Check an appointment for a particular day.");
        System.out.println("  quit - Quit out of program without saving.");
        System.out.print("Please enter option [save|check]: ");
        String strOpt = scanner.nextLine();
        if (strOpt.equalsIgnoreCase("save")) {
            save(scanner, appointments);
        } else if (strOpt.equalsIgnoreCase("check")) {
            check(scanner, appointments);
        } else if (strOpt.equalsIgnoreCase("quit")) {
            System.exit(0);
        } else {
            System.out.println("Invalid option.");
            secondOption(scanner, appointments);
        }
    }

    /**
     * Checks if an appointment matches one sent in from the user.
     * @param scanner used to read arguments from the user.
     * @param appointments list of appointments comprising the appointment book.
     */
    private static void check(Scanner scanner, List<Appointment> appointments) {
        System.out.print("Please enter a date to see appointments: ");
        String strDate = scanner.nextLine();
        if (isValidDate(strDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(strDate, formatter);
            checkAppointment(date, appointments);
        } else {
            System.out.println("Incorrect date format requested");
            check(scanner, appointments);
        }
        secondOption(scanner, appointments);
    }

    /**
     * Checks appointments against date to see if it occurs on that date.
     * @param date Date to check against appointments
     * @param appointments list of appointments comprising the appointment book.
     */
    private static void checkAppointment(LocalDate date, List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            if (appointment.occursOn(date.getYear(), date.getMonthValue(), date.getDayOfMonth())) {
                System.out.println("Appointment on this day. Appointment info: " + appointment.toString());
            }
        }
    }

    /**
     * Saves appointment book that is currently in memory.
     * @param scanner used to get name of file from user
     * @param appointments list of appointments comprising the appointment book
     */
    private static void save(Scanner scanner, List<Appointment> appointments) {
        System.out.print("Please enter name to save file as: ");
        String filename = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(filename + ".txt")) {
            for (Appointment appointment : appointments) {
                writer.println(appointment.toPrintline());
            }
        } catch (FileNotFoundException e) {
            System.out.println("The output file for this sales program could not be found.");
            e.printStackTrace();
        }
    }

    /**
     * Sequence that loads appointment book file
     * @param scanner used to get filename to load from user
     * @return list of appointments comprising appointment book
     */
    private static List<Appointment> loadSequence(Scanner scanner) {
        System.out.println("Loading from project directory path.");
        System.out.print("Please enter name of file to load: ");
        String filename = scanner.nextLine();
        File inputFile = new File(filename + ".txt");
        try {
            List<Appointment> appointments = new ArrayList<>();
            Scanner scanner2 = new Scanner(inputFile);
            while (scanner2.hasNextLine()) {
                String rawLine = scanner2.nextLine();
                String[] strAppointment = rawLine.split(";");
                String strType = strAppointment[0];
                String strDescription = strAppointment[1];
                String strDate = strAppointment[2];
                if (isValidType(strType) && isValidDate(strDate)) {
                    appointments.add(loadAppointment(strType, strDescription, strDate));
                } else {
                    System.out.println("Invalid type or date, was not loaded: " + strType);
                }
            }
            return appointments;
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
            return loadSequence(scanner);
        }
    }

    /**
     * Loads one appointment
     * @param strType type of appointment
     * @param strDescription description of appointment
     * @param strDate date of appointment
     * @return Appointment object
     */
    private static Appointment loadAppointment(String strType, String strDescription, String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strDate, formatter);
        if (strType.equalsIgnoreCase(MONTHLY)) {
            return new Monthly(strDescription, date);
        } else if (strType.equalsIgnoreCase(DAILY)) {
            return new Daily(strDescription, date);
        } else {
            return new Onetime(strDescription, date);
        }
    }

    /**
     * Opening message sequence
     */
    private static void openingMessage() {
        System.out.println("Welcome to appointment book.");
        System.out.println("Please read options and descriptions below:");
        System.out.println("  new - create a new appointment book");
        System.out.println("  load - load and existing appointment book");
        System.out.print("Please type in which option you'd like [new|load]: ");
    }

    /**
     * Sequence for adding a new entry to the appointment book.
     * @param scanner used to get information on new appointment
     * @return new appointment book
     */
    private static List<Appointment> addSequence(Scanner scanner) {
        List<Appointment> appointments = new ArrayList<>();
        String strType = getValidType(scanner);
        System.out.print("Please enter a description for your appointment: ");
        String strDescription = scanner.nextLine();
        LocalDate date = getValidDate(scanner, strType);
        Appointment appt = createNewAppointment(strType, strDescription, date);
        System.out.print("Would you like to add another appointment? [y/N] : ");
        String strAnswer = scanner.nextLine();
        if (strAnswer.equalsIgnoreCase("y")) {
            appointments.addAll(addSequence(scanner));
        }
        appointments.add(appt);
        return appointments;
    }

    /**
     * Creates new appointment with given values
     * @param strType type of appointment
     * @param strDescription description of appointment
     * @param date date of appointment
     * @return new appointment
     */
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

    /**
     * Checks if date type is valid.
     * @param strDate formatted date to be checked
     * @return true if valid, false if invalid
     */
    private static boolean isValidDate(String strDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(strDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Gets a valid date from the user
     * @param scanner used to get date from the user
     * @param strType type of appointment object
     * @return date
     */
    private static LocalDate getValidDate(Scanner scanner, String strType) {
        LocalDate date = LocalDate.now();
        if (strType.equalsIgnoreCase(ONETIME)) {
            System.out.print("Please enter a date for this appointment in the format YYYY-MM-DD: ");
            String strDate = scanner.nextLine();
            if (isValidDate(strDate)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(strDate, formatter);
            } else {
                date = getValidDate(scanner, strType);
            }
        } else if (strType.equalsIgnoreCase(MONTHLY)) {
            System.out.print("Please enter an integer day of the month for this appointment: ");
            int nDay = Integer.valueOf(scanner.nextLine());
            if (nDay > 0 && nDay <= 31) {
                LocalDate.of(2016, 7, nDay);
            } else {
                System.out.println("Invalid day entered. Please try again: ");
                date = getValidDate(scanner, strType);
            }
        }
        return date;
    }

    /**
     * Gets a valid appointment type entry from user
     * @param scanner used to get value from user
     * @return new type
     */
    private static String getValidType(Scanner scanner) {
        System.out.print("Please enter the type of appointment [daily|monthly|onetime]: ");
        String strType = scanner.nextLine();
        if (isValidType(strType)) {
            return strType;
        } else {
            return getValidType(scanner);
        }
    }

    /**
     * Checks if type of appointment is valid
     * @param strType type of appointment to check
     * @return true if valid, false if invalid
     */
    private static boolean isValidType(String strType) {
        for (String apptType : APPT_TYPES) {
            if (strType.equalsIgnoreCase(apptType)) {
                return true;
            }
        }
        return false;
    }
}
