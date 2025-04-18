package Setting;

import Main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    public static boolean playSound = true;
    public static boolean saveHistory = true;
    private JLabel historyLabel;
    private JLabel musicLabel;
    public SettingPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //Title for music
        musicLabel = new JLabel("Music");
        musicLabel.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(musicLabel, gbc);

        JButton OnButton = new JButton("On");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(OnButton, gbc);

        JButton OffButton = new JButton("Off");
        gbc.gridx = 1;
        add(OffButton, gbc);

        //Title for history
        historyLabel = new JLabel("Save History");
        historyLabel.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(historyLabel, gbc);

        JButton historyOnButton = new JButton("On");
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(historyOnButton, gbc);

        JButton historyOffButton = new JButton("Off");
        gbc.gridx = 1;
        add(historyOffButton, gbc);

        JButton backButton = new JButton("Back to menu");
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(backButton, gbc);

        //Buttons
        OnButton.addActionListener(e -> handleOn());
        OffButton.addActionListener(e -> handleOff());
        historyOnButton.addActionListener(e -> handleHistoryOn());
        historyOffButton.addActionListener(e -> handleHistoryOff());
        backButton.addActionListener(e -> handleBack());
    }

    private void handleOn() {
        if (!playSound){
            musicLabel.setForeground(Color.GREEN);
            MainFrame.musicPlayer.playMusic("music.wav");
            playSound = true;
        }
    }

    private void handleOff() {
        if (playSound){
            musicLabel.setForeground(Color.RED);
            MainFrame.musicPlayer.stopMusic();
            playSound = false;
        }
    }

    private void handleHistoryOn() {
        if (!saveHistory){
            historyLabel.setForeground(Color.GREEN);
            saveHistory = true;
        }
    }

    private void handleHistoryOff() {
        if (saveHistory){
            historyLabel.setForeground(Color.RED);
            saveHistory = false;
        }
    }

    private void handleBack() {
        MainFrame.showMenu();
    }
}
