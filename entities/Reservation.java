package Model.entities;

import Model.exceptions.DomainException;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    public static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public long duration() {
        Duration diff = Duration.between(checkin.atStartOfDay(), checkout.atStartOfDay());
        return diff.toDays();
    }

    public void updateDates(LocalDate checkin, LocalDate checkout)  {

        LocalDate now = LocalDate.now();
        if(checkin.isBefore(now) || checkout.isBefore(now)) {
            throw new DomainException("reservation dates for update must be future dates");
        }
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("check-out date must be after check-in date");
        }

        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + fmt1.format(checkin)
                + ", check-out: "
                + fmt1.format(checkout)
                + ", "
                + duration()
                + " nights";
    }
}
