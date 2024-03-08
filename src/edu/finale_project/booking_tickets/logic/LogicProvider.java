package edu.finale_project.booking_tickets.logic;

import edu.finale_project.booking_tickets.logic.impl.BookingLogicImpl;
import edu.finale_project.booking_tickets.logic.impl.TrainLogicImpl;

public class LogicProvider {
	private static final LogicProvider instance = new LogicProvider();
	private static final TrainLogic trainLogic = new TrainLogicImpl();

	private LogicProvider() {
	}

	public TrainLogic getTrainLogic() {
		return trainLogic;
	}

	public static LogicProvider getInstance1() {
		return instance;
	}

	private BookingLogic logic = new BookingLogicImpl();

	public BookingLogic getBookingLogic() {
		return logic;
	}

	public static LogicProvider getInstance() {
		return instance;
	}
}
