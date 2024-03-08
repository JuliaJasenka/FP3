package edu.finale_project.booking_tickets.logic;

import java.util.List;

import edu.finale_project.booking_tickets.entity.Booking;
import edu.finale_project.booking_tickets.entity.Train;

public interface BookingLogic {

	String add(Booking booking) throws LogicException;

    Booking findById(String id) throws LogicException;

    void update(Booking entity) throws LogicException;

    boolean delete(String id) throws LogicException;

    List<Booking> getBookingInfoByUserId(int id) throws LogicException;

    List<Train> getTrainInfoByUserId(int id) throws LogicException;

}
