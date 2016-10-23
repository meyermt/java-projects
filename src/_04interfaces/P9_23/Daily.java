package _04interfaces.P9_23;

import java.time.LocalDate;

/**
 * Representation of daily appointment. Occurs every day.
 * Created by michaelmeyer on 10/19/16.
 */
public class Daily extends Appointment {

    /**
     * Constructs appointment. Must have description and LocalDate date.
     * @param description Description of the appointmment
     * @param date Date on which the appointment is set
     */
    public Daily(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * Returns true, since daily appointments occur every day.
     * @param year year
     * @param month integer month
     * @param day integer day of month
     * @return true
     */
    @Override
    public boolean occursOn(int year, int month, int day) {
        return true;
    }
}
