package edu.finale_project.booking_tickets.logic.impl;

import java.time.LocalDate;
import java.util.List;

import edu.finale_project.booking_tickets.dao.DaoException;
import edu.finale_project.booking_tickets.dao.DaoProvider;
import edu.finale_project.booking_tickets.dao.TrainDao;
import edu.finale_project.booking_tickets.entity.Train;
import edu.finale_project.booking_tickets.logic.LogicException;
import edu.finale_project.booking_tickets.logic.TrainLogic;

public class TrainLogicImpl implements TrainLogic {

	private final DaoProvider provider = DaoProvider.getInstance();
	private final TrainDao trainDaoImpl = provider.getTrainDao();

	@Override
	public String add(Train train) throws LogicException {
		if (train.getTrainNumber() == null) {
			throw new LogicException("'trainNumber' field can't be null");
		}
		// ..валидация остальных полей

		try {
			return trainDaoImpl.add(train);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public Train findById(String id) throws LogicException {
		try {
			return trainDaoImpl.findById(id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public List<Train> findAll() throws LogicException {
		try {
			return trainDaoImpl.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public List<Train> findByParameters(String from, String to, LocalDate date) throws LogicException {
		try {
			return trainDaoImpl.findByParameters(from, to, date);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	@Override
	public void update(Train train) throws LogicException {
        try {
            trainDaoImpl.update(train);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

	}

	@Override
	public boolean remove(String id) throws LogicException {
		try {
			return trainDaoImpl.delete(id);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
