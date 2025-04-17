package Data;

import java.time.LocalDateTime;

public class History {
    private String history;

    public History(int score, String date){
        this.history = score + " — " + date;
    }

    public String getHistory() {
        return this.history;
    }

}
