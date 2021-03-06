package seedu.weme.logic.parser.commandparser.memecommandparser;

import static seedu.weme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.weme.commons.core.index.Index;
import seedu.weme.logic.commands.memecommand.MemeDislikeCommand;
import seedu.weme.logic.parser.Parser;
import seedu.weme.logic.parser.exceptions.ParseException;
import seedu.weme.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new MemeLikeCommand object
 */
public class MemeDislikeCommandParser implements Parser<MemeDislikeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MemeDislikeCommand
     * and returns a MemeDislikeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MemeDislikeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MemeDislikeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemeDislikeCommand.MESSAGE_USAGE), pe);
        }
    }

}
