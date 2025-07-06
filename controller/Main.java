package com.cdgn.controller;
import java.util.Scanner;
import com.cdgn.service.BusBookingSystem;
public class Main {
    public static void main(String[] args) {
        BusBookingSystem system = new BusBookingSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMini Bus Booking System");
            System.out.println("1. Book a seat");
            System.out.println("2. View reservations");
            System.out.println("3. Edit a reservation");
            System.out.println("4. Print a ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    system.bookSeat();
                    break;
                case 2:
                    system.viewReservations();
                    break;
                case 3:
                    system.editReservation();
                    break;
                case 4:
                    system.printTicket();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);
    }
}