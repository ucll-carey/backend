package be.ucll.da.carey.cityquest.model;

import lombok.*;

import java.util.ArrayList;

@Data
public class Game {
    private String name;
    private String description;
    private String city;
    private Coordinate cityCoordinate;
    private ArrayList<Question> questions;

    @java.beans.ConstructorProperties({"name", "description", "city", "cityCoordinate", "questions"})
    Game(String name, String description, String city, Coordinate cityCoordinate, ArrayList<Question> questions) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.cityCoordinate = cityCoordinate;
        this.questions = questions;
    }

    public static GameBuilder builder() {
        return new GameBuilder();
    }

    public static class GameBuilder {
        private String name;
        private String description;
        private String city;
        private Coordinate cityCoordinate;
        private ArrayList<Question> questions;

        GameBuilder() {
        }

        public Game.GameBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Game.GameBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Game.GameBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Game.GameBuilder cityCoordinate(Coordinate cityCoordinate) {
            this.cityCoordinate = cityCoordinate;
            return this;
        }

        public Game.GameBuilder questions(ArrayList<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Game build() {
            assert (name != null && description != null && city != null && cityCoordinate != null);
            return new Game(name, description, city, cityCoordinate, questions);
        }

        public String toString() {
            return "Game.GameBuilder(name=" + this.name + ", description=" + this.description + ", city=" + this.city + ", cityCoordinate=" + this.cityCoordinate + ", questions=" + this.questions + ")";
        }
    }
}
