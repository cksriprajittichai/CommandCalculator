package logging;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import commands.*;

public class SaveAsTxtStrategy extends FileSavingStrategy {

	@Override
	public void saveLog(List<? extends Command> commandContainer, String filename, String shortOrLong) {
		File outputFile = getFileForLog(filename);

		try {
			PrintWriter pw = new PrintWriter(outputFile);
			for (int index = 0; index < commandContainer.size(); index++) {
				pw.println(commandContainer.get(index).toString());
			}
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
