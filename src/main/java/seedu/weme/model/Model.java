package seedu.weme.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.weme.commons.core.GuiSettings;
import seedu.weme.model.meme.Meme;
import seedu.weme.model.template.Template;
import seedu.weme.statistics.LikeData;
import seedu.weme.statistics.Stats;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Meme> PREDICATE_SHOW_ALL_MEMES = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Template> PREDICATE_SHOW_ALL_TEMPLATES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' data file path.
     */
    Path getDataFilePath();

    /**
     * Sets the user prefs' data file path.
     */
    void setDataFilePath(Path dataFilePath);

    /**
     * Returns the user prefs' meme image path.
     */
    Path getMemeImagePath();

    /**
     * Sets the user prefs' meme image path.
     */
    void setMemeImagePath(Path memeImagePath);

    /**
     * Returns the user prefs' template image path.
     */
    Path getTemplateImagePath();

    /**
     * Sets the user prefs' template image path.
     */
    void setTemplateImagePath(Path templateImagePath);

    /**
     * Replaces weme data with the data in {@code weme}.
     */
    void setWeme(ReadOnlyWeme weme);

    /**
     * Returns weme
     */
    ReadOnlyWeme getWeme();

    /**
     * Returns true if a meme with the same identity as {@code meme} exists in weme.
     */
    boolean hasMeme(Meme meme);

    /**
     * Deletes the given meme.
     * The meme must exist in weme.
     */
    void deleteMeme(Meme target);

    /**
     * Adds the given meme.
     * {@code meme} must not already exist in weme.
     */
    void addMeme(Meme meme);

    /**
     * Replaces the given meme {@code target} with {@code editedMeme}.
     * {@code target} must exist in weme.
     * The meme identity of {@code editedMeme} must not be the same as another existing meme in weme.
     */
    void setMeme(Meme target, Meme editedMeme);

    /** Returns an unmodifiable view of the filtered meme list */
    ObservableList<Meme> getFilteredMemeList();

    /**
     * Updates the filter of the filtered meme list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemeList(Predicate<Meme> predicate);

    /** Returns an unmodifiable view of the filtered template list */
    ObservableList<Template> getFilteredTemplateList();

    /**
     * Updates the filter of the filtered template list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTemplateList(Predicate<Template> predicate);

    /**
     * Sets the model context.
     * @param context the context to switch to
     */
    void setContext(ModelContext context);

    /**
     * Returns the context of the model.
     * @return the current context
     */
    ObservableValue<ModelContext> getContext();

    /**
     * Returns true if model has a previous state to restore.
     */
    boolean canUndoWeme();

    /**
     * Returns true if model has a undone state to restore.
     */
    boolean canRedoWeme();

    /**
     * Restores the model's weme to its previous state.
     */
    void undoWeme();

    /**
     * Restores the mode's weme to its previously undone state.
     */
    void redoWeme();

    /**
     * Saves the current weme state for undo/redo.
     */
    void commitWeme();

    /**
     * Returns statistics data.
     */
    Stats getStats();

    /**
     * Returns the like data.
     */
    LikeData getLikeData();

    ObservableMap<String, Integer> getObservableLikeData();

    /**
     * Increments likes of a meme by the Meme object.
     */
    void incrementMemeLikeCount(Meme meme);

    /**
     * Deletes stats data by meme.
     */
    void clearMemeStats(Meme memeToDelete);


    /**
     * Clears the image data folder of any memes that are not referenced in weme.
     */
    void cleanMemeStorage();
}