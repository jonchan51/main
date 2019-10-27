package seedu.weme.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.weme.logic.parser.CliSyntax.PREFIX_X_COORDINATE;
import static seedu.weme.logic.parser.CliSyntax.PREFIX_Y_COORDINATE;

import seedu.weme.model.Model;
import seedu.weme.model.template.MemeCreation;
import seedu.weme.model.template.MemeText;

/**
 * Adds a piece of text to the current template.
 */
public class TextAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds text to the current template at the specified "
        + "coordinates.\n"
        + "Parameters: "
        + "TEXT "
        + PREFIX_X_COORDINATE + "X_COORDINATE "
        + PREFIX_Y_COORDINATE + "Y_COORDINATE\n"
        + "Example: " + COMMAND_WORD + " "
        + "CS students be like "
        + PREFIX_X_COORDINATE + "0.2 "
        + PREFIX_Y_COORDINATE + "0.3";

    public static final String MESSAGE_SUCCESS = "New text added at (%s, %s): %s";

    private final MemeText text;

    /**
     * Creates an TextAddCommand to add {@code MemeText} to the meme being created..
     */
    public TextAddCommand(MemeText text) {
        requireNonNull(text);
        this.text = text;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        MemeCreation session = model.getMemeCreation().getValue();
        session.addText(text);
        model.commitWeme();
        return new CommandResult(String.format(MESSAGE_SUCCESS, text.getX(), text.getY(), text.getText()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TextAddCommand // instanceof handles nulls
            && text.equals(((TextAddCommand) other).text));
    }
}

