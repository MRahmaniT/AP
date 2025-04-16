package Data;

import Player.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String FILE_NAME = "scores.json";
    private final Map<String, Person> players = new HashMap<>();
    private static int bestScore;

    // Setters
    public void addPerson(Person person) {
        players.put(person.getName(), person);
    }
    public void setBestScore(int bestScore) { DataManager.bestScore = bestScore; }

    // Getters
    public Person getPerson(String name) {
        return players.get(name);
    }
    public int getBestScore() {
        bestScore = 0;
        for (Person p : players.values()){
            if (p.getScore() > bestScore){
                setBestScore(p.getScore());
            }
        }
        return bestScore;
    }

    // Get all players
    public Map<String, Person> getAllPlayers() {
        return players;
    }

    // Load the map from JSON
    public void loadFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No existing score file found.");
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Person> loaded = mapper.readValue(
                    file,
                    new TypeReference<Map<String, Person>>() {}
            );
            players.clear();
            players.putAll(loaded);
            for (Person p : players.values()){
                addPerson(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save the map to JSON
    public void saveFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_NAME), players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
