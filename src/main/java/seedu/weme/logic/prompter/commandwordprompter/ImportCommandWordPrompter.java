package seedu.weme.logic.prompter.commandwordprompter;

import static seedu.weme.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.weme.logic.prompter.util.PrompterUtil.IMPORT_COMMANDS;
import static seedu.weme.logic.prompter.util.PrompterUtil.findSimilarStrings;

import seedu.weme.logic.prompter.Prompter;
import seedu.weme.logic.prompter.exceptions.PromptException;
import seedu.weme.logic.prompter.prompt.CommandPrompt;
import seedu.weme.model.Model;

/**
 * Prompt possible command word under import context.
 */
public class ImportCommandWordPrompter implements Prompter {
    @Override
    public CommandPrompt prompt(Model model, String arguments) throws PromptException {
        String similarCommands = findSimilarStrings(IMPORT_COMMANDS, arguments);
        if (similarCommands.isEmpty()) {
            throw new PromptException(MESSAGE_UNKNOWN_COMMAND);
        }
        return new CommandPrompt(similarCommands);
    }
}
