package commands;

import static sbcc.Core.*;

import calculator.*;

public class SubtractCommand extends BinaryCommand {

	public SubtractCommand(State state) {
		super(state);
		setSymbol("-");
	}


	@Override
	public void execute() {
		if (getState().nextDigitResetsResultLabel()) {
			// If the user has not entered a new op2 (op1 still remains in the resultLabel).
			println("SubtractCommand: Enter something else");
			return;
		}

		setOp2(Double.parseDouble(getState().getResultLabel().getText()));
		setResult(getOp1() - getOp2());
		printf("Executing SubtractCommand: op1 = %f, op2 = %f, result = %f\n", getOp1(), getOp2(), getResult());

		// State object's nextDigitResetsResultLabel (boolean) is set to false once the
		// next digit is entered. This happens in the Ui.

		getState().setWaiting(false);
		getState().setWaitingCommand(null);
		getState().getCommandStack().add(this);
		getState().getNetCommandList().add(this);
		updateResultLabel();
	}

}
