package comp2522.lab11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * The main application class for the Quiz Game.
 *
 * @author Hailey Kim
 * @author Luis Saberon
 * @version 1.0
 */
public class QuizApp extends Application
{
    private static final int SCENE_WIDTH            = 400;
    private static final int SCENE_HEIGHT           = 500;
    private static final int START_LAYOUT_SPACING   = 20;
    private static final int TEXT_FIELD_MAX_WIDTH   = 250;
    private static final int GAME_LAYOUT_SPACING    = 15;
    private static final int MISSED_LIST_MAX_HEIGHT = 200;
    private static final int MISSED_LIST_MAX_WIDTH  = 300;
    private static final int RESULT_LAYOUT_SPACING  = 15;
    private static final int DEFAULT_VALUE          = 0;
    private static final int       QUESTION_LIMIT   = 10;
    private static final Questions QUESTIONS;


    private Stage            primaryStage;
    private TextField        answerField;
    private Question         nextQuestion;

    private int          score;
    private int          questionsAsked;
    private List<String> missedQuestions;

    static
    {
        QUESTIONS = new Questions();
    }

    /**
     * Sets up the primary stage and displays the initial start scene.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(final Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Quiz App");
        showStartScene();
        primaryStage.show();
    }


    /**
     * Displays the start scene.
     */
    private void showStartScene()
    {
        final Label welcomeLabel;
        final Button startButton;
        final VBox layout;
        final Scene scene;

        nextQuestion = QUESTIONS.getRandomQuestion();
        welcomeLabel = new Label("Press 'Start Quiz' to begin!");
        startButton  = new Button("Start Quiz");
        layout       = new VBox(START_LAYOUT_SPACING);

        startButton.setOnAction(e -> startNewGame());

        scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(welcomeLabel, startButton);

        loadStyle(scene);

        primaryStage.setScene(scene);
    }


    /**
     * Initializes a new game session.
     */
    private void startNewGame()
    {
        questionsAsked  = DEFAULT_VALUE;
        score           = DEFAULT_VALUE;
        missedQuestions = new ArrayList<>();
        showGameScene();
    }


    /**
     * Displays the main game scene.
     * Shows the current question, an input field for the answer, and the current score.
     */
    private void showGameScene()
    {
        final VBox layout;
        final Scene scene;
        final Label questionLabel;
        final Button submitButton;
        final Label scoreLabel;


        scoreLabel    = new Label("Score: " + score);
        questionLabel = new Label(nextQuestion.getQuestion());
        answerField   = new TextField();

        answerField.setPromptText("Type answer...");
        answerField.setMaxWidth(TEXT_FIELD_MAX_WIDTH);

        submitButton = new Button("Submit");

        submitButton.setOnAction(this::handle);
        answerField.setOnAction(this::handle);

        layout = new VBox(GAME_LAYOUT_SPACING);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(questionLabel, answerField, submitButton, scoreLabel);

        scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        loadStyle(scene);

        primaryStage.setScene(scene);
    }


    /**
     * Validates the user's answer and updates the game state.
     * Checks if the input matches the answer, updates the score, and adds wrong answers
     * to the missed list. Proceeds to the next question or the result scene if the limit is reached.
     */
    private void checkAnswer()
    {
        final String input;
        final boolean correct;

        input = answerField.getText();
        System.out.println("This is being called");
        correct = nextQuestion.answerQuestion(input);
        if(correct)
        {
            score++;
            System.out.println("This is right");
        }
        else{
            missedQuestions.add(nextQuestion.toString());
            System.out.println("This is wrong");
        }

        questionsAsked++;
        nextQuestion =  QUESTIONS.getRandomQuestion();

        if(questionsAsked >= QUESTION_LIMIT)
        {
            showResultScene();
        } else {
            showGameScene();
        }

    }


    /**
     * Displays the result scene.
     * Shows the final score, a list of missed questions, and a button to restart the game.
     */
    private void showResultScene()
    {
        final Label gameOverLabel;
        final Label missedTitle;
        final Button restartButton;
        final VBox layout;
        final Scene scene;
        final Label finalScoreLabel;
        final ListView<String> missedQuestionsList;

        gameOverLabel   = new Label("Game Over!");
        finalScoreLabel = new Label("Final Score: " + score);
        missedTitle     = new Label("Missed Questions:");
        restartButton   = new Button("Play Again");

        missedQuestionsList = new ListView<>();
        missedQuestionsList.setMaxHeight(MISSED_LIST_MAX_HEIGHT);
        missedQuestionsList.setMaxWidth(MISSED_LIST_MAX_WIDTH);

        if (missedQuestions != null)
        {
            missedQuestionsList.getItems().addAll(missedQuestions);
        }

        restartButton.setOnAction(e -> showStartScene());



        layout = new VBox(RESULT_LAYOUT_SPACING);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gameOverLabel, finalScoreLabel, missedTitle, missedQuestionsList, restartButton);

        scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        loadStyle(scene);

        primaryStage.setScene(scene);
    }


    /**
     * Loads the CSS stylesheet for the given scene.
     *
     * @param scene the scene to apply the stylesheet to
     */
    private void loadStyle(final Scene scene)
    {
        try
        {
            if (getClass().getResource("styles.css") != null)
            {
                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            }
        }
        catch (final NullPointerException e)
        {
            System.out.println("CSS file not found.");
        }
    }


    /**
     * The main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args)
    {
        launch(args);
    }


    /**
     * Handles ActionEvents from the UI.
     *
     * @param e the ActionEvent triggered by the user
     */
    private void handle(final ActionEvent e)
    {
        checkAnswer();
    }
}