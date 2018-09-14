package commands;

import calculator.CurrentOperationChangedListener;
import calculator.DisplayValueChangedListener;
import calculator.Model;

import java.util.LinkedList;

/**
 * Unary commands are not added to the stack.
 */
public class UndoCommand extends Command {

    String undoneCommandToString;

    /**
     * UndoCommands clear any waiting (unfinished) commands, and returns the state
     * to the most recent result of the last Command from the stack; recall that the
     * stack only contains BinaryCommands.
     *
     * @param model
     */
    public UndoCommand(Model model, LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                       LinkedList<DisplayValueChangedListener> displayChangedListeners, String initialDisplayValue) {
        setModel(model);
        setCurrentOperationChangedListeners(currentOperationChangedListeners);
        setDisplayChangedListeners(displayChangedListeners);
        setInitialDisplayValue(initialDisplayValue);

        setSymbol("⤺");
        execute(initialDisplayValue);
    }


    @Override
    public String execute(String initialDisplayValue) {
        /*
         * CASES / Examples:
         *
         * If the user has completed the expression (3 + 4 = 7), then presses undo, then
         * the current display value is "7". Change the display value to the prior
         * completed expression's result, if any. If not any prior completed expression,
         * then change the display vale to "0".
         *
         * If the user has completed the expressiong (3 + 4 = 7), then enters "26", then
         * the current display value is "726". Do not change the display value to the
         * prior completed expression's result, if any. Instead, change the display
         * value back to "7". Save this UndoCommand to the netCommandList, and set its
         * undoneCommandToString value to the incompleted expression it undid. In this
         * example, the undoneCommandToString value would be set to "726".
         */
        if (getModel().getCommandStack().size() > 1) {

            if (getInitialDisplayValue().equals(getModel().getCommandStack().peek().getResultStr())) {
                setUndoneCommandToString(getModel().getCommandStack().pop().toString());
                BinaryOperation lastCommand = (BinaryOperation) getModel().getCommandStack().peek();
                setResultStr(lastCommand.getResultStr());
            } else {
                if (getModel().isWaiting()) {
                    BinaryOperation wc = (BinaryOperation) getModel().getWaitingCommand();
                    setUndoneCommandToString(String.format("%s %s %s", wc.trimDouble(wc.getOp1()), wc.getSymbol(),
                            getInitialDisplayValue()));

                    getModel().setWaiting(false);
                    getModel().setWaitingCommand(null);
                    getModel().setNextDigitResetsResultLabel(false);
                } else {
                    setUndoneCommandToString(getInitialDisplayValue());
                }

                setResultStr(getModel().getCommandStack().peek().getResultStr());
            }

            getModel().getNetCommandList().add(this);
            notifyCurrentOperationChangedListeners(null);
            notifyDisplayChangedListeners();

        } else if (getModel().getCommandStack().size() == 1) {

            if (getInitialDisplayValue().equals(getModel().getCommandStack().peek().getResultStr())) {
                setUndoneCommandToString(getModel().getCommandStack().pop().toString());
                setResultStr("0");
            } else {
                if (getModel().isWaiting()) {
                    BinaryOperation wc = (BinaryOperation) getModel().getWaitingCommand();
                    setUndoneCommandToString(String.format("%s %s %s", wc.trimDouble(wc.getOp1()), wc.getSymbol(),
                            getInitialDisplayValue()));

                    getModel().setWaiting(false);
                    getModel().setWaitingCommand(null);
                    getModel().setNextDigitResetsResultLabel(false);
                } else {
                    setUndoneCommandToString(getInitialDisplayValue());
                }

                setResultStr(getModel().getCommandStack().peek().getResultStr());
            }

            getModel().getNetCommandList().add(this);
            notifyCurrentOperationChangedListeners(null);
            notifyDisplayChangedListeners();

        } else {

            if (getModel().isWaiting()) {
                BinaryOperation wc = (BinaryOperation) getModel().getWaitingCommand();
                setUndoneCommandToString(String.format("%s %s %s", wc.trimDouble(wc.getOp1()), wc.getSymbol(),
                        getInitialDisplayValue()));

                getModel().setWaiting(false);
                getModel().setWaitingCommand(null);
                getModel().setNextDigitResetsResultLabel(false);

                getModel().getNetCommandList().add(this);
            } else if (!getInitialDisplayValue().equals("0")) {
                setUndoneCommandToString(getInitialDisplayValue());

                getModel().getNetCommandList().add(this);
            }
            // Else: The initialDisplayValue is "0". Don't add this to the netCommandList.

            setResultStr("0");
            notifyCurrentOperationChangedListeners(null);
            notifyDisplayChangedListeners();
        }

        return getResultStr();
    }


    @Override
    public String toString() {
        return String.format("undo (%s)", getUndoneCommandToString());
    }


    public String getUndoneCommandToString() {
        return this.undoneCommandToString;
    }


    public void setUndoneCommandToString(String undoneCommandStr) {
        this.undoneCommandToString = undoneCommandStr;
    }

}
