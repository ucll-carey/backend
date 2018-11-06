package be.ucll.da.carey.cityquest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class QuestionTest {

    private static final Coordinate coordinate = Coordinate.builder().latitude(50.8795315).longitude(4.701221700000019).build();
    private static final String questionPhrase = "What's the name of the cathedral of Leuven?";
    private static final ArrayList<String> answers = new ArrayList<>(Arrays.asList("St. Peter's Church", "St. Michael's Church", "Saint Rumbold's Cathedral"));
    private static final String info = "Saint Peter's Church in Leuven, Belgium, is on the city's Grote Markt, opposite the ornate Town Hall. Built mainly in the 15th century in Brabantine Gothic style, the church has a cruciform floor plan and a low bell tower that has never been completed. It is 93 metres long.";

    @Test
    public void questionBuilderWorks() {
        Question question = Question.builder()
                .coordinate(coordinate)
                .question(questionPhrase)
                .answers(answers)
                .correctAnswer(0)
                .answerInfo(info)
                .build();

        assertEquals(questionPhrase, question.getQuestion());
        assertEquals(answers, question.getAnswers());
        assertEquals(0, question.getCorrectAnswer());
        assertEquals(info, question.getAnswerInfo());
        assertEquals(coordinate, question.getCoordinate());
    }

    @Test(expected = AssertionError.class)
    public void emptyCoordinateThrowsException() {
        Question.builder()
                .question(questionPhrase)
                .answers(answers)
                .correctAnswer(0)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void emptyQuestionThrowsException() {
        Question.builder()
                .coordinate(coordinate)
                .answers(answers)
                .correctAnswer(0)
                .build();
    }

    @Test (expected = AssertionError.class)
    public void correctAnswerGreaterThanOptionsThrowsException() {
        Question.builder()
                .coordinate(coordinate)
                .question(questionPhrase)
                .answers(answers)
                .correctAnswer(3)
                .build();
    }


}
