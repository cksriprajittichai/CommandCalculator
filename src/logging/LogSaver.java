package logging;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import javax.swing.*;

import commands.*;

/**
 * The Invoker will have a LogSaver, and the LogSaver will have a
 * FileSavingStrategy.
 */
public class LogSaver {

	private FileSavingStrategy currentStrategy;
	private SaveAsTxtStrategy instanceOfTxtStrategy = new SaveAsTxtStrategy();
	private SaveAsXmlStrategy instanceOfXmlStrategy = new SaveAsXmlStrategy();

	public static final String path_to_dir = System.getProperty("user.home") + "/CommandCalculator";
	private static final Character illegal_chars_arr[] = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<',
			'>', '|', '\"', ':' };
	private static final HashSet<Character> illegal_chars_set = new HashSet<>(Arrays.asList(illegal_chars_arr));


	/**
	 * Converts the commandStack OR the netCommandList to a log of operations and
	 * saves it to a file of type fileExtension using the current
	 * FileSavingStrategy. Recall that the commandStack only contains complete
	 * BinaryCommands that have not been undone, and the netCommandList contains all
	 * completed commands, including those that have been undone.
	 * 
	 * The LogSaver creates a folder called "CommandCalculator" in the user's home
	 * directory. It then saves logs to that folder, with incrementing names -
	 * "calculatorLog_04", if 01, 02, and 03 already exist.
	 * 
	 * @param commandStack
	 *            The stack of commands that must be converted to a log.
	 * @param fileExtension
	 *            The extension of the desired file. This determines which strategy
	 *            this LogSaver will use to save the file. This provides the
	 *            "StrategyContext" described by the strategy design pattern.
	 * @param shortOrLong
	 *            Equal to "short" if the user wants to save the commandStack to a
	 *            log, or "full" if the user wants to save the netCommandList to a
	 *            log.
	 */
	public void saveLog(List<? extends Command> commandContainer, String fileExtension, String shortOrLong) {
		boolean exitLoop = false;

		while (exitLoop == false) {
			String filenameOnly = JOptionPane.showInputDialog(null, "Name your "
					+ fileExtension.toUpperCase().substring(1, fileExtension.length()) + " file (exclude extension):",
					"Save As", JOptionPane.QUESTION_MESSAGE);

			if (filenameOnly == null) {
				// Could be null if user enters <esc>
				return;
			}

			int availabilityFlag = filenameIsAvailable(filenameOnly, fileExtension);

			if (availabilityFlag == -1) {
				// Invalid filename
				JOptionPane.showMessageDialog(null, "The filename you entered is invalid", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else if (availabilityFlag == 0) {
				// Filename already exists

				// Yes is 0, no is 1
				int overwriteFlag = JOptionPane.showConfirmDialog(null,
						"A file with this name already exists.\nWould you like to overwrite it?", "Overwrite",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (overwriteFlag == 0) {
					availabilityFlag = 1;
				}
			}

			// Put this branch in a separate statement in case user wants to override
			// already existing file, just change availabilityFlag to 1.
			if (availabilityFlag == 1) {
				// Valid filename
				exitLoop = true;

				if (fileExtension.equals(".txt")) {
					currentStrategy = instanceOfTxtStrategy;
				} else if (fileExtension.equals(".xml")) {
					currentStrategy = instanceOfXmlStrategy;
				}

				currentStrategy.saveLog(commandContainer, filenameOnly + fileExtension, shortOrLong);
			}
		}
	}


	/**
	 * Returns a value corresponding to the availability of a file. This is used by
	 * LogSaver's saveLog().
	 * 
	 * @param filenameOnly
	 *            The name of the file to check on.
	 * @param fileExtension
	 *            The extension (type) of the file to check on.
	 * @return -1 if filename is invalid, 0 if file with filename already exists, 1
	 *         if valid and not taken.
	 */
	private int filenameIsAvailable(String filenameOnly, String fileExtension) {
		if (filenameOnly.length() == 0) {
			return -1;
		}

		String filename = filenameOnly + fileExtension;

		for (char c : filename.toCharArray()) {
			if (illegal_chars_set.contains(c)) {
				return -1;
			}
		}

		// Check if a file with filename already exists
		File dir = new File(path_to_dir);
		if (dir.exists() && dir.isDirectory()) {
			for (String fileInDir : dir.list()) {
				if (filename.equals(fileInDir)) {
					return 0;
				}
			}
		}

		return 1;
	}
}
