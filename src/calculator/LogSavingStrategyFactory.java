package calculator;

import logging.LogSavingStrategy;

public class LogSavingStrategyFactory {

    /**
     * Create a LogSavingStrategy of type.
     *
     * @param type The type of the LogSavingStrategy to be created. This String
     *             should start with a capital letter - text files, for example,
     *             should have type = "Txt".
     * @return The LogSavingStrategy of type.
     */
    public LogSavingStrategy createLogSavingStrategy(String type) {
        LogSavingStrategy newStrat = null;

        try {
            Class<LogSavingStrategy> tempClass = (Class<LogSavingStrategy>) Class
                    .forName("logging.SaveAs" + type + "Strategy"); // Package.classname
            newStrat = tempClass.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newStrat;
    }

}
