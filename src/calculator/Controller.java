package calculator;

import logging.LogSaver;

import java.util.LinkedList;

/**
 * The Controller invokes all Commands and also controls the LogSaver.
 * Everything is passed directly from the Ui to the Controller, and the
 * Controller determines what to do with it. All Commands are safe, and interact
 * with the model to complete themselves. Additionally, Commands need the
 * current displayValue to be created/executed - this argument is passed down
 * through the Ui to the Controller and on to the Commands.
 * <p>
 * The only "thinking" that the Controller does is to filter the buttons pressed
 * by the user in the Ui. The Controller will do things such as limit the length
 * of the displayValue, or not display a decimal if the current displayValue
 * already contains a decimal.
 */
public class Controller {

    private Model model;
    private CommandFactory commandFactory = new CommandFactory();
    private LogSaver LogSaver = new LogSaver();


    public Controller(Model state) {
        setModel(state);
    }


    public void invokeClearCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                   LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Execute is called within the constructor of unary commands
        commandFactory.createCommand("Clear", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeUndoCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                  LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Execute is called within the constructor of unary commands
        commandFactory.createCommand("Undo", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeEqualsCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                    LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Execute is called within the constructor of unary commands
        commandFactory.createCommand("Equals", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeToggleSignCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                        LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Execute is called within the constructor of unary commands
        commandFactory.createCommand("ToggleSign", getModel(), currentOperationChangedListeners,
                displayChangedListeners, displayValue);
    }


    public void invokeAddCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                 LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Waiting Commands are finished in the constructor of BinaryCommands
        commandFactory.createCommand("Add", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeSubtractCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                      LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Waiting Commands are finished in the constructor of BinaryCommands
        commandFactory.createCommand("Subtract", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeMultiplyCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                      LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Waiting Commands are finished in the constructor of BinaryCommands
        commandFactory.createCommand("Multiply", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void invokeDivideCommand(LinkedList<CurrentOperationChangedListener> currentOperationChangedListeners,
                                    LinkedList<DisplayValueChangedListener> displayChangedListeners, String displayValue) {
        // Waiting Commands are finished in the constructor of BinaryCommands
        commandFactory.createCommand("Divide", getModel(), currentOperationChangedListeners, displayChangedListeners,
                displayValue);
    }


    public void do_decimalBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                              String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "0.";
        } else if (!displayValue.contains(".")) {
            displayValue = displayValue + ".";
        }
        // Else: ResultLabel contains a '.', so do nothing. This also protects
        // Double.parseDouble() - which is used by commands - because passing it the
        // argument "." throws an InvalidNumberFormatException.

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_zeroBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                           String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "0";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "0";
        }
        // Else: ResultLabel already shows "0" or "-0"

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_oneBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                          String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "1";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "1";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-1";
        } else {
            // ResultLabel shows "0"
            displayValue = "1";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_twoBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                          String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "2";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "2";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-2";
        } else {
            // ResultLabel shows "0"
            displayValue = "2";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_threeBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                            String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "3";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "3";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-3";
        } else {
            // ResultLabel shows "0"
            displayValue = "3";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_fourBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                           String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "4";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "4";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-4";
        } else {
            // ResultLabel shows "0"
            displayValue = "4";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_fiveBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                           String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "5";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "5";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-5";
        } else {
            // ResultLabel shows "0"
            displayValue = "5";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_sixBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                          String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "6";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "6";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-6";
        } else {
            // ResultLabel shows "0"
            displayValue = "6";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_sevenBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                            String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "7";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "7";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-7";
        } else {
            // ResultLabel shows "0"
            displayValue = "7";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_eightBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                            String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "8";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "8";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-8";
        } else {
            // ResultLabel shows "0"
            displayValue = "8";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void do_nineBtn_actionPerformed(LinkedList<DisplayValueChangedListener> displayChangedListeners,
                                           String displayValue) {
        if (getModel().nextDigitResetsResultLabel()) {
            getModel().setNextDigitResetsResultLabel(false);
            displayValue = "9";
        } else if (!displayValue.matches("-?0")) {
            displayValue = displayValue + "9";
        } else if (displayValue.startsWith("-")) {
            // ResultLabel shows "-0"
            displayValue = "-9";
        } else {
            // ResultLabel shows "0"
            displayValue = "9";
        }

        for (DisplayValueChangedListener d : displayChangedListeners) {
            d.updateDisplayValue(displayValue);
        }
    }


    public void invokeSaveFullLogAsTxtCommand() {
        LogSaver.saveLog(getModel().getNetCommandList(), ".txt", "long");
    }


    public void invokeSaveShortLogAsTxtCommand() {
        LogSaver.saveLog(getModel().getCommandStack(), ".txt", "short");
    }


    public void invokeSaveFullLogAsXmlCommand() {
        LogSaver.saveLog(getModel().getNetCommandList(), ".xml", "long");
    }


    public void invokeSaveShortLogAsXmlCommand() {
        LogSaver.saveLog(getModel().getCommandStack(), ".xml", "short");
    }


    public Model getModel() {
        return model;
    }


    public void setModel(Model model) {
        this.model = model;
    }

}
