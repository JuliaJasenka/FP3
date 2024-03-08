package edu.finale_project.booking_tickets.logic;

import java.time.LocalDate;
import java.util.List;

import edu.finale_project.booking_tickets.entity.Train;

public interface TrainLogic {

	String add(Train train) throws LogicException;

	Train findById(String id) throws LogicException;

	List<Train> findAll() throws LogicException;

	List<Train> findByParameters(String from, String to, LocalDate date) throws LogicException;

	void update(Train train) throws LogicException;

	boolean remove(String id) throws LogicException;

}
