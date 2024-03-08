package edu.finale_project.booking_tickets.controller.impl;

import edu.finale_project.booking_tickets.controller.Command;

public class NoSuchCommand implements Command {
	
	@Override
	public String execute(String request) {
		return "Request error.";
	}
}
