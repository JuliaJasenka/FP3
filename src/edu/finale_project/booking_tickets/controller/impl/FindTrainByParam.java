package edu.finale_project.booking_tickets.controller.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.finale_project.booking_tickets.controller.Command;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicProvider;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class FindTrainByParam implements Command {

	private final LogicProvider provider = LogicProvider.getInstance();
	private final TrainLogic trainLogic = provider.getTrainLogic();

	  @Override
	    public String execute(String request) {

	        String[] splitPayload = request.split(";");
	        String from = null;
	        String to = null;
	        LocalDate date = null;

	        Map<String, String> paramMap = new HashMap<>();
	        for (String param : splitPayload) {
	            String[] split = param.split("=");
	            paramMap.put(split[0], split[1]);
	        }
	        from = paramMap.get("from");
	        to = paramMap.get("to");
	        if (paramMap.get("departureTime") != null) {
	            date = LocalDate.parse(paramMap.get("departureTime"));
	        }

	        try {
	            List<Train> trainList = trainLogic.findByParameters(from, to, date);
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
