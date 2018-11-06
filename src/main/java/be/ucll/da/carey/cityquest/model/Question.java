package be.ucll.da.carey.cityquest.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Question {
    private Coordinate coordinate;
    private String question;
    private ArrayList<String> answers;
    private int correctAnswer;
    private String answerInfo;

    @java.beans.ConstructorProperties({"coordinate", "question", "answers", "correctAnswer", "answerInfo"})
    Question(Coordinate coordinate, String question, ArrayList<String> answers, int correctAnswer, String answerInfo) {
        this.coordinate = coordinate;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.answerInfo = answerInfo;
    }

    public static QuestionBuilder builder() {
        return new QuestionBuilder();
    }

    public static class QuestionBuilder {
        private Coordinate coordinate;
        private String question;
        private ArrayList<String> answers;
        private int correctAnswer;
        private String answerInfo;

        QuestionBuilder() {
        }

        public QuestionBuilder coordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public QuestionBuilder question(String question) {
            this.question = question;
            return this;
        }

        public QuestionBuilder answers(ArrayList<String> answers) {
            this.answers = answers;
            return this;
        }

        public QuestionBuilder correctAnswer(int correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public QuestionBuilder answerInfo(String answerInfo) {
            this.answerInfo = answerInfo;
            return this;
        }

        public Question build() {
            assert (coordinate != null && question != null && answers.size() > 0 && 0 <= correctAnswer
                    && correctAnswer < answers.size());
            return new Question(coordinate, question, answers, correctAnswer, answerInfo);
        }

        public String toString() {
            return "Question.QuestionBuilder(coordinate=" + this.coordinate + ", question=" + this.question + ", answers=" + this.answers + ", correctAnswer=" + this.correctAnswer + ", answerInfo=" + this.answerInfo + ")";
        }
    }
}
