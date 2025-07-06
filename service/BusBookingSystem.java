package com.cdgn.service;
import java.util.ArrayList;
import java.util.Scanner;


import com.cdgn.model.Booking;
public class BusBookingSystem {
    private ArrayList<Booking> bookings = new ArrayList<>();
    public void bookSeat() {
        Scanner scanner = new Scanner(System.in);
        int totalSeats = 40;
        int bookedCount = bookings.size();
        int availableSeats = totalSeats - bookedCount;

        if (availableSeats == 0) {
            System.out.println("🛑 Sorry, all seats are booked.");
            return;
        }

        System.out.println("🪑 Seats available: " + availableSeats + " out of " + totalSeats);

        String[] locations = {"Vijayawada", "Guntur", "Ongole", "Nellore", "Tirupati"};

        // Step 1: Show route options
        System.out.println("Available Locations:");
        for (int i = 0; i < locations.length; i++) {
            System.out.println((i + 1) + ". " + locations[i]);
        }
        

        // Step 2: Select from and to
        System.out.print("Select 'From' (enter number): ");
        int fromIndex = scanner.nextInt();
        System.out.print("Select 'To' (enter number): ");
        int toIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (fromIndex < 1 || fromIndex > locations.length || toIndex < 1 || toIndex > locations.length || fromIndex == toIndex) {
            System.out.println("Invalid From/To selection.");
            return;
        }

        String fromPlace = locations[fromIndex - 1];
        String toPlace = locations[toIndex - 1];
        
        

        // Step 3: Book passengers
        System.out.print("Enter names (comma-separated, max 6): ");
        String nameInput = scanner.nextLine().trim();
        System.out.print("Enter seat numbers (comma-separated): ");
        String seatInput = scanner.nextLine().trim();

        String[] names = nameInput.split(",");
        String[] seats = seatInput.split(",");

        if (names.length != seats.length || names.length > 6) {
            System.out.println("Mismatch in count or limit exceeded.");
            return;
        }
 
        System.out.print("Enter payment method (Cash, Card, UPI): ");
        String paymentMethod = scanner.nextLine().trim();

        if (!paymentMethod.equalsIgnoreCase("Cash") &&
            !paymentMethod.equalsIgnoreCase("Card") &&
            !paymentMethod.equalsIgnoreCase("UPI")) {
            System.out.println("❌ Invalid payment method. Booking cancelled.");
            return;
        }

        System.out.println("✅ Payment received via " + paymentMethod + ".");

        ArrayList<Integer> newSeats = new ArrayList<>();
        for (String s : seats) {
            try {
                int seat = Integer.parseInt(s.trim());
                if (seat < 1 || seat > totalSeats) {
                    System.out.println("Invalid seat number: " + seat + ". Must be between 1 and " + totalSeats + ".");
                    return;
                }

                if (seat > totalSeats - bookings.size()) {
                    System.out.println("Seat number " + seat + " exceeds currently available seats (" + (totalSeats - bookings.size()) + ").");
                    return;
                }

                if (newSeats.contains(seat)) {
                    System.out.println("Duplicate seat in input: " + seat);
                    return;
                }

                newSeats.add(seat);
            } catch (NumberFormatException e) {
                System.out.println("Invalid seat number: " + s);
                return;
            }
        }

        for (Booking b : bookings) {
            if (newSeats.contains(b.getSeatNo())) {
                System.out.println("Seat already booked: " + b.getSeatNo());
                return;
            }
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i].trim();
            int seatNo = Integer.parseInt(seats[i].trim());
            Booking booking = new Booking(name, seatNo);
            bookings.add(booking);
            System.out.println("✅ Booked " + name + " | Seat " + seatNo + " | " + fromPlace + " → " + toPlace);
        }
    }


    public void viewReservations() {
        if (bookings.isEmpty()) {
            System.out.println("No reservations made yet.");
            return;
        }
        System.out.println("All reservations:");
        System.out.println("Seat No.\tName");
        for (Booking booking : bookings) {
        	System.out.println(booking.getSeatNo() + "\t\t" + booking.getName());
        }
    }
    public void editReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter seat number to edit: ");
        int seatToEdit = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.getSeatNo() == seatToEdit) {
                System.out.print("Enter new name: ");
                booking.setName(scanner.nextLine());
                System.out.println("Reservation edited successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Reservation not found.");
        }
    }
    public void printTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter seat number(s) to print ticket (e.g. 5 or 5,6,10): ");
        String input = scanner.nextLine();
        String[] seatTokens = input.split(",");

        boolean foundAny = false;

        for (String token : seatTokens) {
            try {
                int seatNo = Integer.parseInt(token.trim());
                boolean found = false;

                for (Booking booking : bookings) {
                    if (booking.getSeatNo() == seatNo) {
                        booking.printDetails();
                        found = true;
                        foundAny = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("❌ No reservation found for seat number: " + seatNo);
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input: '" + token + "'. Skipping...");
            }
        }

        if (!foundAny) {
            System.out.println("No valid tickets found to print.");
        }
    }
}