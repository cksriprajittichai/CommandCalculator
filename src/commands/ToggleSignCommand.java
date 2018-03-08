package commands;

import calculator.*;

/**
 * Unary commands are not added to the stack.
 */
public class ToggleSignCommand extends Command {

	private String labelVal;


	public ToggleSignCommand(State state) {
		setState(state);
		setSymbol("±");

		if (getState().resultLabelContainsValidInput()) {
			setOp(getState().getResultLabel().getText());
			execute();
		}
	}


	@Override
	public void execute() {
		if (getOp().startsWith("-")) {
			setOp(getOp().substring(1, getOp().length()));
		} else {
			setOp("-" + getOp());
		}
		updateResultLabel();
	}


	@Override
	public void updateResultLabel() {
		// Do not change or round the number at all. For example, "-1." should become
		// "1.".
		getState().getResultLabel().setText(getOp());
	}


	private String getOp() {
		return labelVal;
	}


	private void setOp(String op) {
		this.labelVal = op;
	}

}
