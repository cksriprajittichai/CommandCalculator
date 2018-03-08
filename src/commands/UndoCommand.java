package commands;

import static sbcc.Core.*;

import calculator.*;

/**
 * Unary commands are not added to the stack.
 */
public class UndoCommand extends Command {

	private double result;
	private Command poppedCommand;


	/**
	 * UndoCommands clear any waiting (unfinished) commands, and returns the state
	 * to the most recent result of the last Command from the stack; recall that the
	 * stack only contains BinaryCommands.
	 * 
	 * @param state
	 */
	public UndoCommand(State state) {
		setState(state);

		setSymbol("⤺");
		execute();
	}


	@Override
	public void execute() {
		if (getState().isWaiting()) {
			// If there is a waiting command, delete it.
			getState().setWaiting(false);
			getState().setWaitingCommand(null);
			getState().setNextDigitResetsResultLabel(false);
		}

		if (getState().getCommandStack().size() > 1) {
			poppedCommand = getState().getCommandStack().pop();
			BinaryCommand lastCommand = (BinaryCommand) getState().getCommandStack().peek();

			setResult(lastCommand.getResult());
		} else {
			// If there are no more commands to undo, take the state back to the absolute
			// beginning.
			if (getState().getCommandStack().size() == 1) {
				poppedCommand = getState().getCommandStack().pop();
			}
			setResult(0);
		}

		getState().getNetCommandList().add(this);
		updateResultLabel();
	}


	@Override
	public void updateResultLabel() {
		if (getResult() % 1 == 0.0) {
			getState().getResultLabel().setText((long) getResult() + "");
		} else {
			getState().getResultLabel().setText(getResult() + "");
		}
	}


	@Override
	public String toString() {
		return String.format("undo (%s)", poppedCommand.toString());
	}


	public double getResult() {
		return result;
	}


	public void setResult(double result) {
		this.result = result;
	}

}
