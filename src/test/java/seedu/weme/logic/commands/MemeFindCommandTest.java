package seedu.weme.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.weme.commons.core.Messages.MESSAGE_MEMES_LISTED_OVERVIEW;
import static seedu.weme.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weme.testutil.TypicalWeme.getTypicalWeme;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.weme.logic.commands.memecommand.MemeFindCommand;
import seedu.weme.model.Model;
import seedu.weme.model.ModelManager;
import seedu.weme.model.UserPrefs;
import seedu.weme.model.meme.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code MemeFindCommand}.
 */
public class MemeFindCommandTest {
    private Model model = new ModelManager(getTypicalWeme(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalWeme(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        MemeFindCommand findFirstCommand = new MemeFindCommand(firstPredicate);
        MemeFindCommand findSecondCommand = new MemeFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        MemeFindCommand findFirstCommandCopy = new MemeFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different meme -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMemeFound() {
        String expectedMessage = String.format(MESSAGE_MEMES_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        MemeFindCommand command = new MemeFindCommand(predicate);
        expectedModel.updateFilteredMemeList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMemeList());
    }

    @Test
    public void execute_multipleKeywords_multipleMemesFound() {
        String expectedMessage = String.format(MESSAGE_MEMES_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        MemeFindCommand command = new MemeFindCommand(predicate);
        expectedModel.updateFilteredMemeList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMemeList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
