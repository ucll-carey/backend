package be.ucll.da.carey.cityquest.db;

import be.ucll.da.carey.cityquest.model.Coordinate;
import be.ucll.da.carey.cityquest.model.Game;
import be.ucll.da.carey.cityquest.model.Question;

import java.util.ArrayList;
import java.util.Collections;

public class GameInMemoryDb implements GameDb {
    private ArrayList<Game> games = new ArrayList<>();

    public GameInMemoryDb() {
        Question question1 = Question.builder()
                .question("Hoe heet de kathedraal")
                .build();

        ArrayList<Question> questionsLeuven = new ArrayList<>();
        questionsLeuven.add(question1);

        Coordinate coordinateLeuven = Coordinate.builder()
                .latitude(50.8798438)
                .longitude(4.700517600000012)
                .build();

        Game gameLeuven = Game.builder()
                .name("Leuven City Quest")
                .location("Leuven")
                .coordinate(coordinateLeuven)
                .description("City Quest in Leuven")
                .questions(questionsLeuven)
                .build();

        games.add(gameLeuven);
    }

    @Override
    public ArrayList<Game> getAll() {
        return games;
    }
}
