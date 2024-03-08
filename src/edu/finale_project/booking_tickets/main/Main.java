package edu.finale_project.booking_tickets.main;


import edu.finale_project.booking_tickets.controller.Controller;
import java.time.LocalDateTime;

public class Main {

	 public static void main(String[] args) {
	        Controller controller = new Controller();
	        trainCommands(controller);
	        bookingCommands(controller);
	    }

	    private static void bookingCommands(Controller controller) {
	        String response = null;
	        String addBooking = "ADD_BOOKING;userId=3;trainId=97306d81-fdcc-4cd2-bfd0-8112d6e68d80;seatsNumber=3";
	        response = controller.doAction(addBooking);
	        System.out.println(response);

//	        String removeBooking = "REMOVE_BOOKING;id=288584e8-5ff9-4d5b-9348-de39c1c4cc6d";
//	        response = controller.doAction(removeBooking);
//	        System.out.println(response);

//	        String updateBooking = "UPDATE_BOOKING;id=5a93f090-71e4-45b8-be33-3b0f7caa5cb8;seatsNumber=5";
//	        response = controller.doAction(updateBooking);
//	        System.out.println(response);

//	        String findById = "FIND_BY_ID_BOOKING;id=acd94b60-bab7-492b-bf2c-4f1accc91e48";
//	        response = controller.doAction(findById);
//	        System.out.println(response);

//	        String findByUserId = "FIND_BY_USER_ID_BOOKING;userId=3";
//	        response = controller.doAction(findByUserId);
//	        System.out.println(response);

//	        String findTrainByUserId = "FIND_TRAIN_BY_USER_ID_BOOKING;userId=1";
//	        response = controller.doAction(findTrainByUserId);
//	        System.out.println(response);
	    }

	    private static void trainCommands(Controller controller) {
	        String response = null;
//	        String addTrain = "ADD_TRAIN;to=Orsha;from=Minsk;departureTime=" + LocalDateTime.now() + ";trainNumber=103;freeSeats=50";
//	        response = controller.doAction(addTrain);
//	        System.out.println(response);

//	        String updateTrain = "UPDATE_TRAIN;id=97306d81-fdcc-4cd2-bfd0-8112d6e68d80;to=Brest;freeSeats=30";
//	        response = controller.doAction(updateTrain);
//	        System.out.println(response);

//	        String removeTrain = "REMOVE_TRAIN;id=4a93f090-71e4-45b8-be33-1b0f7ca25cb2";
//	        response = controller.doAction(removeTrain);
//	        System.out.println(response);

//	        String findAll = "FIND_ALL_TRAIN;";
//	        response = controller.doAction(findAll);
//	        System.out.println(response);

//	        String findByParameters = "FIND_BY_PARAMETERS_TRAIN"
//	                                  +";from=Lida"
//	                                  +";to=Moscow"
//	                                  + ";departureTime=2024-03-15";
//	        response = controller.doAction(findByParameters);

//	        String findById = "FIND_BY_ID_TRAIN;id=fa93f090-71e4-45b8-be33-5b0f7caa5cb9";
//	        response = controller.doAction(findById);
//	        System.out.println(response);
	    }

}
