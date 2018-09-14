package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

public abstract class Command {

    private Model model;
    private String symbol;
    private String initialDisplayValue;
    private String resultStr;
    private LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners;
    private LinkedList<DisplayValueChangedListener> displayChangedListeners;


    /**
     * @param displayValue The parameter needed to complete this Command.
     * @return The displayValue after execution.
     */
    public abstract String execute(String displayValue);


    /**
     * Checks for valid float
     */
    public boolean isValidNumber(String s) {
        if (s != null && s.matches("-?\\d*\\.?\\d*")) {
            return true;
        } else {
            return false;
        }
    }


    public String roundDoubleString(String s) {
        if (isValidNumber(s)) {
            Double parseDouble = Double.parseDouble(s);

            if (parseDouble % 1 == 0) {
                s = "" + (long) Double.parseDouble(s);
            } else {
                s = "" + parseDouble;
            }
        }
        // If the resultString isn't a valid number, don't do anything.

        return s;
    }


    public void notifyCurrentOperationChangedListeners(String currentOperatorSymbol) {
        for (CurrentOperationChangedListener c : getCurrentOperationChangedListeners()) {
            c.updateCurrentCommandDisplay(currentOperatorSymbol);
        }
    }


    public void notifyDisplayChangedListeners() {
        for (DisplayValueChangedListener d : getDisplayChangedListeners()) {
            d.updateDisplayValue(getResultStr());
        }
    }


    public Model getModel() {
        return model;
    }


    public void setModel(Model model) {
        this.model = model;
    }


    public String getSymbol() {
        return symbol;
    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public LinkedList<DisplayValueChangedListener> getDisplayChangedListeners() {
        return displayChangedListeners;
    }


    public void setDisplayChangedListeners(LinkedList<DisplayValueChangedListener> displayChangedListeners) {
        this.displayChangedListeners = displayChangedListeners;
    }


    public LinkedList<CurrentOperationChangedListener> getCurrentOperationChangedListeners() {
        return currentOperationChangedListeners;
    }


    public void setCurrentOperationChangedListeners(
            LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners) {
        this.currentOperationChangedListeners = currentOperationChangedListeners;
    }


    public String getResultStr() {
        return resultStr;
    }


    public void setResultStr(String result) {
        this.resultStr = result;
    }


    public String getInitialDisplayValue() {
        return initialDisplayValue;
    }


    public void setInitialDisplayValue(String initialDisplayValue) {
        this.initialDisplayValue = initialDisplayValue;
    }

}
