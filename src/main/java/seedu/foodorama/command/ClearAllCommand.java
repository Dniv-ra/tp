package seedu.foodorama.command;

import seedu.foodorama.DishList;
import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearAllCommand extends Command {
    private static final Logger logger = Logger.getLogger("ClearAllCommand.execute()");
    private static final Ui UI = new Ui();

    ClearAllCommand() {
        LoggerManager.setupLogger(logger);
    }

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        logger.log(Level.INFO, "Start of process");
        Scanner input = new Scanner(System.in);
        UI.printConfirmClearAll();
        String confirmClear = input.nextLine().toLowerCase();
        while (!(confirmClear.equals("y") | confirmClear.equals("n"))) {
            UI.clearTerminalAndPrintNewPage();
            UI.printInvalidConfirmation();
            confirmClear = input.nextLine().toLowerCase();
        }
        UI.clearTerminalAndPrintNewPage();
        if (confirmClear.equals("y")) {
            DishList.dishList.clear();
            IngredientList.ingredientList.clear();
            UI.printAllCleared();
            logger.log(Level.INFO, "Successfully cleared both lists");
        } else {
            UI.printDisregardMsg();
        }
        logger.log(Level.INFO, "End of process");
    }
}
