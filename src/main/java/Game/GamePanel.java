package Game;

import Player.Person;
import Shape.GameShape;
import Shape.RotatingBackground;
import Shape.HexagonShape;
import Data.DataManager;
import Player.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements ActionListener {

    private Timer timer = new Timer(10, this);
    private final List<GameShape> shapes = new ArrayList<>();
    private final DataManager dataManager = new DataManager();
    private final PlayerPanel playerPanel = new PlayerPanel();
    private final Map<String, Person> players;
    private Person whoIsPlaying;
    private JLabel bestScoreLabel;
    private int bestScore;
    private int scoreCounter = 0;

    public GamePanel() {
        dataManager.loadFile();
        players = dataManager.getAllPlayers();

        //Best scoreCounter
        bestScore = dataManager.getBestScore();
        bestScoreLabel = new JLabel("Best Score: " + bestScore);
        bestScoreLabel.setForeground(Color.MAGENTA);
        bestScoreLabel.setFont(bestScoreLabel.getFont().deriveFont(Font.BOLD, 18f));
        add(bestScoreLabel);

        // Create a rotating background with a large radius (say 1000) and 1 degree per frame
        RotatingBackground bg = new RotatingBackground(1000, 0.03f);
        HexagonShape hs = new HexagonShape(0, 0,50,0.03f);
        shapes.add(bg);
        shapes.add(hs);

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // to the panel’s center, then draw.
        Graphics2D g2d = (Graphics2D) g.create();
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2d.translate(cx, cy);

        // Now draw each shape
        for (GameShape shape : shapes) {
            shape.draw(g2d);
        }
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scoreCounter = scoreCounter + 10;
        dataManager.setBestScore(scoreCounter);
        bestScoreLabel.setText("Best Score: " + bestScore);
        whoIsPlaying = players.get(playerPanel.getWhoIsPlaying());
        if (whoIsPlaying != null){
            whoIsPlaying.setScore(scoreCounter);
            System.out.println((scoreCounter)/1000);
        }
        // Update each shape
        for (GameShape shape : shapes) {
            shape.update();
        }
        repaint();
    }
}
