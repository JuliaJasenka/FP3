package edu.finale_project.booking_tickets.dao;

import edu.finale_project.booking_tickets.dao.impl.BookingDaoImpl;
import edu.finale_project.booking_tickets.dao.impl.TrainDaoImpl;

public class DaoProvider {
	private static final DaoProvider INSTANCE = new DaoProvider();
	private static final TrainDao trainDao = new TrainDaoImpl();
	private BookingDaoImpl BookingDao = new BookingDaoImpl();

	private DaoProvider() {
	}

	public TrainDao getTrainDao() {
		return trainDao;
	}

	public BookingDao getBookingDao() {
		return BookingDao;
	}

	public static DaoProvider getInstance() {
		return INSTANCE;
	}

}
