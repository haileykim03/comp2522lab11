package comp2522.lab11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Reads from a file of questions, can return those questions as question and answers
 */
public class Questions
{
    private static final Path           QUIZ_FILE;
    private static final Path           DIRS;
    private static final Path           PATH;
    private static final int            QUESTION  = 0;
    private static final int            ANSWER    = 1;
    private static final Random         RAND;
    private static final String         REGEX     = "\\|";
    private final        List<Question> questions;

    static
    {
        QUIZ_FILE = Paths.get("quiz.txt");
        DIRS = Paths.get("src");
        RAND = new Random();
        PATH = DIRS.resolve(QUIZ_FILE);
    }

    public Questions()
    {
        this.questions = readQuestionsFromFile();
    }

    private List<Question> readQuestionsFromFile()
    {
        final List<Question>    questions;
        final List<String>      lines;
        questions = new ArrayList<>();
        try{
            lines =  Files.readAllLines(PATH);
            for(String line : lines)
            {
                final String[] splitLine;
                final Question question;

                splitLine = line.split(REGEX);
                question = new Question(splitLine[QUESTION], splitLine[ANSWER]);
                questions.add(question);
            }
        } catch (final IOException e)
        {
            e.printStackTrace();
        }

        return questions;
    }

    public Question getRandomQuestion()
    {
        final int  index;
        index = RAND.nextInt(questions.size());
        return questions.get(index);
    }
}
