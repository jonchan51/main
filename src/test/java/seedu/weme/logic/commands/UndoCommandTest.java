package seedu.weme.logic.commands;

import static seedu.weme.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.weme.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weme.logic.commands.CommandTestUtil.deleteFirstMeme;
import static seedu.weme.testutil.TypicalWeme.getTypicalWeme;

import org.junit.jupiter.api.Test;

import seedu.weme.model.Model;
import seedu.weme.model.ModelManager;
import seedu.weme.model.UserPrefs;

public class UndoCommandTest {
    private final Model model = new ModelManager(getTypicalWeme(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalWeme(), new UserPrefs());

    @Test
    public void execute() {
        deleteFirstMeme(model);
        deleteFirstMeme(model);

        deleteFirstMeme(expectedModel);
        deleteFirstMeme(expectedModel);

        UndoCommand undoCommand = new UndoCommand();

        expectedModel.undoWeme();

        // multiple undo states
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.undoWeme();

        // single undo states
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undo states
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_FAILURE);
    }
}
