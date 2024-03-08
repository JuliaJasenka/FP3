package edu.finale_project.booking_tickets.controller.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class AddBooking implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final BookingLogic bookingLogic = provider.getBookingLogic();

	@Override
	public String execute(String request) {

		String[] splitPayload = request.split(";");
		Booking booking = new Booking();
		Map<String, String> bookingMap = new HashMap<>();
		for (String param : splitPayload) {
			String[] split = param.split("=");
			bookingMap.put(split[0], split[1]);
		}
		booking.setId(UUID.randomUUID().toString());
		booking.setBookingDate(LocalDateTime.now());
		booking.setUserId(Integer.parseInt(bookingMap.get("userId")));
		booking.setTrainId(bookingMap.get("trainId"));
		booking.setSeatsNumber(Integer.parseInt(bookingMap.get("seatsNumber")));

		try {
			String bookingId = bookingLogic.add(booking);
			return "Места забранированы, booking ID - " + bookingId;
		} catch (Exception ex) {
			return "Не удалось забронировать места: " + ex.getMessage();
		}
	}

}
