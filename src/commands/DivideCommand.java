package commands;

import static sbcc.Core.*;

import java.util.*;

import calculator.*;

public class DivideCommand extends BinaryOperation {

	public DivideCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
			LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
		super(model, currentOperationChangedListeners, displayChangedListeners, initialDisplayValue);
		setSymbol("÷");

		notifyCurrentOperationChangedListeners(getSymbol());
	}


	@Override
	public String execute(String displayValue) {
		if (getModel().nextDigitResetsResultLabel()) {
			// If the user has not entered a new op2 (op1 still remains in the resultLabel).
			println("DivideCommand: Enter something else");
			return null;
		}

		// Guard against division by 0
		setOp2(Double.parseDouble(displayValue));
		if (getOp2() == 0) {

			getModel().setWaiting(false);
			getModel().setWaitingCommand(null);
			getModel().setNextDigitResetsResultLabel(true);
			setResultStr(roundDoubleString("error: ÷ by 0"));

			// Do not add errors to the commandStack, but add them to the netCommandList.
			getModel().getNetCommandList().add(this);

			notifyCurrentOperationChangedListeners(null);
			notifyDisplayChangedListeners();

		} else {
			setResult(getOp1() / getOp2());
			setResultStr(roundDoubleString(getResult() + ""));
			printf("Executing DivideCommand: op1 = %f, op2 = %f, result = %s\n", getOp1(), getOp2(), getResultStr());

			// Model object's nextDigitResetsResultLabel (boolean) is set to false once the
			// next digit is entered. This happens in the Ui.

			getModel().setWaiting(false);
			getModel().setWaitingCommand(null);
			getModel().getCommandStack().add(this);
			getModel().getNetCommandList().add(this);
			notifyCurrentOperationChangedListeners(null);
			notifyDisplayChangedListeners();
		}

		return getResultStr();
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
