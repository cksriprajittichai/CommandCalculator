package commands;

import static sbcc.Core.*;

import calculator.*;

public class MultiplyCommand extends BinaryCommand {

	public MultiplyCommand(State state) {
		super(state);
		setSymbol("x");
	}


	@Override
	public void execute() {
		if (getState().nextDigitResetsResultLabel()) {
			// If the user has not entered a new op2 (op1 still remains in the resultLabel).
			println("MultiplyCommand: Enter something else");
			return;
		}

		setOp2(Double.parseDouble(getState().getResultLabel().getText()));
		setResult(getOp1() * getOp2());
		printf("Executing MultiplyCommand: op1 = %f, op2 = %f, result = %f\n", getOp1(), getOp2(), getResult());

		// State object's nextDigitResetsResultLabel (boolean) is set to false once the
		// next digit is entered. This happens in the Ui.

		getState().setWaiting(false);
		getState().setWaitingCommand(null);
		getState().getCommandStack().add(this);
		getState().getNetCommandList().add(this);
		updateResultLabel();
	}

}
