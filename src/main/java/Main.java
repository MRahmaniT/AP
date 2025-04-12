import Player.Person;
import Data.DataManager;
public class Main {
    public static void main(String[] args) {

        DataManager manager = new DataManager();
        manager.loadScores();

        Person ali = new Person("Ali");
        ali.setScore(100);
        manager.addPerson(ali);

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