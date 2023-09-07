import controller.CalculatorController;
import repository.FileReaderManager;
import repository.FileReaderManagerImpl;
import repository.FileWriteManager;
import repository.FileWriteManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.menuMain();

    }
}