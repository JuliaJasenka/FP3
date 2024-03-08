package edu.finale_project.booking_tickets.controller.impl;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class RemoveTrain implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final TrainLogic trainLogic = provider.getTrainLogic();

	@Override
	public String execute(String request) {
		String[] splitElement = request.split("=");
		if (splitElement[0].equals("id")) {
			try {
				boolean isRemoved = trainLogic.remove(splitElement[1]);
				if (isRemoved)
					return "Маршрут удален.";
			} catch (Exception ex) {
				return "Не удалось добавить новый маршрут. " + ex.getMessage();
			}
		}

		return "Маршрут не был удален.";
	}

}
