package calculator;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import commands.*;

/**
 * The CommandInvoker has a CommandFactory.
 */
public class CommandFactory {

	/**
	 * Create a Command of type.
	 * 
	 * @param type
	 *            The type of Command to be created. The type String should exclude
	 *            "Command". To create a DivideCommand, for example, type should be
	 *            "Divide".
	 * 
	 * @return The Command of type.
	 */
	public Command createCommand(State state, String type) {
		Command newCommand = null;

		try {
			Class<Command> tempClass = (Class<Command>) Class.forName("commands." + type + "Command"); // Package.class
			Constructor<Command> constructor = tempClass.getDeclaredConstructor(State.class);
			newCommand = constructor.newInstance(state);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return newCommand;
	}

}
