package comp2522.lab11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Reads from a file of questions, can return those questions as question and answers.
 *
 * @author Hailey Kim
 * @author Luis Seberon
 * @version 1.0
 */
public class Questions
{
    private static final Path           QUIZ_FILE = Paths.get("quiz.txt");
    private static final Path           DIRS      = Paths.get("src");
    private static final Path           PATH      = DIRS.resolve(QUIZ_FILE);
    private static final int            QUESTION  = 0;
    private static final int            ANSWER    = 1;
    private static final Random         rand      = new Random();

    private final List<Question>        questions;


    /**
     * Constructs a Questions object.
     */
    public Questions()
    {
        this.questions = readQuestionsFromFile();
    }


    /**
     * Reads lines from the source file and parses them into Question objects.
     *
     * @return a List of Question objects parsed from the file
     */
    private List<Question> readQuestionsFromFile()
    {
        final List<Question>    questions;
        final List<String>      lines;

        questions = new ArrayList<>();

        try
        {
            lines =  Files.readAllLines(PATH);
            for (final String line : lines)
            {
                final String[] splitLine;
                final Question question;

                splitLine = line.split("\\|");
                question = new Question(splitLine[QUESTION], splitLine[ANSWER]);
                questions.add(question);
            }
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        return questions;
    }


    /**
     * Retrieves a random question from the list.
     *
     * @return a randomly selected Question object
     */
    public Question getRandomQuestion()
    {
        final int  index;
        index = rand.nextInt(questions.size());
        return questions.get(index);
    }
}
