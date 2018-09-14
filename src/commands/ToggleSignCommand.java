package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

/**
 * Unary commands are not added to the stack.
 */
public class ToggleSignCommand extends Command {

    private String op;


    public ToggleSignCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                             LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        setModel(model);
        setCurrentOperationChangedListeners(currentOperationChangedListeners);
        setDisplayChangedListeners(displayChangedListeners);
        setInitialDisplayValue(initialDisplayValue);
        setSymbol("±");

        if (isValidNumber(getInitialDisplayValue())) {
            execute(initialDisplayValue);
        }
    }


    @Override
    public String execute(String initialDisplayValue) {
        // Don't round the double strings, so that when toggleSign is pressed on "0.00",
        // the result is "-0.00".
        if (initialDisplayValue.startsWith("-")) {
            setOp(initialDisplayValue.substring(1, initialDisplayValue.length()));
            setResultStr(getOp());
        } else {
            setOp("-" + initialDisplayValue);
            setResultStr(getOp());
        }

        notifyDisplayChangedListeners();
        return getResultStr();
    }


    private String getOp() {
        return op;
    }


    private void setOp(String op) {
        this.op = op;
    }

}
