import Player.Person;
import Data.DataManager;
public class Main {
    public static void main(String[] args) {
        // 1) Create a DataManager (which manages the in-memory map and file I/O)
        DataManager manager = new DataManager();

        // 2) Create Person objects
        Person alice = new Person("Alice");
        alice.setScore(100);

        Person bob = new Person("Bob");
        bob.setScore(80);

        // 3) Add them to the manager
        manager.addPerson(alice);
        manager.addPerson(bob);

        // 4) Save the scores to JSON (creates or overwrites 'scores.json')
        manager.saveScores();

        // 5) (Optional) Load the file back to verify
        manager.loadScores();

        // 6) Print out all the players
        System.out.println("Players after loading:");
        manager.getAllPlayers().forEach((name, person) ->
                System.out.println("Name: " + name + ", Score: " + person.getScore())
        );
    }
}