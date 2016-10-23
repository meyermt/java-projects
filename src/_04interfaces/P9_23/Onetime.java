package _04interfaces.P9_23;

import java.time.LocalDate;

/**
 * Representation of a one-time appointment. Occurs one time, and thus date must match exactly to occur.
 * Created by michaelmeyer on 10/19/16.
 */
public class Onetime extends Appointment {

    /**
     * Constructs appointment. Must have description and LocalDate date.
     * @param description Description of the appointmment
     * @param date Date on which the appointment is set
     */
    public Onetime(String description, LocalDate date) {
        super(description, date);
    }
}
