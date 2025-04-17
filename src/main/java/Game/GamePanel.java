package Game;

import Data.History;
import Player.Person;
import Setting.SettingPanel;
import Shape.GameShape;
import Shape.RotatingBackground;
import Shape.HexagonShape;
import Shape.TriangleShape;
import Shape.HexagonShapeMode1;
import Shape.HexagonShapeMode2;
import Shape.CheckCollision;
import Data.DataManager;
import Player.PlayerPanel;
import Main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel implements ActionListener {
    //Frame rate
    private Timer timer = new Timer(10,this);
    //Shapes
    private final List<GameShape> shapes = new ArrayList<>();
    private final List<GameShape> shapesToDraw = new ArrayList<>();
    private HexagonShapeMode1 hs1;
    private HexagonShapeMode2 hs2;
    //Data
    private final DataManager dataManager = new DataManager();
    private History history;
    private String now;
    //Player
    private final PlayerPanel playerPanel = new PlayerPanel();
    private final Map<String, Person> players;
    private Person whoIsPlaying;
    //Score
    private JLabel bestScoreLabel;
    private JLabel yourScoreLabel;
    private int bestScore;
    private int scoreCounter = 0;
    private long startScoreCounter = 0;
    //Game
    private boolean firstEnter = true;
    private boolean gameOver = false;

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
        HexagonShape hs = new HexagonShape(0, 0,20,0.03f);
        TriangleShape ta = new TriangleShape(0, 0,20    ,0.03f);

        shapes.add(bg);
        shapes.add(hs);
        shapes.add(ta);

        for(int i = 1; i < 10000; i++){
            int randomInt = (int)(Math.random() * 2);
            if(randomInt == 1){
                int randomInt1 = (int)(Math.random() * 6);
                hs1 = new HexagonShapeMode1(0, 0, 200*i, 0.03f, 0.03f, randomInt1);
                shapes.add(hs1);
            }
            if(randomInt == 0){
                int randomInt2 = (int)(Math.random() * 2);
                hs2 = new HexagonShapeMode2(0, 0, 200*i, 0.03f, 0.03f, randomInt2);
                shapes.add(hs2);
            }
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
        shapesToDraw.clear();
        for (int i = 1; i < 6; i++){
            shapesToDraw.add(shapes.get(i));
        }
        shapes.get(0).draw(g2d);
        shapes.get(1).draw(g2d);
        shapes.get(2).draw(g2d);
        for (GameShape shape : shapesToDraw) {
            shape.draw(g2d);
        }
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dataManager.loadFile();
        whoIsPlaying = players.get(playerPanel.getWhoIsPlaying());
        if(CheckCollision.checkCollision(shapes.get(2), shapes.get(3))){
            gameOver = true;
        }
        if (gameOver){
            if(SettingPanel.saveHistory){
                history = new History(scoreCounter, now);
                dataManager.addGameHistory(whoIsPlaying.getName(), history);
            }
            dataManager.saveFile();
            timer.stop();
            MainFrame.showGameOver();
        }
        if (whoIsPlaying != null) {
            if (firstEnter) {
                now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                startScoreCounter = System.currentTimeMillis();
                firstEnter = false;
            }
            scoreCounter = (int) (System.currentTimeMillis() - startScoreCounter)/1000;
            whoIsPlaying.setScore(scoreCounter);
            if(SettingPanel.saveHistory){
                history = new History(scoreCounter, now);
                dataManager.addGameHistory(whoIsPlaying.getName(), history);
            }
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

        // Update and remove each shape
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (GameShape shape : shapes) {
            shape.update();
            if(shape.getRadius()<10){
                toRemove.add(shapes.indexOf(shape));
            }
        }
        int removed = 0;
        for (int i : toRemove) {
            if(shapes.get(i).getRadius()<20){
                //Remove the small one
                GameShape shape1 = shapes.get(i-removed);
                shapes.remove(i-removed);
                shapes.add(shape1);
                removed++;

                //Prevention of repetetion
                int randomRemove = (int) (Math.random()*1000);
                GameShape shape2 = shapes.get(randomRemove+10);
                shapes.remove(shape2);
                shapes.add(shape2);
            }
        }
        repaint();
    }
}
