package calculator;

import java.util.*;

import javax.swing.*;

import commands.*;

public class State {

	private JLabel resultLabel;
	private boolean nextDigitResetsResultLabel = false;
	private boolean isWaiting = false;
	private Command waitingCommand;
	private Stack<Command> commandStack = new Stack<Command>(); // Completed BinaryCommands are added to this stack. The
																// UndoCommand's execute() pops a command from this
																// stack.
	private LinkedList<Command> netCommandList = new LinkedList<Command>(); // This list contains every completed
																			// BinaryCommand, as well as UndoCommands.


	public boolean resultLabelContainsValidInput() {
		if (getResultLabel().getText().matches("-?\\d*\\.?\\d*")) {
			return true;
		} else {
			return false;
		}
	}


	public State(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}


	public JLabel getResultLabel() {
		return resultLabel;
	}


	public Command getWaitingCommand() {
		return waitingCommand;
	}


	public void setWaitingCommand(Command waitingCommand) {
		this.waitingCommand = waitingCommand;
	}


	public boolean isWaiting() {
		return isWaiting;
	}


	public void setWaiting(boolean val) {
		isWaiting = val;
	}


	public boolean nextDigitResetsResultLabel() {
		return nextDigitResetsResultLabel;
	}


	public void setNextDigitResetsResultLabel(boolean nextDigitResetsResultLabel) {
		this.nextDigitResetsResultLabel = nextDigitResetsResultLabel;
	}


	public Stack<Command> getCommandStack() {
		return commandStack;
	}


	public LinkedList<Command> getNetCommandList() {
		return netCommandList;
	}

}
