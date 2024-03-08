package edu.finale_project.booking_tickets.dao.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.finale_project.booking_tickets.dao.BookingDao;
import edu.finale_project.booking_tickets.dao.DaoException;
import edu.finale_project.booking_tickets.dao.DaoHelper;
import edu.finale_project.booking_tickets.entity.Booking;

public class BookingDaoImpl implements BookingDao {

	private static final String DELIMITER = ";";
	private static final String FILE_NAME = "booking.txt";

	@Override
	public String add(Booking booking) throws DaoException {
		try {
			DaoHelper.writeFile(prepareForStoring(booking) + '\n', FILE_NAME, true);
			return booking.getId();
		} catch (IOException ex) {
			throw new DaoException("Не удалось сохранить бронь (ID - " + booking.getId() + ". " + ex.getMessage());
		}
	}

	@Override
	public Booking findById(String id) throws DaoException {
		try {
			List<String> bookingList = DaoHelper.readFileByLine(FILE_NAME);
			for (String booking : bookingList) {
				if (booking.startsWith(id)) {
					return convertFromString(booking);
				}
			}
			throw new DaoException("Бранирование с ID - " + id + " не найден");
		} catch (IOException ex) {
			throw new DaoException("Не удалось найти бронь (ID - " + id + ". " + ex.getMessage());
		}
	}

	@Override
	public void update(Booking booking) throws DaoException {
		try {
			List<String> bookingList = DaoHelper.readFileByLine(FILE_NAME);
			for (int i = 0; i < bookingList.size(); i++) {
				if (bookingList.get(i).startsWith(booking.getId())) {
					bookingList.set(i, prepareForStoring(booking));
				}
			}
			String updatedPayload = "";
			for (String line : bookingList) {
				if (!line.isEmpty()) {
					updatedPayload += line + '\n';
				}
			}
			DaoHelper.writeFile(updatedPayload, FILE_NAME);
		} catch (IOException ex) {
			throw new DaoException("Не удалось обновить бронь (ID - " + booking.getId() + ". " + ex.getMessage());
		}
	}

	@Override
	public boolean delete(String id) throws DaoException {
		try {
			List<String> bookingList = DaoHelper.readFileByLine(FILE_NAME);
			List<String> newBookingList = new ArrayList<>();
			boolean isRemoved = false;
			for (String booking : bookingList) {
				if (booking.startsWith(id)) {
					isRemoved = true;
				} else {
					newBookingList.add(booking);
				}
			}
			if (isRemoved) {
				String updatedPayload = "";
				for (String line : newBookingList) {
					updatedPayload += line + '\n';
				}
				DaoHelper.writeFile(updatedPayload, FILE_NAME);
			}
			return isRemoved;
		} catch (IOException ex) {
			throw new DaoException("Не удалось удалить бронь (ID - " + id + ". " + ex.getMessage());
		}
	}

	@Override
	public List<Booking> getBookingInfoByUserId(int id) throws DaoException {
		try {
			List<String> bookingList = DaoHelper.readFileByLine(FILE_NAME);
			List<Booking> filteredBookingList = new ArrayList<>();
			for (String bookingStr : bookingList) {
				Booking booking = convertFromString(bookingStr);
				if (id == booking.getUserId()) {
					filteredBookingList.add(booking);
				}
			}

			return filteredBookingList;
		} catch (IOException ex) {
			throw new DaoException("Не удалось найти бронь (ID - " + id + ". " + ex.getMessage());
		}
	}

	private String prepareForStoring(Booking booking) {
		return booking.getId() + DELIMITER + booking.getTrainId() + DELIMITER + booking.getUserId() + DELIMITER
				+ booking.getBookingDate() + DELIMITER + booking.getSeatsNumber();
	}

	private Booking convertFromString(String bookingStr) {
		if (bookingStr == null || bookingStr.isEmpty())
			return null;
		Booking booking = new Booking();
		String[] split = bookingStr.split(DELIMITER);
		booking.setId(split[0]);
		booking.setTrainId(split[1]);
		booking.setUserId(Integer.parseInt(split[2]));
		booking.setBookingDate(LocalDateTime.parse(split[3]));
		booking.setSeatsNumber(Integer.parseInt(split[4]));

		return booking;
	}
}
