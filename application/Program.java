package Model.application;

import Model.entities.Reservation;
import Model.exceptions.DomainException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {


        Scanner sc  = new Scanner(System.in);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.println("room number: ");
            int number = sc.nextInt();
            System.out.println("check-in date (dd/MM/yyyy): ");
            LocalDate checkin = LocalDate.parse(sc.next(), fmt1);
            System.out.println("check-out date (dd/MM/yyyy): ");
            LocalDate checkout = LocalDate.parse(sc.next(), fmt1);


            Reservation reservation = new Reservation(number, checkin, checkout);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("enter data to update the reservation:");
            System.out.println("check-in date (dd/MM/yyyy): ");
            checkin = LocalDate.parse(sc.next(), fmt1);
            System.out.println("check-out date (dd/MM/yyyy): ");
            checkout = LocalDate.parse(sc.next(), fmt1);


            reservation.updateDates(checkin, checkout);
            System.out.println("Reservation: " + reservation);
        }
        catch (DomainException e) {
            System.out.println("error in reservation: " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("unexpected error");
        }


        sc.close();
    }
}
