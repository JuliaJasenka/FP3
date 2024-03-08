package edu.finale_project.booking_tickets.controller.impl;

import java.util.List;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class FindAllTrain implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final TrainLogic trainLogic = provider.getTrainLogic();

	@Override
	public String execute(String request) {

		try {
			List<Train> trainList = trainLogic.findAll();
			if (trainList.isEmpty()) {
				return "Маршруты не найдены";
			} else {
				String trains = "";
				for (Train train : trainList) {
					trains += train + "\n";
				}

				return trains;
			}
		} catch (Exception ex) {
			return "Маршруты не найдены. " + ex.getMessage();
		}
	}

}
