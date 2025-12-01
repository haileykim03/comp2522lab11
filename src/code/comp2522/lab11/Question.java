package comp2522.lab11;


/**
 * This class stores a question string and its corresponding answer, providing
 * functionality to validate guesses against the stored answer.
 *
 *
 * @author Hailey Kim
 * @author Luis Sebaron
 * @version 1.0
 */
public class Question
{
    private final String question;
    private final String answer;


    /**
     * Constructs a new Question object.
     *
     * @param question the question text to set
     * @param answer the answer text to set
     */
    public Question(final String question,
                    final String answer)
    {
        validateString(question);
        validateString(answer);

        this.question   = question;
        this.answer     = answer.toLowerCase();
    }


    /**
     * Returns the question text.
     *
     * @return the question as a String
     */
    public String getQuestion()
    {
        return question;
    }


    /**
     * Returns the answer text.
     *
     * @return the answer as a String
     */
    public String getAnswer()
    {
        return answer;
    }


    /**
     * Checks if the provided guess matches the answer.
     *
     * @param guess the user's guess to check
     * @return true if the guess matches the answer, false otherwise
     */
    public boolean answerQuestion(final String guess)
    {
        return guess.toLowerCase().equals(answer);
    }


    /**
     * Validates that the provided string is not null or empty.
     *
     * @param toValidate the string to check
     * @throws IllegalArgumentException if the string is null or empty
     */
    private void validateString(final String toValidate)
    {
        if(toValidate == null ||
           toValidate.isEmpty())
        {
            throw new IllegalArgumentException("String cannot be null or empty");
        }
    }


    /**
     * Returns a string representation of the Question object.
     *
     * @return a String containing the question and answer
     */
    @Override
    public String toString()
    {
        return getQuestion() + " " + getAnswer();
    }
}
