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
	 * @param commandtype
	 *            The type of Command to be created. The type String should exclude
	 *            "Command". To create a DivideCommand, for example, type should be
	 *            "Divide".
	 * 
	 * @return The Command of type.
	 */
	public Command createCommand(String commandtype, Model state,
			LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
			LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
		Command newCommand = null;

		try {
			Class<Command> tempClass = (Class<Command>) Class.forName("commands." + commandtype + "Command"); // Package.class
			Constructor<Command> constructor = tempClass.getDeclaredConstructor(Model.class, LinkedList.class,
					LinkedList.class, String.class);
			newCommand = constructor.newInstance(state, currentOperationChangedListeners, displayChangedListeners,
					initialDisplayValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return newCommand;
	}

}
