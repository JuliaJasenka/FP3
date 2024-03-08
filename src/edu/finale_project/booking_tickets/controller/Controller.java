package edu.finale_project.booking_tickets.controller;



public class Controller {
	
	private static final char paramDelimeter = ';';

	private final CommandProvider provider = new CommandProvider();

	public String doAction(String request) {

		String commandName;
		Command executionCommand;

		commandName = request.substring(0, request.indexOf(paramDelimeter));
		executionCommand = provider.getCommand(commandName.toUpperCase());

		
		return executionCommand.execute(request.substring(request.indexOf(paramDelimeter) + 1));

	}
}
