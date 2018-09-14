package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

public abstract class BinaryOperation extends Command {

    private double op1;
    private double op2;
    private double result;
    private int precedence; // The operation with higher precedence is executed first.


    /**
     * The first operator is taken from the state when this is constructed. The
     * second operator will be taken from the state when execute() is called.
     *
     * @param model
     */
    public BinaryOperation(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                           LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        setModel(model);
        setCurrentOperationChangedListeners(currentOperationChangedListeners);
        setDisplayChangedListeners(displayChangedListeners);

        if (getModel().isWaiting() && !getModel().nextDigitResetsResultLabel()) {
            // If there is a waiting command, finish it before creating this BinaryCommand.
            // Update the value that is passed to this BinaryCommand.
            setInitialDisplayValue(getModel().getWaitingCommand().execute(initialDisplayValue));
        } else {
            setInitialDisplayValue(initialDisplayValue);
        }

        if (isValidNumber(getInitialDisplayValue())) {
            setOp1(Double.parseDouble(getInitialDisplayValue()));

            model.setWaiting(true);
            model.setWaitingCommand(this);
            model.setNextDigitResetsResultLabel(true);
        }
    }


    @Override
    public String toString() {
        return String.format("%s %s %s = %s", trimDouble(getOp1()), getSymbol(), trimDouble(getOp2()),
                trimDouble(getResult()));
    }


    /**
     * Returns trimmed String representations of doubles. Used in
     * updateResultLabel() and toString().
     *
     * @return A String of the value, with excess 0s trimmed off.
     */
    public String trimDouble(double value) {
        if (value % 1 == 0) {
            return (long) value + "";
        } else {
            return value + "";
        }
    }


    public double getOp1() {
        return op1;
    }


    public void setOp1(double op1) {
        this.op1 = op1;
    }


    public double getOp2() {
        return op2;
    }


    public void setOp2(double op2) {
        this.op2 = op2;
    }


    public double getResult() {
        return result;
    }


    public void setResult(double result) {
        this.result = result;
    }


    public int getPrecedence() {
        return precedence;
    }


    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

}
