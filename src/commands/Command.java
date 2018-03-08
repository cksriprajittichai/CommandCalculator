package commands;

import java.util.*;

import calculator.*;

/**
 * All Commands will have a reference to the State.
 */
public abstract class Command {

	private State state;
	private String symbol;


	public abstract void execute();


	/**
	 * Updates the label correctly, meaning that this controls length of numbers,
	 * and removes the decimal if the number is an integer.
	 */
	public abstract void updateResultLabel();


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public String getSymbol() {
		return symbol;
	}


	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
