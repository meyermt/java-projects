package _04interfaces.P9_23;

import java.time.LocalDate;

/**
 * Representation of a monthly appointment. Occurs once a month.
 * Created by michaelmeyer on 10/19/16.
 */
public class Monthly extends Appointment {

    /**
     * Constructs appointment. Must have description and LocalDate date.
     * @param description Description of the appointmment
     * @param date Date on which the appointment is set
     */
    public Monthly(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * Returns true if day of the month passed in is equal to stored date.
     * @param year year
     * @param month integer month
     * @param day integer day of month
     * @return true if day matches
     */
    @Override
    public boolean occursOn(int year, int month, int day) {
        if (super.getDate().getDayOfMonth() == day) {
            return true;
        } else {
            return false;
        }
    }
}
