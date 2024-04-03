package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));

        exitBtn.addActionListener(this::exitGame);

        add(loadBtn);
        add(exitBtn);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
// ...TODO
}
