package commands;

import static sbcc.Core.*;

import calculator.*;

public class DivideCommand extends BinaryCommand {

	public DivideCommand(State state) {
		super(state);
		setSymbol("÷");
	}


	@Override
	public void execute() {
		if (getState().nextDigitResetsResultLabel()) {
			// If the user has not entered a new op2 (op1 still remains in the resultLabel).
			println("DivideCommand: Enter something else");
			return;
		}

		// Guard against division by 0
		setOp2(Double.parseDouble(getState().getResultLabel().getText()));
		if (getOp2() == 0) {

			// Do not add errors to the stack

			getState().getResultLabel().setText("error: ÷ by 0");
			getState().setWaiting(false);
			getState().setWaitingCommand(null);
			getState().setNextDigitResetsResultLabel(true);

			getState().getNetCommandList().add(this);

			return;
		}

		setResult(getOp1() / getOp2());
		printf("Executing DivideCommand: op1 = %f, op2 = %f, result = %f\n", getOp1(), getOp2(), getResult());

		// State object's nextDigitResetsResultLabel (boolean) is set to false once the
		// next digit is entered. This happens in the Ui.

		getState().setWaiting(false);
		getState().setWaitingCommand(null);
		getState().getCommandStack().add(this);
		getState().getNetCommandList().add(this);
		updateResultLabel();
	}


	@Override
	public String toString() {
		if (getOp2() == 0) {
			return String.format("%s %s %s = error: %s by 0", trimDouble(getOp1()), getSymbol(), trimDouble(getOp2()),
					getSymbol());
		} else {
			return String.format("%s %s %s = %s", trimDouble(getOp1()), getSymbol(), trimDouble(getOp2()),
					trimDouble(getResult()));
		}
	}

}
