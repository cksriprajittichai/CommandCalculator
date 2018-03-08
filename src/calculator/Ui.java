package calculator;

import static sbcc.Core.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import commands.*;

public class Ui {

	private JFrame frame;
	private JPanel panel;
	private JLabel resultLabel;
	private JButton clearBtn;
	private JButton undoBtn;
	private JButton equalsBtn;
	private JButton toggleSignBtn; // Toggle current number sign
	private JButton plusBtn;
	private JButton subtractBtn;
	private JButton multiplyBtn;
	private JButton divideBtn;
	private JButton decimalBtn;
	private JButton zeroBtn;
	private JButton oneBtn;
	private JButton twoBtn;
	private JButton threeBtn;
	private JButton fourBtn;
	private JButton fiveBtn;
	private JButton sixBtn;
	private JButton sevenBtn;
	private JButton eightBtn;
	private JButton nineBtn;

	private State state;
	private Invoker invoker;

	private final Color STEPHENS_YELLOW = new Color(51, 51, 51);


	private void printCurrentState() {
		printf("> resultLabel: %s\n", getState().getResultLabel().getText());
		printf("> isWaiting: %s\n", getState().isWaiting());
		printf("> waitingCommand: %s\n",
				getState().getWaitingCommand() == null ? "null" : getState().getWaitingCommand().getClass());

		int index = 0;
		for (Command c : getState().getCommandStack()) {
			printf(">> Stack index %d: %s\n", index++, c.getClass().toGenericString());
		}
	}


	private void do_clearBtn_actionPerformed(ActionEvent e) {
		invoker.invokeClearCommand();
		printCurrentState();
	}


	private void do_UndoBtn_actionPerformed(ActionEvent e) {
		invoker.invokeUndoCommand();
		printCurrentState();
	}


	private void do_equalsBtn_actionPerformed(ActionEvent e) {
		invoker.invokeEqualsCommand();
		printCurrentState();
	}


	private void do_toggleSignBtn_actionPerformed(ActionEvent e) {
		invoker.invokeToggleSignCommand();
		printCurrentState();
	}


	private void do_plusBtn_actionPerformed(ActionEvent e) {
		invoker.invokeAddCommand();
		printCurrentState();
	}


	private void do_minusBtn_actionPerformed(ActionEvent e) {
		invoker.invokeSubtractCommand();
		printCurrentState();
	}


	private void do_multiplyBtn_actionPerformed(ActionEvent e) {
		invoker.invokeMultiplyCommand();
		printCurrentState();
	}


	private void do_divideBtn_actionPerformed(ActionEvent e) {
		invoker.invokeDivideCommand();
		printCurrentState();
	}


	private void do_decimalBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("0.");
		} else if (!getResultLabel().getText().contains(".")) {
			getResultLabel().setText(getResultLabel().getText() + ".");
		}
		// Else: ResultLabel contains a '.', so do nothing. This also protects
		// Double.parseDouble() - which is used by commands - because passing it the
		// argument "." throws an InvalidNumberFormatException.
	}


	private void do_zeroBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("0");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "0");
		}
		// Else: ResultLabel already shows "0" or "-0"
	}


	private void do_oneBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("1");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "1");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-1");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("1");
		}
	}


	private void do_twoBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("2");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "2");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-2");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("2");
		}
	}


	private void do_threeBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("3");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "3");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-3");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("3");
		}
	}


	private void do_fourBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("4");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "4");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-4");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("4");
		}
	}


	private void do_fiveBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("5");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "5");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-5");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("5");
		}
	}


	private void do_sixBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("6");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "6");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-6");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("6");
		}
	}


	private void do_sevenBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("7");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "7");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-7");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("7");
		}
	}


	private void do_eightBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("8");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "8");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-8");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("8");
		}
	}


	private void do_nineBtn_actionPerformed(ActionEvent e) {
		if (getState().nextDigitResetsResultLabel()) {
			getState().setNextDigitResetsResultLabel(false);
			getResultLabel().setText("9");
		} else if (!getResultLabel().getText().matches("-?0")) {
			getResultLabel().setText(getResultLabel().getText() + "9");
		} else if (getResultLabel().getText().startsWith("-")) {
			// ResultLabel shows "-0"
			getResultLabel().setText("-9");
		} else {
			// ResultLabel shows "0"
			getResultLabel().setText("9");
		}
	}


	private void do_saveFullLogAsXmlMenuItem_actionPerformed(ActionEvent e) {
		invoker.invokeSaveFullLogAsXmlCommand();
	}


	private void do_saveShortLogAsXmlMenuItem_actionPerformed(ActionEvent e) {
		invoker.invokeSaveShortLogAsXmlCommand();
	}


	private void do_saveFullLogAsTxtMenuItem_actionPerformed(ActionEvent e) {
		invoker.invokeSaveFullLogAsTxtCommand();
	}


	private void do_saveShortLogAsTxtMenuItem_actionPerformed(ActionEvent e) {
		invoker.invokeSaveShortLogAsTxtCommand();
	}


	public Invoker getInvoker() {
		return invoker;
	}


	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}


	public JLabel getResultLabel() {
		return resultLabel;
	}


	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	private void do_quitMenuItem_actionPerformed(ActionEvent e) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // Exits program
	}


	public Ui() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(443, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setFont(new Font("Arial", Font.PLAIN, 18));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		resultLabel = new JLabel();
		resultLabel.setText("0");
		resultLabel.setBorder(new EmptyBorder(3, 8, 0, 40));
		resultLabel.setBackground(STEPHENS_YELLOW);
		resultLabel.setOpaque(true);
		resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		resultLabel.setForeground(new Color(255, 255, 255));
		resultLabel.setFont(new Font("Arial", Font.PLAIN, 72));
		resultLabel.setBounds(0, 0, 443, 96);
		panel.add(resultLabel);

		clearBtn = new JButton("ac");
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_clearBtn_actionPerformed(e);
			}
		});
		clearBtn.setBorderPainted(false);
		clearBtn.setOpaque(true);
		clearBtn.setForeground(STEPHENS_YELLOW);
		clearBtn.setFont(new Font("Arial", Font.PLAIN, 26));
		clearBtn.setFocusable(false);
		clearBtn.setDoubleBuffered(true);
		clearBtn.setBackground(new Color(255, 255, 255));
		clearBtn.setBounds(10, 108, 96, 96);
		panel.add(clearBtn);

		// undoBtn = new JButton("⤺"); // \u293A
		undoBtn = new JButton("undo");
		undoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_UndoBtn_actionPerformed(e);
			}
		});
		undoBtn.setBorderPainted(false);
		undoBtn.setOpaque(true);
		undoBtn.setForeground(STEPHENS_YELLOW);
		undoBtn.setFont(new Font("Arial", Font.PLAIN, 26));
		undoBtn.setFocusable(false);
		undoBtn.setDoubleBuffered(true);
		undoBtn.setBackground(new Color(255, 255, 255));
		undoBtn.setBounds(118, 108, 96, 96);
		panel.add(undoBtn);

		equalsBtn = new JButton("=");
		equalsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_equalsBtn_actionPerformed(e);
			}
		});
		equalsBtn.setBorderPainted(false);
		equalsBtn.setOpaque(true);
		equalsBtn.setForeground(STEPHENS_YELLOW);
		equalsBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		equalsBtn.setFocusable(false);
		equalsBtn.setDoubleBuffered(true);
		equalsBtn.setBackground(new Color(255, 204, 0));
		equalsBtn.setBounds(334, 540, 96, 96);
		panel.add(equalsBtn);

		toggleSignBtn = new JButton("±");
		toggleSignBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_toggleSignBtn_actionPerformed(e);
			}
		});
		toggleSignBtn.setBorderPainted(false);
		toggleSignBtn.setOpaque(true);
		toggleSignBtn.setForeground(STEPHENS_YELLOW);
		toggleSignBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		toggleSignBtn.setFocusable(false);
		toggleSignBtn.setDoubleBuffered(true);
		toggleSignBtn.setBackground(new Color(255, 255, 255));
		toggleSignBtn.setBounds(226, 108, 96, 96);
		panel.add(toggleSignBtn);

		plusBtn = new JButton("+");
		plusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_plusBtn_actionPerformed(e);
			}
		});
		plusBtn.setBorderPainted(false);
		plusBtn.setOpaque(true);
		plusBtn.setForeground(STEPHENS_YELLOW);
		plusBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		plusBtn.setFocusable(false);
		plusBtn.setDoubleBuffered(true);
		plusBtn.setBackground(new Color(255, 204, 0));
		plusBtn.setBounds(334, 432, 96, 96);
		panel.add(plusBtn);

		subtractBtn = new JButton("-");
		subtractBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_minusBtn_actionPerformed(e);
			}
		});
		subtractBtn.setOpaque(true);
		subtractBtn.setForeground(UIManager.getColor("Button.foreground"));
		subtractBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		subtractBtn.setFocusable(false);
		subtractBtn.setDoubleBuffered(true);
		subtractBtn.setBorderPainted(false);
		subtractBtn.setBackground(new Color(255, 204, 0));
		subtractBtn.setBounds(334, 324, 96, 96);
		panel.add(subtractBtn);

		multiplyBtn = new JButton("x");
		multiplyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_multiplyBtn_actionPerformed(e);
			}
		});
		multiplyBtn.setBorderPainted(false);
		multiplyBtn.setOpaque(true);
		multiplyBtn.setForeground(STEPHENS_YELLOW);
		multiplyBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		multiplyBtn.setFocusable(false);
		multiplyBtn.setDoubleBuffered(true);
		multiplyBtn.setBackground(new Color(255, 204, 0));
		multiplyBtn.setBounds(334, 216, 96, 96);
		panel.add(multiplyBtn);

		divideBtn = new JButton("÷");
		divideBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_divideBtn_actionPerformed(e);
			}
		});
		divideBtn.setBorderPainted(false);
		divideBtn.setOpaque(true);
		divideBtn.setForeground(STEPHENS_YELLOW);
		divideBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		divideBtn.setFocusable(false);
		divideBtn.setDoubleBuffered(true);
		divideBtn.setBackground(new Color(255, 204, 0));
		divideBtn.setBounds(334, 108, 96, 96);
		panel.add(divideBtn);

		decimalBtn = new JButton(".");
		decimalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_decimalBtn_actionPerformed(e);
			}
		});
		decimalBtn.setBorderPainted(false);
		decimalBtn.setOpaque(true);
		decimalBtn.setForeground(STEPHENS_YELLOW);
		decimalBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		decimalBtn.setFocusable(false);
		decimalBtn.setDoubleBuffered(true);
		decimalBtn.setBackground(Color.WHITE);
		decimalBtn.setBounds(118, 540, 96, 96);
		panel.add(decimalBtn);

		zeroBtn = new JButton(" 0");
		zeroBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_zeroBtn_actionPerformed(e);
			}
		});
		zeroBtn.setHorizontalAlignment(SwingConstants.LEFT);
		zeroBtn.setBorderPainted(false);
		zeroBtn.setOpaque(true);
		zeroBtn.setForeground(STEPHENS_YELLOW);
		zeroBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		zeroBtn.setFocusable(false);
		zeroBtn.setDoubleBuffered(true);
		zeroBtn.setBackground(Color.WHITE);
		zeroBtn.setBounds(16, 540, 96, 96);
		panel.add(zeroBtn);

		oneBtn = new JButton("1");
		oneBtn.setBorderPainted(false);
		oneBtn.setOpaque(true);
		oneBtn.setFocusable(false);
		oneBtn.setDoubleBuffered(true);
		oneBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		oneBtn.setForeground(STEPHENS_YELLOW);
		oneBtn.setBackground(new Color(255, 255, 255));
		oneBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_oneBtn_actionPerformed(e);
			}
		});
		oneBtn.setBounds(10, 432, 96, 96);
		panel.add(oneBtn);

		twoBtn = new JButton("2");
		twoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_twoBtn_actionPerformed(e);
			}
		});
		twoBtn.setBorderPainted(false);
		twoBtn.setOpaque(true);
		twoBtn.setForeground(STEPHENS_YELLOW);
		twoBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		twoBtn.setFocusable(false);
		twoBtn.setDoubleBuffered(true);
		twoBtn.setBackground(Color.WHITE);
		twoBtn.setBounds(118, 432, 96, 96);
		panel.add(twoBtn);

		threeBtn = new JButton("3");
		threeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_threeBtn_actionPerformed(e);
			}
		});
		threeBtn.setBorderPainted(false);
		threeBtn.setOpaque(true);
		threeBtn.setForeground(STEPHENS_YELLOW);
		threeBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		threeBtn.setFocusable(false);
		threeBtn.setDoubleBuffered(true);
		threeBtn.setBackground(Color.WHITE);
		threeBtn.setBounds(226, 432, 96, 96);
		panel.add(threeBtn);

		fourBtn = new JButton("4");
		fourBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_fourBtn_actionPerformed(e);
			}
		});
		fourBtn.setBorderPainted(false);
		fourBtn.setOpaque(true);
		fourBtn.setForeground(STEPHENS_YELLOW);
		fourBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		fourBtn.setFocusable(false);
		fourBtn.setDoubleBuffered(true);
		fourBtn.setBackground(Color.WHITE);
		fourBtn.setBounds(10, 324, 96, 96);
		panel.add(fourBtn);

		fiveBtn = new JButton("5");
		fiveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_fiveBtn_actionPerformed(e);
			}
		});
		fiveBtn.setBorderPainted(false);
		fiveBtn.setOpaque(true);
		fiveBtn.setForeground(STEPHENS_YELLOW);
		fiveBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		fiveBtn.setFocusable(false);
		fiveBtn.setDoubleBuffered(true);
		fiveBtn.setBackground(Color.WHITE);
		fiveBtn.setBounds(118, 324, 96, 96);
		panel.add(fiveBtn);

		sixBtn = new JButton("6");
		sixBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_sixBtn_actionPerformed(e);
			}
		});
		sixBtn.setBorderPainted(false);
		sixBtn.setOpaque(true);
		sixBtn.setForeground(STEPHENS_YELLOW);
		sixBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		sixBtn.setFocusable(false);
		sixBtn.setDoubleBuffered(true);
		sixBtn.setBackground(Color.WHITE);
		sixBtn.setBounds(226, 324, 96, 96);
		panel.add(sixBtn);

		sevenBtn = new JButton("7");
		sevenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_sevenBtn_actionPerformed(e);
			}
		});
		sevenBtn.setBorderPainted(false);
		sevenBtn.setOpaque(true);
		sevenBtn.setForeground(STEPHENS_YELLOW);
		sevenBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		sevenBtn.setFocusable(false);
		sevenBtn.setDoubleBuffered(true);
		sevenBtn.setBackground(Color.WHITE);
		sevenBtn.setBounds(10, 216, 96, 96);
		panel.add(sevenBtn);

		eightBtn = new JButton("8");
		eightBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_eightBtn_actionPerformed(e);
			}
		});
		eightBtn.setBorderPainted(false);
		eightBtn.setOpaque(true);
		eightBtn.setForeground(STEPHENS_YELLOW);
		eightBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		eightBtn.setFocusable(false);
		eightBtn.setDoubleBuffered(true);
		eightBtn.setBackground(Color.WHITE);
		eightBtn.setBounds(118, 216, 96, 96);
		panel.add(eightBtn);

		nineBtn = new JButton("9");
		nineBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_nineBtn_actionPerformed(e);
			}
		});
		nineBtn.setBorderPainted(false);
		nineBtn.setOpaque(true);
		nineBtn.setForeground(STEPHENS_YELLOW);
		nineBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		nineBtn.setFocusable(false);
		nineBtn.setDoubleBuffered(true);
		nineBtn.setBackground(Color.WHITE);
		nineBtn.setBounds(226, 216, 96, 96);
		panel.add(nineBtn);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Arial", Font.PLAIN, 18));
		menuBar.add(menuFile);

		JMenu saveFullLogMenu = new JMenu("Save full log as");
		saveFullLogMenu.setFont(new Font("Arial", Font.PLAIN, 18));
		menuFile.add(saveFullLogMenu);

		JMenu saveShortLogMenu = new JMenu("Save short log as");
		saveShortLogMenu.setFont(new Font("Arial", Font.PLAIN, 18));
		menuFile.add(saveShortLogMenu);

		JMenuItem saveFullLogAsTxtMenuItem = new JMenuItem("TXT");
		saveFullLogAsTxtMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_saveFullLogAsTxtMenuItem_actionPerformed(e);
			}
		});
		saveFullLogAsTxtMenuItem.setFont(new Font("Arial", Font.PLAIN, 14));
		saveFullLogMenu.add(saveFullLogAsTxtMenuItem);

		JMenuItem saveShortLogAsTxtMenuItem = new JMenuItem("TXT");
		saveShortLogAsTxtMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_saveShortLogAsTxtMenuItem_actionPerformed(e);
			}
		});
		saveShortLogAsTxtMenuItem.setFont(new Font("Arial", Font.PLAIN, 14));
		saveShortLogMenu.add(saveShortLogAsTxtMenuItem);

		JMenuItem saveFullLogAsXmlMenuItem = new JMenuItem("XML");
		saveFullLogAsXmlMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_saveFullLogAsXmlMenuItem_actionPerformed(e);
			}
		});
		saveFullLogAsXmlMenuItem.setFont(new Font("Arial", Font.PLAIN, 14));
		saveFullLogMenu.add(saveFullLogAsXmlMenuItem);

		JMenuItem saveShortLogAsXmlMenuItem = new JMenuItem("XML");
		saveShortLogAsXmlMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_saveShortLogAsXmlMenuItem_actionPerformed(e);
			}
		});
		saveShortLogAsXmlMenuItem.setFont(new Font("Arial", Font.PLAIN, 14));
		saveShortLogMenu.add(saveShortLogAsXmlMenuItem);

		menuFile.add(new JSeparator()); // Draw line in menuBar between file saving item and quit item

		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_quitMenuItem_actionPerformed(e);
			}
		});
		quitMenuItem.setFont(new Font("Arial", Font.PLAIN, 18));
		menuFile.add(quitMenuItem);

		// Must be initialized after resultLabel!
		setState(new State(resultLabel));
		setInvoker(new Invoker(getState()));
	}


	public void startApplication() {
		frame.setVisible(true);
	}

}
