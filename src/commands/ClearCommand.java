package commands;

import static sbcc.Core.*;

import java.util.*;

import javax.swing.*;

import calculator.*;
import commands.*;

/**
 * Unary commands are not added to the stack.
 */
public class ClearCommand extends Command {

	/**
	 * ClearCommand is unique among UnaryCommands because it will always execute.
	 */
	public ClearCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
			LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
		setModel(model);
		setCurrentOperationChangedListeners(currentOperationChangedListeners);
		setDisplayChangedListeners(displayChangedListeners);
		setInitialDisplayValue(initialDisplayValue);
		setSymbol("AC");

		setResultStr(roundDoubleString("0"));
		execute(initialDisplayValue);
	}


	@Override
	public String execute(String initialDisplayValue) {
		getModel().setWaiting(false);
		getModel().setWaitingCommand(null);
		getModel().setNextDigitResetsResultLabel(false);
		notifyCurrentOperationChangedListeners(null);
		notifyDisplayChangedListeners();

		return getResultStr();
	}

}
