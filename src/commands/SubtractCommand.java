package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

public class SubtractCommand extends BinaryOperation {

    public SubtractCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                           LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        // The super constructor of a BinaryOperation finishes waiting BinaryOperations
        // if there are any, and updates initialDisplayValue to the result of the
        // waiting BinaryOperation, if any.
        super(model, currentOperationChangedListeners, displayChangedListeners, initialDisplayValue);
        setPrecedence(0);

        if (isValidNumber(initialDisplayValue)) {
            // If the display value is "error", for example, do not notify the
            // currentOperationChangedListeners.
            setSymbol("-");
            notifyCurrentOperationChangedListeners(getSymbol());
        }
    }


    @Override
    public String execute(String displayValue) {
        setOp2(Double.parseDouble(displayValue));
        setResult(getOp1() - getOp2());
        setResultStr(roundDoubleString(getResult() + ""));
        System.out.printf("Executing SubtractCommand: op1 = %f, op2 = %f, result = %s\n", getOp1(), getOp2(), getResultStr());

        // State object's nextDigitResetsResultLabel (boolean) is set to false once the
        // next digit is entered. This happens in the Ui.

        getModel().setWaiting(false);
        getModel().setWaitingCommand(null);
        getModel().getCommandStack().add(this);
        getModel().getNetCommandList().add(this);
        notifyCurrentOperationChangedListeners(null);
        notifyDisplayChangedListeners();

        return getResultStr();
    }

}
