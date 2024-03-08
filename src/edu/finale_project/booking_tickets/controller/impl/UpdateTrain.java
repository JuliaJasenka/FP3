package edu.finale_project.booking_tickets.controller.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class UpdateTrain implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final TrainLogic trainLogic = provider.getTrainLogic();

	@Override
	public String execute(String request) {
		String[] splitPayload = request.split(";");
		Map<String, String> trainMap = new HashMap<>();
		for (String param : splitPayload) {
			String[] split = param.split("=");
			trainMap.put(split[0], split[1]);
		}
		Train train = new Train();
		train.setId(trainMap.get("id"));
		if (trainMap.get("trainNumber") != null) {
			train.setTrainNumber(Integer.parseInt(trainMap.get("trainNumber")));
		}
		train.setFrom(trainMap.get("from"));
		train.setTo(trainMap.get("to"));
		if (trainMap.get("departureTime") != null) {
			train.setDepartureTime(LocalDateTime.parse(trainMap.get("departureTime")));
		}
		if (trainMap.get("freeSeats") != null) {
			train.setFreeSeats(Integer.parseInt(trainMap.get("freeSeats")));
		}

		try {
			trainLogic.update(train);
			return "Маршрут обновлен.";
		} catch (Exception ex) {
			return "Не удалось обновить маршрут. " + ex.getMessage();
		}
	}

}
