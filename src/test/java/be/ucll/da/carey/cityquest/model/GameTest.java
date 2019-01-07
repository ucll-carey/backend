package be.ucll.da.carey.cityquest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class GameTest {

    private static final String name = "City Quest Leuven";
    private static final String description = "City quest in Leuven";
    private static final String location = "Leuven";
    private static final Coordinate coordinate
            = Coordinate.builder().latitude(50.8798438).longitude(4.700517600000012).build();

    private static final Question question = Question.builder().question("What's the name of the cathedral of Leuven?")
            .answers(new ArrayList<>(Arrays.asList("St. Peter's Church", "St. Michael's Church", "Saint Rumbold's Cathedral")))
            .coordinate(coordinate)
            .build();

    private static final ArrayList<Question> questions = new ArrayList<>(Collections.singletonList(question));

    @Test
    public void gameBuilderWorks() {
        Game newGame = Game.builder()
                .name(name)
                .description(description)
                .location(location)
                .coordinates(coordinate)
                .questions(questions)
                .build();

        assertEquals(name, newGame.getName());
        assertEquals(description, newGame.getDescription());
        assertEquals(location, newGame.getLocation());
        assertEquals(coordinate, newGame.getCoordinates());
        assertEquals(questions, newGame.getQuestions());
    }

    @Test(expected = NullPointerException.class)
    public void emptyNameThrowsException() {
        Game.builder()
                .description(description)
                .location(location)
                .coordinates(coordinate)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void emptyDescriptionThrowsException() {
        Game.builder()
                .name(name)
                .location(location)
                .coordinates(coordinate)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void emptyCityThrowsException() {
        Game.builder()
                .name(name)
                .description(description)
                .coordinates(coordinate)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void emptyCoordinateThrowsException() {
        Game.builder()
                .name(name)
                .description(description)
                .location(location)
                .build();
    }


}