package edu.finale_project.booking_tickets.controller.impl;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class FindTrainById implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final TrainLogic trainLogic = provider.getTrainLogic();

	@Override
	public String execute(String request) {

		 String[] splitElement = request.split("=");
	        if (splitElement[0].equals("id")) {
	            String id = splitElement[1];
	            try {
	                Train train = trainLogic.findById(id);
	                if (train == null) {
	                    return "Маршрут с ID - " + id + " не найден";
	                }
	                return train.toString();
	            } catch (Exception ex) {
	                return "Маршрут с ID - " + id + " не найден" + ex.getMessage();
	            }
	        }

	        return "Маршрут не найден. Проверьте параметры поиска.";
	    }

}
