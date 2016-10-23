package _04interfaces.P9_23;

import java.time.LocalDate;

/**
 * Representation of an appointment, with description and date of appointment.
 * Created by michaelmeyer on 10/19/16.
 */
public abstract class Appointment {

    private final String description;
    private final LocalDate date;

    /**
     * Constructs appointment. Must have description and LocalDate date.
     * @param description Description of the appointmment
     * @param date Date on which the appointment is set
     */
    public Appointment(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Returns true if appointment is on the year, month and day that is passed in.
     * @param year year
     * @param month integer month
     * @param day integer day of month
     * @return
     */
    public boolean occursOn(int year, int month, int day) {
        if (this.date.getYear() == year &&
                this.date.getMonth().getValue() == month &&
                this.date.getDayOfMonth() == day) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves description for the appointment.
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the date of the appointment.
     * @return
     */
    public LocalDate getDate() {
        return date;
    }
}
