package edu.finale_project.booking_tickets.controller.impl;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class RemoveBooking implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final BookingLogic bookingLogic = provider.getBookingLogic();

	@Override
	public String execute(String request) {
		 String[] splitElement = request.split("=");
	        if (splitElement[0].equals("id")) {
	            try {
	                boolean isRemoved = bookingLogic.delete(splitElement[1]);
	                if (isRemoved) return "Бронь удалена.";
	            } catch (Exception ex) {
	                return "Не удалось удалить бронь. " + ex.getMessage();
	            }
	        }

	        return "Не удалось удалить бронь.";
	    }

}
