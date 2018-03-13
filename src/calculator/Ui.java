package calculator;

import static sbcc.Core.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import commands.*;

public class Ui implements DisplayValueChangedListener, CurrentOperationChangedListener {

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

	private LinkedList<JButton> binaryOperationBtns = new LinkedList<JButton>();

	private Model model;
	private Controller controller;

	private final Color DEFAULT_BTN_FONT_COLOR = new Color(55, 55, 55);
	private final Color OPERATION_BTN_BACKGROUND = new Color(9, 86, 164);


	@Override
	public void updateDisplayValue(String updatedDisplay) {
		getResultLabel().setText(updatedDisplay);
	}


	@Override
	public void updateCurrentCommandDisplay(String commandSymbol) {
		if (commandSymbol == null) {
			// There is no waiting command
			for (JButton b : binaryOperationBtns) {
				b.setBackground(OPERATION_BTN_BACKGROUND);
			}
		} else {
			for (JButton b : binaryOperationBtns) {
				if (b.getText().equals(commandSymbol)) {
					b.setBackground(new Color(107, 115, 130));
				}
			}
		}
	}


	private void do_clearBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeClearCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_UndoBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeUndoCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_equalsBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeEqualsCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_toggleSignBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeToggleSignCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_plusBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeAddCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_minusBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeSubtractCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_multiplyBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeMultiplyCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_divideBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		LinkedList<CurrentOperationChangedListener> tempCurCommandChangedListeners = new LinkedList<CurrentOperationChangedListener>();
		tempCurCommandChangedListeners.add(this);
		getController().invokeDivideCommand(tempCurCommandChangedListeners, tempValChangedListeners,
				getResultLabel().getText());
	}


	private void do_decimalBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_decimalBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_zeroBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_zeroBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_oneBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_oneBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_twoBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_twoBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_threeBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_threeBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_fourBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_fourBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_fiveBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_fiveBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_sixBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_sixBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_sevenBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_sevenBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_eightBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_eightBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_nineBtn_actionPerformed(ActionEvent e) {
		LinkedList<DisplayValueChangedListener> tempValChangedListeners = new LinkedList<DisplayValueChangedListener>();
		tempValChangedListeners.add(this);
		controller.do_nineBtn_actionPerformed(tempValChangedListeners, getResultLabel().getText());
	}


	private void do_saveFullLogAsXmlMenuItem_actionPerformed(ActionEvent e) {
		getController().invokeSaveFullLogAsXmlCommand();
	}


	private void do_saveShortLogAsXmlMenuItem_actionPerformed(ActionEvent e) {
		getController().invokeSaveShortLogAsXmlCommand();
	}


	private void do_saveFullLogAsTxtMenuItem_actionPerformed(ActionEvent e) {
		getController().invokeSaveFullLogAsTxtCommand();
	}


	private void do_saveShortLogAsTxtMenuItem_actionPerformed(ActionEvent e) {
		getController().invokeSaveShortLogAsTxtCommand();
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public JLabel getResultLabel() {
		return resultLabel;
	}


	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
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
		resultLabel.setBackground(DEFAULT_BTN_FONT_COLOR);
		resultLabel.setOpaque(true);
		resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		resultLabel.setForeground(new Color(255, 255, 255));
		resultLabel.setFont(new Font("Arial", Font.PLAIN, 72));
		resultLabel.setBounds(0, 0, 443, 96);
		panel.add(resultLabel);

		clearBtn = new JButton("AC");
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_clearBtn_actionPerformed(e);
			}
		});
		clearBtn.setBorderPainted(false);
		clearBtn.setOpaque(true);
		clearBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		clearBtn.setFont(new Font("Arial", Font.PLAIN, 26));
		clearBtn.setFocusable(false);
		clearBtn.setBorderPainted(true);
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
		undoBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		undoBtn.setFont(new Font("Arial", Font.PLAIN, 26));
		undoBtn.setFocusable(false);
		undoBtn.setBorderPainted(true);
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
		equalsBtn.setForeground(Color.LIGHT_GRAY);
		equalsBtn.setFont(new Font("Arial", Font.PLAIN, 56));
		equalsBtn.setFocusable(false);
		equalsBtn.setDoubleBuffered(true);
		equalsBtn.setBackground(OPERATION_BTN_BACKGROUND);
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
		toggleSignBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		toggleSignBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		toggleSignBtn.setFocusable(false);
		toggleSignBtn.setBorderPainted(true);
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
		plusBtn.setForeground(Color.LIGHT_GRAY);
		plusBtn.setFont(new Font("Arial", Font.PLAIN, 56));
		plusBtn.setFocusable(false);
		plusBtn.setDoubleBuffered(true);
		plusBtn.setBackground(OPERATION_BTN_BACKGROUND);
		plusBtn.setBounds(334, 432, 96, 96);
		panel.add(plusBtn);
		binaryOperationBtns.add(plusBtn);

		subtractBtn = new JButton("-");
		subtractBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_minusBtn_actionPerformed(e);
			}
		});
		subtractBtn.setOpaque(true);
		subtractBtn.setForeground(Color.LIGHT_GRAY);
		subtractBtn.setFont(new Font("Arial", Font.PLAIN, 56));
		subtractBtn.setFocusable(false);
		subtractBtn.setDoubleBuffered(true);
		subtractBtn.setBorderPainted(false);
		subtractBtn.setBackground(OPERATION_BTN_BACKGROUND);
		subtractBtn.setBounds(334, 324, 96, 96);
		panel.add(subtractBtn);
		binaryOperationBtns.add(subtractBtn);

		multiplyBtn = new JButton("x");
		multiplyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_multiplyBtn_actionPerformed(e);
			}
		});
		multiplyBtn.setBorderPainted(false);
		multiplyBtn.setOpaque(true);
		multiplyBtn.setForeground(Color.LIGHT_GRAY);
		multiplyBtn.setFont(new Font("Arial", Font.PLAIN, 56));
		multiplyBtn.setFocusable(false);
		multiplyBtn.setDoubleBuffered(true);
		multiplyBtn.setBackground(OPERATION_BTN_BACKGROUND);
		multiplyBtn.setBounds(334, 216, 96, 96);
		panel.add(multiplyBtn);
		binaryOperationBtns.add(multiplyBtn);

		divideBtn = new JButton("÷");
		divideBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_divideBtn_actionPerformed(e);
			}
		});
		divideBtn.setBorderPainted(false);
		divideBtn.setOpaque(true);
		divideBtn.setForeground(Color.LIGHT_GRAY);
		divideBtn.setFont(new Font("Arial", Font.PLAIN, 56));
		divideBtn.setFocusable(false);
		divideBtn.setDoubleBuffered(true);
		divideBtn.setBackground(OPERATION_BTN_BACKGROUND);
		divideBtn.setBounds(334, 108, 96, 96);
		panel.add(divideBtn);
		binaryOperationBtns.add(divideBtn);

		decimalBtn = new JButton(".");
		decimalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_decimalBtn_actionPerformed(e);
			}
		});
		decimalBtn.setBorderPainted(false);
		decimalBtn.setOpaque(true);
		decimalBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		decimalBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		decimalBtn.setFocusable(false);
		decimalBtn.setBorderPainted(true);
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
		zeroBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		zeroBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		zeroBtn.setFocusable(false);
		zeroBtn.setBorderPainted(true);
		zeroBtn.setDoubleBuffered(true);
		zeroBtn.setBackground(Color.WHITE);
		zeroBtn.setBounds(16, 540, 96, 96);
		panel.add(zeroBtn);

		oneBtn = new JButton("1");
		oneBtn.setBorderPainted(false);
		oneBtn.setOpaque(true);
		oneBtn.setFocusable(false);
		oneBtn.setBorderPainted(true);
		oneBtn.setDoubleBuffered(true);
		oneBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		oneBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
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
		twoBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		twoBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		twoBtn.setFocusable(false);
		twoBtn.setBorderPainted(true);
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
		threeBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		threeBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		threeBtn.setFocusable(false);
		threeBtn.setBorderPainted(true);
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
		fourBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		fourBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		fourBtn.setFocusable(false);
		fourBtn.setBorderPainted(true);
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
		fiveBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		fiveBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		fiveBtn.setFocusable(false);
		fiveBtn.setBorderPainted(true);
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
		sixBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		sixBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		sixBtn.setFocusable(false);
		sixBtn.setBorderPainted(true);
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
		sevenBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		sevenBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		sevenBtn.setFocusable(false);
		sevenBtn.setBorderPainted(true);
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
		eightBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		eightBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		eightBtn.setFocusable(false);
		eightBtn.setBorderPainted(true);
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
		nineBtn.setForeground(DEFAULT_BTN_FONT_COLOR);
		nineBtn.setFont(new Font("Arial", Font.PLAIN, 48));
		nineBtn.setFocusable(false);
		nineBtn.setBorderPainted(true);
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
		setModel(new Model());
		setController(new Controller(getModel()));
	}


	public void startApplication() {
		frame.setVisible(true);
	}

}
