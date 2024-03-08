package edu.finale_project.booking_tickets.dao;

import java.util.List;

import edu.finale_project.booking_tickets.entity.Booking;

public interface BookingDao {

	 String add(Booking booking) throws DaoException;

	    Booking findById(String id) throws DaoException;

	    void update(Booking entity) throws DaoException;

	    boolean delete(String id) throws DaoException;

	    List<Booking> getBookingInfoByUserId(int id) throws DaoException;

}
