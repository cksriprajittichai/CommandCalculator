package commands;

import calculator.*;

/**
 * Unary commands are not added to the stack.
 */
public class EqualsCommand extends Command {

	public EqualsCommand(State state) {
		setState(state);
		setSymbol("=");

		if (getState().isWaiting()) {
			// I THINK that the state cannot be waiting if there is invalid input in the
			// result label. Therefore, don't need to check if input is valid.

			execute();
		}
	}


	@Override
	public void execute() {
		getState().getWaitingCommand().execute();
	}


	@Override
	public void updateResultLabel() {
		// Do nothing. The waiting command's execute method will update the resultLabel.
	}

}
