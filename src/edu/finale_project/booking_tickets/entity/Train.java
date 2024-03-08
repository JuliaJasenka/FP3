package edu.finale_project.booking_tickets.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Train {

	 private String id;
	    private Integer trainNumber;
	    private String from;
	    private String to;
	    private LocalDateTime departureTime;
	    private Integer freeSeats;

	    public Train() {
	    }

	    public Train(Integer trainNumber, String from, String to, LocalDateTime departureTime, Integer freeSeats) {
	        this.trainNumber = trainNumber;
	        this.from = from;
	        this.to = to;
	        this.departureTime = departureTime;
	        this.freeSeats = freeSeats;
	    }

		

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getTrainNumber() {
			return trainNumber;
		}

		public void setTrainNumber(Integer trainNumber) {
			this.trainNumber = trainNumber;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public LocalDateTime getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(LocalDateTime departureTime) {
			this.departureTime = departureTime;
		}

		public Integer getFreeSeats() {
			return freeSeats;
		}

		public void setFreeSeats(Integer freeSeats) {
			this.freeSeats = freeSeats;
		}

		
		
		@Override
		public int hashCode() {
			return Objects.hash(departureTime, freeSeats, from, id, to, trainNumber);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Train other = (Train) obj;
			return Objects.equals(departureTime, other.departureTime) && Objects.equals(freeSeats, other.freeSeats)
					&& Objects.equals(from, other.from) && Objects.equals(id, other.id) && Objects.equals(to, other.to)
					&& Objects.equals(trainNumber, other.trainNumber);
		}

		@Override
	    public String toString() {
	        return "Train{" +
	               "id=" + id +
	               ", trainNumber=" + trainNumber +
	               ", departurePoint='" + from + '\'' +
	               ", destination='" + to + '\'' +
	               ", departureTime=" + departureTime +
	               ", freeSeats=" + freeSeats +
	               '}';
	    }

}
