package comp2522.lab11;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


/**
 *
 *
 * @author Hailey Kim
 * @author Luis Saberon
 * @version 1.0
 */
public class QuizApp extends Application
{
    private static final int SCENE_WIDTH = 400;
    private static final int SCENE_HEIGHT = 500;
    private static final int START_LAYOUT_SPACING = 20;
    private static final int TEXT_FIELD_MAX_WIDTH = 250;
    private static final int GAME_LAYOUT_SPACING = 15;
    private static final int MISSED_LIST_MAX_HEIGHT = 200;
    private static final int MISSED_LIST_MAX_WIDTH = 300;
    private static final int RESULT_LAYOUT_SPACING = 15;

    private Stage primaryStage;
    private Label questionLabel;
    private TextField answerField;
    private Button submitButton;
    private Label scoreLabel;
    private ListView<String> missedQuestionsList;
    private Label finalScoreLabel;

    private int score;
    private List<String> missedQuestions;



    /**
     *
     * @param primaryStage
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
     *
     */
    private void showStartScene()
    {
        final Label welcomeLabel;
        final Button startButton;
        final VBox layout;
        final Scene scene;

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
     *
     */
    private void startNewGame() {
        // TODO: Replace this with actual logic
        showGameScene();
    }


    /**
     *
     */
    private void showGameScene()
    {
        scoreLabel = new Label("Score: 0");
        questionLabel = new Label("Question goes here");
        answerField = new TextField();

        answerField.setPromptText("Type answer...");
        answerField.setMaxWidth(TEXT_FIELD_MAX_WIDTH);

        submitButton = new Button("Submit");

        submitButton.setOnAction(e -> checkAnswer());
        answerField.setOnAction(e -> checkAnswer());

        final VBox layout;
        final Scene scene;

        layout = new VBox(GAME_LAYOUT_SPACING);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(questionLabel, answerField, submitButton, scoreLabel);

        scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        loadStyle(scene);

        primaryStage.setScene(scene);
    }



    private void checkAnswer() {
        // TODO: Replace this with actual logic
        showResultScene();
    }



    private void showResultScene()
    {
        final Label gameOverLabel;
        final Label missedTitle;
        final Button restartButton;

        gameOverLabel = new Label("Game Over!");
        finalScoreLabel = new Label("Final Score: " + score);
        missedTitle = new Label("Missed Questions:");
        restartButton = new Button("Play Again");

        missedQuestionsList = new ListView<>();
        missedQuestionsList.setMaxHeight(MISSED_LIST_MAX_HEIGHT);
        missedQuestionsList.setMaxWidth(MISSED_LIST_MAX_WIDTH);


        // TODO: Replace this with actual logic
        if (missedQuestions != null)
        {
            missedQuestionsList.getItems().addAll(missedQuestions);
        }

        restartButton.setOnAction(e -> showStartScene());

        final VBox layout;
        final Scene scene;

        layout = new VBox(RESULT_LAYOUT_SPACING);

        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gameOverLabel, finalScoreLabel, missedTitle, missedQuestionsList, restartButton);

        scene = new Scene(layout, SCENE_WIDTH, SCENE_HEIGHT);

        loadStyle(scene);

        primaryStage.setScene(scene);
    }



    /**
     *
     * @param scene
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
        catch (final Exception e)
        {
            System.out.println("CSS file not found.");
        }
    }



    /**
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        launch(args);
    }
}