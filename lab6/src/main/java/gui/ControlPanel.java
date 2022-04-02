package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class ControlPanel extends JPanel {

    private final MainFrame frame;
    private JButton loadButton;
    private JButton saveButton;
    private JButton exitButton;
    private JButton imageButton;

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        setLayout(new FlowLayout());
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");
        imageButton = new JButton("Image");

        add(loadButton);
        add(saveButton);
        add(exitButton);
        add(imageButton);

        loadButton.addActionListener(this::loadGame);
        saveButton.addActionListener(this::saveGame);
        exitButton.addActionListener(this::exitGame);
        imageButton.addActionListener(this::toImage);
    }

    private void loadGame(ActionEvent e){
        frame.load();
    }

    private void saveGame(ActionEvent e){
        frame.save();
    }

    private void exitGame(ActionEvent e){
        frame.dispose();
    }

    private void toImage(ActionEvent e){
        frame.canvas.toImage();
    }
}
