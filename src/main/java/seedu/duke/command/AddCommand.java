package seedu.duke.command;

import seedu.duke.DishList;

import java.util.ArrayList;

public class AddCommand extends Command {
    @Override
    public void execute(ArrayList<String> parameters) {
        switch (parameters.get(0)) {
        case "dish":
            System.out.println("____________________________________________");
            DishList.add(parameters.get(1));
            System.out.println("____________________________________________");
            break;
        default:
            System.out.println("____________________________________________");
            System.out.println("Invalid input");
            System.out.println("____________________________________________");
            break;
        }
    }
}
