package Model.application;

import Model.entities.Reservation;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {


        Scanner sc  = new Scanner(System.in);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("room number: ");
        int number = sc.nextInt();
        System.out.println("check-in date (dd/MM/yyyy): ");
        LocalDate checkin = LocalDate.parse(sc.next(), fmt1);
        System.out.println("check-out date (dd/MM/yyyy): ");
        LocalDate checkout = LocalDate.parse(sc.next(), fmt1);

        if(!checkout.isAfter(checkin)) {
            System.out.println("error in reservation: check-out date must be after check-in date");
        } else {
            Reservation reservation = new Reservation(number, checkin, checkout);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("enter data to update the reservation:");
            System.out.println("check-in date (dd/MM/yyyy): ");
            checkin = LocalDate.parse(sc.next(), fmt1);
            System.out.println("check-out date (dd/MM/yyyy): ");
            checkout = LocalDate.parse(sc.next(), fmt1);

            LocalDate now = LocalDate.now();
            if(checkin.isBefore(now) || checkout.isBefore(now)) {
                System.out.println("error in reservation: reservation dates for update must be future dates");
            } else if (!checkout.isAfter(checkin)) {
                System.out.println("error in reservation: check-out date must be after check-in date");
            } else {
                reservation.updateDates(checkin, checkout);
                System.out.println("Reservation: " + reservation);
            }
        }






        sc.close();
    }
}
