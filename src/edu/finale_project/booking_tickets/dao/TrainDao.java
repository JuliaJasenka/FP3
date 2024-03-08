package edu.finale_project.booking_tickets.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import edu.finale_project.booking_tickets.entity.Train;

public interface TrainDao {
	
	String add(Train entity) throws DaoException;

    Train findById(String id) throws DaoException;

    void update(Train entity) throws DaoException;

    boolean delete(String id) throws DaoException;

    List<Train> findAll() throws DaoException;

    List<Train> findAllByIds(Set<String> ids) throws DaoException;

    List<Train> findByParameters(String from, String to, LocalDate date) throws DaoException;

}
