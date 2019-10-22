package seedu.weme.model.util;

import static seedu.weme.commons.util.FileUtil.MESSAGE_READ_FILE_FAILURE;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.weme.commons.util.FileUtil;
import seedu.weme.model.MemeBook;
import seedu.weme.model.ReadOnlyMemeBook;
import seedu.weme.model.ReadOnlyUserPrefs;
import seedu.weme.model.imagePath.ImagePath;
import seedu.weme.model.meme.Description;
import seedu.weme.model.meme.Meme;
import seedu.weme.model.tag.Tag;
import seedu.weme.model.template.Name;
import seedu.weme.model.template.Template;
import seedu.weme.statistics.StatsEngine;
import seedu.weme.statistics.StatsManager;

/**
 * Contains utility methods for populating {@code MemeBook} with sample data.
 */
public class SampleDataUtil {
    public static List<Meme> getSampleMemes(ReadOnlyUserPrefs userPrefs) {
        // sample memes from resources folder
        List<MemeFieldsContainer> memeFields = List.of(
            new MemeFieldsContainer("memes/5642dc30-927c-4e02-805d-831ea16bc68e.png",
                    "A meme about doge.", "doge"), // doge
            new MemeFieldsContainer("memes/74b9fc9f-a545-4bbc-98d5-09596a9166a9.jpg",
                    "A meme about Char and charmander.", "charmander"), // charmander
            new MemeFieldsContainer("memes/8de6b9f5-32a5-4eab-aebe-f47c2257e7d5.png",
                    "A meme about joker.", "joker"), // joker
            new MemeFieldsContainer("memes/ab6e1ed6-6025-4e84-b5da-8555ef1e0b05.png",
                    "A meme about toy.", "toy", "jokes"), // toy
            new MemeFieldsContainer("memes/b3afd215-8746-4113-aa19-1747d3578f41.jpg",
                    "A meme about a test.", "test") // test
        );
        return createSampleMemes(memeFields, userPrefs);
    }

    public static List<Template> getSampleTemplates(ReadOnlyUserPrefs userPrefs) {
        // sample templates from resources folder
        List<TemplateFieldsContainer> templateFields = List.of(
            new TemplateFieldsContainer("Drake Reaction",
                "templates/e2493713-6904-4530-98d1-eedc7fd88e5d.jpg"),
            new TemplateFieldsContainer("Is This",
                "templates/0b4cc6ed-85b5-4ca0-a6b2-95ba5d29d06a.jpg"),
            new TemplateFieldsContainer("Quiz Kid",
                "templates/51460170-ef3e-41ad-8243-d0890e838cff.jpg")
        );
        return createSampleTemplates(templateFields, userPrefs);
    }

    /**
     * Copies meme images from Resource folder to the Data folder.
     * @param memeFields the data for the memes in the resource folder
     * @param userPrefs the user preferences for this instance of weme
     * @return a List of Memes to import
     */
    public static List<Meme> createSampleMemes(List<MemeFieldsContainer> memeFields, ReadOnlyUserPrefs userPrefs) {
        ClassLoader classLoader = SampleDataUtil.class.getClassLoader();
        List<Meme> copiedMemes = new ArrayList<>();

        for (MemeFieldsContainer fields : memeFields) {
            String path = fields.getImagePath();
            Path newPath = userPrefs.getMemeImagePath().resolve(FileUtil.getFileName(path));
            try {
                FileUtil.copy(classLoader.getResourceAsStream(path), newPath);
            } catch (FileAlreadyExistsException e) {
                // let the file pass
            } catch (IOException e) {
                throw new IllegalArgumentException(MESSAGE_READ_FILE_FAILURE);
            }
            copiedMemes.add(new Meme(new ImagePath(newPath.toString()),
                    new Description(fields.getDescription()), getTagSet(fields.getTags())));
        }
        return copiedMemes;
    }

    /**
     * Copies template images from Resource folder to the Data folder.
     * @param templateFields the data for the templates in the resource folder
     * @param userPrefs the user preferences for this instance of weme
     * @return a List of {@code Templates} to import
     */
    public static List<Template> createSampleTemplates(List<TemplateFieldsContainer> templateFields,
                                                       ReadOnlyUserPrefs userPrefs) {
        ClassLoader classLoader = SampleDataUtil.class.getClassLoader();
        List<Template> copiedTemplates = new ArrayList<>();

        for (TemplateFieldsContainer fields : templateFields) {
            String path = fields.getImagePath();
            Path newPath = userPrefs.getTemplateImagePath().resolve(FileUtil.getFileName(path));
            try {
                FileUtil.copy(classLoader.getResourceAsStream(path), newPath);
            } catch (FileAlreadyExistsException e) {
                // let the file pass
            } catch (IOException e) {
                throw new IllegalArgumentException(MESSAGE_READ_FILE_FAILURE);
            }
            copiedTemplates.add(new Template(new Name(fields.getName()), new ImagePath(newPath.toString())));
        }
        return copiedTemplates;
    }

    public static ReadOnlyMemeBook getSampleMemeBook(ReadOnlyUserPrefs userPrefs) {
        MemeBook sampleMb = new MemeBook();
        for (Meme sampleMeme : getSampleMemes(userPrefs)) {
            sampleMb.addMeme(sampleMeme);
        }
        for (Template sampleTemplate : getSampleTemplates(userPrefs)) {
            sampleMb.addTemplate(sampleTemplate);
        }
        return sampleMb;
    }

    public static StatsEngine getSampleStatsData(ReadOnlyUserPrefs userPrefs) {
        StatsEngine statsEngine = new StatsManager();
        return statsEngine;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Container class for sample meme data
     */
    private static class MemeFieldsContainer {
        private String imagePath;
        private String description;
        private String[] tags;

        public MemeFieldsContainer(String imagePath, String description, String... tags) {
            this.imagePath = imagePath;
            this.description = description;
            this.tags = tags;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getDescription() {
            return description;
        }

        public String[] getTags() {
            return tags;
        }
    }

    /**
     * Container class for sample template data
     */
    private static class TemplateFieldsContainer {
        private String name;
        private String imagePath;

        public TemplateFieldsContainer(String name, String imagePath) {
            this.name = name;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getImagePath() {
            return imagePath;
        }

    }
}