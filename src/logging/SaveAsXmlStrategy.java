package logging;

import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SaveAsXmlStrategy extends FileSavingStrategy {

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
