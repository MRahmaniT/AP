package Game;

import Player.Person;
import Shape.GameShape;
import Shape.RotatingBackground;
import Shape.HexagonShape;
import Shape.HexagonShapeMode1;
import Data.DataManager;
import Player.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer = new Timer(10,this);
    private final List<GameShape> shapes = new ArrayList<>();
    private HexagonShapeMode1 hs1;
    private final DataManager dataManager = new DataManager();
    private final PlayerPanel playerPanel = new PlayerPanel();
    private final Map<String, Person> players;
    private Person whoIsPlaying;
    private JLabel bestScoreLabel;
    private JLabel yourScoreLabel;
    private int bestScore;
    private int scoreCounter = 0;
    private long startScoreCounter = 0;
    private boolean firstEnter = true;

    public GamePanel() {
        setLayout(new BorderLayout());
        dataManager.loadFile();
        players = dataManager.getAllPlayers();

        //Best scoreCounter
        bestScore = dataManager.getBestScore();
        bestScoreLabel = new JLabel("Best Score: " + bestScore,SwingConstants.LEFT);
        bestScoreLabel.setVerticalAlignment(SwingConstants.NORTH);
        bestScoreLabel.setForeground(Color.BLACK);
        bestScoreLabel.setFont(bestScoreLabel.getFont().deriveFont(Font.BOLD, 18f));
        add(bestScoreLabel);

        //Your scoreCounter
        yourScoreLabel = new JLabel("Your Score: " + scoreCounter,SwingConstants.RIGHT);
        yourScoreLabel.setVerticalAlignment(SwingConstants.NORTH);
        yourScoreLabel.setForeground(Color.BLACK);
        yourScoreLabel.setFont(yourScoreLabel.getFont().deriveFont(Font.BOLD, 18f));
        add(yourScoreLabel);

        // Create a rotating background with a large radius (say 1000) and 1 degree per frame
        RotatingBackground bg = new RotatingBackground(1000, 0.03f);
        HexagonShape hs = new HexagonShape(0, 0,10,0.03f);

        shapes.add(bg);
        shapes.add(hs);
        for(int i = 1; i < 101; i++){
             hs1 = new HexagonShapeMode1(0, 0, 200*i, 0.03f, 0.03f);
            shapes.add(hs1);
        }

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
        dataManager.loadFile();
        whoIsPlaying = players.get(playerPanel.getWhoIsPlaying());
        if (whoIsPlaying != null) {
            if (firstEnter) {
                startScoreCounter = System.currentTimeMillis();
                firstEnter = false;
            }
            scoreCounter = (int) (System.currentTimeMillis() - startScoreCounter)/1000;
            whoIsPlaying.setScore(scoreCounter);
        }
        if (scoreCounter > bestScore) {
            dataManager.setBestScore(scoreCounter);
            bestScore = scoreCounter;
        }
        //System.out.println("Best Score: " + bestScore + "Your Score " + scoreCounter);
        bestScoreLabel.setText("Best Score: " + bestScore);
        yourScoreLabel.setText("Your Score: " + scoreCounter);
        add(bestScoreLabel);
        dataManager.saveFile();

        // Update each shape
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (GameShape shape : shapes) {
            shape.update();
            if(shape.getRadius()<10){
                toRemove.add(shapes.indexOf(shape));
            }
        }

        int removed = 0;
        for (int i : toRemove) {
            if(shapes.get(i).getRadius()<10){
                shapes.remove(i-removed);
                removed++;
            }
        }
        repaint();
    }
}
