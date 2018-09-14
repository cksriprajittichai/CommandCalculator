package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

public class DivideCommand extends BinaryOperation {

    public DivideCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                         LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        // The super constructor of a BinaryOperation finishes waiting BinaryOperations
        // if there are any, and updates initialDisplayValue to the result of the
        // waiting BinaryOperation, if any.
        super(model, currentOperationChangedListeners, displayChangedListeners, initialDisplayValue);
        setPrecedence(1);

        if (isValidNumber(initialDisplayValue)) {
            // If the display value is "error", for example, do not notify the
            // currentOperationChangedListeners.
            setSymbol("÷");
            notifyCurrentOperationChangedListeners(getSymbol());
        }
    }


    @Override
    public String execute(String displayValue) {
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
            System.out.printf("Executing DivideCommand: op1 = %f, op2 = %f, result = %s\n", getOp1(), getOp2(), getResultStr());

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
