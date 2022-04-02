package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {

    private final MainFrame frame;
    JLabel label;
    JSpinner spRows;
    JSpinner spCols;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        setLayout(new FlowLayout());
        label = new JLabel("Grid size:");
        spRows = new JSpinner(new SpinnerNumberModel(10,2,100,1));
        spCols = new JSpinner(new SpinnerNumberModel(10,2,100,1));
        newGameButton = new JButton("New Game");
        add(label);
        add(spRows);
        add(spCols);
        add(newGameButton);
        newGameButton.addActionListener(this::createGame);
    }

    public int getRows(){
        return (int) spRows.getValue();
    }

    public int getCols(){
        return (int) spCols.getValue();
    }

    public void setRows(int value){
        spRows.setValue(value);
    }

    public void setCols(int value){
        spCols.setValue(value);
    }

    private void createGame(ActionEvent e){
        frame.restart();
    }
}
