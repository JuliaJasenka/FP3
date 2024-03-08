package edu.finale_project.booking_tickets.controller.impl;

import java.util.List;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicProvider;

public class FindTrainInfoByUserId implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final BookingLogic bookingLogic = provider.getBookingLogic();

	@Override
	public String execute(String request) {
		String[] splitElement = request.split("=");
		if (splitElement[0].equals("userId")) {
			String userId = splitElement[1];
			try {
				List<Train> trainList = bookingLogic.getTrainInfoByUserId(Integer.parseInt(userId));
				if (trainList.isEmpty()) {
					return "Маршруты для user ID - " + userId + " не найдены";
				}
				String trains = "";
				for (Train train : trainList) {
					trains += train + "\n";
				}

				return trains;
			} catch (Exception ex) {
				return "Маршруты для user ID - " + userId + " не найдены" + ex.getMessage();
			}
		}

		return "Маршруты не найдены. Проверьте параметры поиска.";
	}

}
