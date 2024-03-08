package edu.finale_project.booking_tickets.controller.impl;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class FindBookingById implements Command {
	private final LogicProvider provider = LogicProvider.getInstance();
	private final BookingLogic bookingLogic = provider.getBookingLogic();

	@Override
	public String execute(String request) {

		String[] splitElement = request.split("=");
		if (splitElement[0].equals("id")) {
			String id = splitElement[1];
			try {
				Booking booking = bookingLogic.findById(id);
				if (booking == null) {
					return "Бронь с ID - " + id + " не найдена";
				}
				return booking.toString();
			} catch (Exception ex) {
				return "Бронь с ID - " + id + " не найдена" + ex.getMessage();
			}
		}

		return "Бронь не найдена. Проверьте параметры поиска.";
	}

}
