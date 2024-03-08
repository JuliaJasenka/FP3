package edu.finale_project.booking_tickets.controller.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class AddTrain implements Command {

	 private final LogicProvider provider = LogicProvider.getInstance();
	    private final TrainLogic trainLogic = provider.getTrainLogic();

	    @Override
	    public String execute(String request) {
	        String[] splitPayload = request.split(";");
	        Train train = new Train();
	        train.setId(UUID.randomUUID().toString());
	        Map<String, String> trainMap = new HashMap<>();
	        for (String param : splitPayload) {
	            String[] split = param.split("=");
	            trainMap.put(split[0], split[1]);
	        }
	        train.setTrainNumber(Integer.parseInt(trainMap.get("trainNumber")));
	        train.setFrom(trainMap.get("from"));
	        train.setTo(trainMap.get("to"));
	        train.setDepartureTime(LocalDateTime.parse(trainMap.get("departureTime")));
	        train.setFreeSeats(Integer.parseInt(trainMap.get("freeSeats")));

	        try {
	            String trainId = trainLogic.add(train);
	            return "Новый маршрут добавлен: ID - " + trainId;
	        } catch (Exception ex) {
	            return "Не удалось добавить новый поезд. " + ex.getMessage();
	        }
	    }

}
