package Player;

import Data.DataManager;
import javax.swing.*;
import java.awt.*;

import Main.MainFrame;

public class PlayerPanel extends JPanel {

    private final DataManager dataManager = new DataManager();
    private final JTextField nameField;
    private final JLabel infoLabel;
    private static String whoIsPlaying;

    public String getWhoIsPlaying() {
        return whoIsPlaying;
    }
    public void setWhoIsPlaying(String whoIsPlaying) {
        PlayerPanel.whoIsPlaying = whoIsPlaying;
    }

    public PlayerPanel() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title or instructions
        JLabel promptLabel = new JLabel("Enter your name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(promptLabel, gbc);

        // Name text field
        nameField = new JTextField(15);
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(nameField, gbc);

        // Info label to display messages
        infoLabel = new JLabel(" ");
        infoLabel.setForeground(Color.RED);
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(infoLabel, gbc);

        // Proceed button
        JButton proceedButton = new JButton("Proceed");
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(proceedButton, gbc);

        // Optional: a "Back" or "Cancel" button
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        add(cancelButton, gbc);

        // Button action: validate or create Person
        proceedButton.addActionListener(e -> handleProceed());
        cancelButton.addActionListener(e -> handleCancel());
    }

    private void handleProceed() {
        dataManager.loadFile();
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            infoLabel.setText("Name cannot be empty.");
            return;
        }

        // Check if the person exists
        Person person = dataManager.getPerson(name);
        if (person == null) {
            person = new Person(name);
            dataManager.addPerson(person);
            dataManager.saveFile();
        }
        // If we got here, we have a valid Person
        setWhoIsPlaying(name);
        MainFrame.showGame();
    }

    private void handleCancel() {
        MainFrame.showMenu();
    }
}
