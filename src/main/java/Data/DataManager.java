package Data;

import Player.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String PLAYERS_FILE_NAME = "scores.json";
    private static final String HISTORY_FILE_NAME = "history.json";
    private final Map<String, Person> players = new HashMap<>();
    private final Map<String, Integer> gameHistory = new HashMap<>();
    private static int bestScore;

    // Setters
    public void addPerson(Person person) {
        players.put(person.getName(), person);
    }
    public void addGameHistory(String name, int score){gameHistory.put(name, score); }
    public void setBestScore(int bestScore) { DataManager.bestScore = bestScore; }

    // Getters
    public Person getPerson(String name) { return players.get(name); }
    public Map<String, Person> getAllPlayers() { return players; }
    public Map<String, Integer> getGameHistory() { return gameHistory; }
    public int getBestScore() {
        bestScore = 0;
        for (Person p : players.values()){
            if (p.getScore() > bestScore){
                setBestScore(p.getScore());
            }
        }
        return bestScore;
    }
    public Person getBestPlayer() {
        int bestScore = 0;
        Person bestplayer = null;
        for (Person p : players.values()){
            if (p.getScore() > bestScore){
                bestplayer = p;
            }
        }
        return bestplayer;
    }

    // Load the map from JSON
    public void loadFile() {
        File file1 = new File(PLAYERS_FILE_NAME);
        File file2 = new File(HISTORY_FILE_NAME);
        ObjectMapper mapper1 = new ObjectMapper();
        ObjectMapper mapper2 = new ObjectMapper();
        if (!file1.exists() || !file2.exists()) {
            return;
        }
        try {
            Map<String, Person> loaded1 = mapper1.readValue(
                    file1,
                    new TypeReference<Map<String, Person>>() {}
            );
            players.clear();
            players.putAll(loaded1);
            for (Person p : players.values()){
                addPerson(p);
            }
            Map<String, Integer> loaded2 = mapper2.readValue(
                    file2,
                    new TypeReference<Map<String, Integer>>() {}
            );
            gameHistory.clear();
            gameHistory.putAll(loaded2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save the map to JSON
    public void saveFile() {
        ObjectMapper mapper1 = new ObjectMapper();
        ObjectMapper mapper2 = new ObjectMapper();
        try {
            mapper1.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(PLAYERS_FILE_NAME), players);
            mapper2.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(HISTORY_FILE_NAME), gameHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
