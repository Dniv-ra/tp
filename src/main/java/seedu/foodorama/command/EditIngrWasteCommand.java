package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows the user to edit the wastage of an ingredient.
 * Format: edit ingr waste [INGR_NAME]/[INDEX]
 *
 * @author renzocanare
 */
public class EditIngrWasteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("EditIngrCommand");
    private static final Ui UI = new Ui();

    EditIngrWasteCommand() {
        LoggerManager.setupLogger(LOGGER);
    }

    /**
     * User command to edit the wastage of an ingredient in the ingredient list.
     * Parameters can either accept the [INGR_NAME] of the ingredient to be edited
     * or the [INDEX] of the ingredient in the ingredient list.
     * The method checks if the [INGR_INDEX] is an integer and is out of bounds of the size of the ingredient list
     * or if the [INGR_NAME] doesn't exists in the list and throws an exception.
     *
     * <p>If no exceptions are thrown, the user is prompted to provide the new wastage for the ingredient.</p>
     *
     * @param parameters contains the [INGR_NAME] or [INGR_INDEX] of the ingredient to edit wastage of
     * @throws FoodoramaException if [INGR_NAME] doesn't exist in the ingredient list or if [INDEX] is not an integer,
     * [INDEX] is an integer that's out of bounds
     *
     * @author renzocanare
     */
    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        LOGGER.log(Level.INFO, "Start of process");
        String ingr = parameters.get(0);
        int ingrIndex;
        if (isNumber(ingr)) {
            if (isInteger(ingr)) {
                ingrIndex = Integer.parseInt(parameters.get(0)) - 1;
                LOGGER.log(Level.INFO, "Parameter is Integer " + ingrIndex);
            } else {
                throw new FoodoramaException(UI.getInvalidIndexMsg());
            }
        } else if (!isNumber(ingr) & ingr.isEmpty()) {
            LOGGER.log(Level.INFO, "Parameter is Empty");
            throw new FoodoramaException(UI.getIngrIndexMissingMsg());
        } else {
            LOGGER.log(Level.INFO, "Parameter is String '" + ingr + "'");
            ingrIndex = IngredientList.find(ingr);
        }
        if (!isNumber(ingr) & ingrIndex == -1) {
            LOGGER.log(Level.INFO, "Ingredient does not exist");
            throw new FoodoramaException(UI.getIngrNotExistMsg());
        } else if (ingrIndex < 0 || ingrIndex >= IngredientList.ingredientList.size()) {
            LOGGER.log(Level.INFO, "Ingredient Index is not within Ingredient List Range");
            throw new FoodoramaException(UI.getIngrIndexExceedSizeMsg());
        } else {
            assert (ingrIndex != -1) : "The dishIndex cannot be -1";
            try {
                IngredientList.editWastage(ingrIndex);
            } catch (FoodoramaException e) {
                throw new FoodoramaException(e.getMessage());
            }
        }
        LOGGER.log(Level.INFO, "Ingredient Name edited.");
        LOGGER.log(Level.INFO, "End of process.");
    }

    /**
     * Checks if given string can be converted into a number
     * @param numberString string to be checked
     * @return true if string can be converted into a double, false otherwise
     *
     * @author Rakesh12000
     */
    public boolean isNumber(String numberString) {
        try {
            double number = Double.parseDouble(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if given string can be converted into an integer
     * @param numberString string to be checked
     * @return true if string can be converted into an integer, false otherwise
     *
     * @author Dniv-ra
     */
    public boolean isInteger(String numberString) {
        if (isNumber(numberString)) {
            double number = Double.parseDouble(numberString);
            return Math.rint(number) - number == 0;
        } else {
            return false;
        }
    }
}
