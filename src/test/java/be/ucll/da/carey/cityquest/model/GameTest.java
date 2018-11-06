package be.ucll.da.carey.cityquest.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class GameTest {

    private static final String name = "City Quest Leuven";
    private static final String description = "City quest in Leuven";
    private static final String city = "Leuven";
    private static final Coordinate cityCoordinate
            = Coordinate.builder().latitude(50.8798438).longitude(4.700517600000012).build();

    private static final Question question = Question.builder().question("What's the name of the cathedral of Leuven?")
            .answers(new ArrayList<>(Arrays.asList("St. Peter's Church", "St. Michael's Church", "Saint Rumbold's Cathedral")))
            .coordinate(cityCoordinate)
            .build();

    private static final ArrayList<Question> questions = new ArrayList<>(Collections.singletonList(question));

    @Test
    public void gameBuilderWorks() {
        Game newGame = Game.builder()
                .name(name)
                .description(description)
                .city(city)
                .cityCoordinate(cityCoordinate)
                .questions(questions)
                .build();

        assertEquals(name, newGame.getName());
        assertEquals(description, newGame.getDescription());
        assertEquals(city, newGame.getCity());
        assertEquals(cityCoordinate, newGame.getCityCoordinate());
        assertEquals(questions, newGame.getQuestions());
    }

    @Test(expected = AssertionError.class)
    public void emptyNameThrowsException() {
        Game.builder()
                .description(description)
                .city(city)
                .cityCoordinate(cityCoordinate)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void emptyDescriptionThrowsException() {
        Game.builder()
                .name(name)
                .city(city)
                .cityCoordinate(cityCoordinate)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void emptyCityThrowsException() {
        Game.builder()
                .name(name)
                .description(description)
                .cityCoordinate(cityCoordinate)
                .build();
    }

    @Test(expected = AssertionError.class)
    public void emptyCoordinateThrowsException() {
        Game.builder()
                .name(name)
                .description(description)
                .city(city)
                .build();
    }


}