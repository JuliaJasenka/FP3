package edu.finale_project.booking_tickets.logic.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.finale_project.booking_tickets.dao.BookingDao;
import edu.finale_project.booking_tickets.dao.DaoException;
import edu.finale_project.booking_tickets.dao.DaoProvider;
import edu.finale_project.booking_tickets.dao.TrainDao;
import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.BookingLogic;
import edu.finale_project.booking_tickets.logic.LogicException;

public class BookingLogicImpl implements BookingLogic {

	 private final DaoProvider daoProvider = DaoProvider.getInstance();
	    private final TrainDao trainDao = daoProvider.getTrainDao();
	    private final BookingDao bookingDao = daoProvider.getBookingDao();

	    @Override
	    public String add(Booking booking) throws LogicException {
	        try { 
	            Train train = trainDao.findById(booking.getTrainId());
	            if (train == null) {
	                throw new LogicException("Маршурт с ID: " + booking.getTrainId() + " не был найден.");
	            }
	            if (train.getFreeSeats() >= booking.getSeatsNumber()) {
	                train.setFreeSeats(train.getFreeSeats() - booking.getSeatsNumber());
	                bookingDao.add(booking);
	                trainDao.update(train);

	                return booking.getId();
	            } else {
	                throw new LogicException("Недастаточно мест. Свободных мест: " + train.getFreeSeats() + ", количество бронируемых мест: " + booking.getSeatsNumber());
	            }
	        } catch (DaoException ex) {
	            throw new LogicException(ex);
	        }
	    }

	    @Override
	    public Booking findById(String id) throws LogicException {
	        try {
	            return bookingDao.findById(id);
	        } catch (DaoException e) {
	            throw new LogicException(e);
	        }
	    }

	    @Override
	    public void update(Booking updatedBooking) throws LogicException {
	        try {
	            Booking booking = findById(updatedBooking.getId());
	            if (booking != null) {
	                int oldSeats = booking.getSeatsNumber();
	                int newSeats = updatedBooking.getSeatsNumber();
	                Train train = trainDao.findById(booking.getTrainId());
	                if (newSeats < oldSeats) {
	                    train.setFreeSeats(train.getFreeSeats() + (oldSeats - newSeats));
	                } else {
	                    int seats = newSeats - oldSeats;
	                    if (train.getFreeSeats() >= seats) {
	                        train.setFreeSeats(train.getFreeSeats() - seats);
	                    } else {
	                        throw new LogicException("Недастаточно мест. Свободных мест: " + train.getFreeSeats() + ", количество бронируемых мест: " + updatedBooking.getSeatsNumber());
	                    }
	                }
	                booking.setBookingDate(LocalDateTime.now());
	                booking.setSeatsNumber(updatedBooking.getSeatsNumber());
	                trainDao.update(train);
	                bookingDao.update(booking);
	            }
	        } catch (DaoException e) {
	            throw new LogicException(e);
	        }
	    }

	    @Override
	    public boolean delete(String id) throws LogicException {
	        try {
	            Booking booking = bookingDao.findById(id);
	            if (booking == null) {
	                throw new LogicException("Бронь с ID - " + id + " не найдена.");
	            }
	            Train train = trainDao.findById(booking.getTrainId());
	            train.setFreeSeats(train.getFreeSeats() + booking.getSeatsNumber());
	            trainDao.update(train);
	            return bookingDao.delete(id);
	        } catch (DaoException e) {
	            throw new LogicException(e);
	        }
	    }

	    @Override
	    public List<Booking> getBookingInfoByUserId(int id) throws LogicException {
	        try {
	            return bookingDao.getBookingInfoByUserId(id);
	        } catch (DaoException e) {
	            throw new LogicException(e);
	        }
	    }

	    @Override
	    public List<Train> getTrainInfoByUserId(int id) throws LogicException {
	        try {
	            List<Booking> bookingList = getBookingInfoByUserId(id);
	            Set<String> trainIdSet = new HashSet<>();
	            for (Booking booking : bookingList) {
	                if (booking.getUserId() == id) {
	                    trainIdSet.add(booking.getTrainId());
	                }
	            }
	            if (trainIdSet.isEmpty()) {
	                return new ArrayList<>();
	            }

	            return trainDao.findAllByIds(trainIdSet);
	        } catch (DaoException e) {
	            throw new LogicException(e);
	        }
	    }

}
