package edu.finale_project.booking_tickets.controller.impl;

import java.util.List;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class FindBookingByUserId implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
    private final BookingLogic bookingLogic = provider.getBookingLogic();

    @Override
    public String execute(String request) {

        String[] splitElement = request.split("=");
        if (splitElement[0].equals("userId")) {
            String userId = splitElement[1];
            try {
                List<Booking> bookingList = bookingLogic.getBookingInfoByUserId(Integer.parseInt(userId));
                if (bookingList.isEmpty()) {
                    return "Бронь для user ID - " + userId + " не найдена";
                }
                String bookings = "";
                for (Booking booking : bookingList) {
                    bookings += booking + "\n";
                }

                return bookings;
            } catch (Exception ex) {
                return "Бронь для user ID - " + userId + " не найдена" + ex.getMessage();
            }
        }

        return "Бронь не найдена. Проверьте параметры поиска.";
    }


}
