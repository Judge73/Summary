package ua.nure.kotkov.SummaryTask4.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.kotkov.SummaryTask4.command.admin.AddEmployeeCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.AddEmployeePageCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.AddFlightCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.AddFlightPageCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.AdminEmployeeViewCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.AdminFlightViewCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.ConfigureEmployeeCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.ConfigureEmployeePageCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.ConfigureFlightCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.ConfigureFlightPageCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.FlightsStatusesCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.FreeCrewCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.OpenRequestCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.OpenRequestsPageCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.RemoveEmployeeCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.RemoveFlightCommand;
import ua.nure.kotkov.SummaryTask4.command.admin.UpdateRequestStatusCommand;
import ua.nure.kotkov.SummaryTask4.command.dispatcher.DispatcherViewCommand;
import ua.nure.kotkov.SummaryTask4.command.dispatcher.FormCrewCommand;
import ua.nure.kotkov.SummaryTask4.command.dispatcher.RequestPageCommand;
import ua.nure.kotkov.SummaryTask4.command.dispatcher.SetStatusCommand;
import ua.nure.kotkov.SummaryTask4.command.dispatcher.WriteRequestCommand;

public class CommandContainer {
	
	private static final Logger LOG = LogManager.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("sortFlight", new SortFlightCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		
		// dispatcher commands
		commands.put("dispatcherView", new DispatcherViewCommand());
		commands.put("formCrew", new FormCrewCommand());
		commands.put("requestPage", new RequestPageCommand());
		commands.put("writeRequest", new WriteRequestCommand());
		commands.put("setStatus", new SetStatusCommand());
		
		// admin commands
		commands.put("adminFlightView", new AdminFlightViewCommand());
		commands.put("adminEmployeeView", new AdminEmployeeViewCommand());
		commands.put("removeFlight", new RemoveFlightCommand());
		commands.put("configureFlightPage", new ConfigureFlightPageCommand());
		commands.put("configureFlight", new ConfigureFlightCommand());
		commands.put("addFlightPage", new AddFlightPageCommand());
		commands.put("addFlight", new AddFlightCommand());
		commands.put("freeCrew", new FreeCrewCommand());
		commands.put("removeEmployee", new RemoveEmployeeCommand());
		commands.put("addEmployeePage", new AddEmployeePageCommand());
		commands.put("addEmployee", new AddEmployeeCommand());
		commands.put("configureEmployeePage", new ConfigureEmployeePageCommand());
		commands.put("configureEmployee", new ConfigureEmployeeCommand());
		commands.put("openRequestsPage", new OpenRequestsPageCommand());
		commands.put("openRequest", new OpenRequestCommand());
		commands.put("updateRequestStatus", new UpdateRequestStatusCommand());
		commands.put("flightsStatuses", new FlightsStatusesCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}