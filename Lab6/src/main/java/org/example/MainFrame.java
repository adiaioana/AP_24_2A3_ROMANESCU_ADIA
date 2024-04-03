package org.example;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this,10,10);
        add(canvas,BorderLayout.CENTER);
        configPanel=new ConfigPanel(this);
        controlPanel=new ControlPanel(this);
        add(configPanel,BorderLayout.PAGE_START);
        add(controlPanel,BorderLayout.AFTER_LAST_LINE);
        pack();
    }
}
