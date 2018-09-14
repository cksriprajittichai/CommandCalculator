package commands;


import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

public class MultiplyCommand extends BinaryOperation {

    public MultiplyCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                           LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        super(model, currentOperationChangedListeners, displayChangedListeners, initialDisplayValue);
        setSymbol("x");

        notifyCurrentOperationChangedListeners(getSymbol());
    }


    @Override
    public String execute(String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            // If the user has not entered a new op2 (op1 still remains in the resultLabel).
            System.out.println("MultiplyCommand: Enter something else");
            return null;
        }

        setOp2(Double.parseDouble(displayValue));
        setResult(getOp1() * getOp2());
        setResultStr(roundDoubleString(getResult() + ""));
        System.out.printf("Executing MultiplyCommand: op1 = %f, op2 = %f, result = %s\n", getOp1(), getOp2(), getResultStr());

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
