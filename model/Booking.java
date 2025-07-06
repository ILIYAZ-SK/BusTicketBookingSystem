package com.cdgn.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking extends Ticket implements PrintTicket {
    private String name;
    private int seatNo;
    public Booking(String name, int seatNo) {
        this.name = name;
        this.seatNo = seatNo;
    }
    public String getName() {
        return name;
    }
    public int getSeatNo() {
        return seatNo;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }
    @Override
    public void printDetails() {
        printHeader();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Passenger Name: " + name);
        System.out.println("Seat No: " + seatNo);
        System.out.println("Booking Time: " + now.format(formatter));

        printFooter();
    }
	private void printFooter() {
		
		
	}

    }
