package logging;

import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SaveAsXmlStrategy extends LogSavingStrategy {

    @Override
    public void saveLog(List<? extends Command> commandContainer, String filename, String shortOrLong) {
        StringBuilder sb = new StringBuilder();

        sb.append("<" + filename + " - Calculator Log>\n");
        for (int index = 0; index < commandContainer.size(); index++) {
            sb.append(String.format("\t<Expression %s>%s</Expression %s>%n", String.valueOf(index + 1),
                    commandContainer.get(index).toString(), String.valueOf(index + 1)));
        }
        sb.append("</" + filename + " - Calculator Log>\n");

        File outputFile = getFileForLog(filename);

        try {
            PrintWriter pw = new PrintWriter(outputFile);
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
