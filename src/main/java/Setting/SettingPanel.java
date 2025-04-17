package Setting;

import Music.SoundPlayer;
import Main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    SoundPlayer soundPlayer = new SoundPlayer();
    public static boolean playSound = true;
    public SettingPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //Title for music
        JLabel promptLabel = new JLabel("Music");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(promptLabel, gbc);

        JButton OnButton = new JButton("On");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(OnButton, gbc);

        JButton OffButton = new JButton("Off");
        gbc.gridx = 1;
        add(OffButton, gbc);

        //Title for history
        JLabel historylable = new JLabel("Save History");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(historylable, gbc);

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
            soundPlayer.playMusic("Music");
            playSound = true;
        }
    }

    private void handleOff() {
        if (playSound){
            soundPlayer.stopMusic();
            playSound = false;
        }
    }

    private void handleHistoryOn() {
        if (!playSound){
            soundPlayer.playMusic("Music");
            playSound = true;
        }
    }

    private void handleHistoryOff() {
        if (playSound){
            soundPlayer.stopMusic();
            playSound = false;
        }
    }

    private void handleBack() {
        MainFrame.showMenu();
    }
}
