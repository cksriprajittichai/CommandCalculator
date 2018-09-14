package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

/**
 * Unary commands are not added to the stack.
 */
public class EqualsCommand extends Command {

    private String initialDisplayValue;

    public EqualsCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                         LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        setModel(model);
        setCurrentOperationChangedListeners(currentOperationChangedListeners);
        setDisplayChangedListeners(displayChangedListeners);
        setInitialDisplayValue(initialDisplayValue);
        setSymbol("=");

        if (getModel().isWaiting()) {
            // The state cannot be waiting if there is invalid input in the
            // result label. Therefore, no need to check if input is valid.

            execute(getInitialDisplayValue());
        }
    }


    @Override
    public String execute(String initialDisplayValue) {
        return getModel().getWaitingCommand().execute(initialDisplayValue);
    }


    @Override
    public String getInitialDisplayValue() {
        return initialDisplayValue;
    }


    @Override
    public void setInitialDisplayValue(String initialDisplayValue) {
        this.initialDisplayValue = initialDisplayValue;
    }

}
