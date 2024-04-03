package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    JSpinner spinner;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {

        label = new JLabel("Grid size:");
        spinner = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        newGameButton = new JButton("New Game");
        //newGameButton.addActionListener();

        setLayout(new FlowLayout());
        add(label);
        add(spinner);
        add(spinner);
        add(newGameButton);
    }

}
