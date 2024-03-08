package edu.finale_project.booking_tickets.controller.impl;

import java.util.HashMap;
import java.util.Map;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class UpdateBooking implements Command {

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
		booking.setId(bookingMap.get("id"));
		booking.setSeatsNumber(Integer.parseInt(bookingMap.get("seatsNumber")));

		try {
			bookingLogic.update(booking);
			return "Бронь обновлена.";
		} catch (Exception ex) {
			return "Не удалось обновить бронь. " + ex.getMessage();
		}
	}

}
