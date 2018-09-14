package calculator;

public class Main {

    public static void main(String[] args) {
        try {
            Ui calcUi = new Ui();
            calcUi.startApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
