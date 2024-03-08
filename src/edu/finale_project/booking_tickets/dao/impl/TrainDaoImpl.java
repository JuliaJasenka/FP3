package edu.finale_project.booking_tickets.dao.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.finale_project.booking_tickets.dao.DaoException;
import edu.finale_project.booking_tickets.dao.DaoHelper;
import edu.finale_project.booking_tickets.dao.TrainDao;
import edu.finale_project.booking_tickets.entity.Train;

public class TrainDaoImpl implements TrainDao {

	private static final String DELIMITER = ";";
	private static final String FILE_NAME = "train.txt";

	@Override
	public String add(Train entity) throws DaoException {
		try {
			DaoHelper.writeFile(prepareForStoring(entity), FILE_NAME, true);
			return entity.getId();
		} catch (IOException ex) {
			throw new DaoException("Failed to store the train (ID - " + entity.getId() + ". " + ex.getMessage());
		}
	}

	@Override
	public Train findById(String id) throws DaoException {
		try {
			List<String> trainList = DaoHelper.readFileByLine(FILE_NAME);
			for (String train : trainList) {
				if (train.startsWith(id)) {
					return convertFromString(train);
				}
			}
			throw new DaoException("Маршурт с ID - " + id + " не найден");
		} catch (IOException ex) {
			throw new DaoException("Не удалость сохранить маршрут (ID - " + id + ". " + ex.getMessage());
		}
	}

	 @Override
	    public void update(Train updatedTrain) throws DaoException {
	        try {
	            List<String> trainStrList = DaoHelper.readFileByLine(FILE_NAME);
	            List<Train> trainList = new ArrayList<>();
	            for (String line : trainStrList) {
	                trainList.add(convertFromString(line));
	            }
	            for (Train train : trainList) {
	                if (train.getId().equals(updatedTrain.getId())) {
	                    if (updatedTrain.getTrainNumber() != null) {
	                        train.setTrainNumber(updatedTrain.getTrainNumber());
	                    }
	                    if (updatedTrain.getTo() != null) {
	                        train.setTo(updatedTrain.getTo());
	                    }
	                    if (updatedTrain.getFrom() != null) {
	                        train.setFrom(updatedTrain.getFrom());
	                    }
	                    if (updatedTrain.getDepartureTime() != null) {
	                        train.setDepartureTime(updatedTrain.getDepartureTime());
	                    }
	                    if (updatedTrain.getFreeSeats() != null) {
	                        train.setFreeSeats(updatedTrain.getFreeSeats());
	                    }
	                }
	            }
	            String updatedPayload = "";
	            for (Train train : trainList) {
	                updatedPayload += prepareForStoring(train) + '\n';
	            }
	            DaoHelper.writeFile(updatedPayload, FILE_NAME);
	        } catch (IOException ex) {
	            throw new DaoException("Не удалось обновить маршурт (ID - " + updatedTrain.getId() + ". " + ex.getMessage());
	        }
	    }


	@Override
	public boolean delete(String id) throws DaoException {
		try {
			List<String> trainList = DaoHelper.readFileByLine(FILE_NAME);
			List<String> newTrainList = new ArrayList<>();
			boolean isRemoved = false;
			for (String train : trainList) {
				if (train.startsWith(id)) {
					isRemoved = true;
				} else {
					newTrainList.add(train);
				}
			}
			if (isRemoved) {
				String updatedPayload = "";
				for (int i = 0; i < newTrainList.size(); i++) {
					updatedPayload += newTrainList.get(i);
					if (i != newTrainList.size() - 1) {
						updatedPayload += '\n';
					}
				}
				DaoHelper.writeFile(updatedPayload, FILE_NAME);
			}
			return isRemoved;
		} catch (IOException ex) {
			throw new DaoException("Не удалось удалить маршурт (ID - " + id + ". " + ex.getMessage());
		}
	}

	@Override
	public List<Train> findAll() throws DaoException {
		try {
			return DaoHelper.readFileByLine(FILE_NAME).stream().map(this::convertFromString)
					.collect(Collectors.toList());
		} catch (IOException ex) {
			throw new DaoException("Не удалось получить список поездов. " + ex.getMessage());
		}
	}

	@Override
	public List<Train> findAllByIds(Set<String> ids) throws DaoException {
		try {
			return DaoHelper.readFileByLine(FILE_NAME).stream().map(this::convertFromString)
					.filter(train -> ids.contains(train.getId())).collect(Collectors.toList());
		} catch (IOException ex) {
			throw new DaoException("Не удалось получить список поездов. " + ex.getMessage());
		}
	}

	@Override
	public List<Train> findByParameters(String from, String to, LocalDate date) throws DaoException {
		try {
			List<Train> trainList = DaoHelper.readFileByLine(FILE_NAME).stream().map(this::convertFromString).toList();
			List<Train> filteredList = new ArrayList<>();
			for (Train train : trainList) {
				if (from != null && !train.getFrom().equals(from)) {
					continue;
				}
				if (to != null && !train.getTo().equals(to)) {
					continue;
				}
				if (date != null && !date.equals(train.getDepartureTime().toLocalDate())) {
					continue;
				}
				filteredList.add(train);
			}
			return filteredList;
		} catch (IOException ex) {
			throw new DaoException("Не удалось получить список поездов. " + ex.getMessage());
		}
	}

	private String prepareForStoring(Train train) {
		return train.getId() + DELIMITER + train.getTrainNumber() + DELIMITER + train.getFrom() + DELIMITER
				+ train.getTo() + DELIMITER + train.getDepartureTime() + DELIMITER + train.getFreeSeats() + DELIMITER
				+ '\n';
	}

	private Train convertFromString(String trainStr) {
		if (trainStr == null || trainStr.isEmpty())
			return null;
		Train train = new Train();
		String[] split = trainStr.split(DELIMITER);
		train.setId(split[0]);
		train.setTrainNumber(Integer.parseInt(split[1]));
		train.setFrom(split[2]);
		train.setTo(split[3]);
		train.setDepartureTime(LocalDateTime.parse(split[4]));
		train.setFreeSeats(Integer.parseInt(split[5]));

		return train;
	}

}
