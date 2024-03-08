package edu.finale_project.booking_tickets.controller;

import java.util.HashMap;
import java.util.Map;

import edu.finale_project.booking_tickets.controller.impl.AddBooking;
import edu.finale_project.booking_tickets.controller.impl.AddTrain;
import edu.finale_project.booking_tickets.controller.impl.FindAllTrain;
import edu.finale_project.booking_tickets.controller.impl.FindBookingById;
import edu.finale_project.booking_tickets.controller.impl.FindBookingByUserId;
import edu.finale_project.booking_tickets.controller.impl.FindTrainById;
import edu.finale_project.booking_tickets.controller.impl.FindTrainByParam;
import edu.finale_project.booking_tickets.controller.impl.FindTrainInfoByUserId;
import edu.finale_project.booking_tickets.controller.impl.RemoveTrain;
import edu.finale_project.booking_tickets.controller.impl.UpdateBooking;
import edu.finale_project.booking_tickets.controller.impl.UpdateTrain;
import edu.finale_project.booking_tickets.controller.impl.NoSuchCommand;
import edu.finale_project.booking_tickets.controller.impl.RemoveBooking;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	 CommandProvider() {
	        repository.put(CommandName.ADD_TRAIN, new AddTrain());
	        repository.put(CommandName.REMOVE_TRAIN, new RemoveTrain());
	        repository.put(CommandName.UPDATE_TRAIN, new UpdateTrain());
	        repository.put(CommandName.FIND_BY_ID_TRAIN, new FindTrainById());
	        repository.put(CommandName.FIND_ALL_TRAIN, new FindAllTrain());
	        repository.put(CommandName.FIND_BY_PARAMETERS_TRAIN, new FindTrainByParam());
	        repository.put(CommandName.ADD_BOOKING, new AddBooking());
	        repository.put(CommandName.REMOVE_BOOKING, new RemoveBooking());
	        repository.put(CommandName.UPDATE_BOOKING, new UpdateBooking());
	        repository.put(CommandName.FIND_BY_ID_BOOKING, new FindBookingById());
	        repository.put(CommandName.FIND_BY_USER_ID_BOOKING, new FindBookingByUserId());
	        repository.put(CommandName.FIND_TRAIN_BY_USER_ID_BOOKING, new FindTrainInfoByUserId());

	        repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
	    }

	    Command getCommand(String name) {
	        CommandName commandName = null;
	        Command command = null;

	        try {
	            commandName = CommandName.valueOf(name.toUpperCase());
	            command = repository.get(commandName);
	        } catch (IllegalArgumentException | NullPointerException e) {

	            command = repository.get(CommandName.WRONG_REQUEST);
	        }
	        return command;
	    }

}