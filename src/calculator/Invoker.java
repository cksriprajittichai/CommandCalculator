package calculator;

import files.*;

/**
 * The Invoker invokes all Commands for the program, but is intended to act like
 * the back-end of the calculator. So, in addition to invoking Commands, the
 * Invoker also controls the FileSaver, which does not utilize the Command
 * pattern. Nearly everything is passed directly from the Ui to the Invoker.
 */
public class Invoker {

	private State state;
	private CommandFactory commandFactory = new CommandFactory();
	private FileSaver fileSaver = new FileSaver();


	public Invoker(State state) {
		setState(state);
	}


	/**
	 * This function does the same thing as creating a new EqualsCommand.
	 */
	public void finishWaitingCommand() {
		if (getState().isWaiting()) {
			// Finish the waiting command
			getState().getWaitingCommand().execute();
		}
	}


	public void invokeClearCommand() {
		// Execute is called within the constructor of unary commands
		commandFactory.createCommand(getState(), "Clear");
	}


	public void invokeUndoCommand() {
		// Execute is called within the constructor of unary commands
		if (!getState().getCommandStack().isEmpty()) {
			commandFactory.createCommand(getState(), "Undo");
		}
	}


	public void invokeEqualsCommand() {
		// Execute is called within the constructor of unary commands
		commandFactory.createCommand(getState(), "Equals");
	}


	public void invokeToggleSignCommand() {
		// Execute is called within the constructor of unary commands
		commandFactory.createCommand(getState(), "ToggleSign");
	}


	public void invokeAddCommand() {
		if (getState().isWaiting()) {
			// Finish the waiting command, then create a new one
			finishWaitingCommand();
		}
		commandFactory.createCommand(getState(), "Add");
	}


	public void invokeSubtractCommand() {
		if (getState().isWaiting()) {
			// Finish the waiting command, then create a new one
			finishWaitingCommand();
		}
		commandFactory.createCommand(getState(), "Subtract");
	}


	public void invokeMultiplyCommand() {
		if (getState().isWaiting()) {
			// Finish the waiting command, then create a new one
			finishWaitingCommand();
		}
		commandFactory.createCommand(getState(), "Multiply");
	}


	public void invokeDivideCommand() {
		if (getState().isWaiting()) {
			// Finish the waiting command, then create a new one
			finishWaitingCommand();
		}
		commandFactory.createCommand(getState(), "Divide");
	}


	public void invokeSaveFullLogAsTxtCommand() {
		fileSaver.saveLog(getState().getNetCommandList(), ".txt", "long");
	}


	public void invokeSaveShortLogAsTxtCommand() {
		fileSaver.saveLog(getState().getCommandStack(), ".txt", "short");
	}


	public void invokeSaveFullLogAsXmlCommand() {
		fileSaver.saveLog(getState().getNetCommandList(), ".xml", "long");
	}


	public void invokeSaveShortLogAsXmlCommand() {
		fileSaver.saveLog(getState().getCommandStack(), ".xml", "short");
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}

}
