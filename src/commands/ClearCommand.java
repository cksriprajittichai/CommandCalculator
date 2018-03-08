package commands;

import static sbcc.Core.*;

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
	public ClearCommand(State state) {
		setState(state);
		setSymbol("ac");
		execute();
	}


	@Override
	public void execute() {
		getState().setWaiting(false);
		getState().setWaitingCommand(null);
		getState().setNextDigitResetsResultLabel(false);
		updateResultLabel();
	}


	@Override
	public void updateResultLabel() {
		getState().getResultLabel().setText("0");
	}

}
