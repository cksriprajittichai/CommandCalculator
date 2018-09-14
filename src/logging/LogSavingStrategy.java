package logging;

import commands.Command;

import java.io.File;
import java.util.List;

public abstract class LogSavingStrategy {

    private StringBuilder contents = new StringBuilder();

    /**
     * If it doesn't already exist, this function creates a folder called
     * "CommandCalculator" in the user's home directory. This function then creates
     * the file to write to.
     *
     * @return File to save the log to.
     */
    protected File getFileForLog(String filename) {
        File dir = new File(LogSaver.path_to_dir);
        File outputFile = new File(LogSaver.path_to_dir + "/" + filename);

        if (!outputFile.exists()) {
            // OuputFile doesn't exist, so create it
            try {
                if (!outputFile.getParentFile().exists()) {
                    dir.mkdirs(); // Make directory
                }
                outputFile.createNewFile();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return outputFile;
    }


    /**
     * Converts the command stack to a log of operations and saves it to a file of
     * type fileExtension.
     *
     * @param commandStack The stack of commands that must be converted to a log.
     * @param filename     The filename to save the log to.
     */
    public abstract void saveLog(List<? extends Command> commandContainer, String filename, String shortOrLong);


    public StringBuilder getContents() {
        return contents;
    }


    public void setContents(StringBuilder contents) {
        this.contents = contents;
    }

}
