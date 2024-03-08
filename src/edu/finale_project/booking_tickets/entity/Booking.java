package edu.finale_project.booking_tickets.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
	
	private String id;
    private String trainId;
    private int userId;
    private LocalDateTime bookingDate;
    private int seatsNumber;
	
    public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTrainId() {
		return trainId;
	}
	
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public int getSeatsNumber() {
		return seatsNumber;
	}
	
	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookingDate, id, seatsNumber, trainId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(bookingDate, other.bookingDate) && Objects.equals(id, other.id)
				&& seatsNumber == other.seatsNumber && Objects.equals(trainId, other.trainId) && userId == other.userId;
	}

	@Override
    public String toString() {
        return "Booking{" +
               "id='" + id + '\'' +
               ", trainId=" + trainId +
               ", userId=" + userId +
               ", bookingDate=" + bookingDate +
               ", seatsNumber=" + seatsNumber +
               '}';
	}	
	
}
