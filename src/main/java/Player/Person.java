package Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private final String name;
    private int score;

    //Constructor
    @JsonCreator
    public Person(@JsonProperty("name") String name,
                  @JsonProperty("score") int score) {
        this.name = name;
        this.score = score;
    }
    public Person(String name) {
        this.name = name;
        this.score = 0;
    }
    //Getters
    public String getName() { return name; }
    public int getScore() { return score; }

    //Setters
    public void setScore(int newScore) {
        if (newScore > this.score) {
            this.score = newScore;
        }
    }
}
