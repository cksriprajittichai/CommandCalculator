package calculator;

import commands.BinaryOperation;
import commands.Command;

import java.util.LinkedList;
import java.util.Stack;

public class Model {

    private boolean nextDigitResetsResultLabel = false;
    private boolean isWaiting = false;
    private Command waitingCommand;

    // Completed BinaryOperations are added to this stack. The UndoCommand's
    // execute() pops a command from this stack.
    private Stack<BinaryOperation> commandStack = new Stack<BinaryOperation>();

    // This list contains every completed BinaryOperation, as well as UndoCommands.
    private LinkedList<Command> netCommandList = new LinkedList<Command>();


    public Command getWaitingCommand() {
        return waitingCommand;
    }


    public void setWaitingCommand(Command waitingCommand) {
        this.waitingCommand = waitingCommand;
    }


    public boolean isWaiting() {
        return isWaiting;
    }


    public void setWaiting(boolean val) {
        isWaiting = val;
    }


    public boolean nextDigitResetsResultLabel() {
        return nextDigitResetsResultLabel;
    }


    public void setNextDigitResetsResultLabel(boolean nextDigitResetsResultLabel) {
        this.nextDigitResetsResultLabel = nextDigitResetsResultLabel;
    }


    public Stack<BinaryOperation> getCommandStack() {
        return commandStack;
    }


    public LinkedList<Command> getNetCommandList() {
        return netCommandList;
    }

}
