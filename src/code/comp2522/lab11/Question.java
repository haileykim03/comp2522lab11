package comp2522.lab11;

/**
 * Models a question and answer
 */
public class Question
{
    private final String question;
    private final String answer;
    public Question(final String question, final String answer)
    {
        validateString(question);
        validateString(answer);

        this.question   = question;
        this.answer     = answer.toLowerCase();
    }

    public String getQuestion()
    {
        return question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public boolean answerQuestion(final String guess)
    {
        return guess.toLowerCase().equals(answer);
    }
    private void validateString(final String toValidate)
    {
        if(toValidate == null ||
           toValidate.isEmpty())
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        return getQuestion() + " " + getAnswer();
    }

}
