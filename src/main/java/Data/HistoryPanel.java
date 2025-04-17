package Data;

import Data.DataManager;
import Player.Person;
import Main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.Map;

public class HistoryPanel extends JPanel {

    public HistoryPanel(DataManager dataManager) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Best score at the top
        Person bestPlayer = dataManager.getBestPlayer();
        String bestText = (bestPlayer != null)
                ? "🏆 Best Player: " + bestPlayer.getName() + " — " + bestPlayer.getScore()
                : "No scores recorded yet";

        JLabel bestLabel = new JLabel(bestText, SwingConstants.CENTER);
        bestLabel.setFont(bestLabel.getFont().deriveFont(Font.BOLD, 18f));
        bestLabel.setForeground(Color.BLUE);
        add(bestLabel, BorderLayout.NORTH);

        //Scores list
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Map.Entry<String, Integer> entry : dataManager.getGameHistory().entrySet()) {
            JLabel playerLabel = new JLabel(entry.getKey() + " — " + entry.getValue());
            playerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            playerLabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
            listPanel.add(playerLabel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        //Exit
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> MainFrame.showMenu());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
