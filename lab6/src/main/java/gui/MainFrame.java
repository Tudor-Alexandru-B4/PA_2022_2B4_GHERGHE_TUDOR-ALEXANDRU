package gui;

import objects.Stone;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

import static java.lang.System.*;

public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Game");
        init();
    }

    private void init(){

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);
        setLayout(new BorderLayout());
        setSize(420, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    public void load(){

        try {
            File file = new File("target/save.txt");
            if(!file.exists()){
                err.println("No save file found!");
                return;
            }
            Scanner reader = new Scanner(file);
            String data[];
            //set game size
            String line = reader.nextLine();
            data = line.split(" ");
            configPanel.setRows(Integer.parseInt(data[0]));
            configPanel.setCols(Integer.parseInt(data[1]));
            restart();
            //set turn
            line = reader.nextLine();
            data = line.split(" ");
            canvas.getBoard().setTurn(Integer.parseInt(data[0]));
            canvas.getBoard().setPlayerTurn(Boolean.parseBoolean(data[1]));
            //set stones
            for(int i = 0; i < configPanel.getRows(); i++){
                line = reader.nextLine();
                data = line.split(" ");
                for(int j = 0; j < configPanel.getCols(); j++){
                    canvas.getBoard().getStones()[i][j].setTeam(data[j]);
                    canvas.getBoard().getStickMap().get(canvas.getBoard().getStones()[i][j]).clear();
                }
            }
            //set sticks
            while(reader.hasNextLine()){
                line = reader.nextLine();
                data = line.split(" ");
                canvas.getBoard().getStickMap().get(canvas.getBoard().getStones()[Integer.parseInt(data[0])][Integer.parseInt(data[1])]).add(canvas.getBoard().getStones()[Integer.parseInt(data[2])][Integer.parseInt(data[3])]);
            }
            reader.close();
        }catch (Exception e){
            err.println("Error reading from file: " + e);
        }

    }

    public void save(){
        try{
            FileWriter writer = new FileWriter("target/save.txt");
            writer.write(configPanel.getRows() + " " + configPanel.getCols() + "\n");
            //write turn
            writer.write(canvas.getBoard().getTurn() + " " + canvas.getBoard().getPlayerTurn() + "\n");
            //writing stone matrix
            for(int i = 0; i < configPanel.getRows(); i++) {
                for(int j = 0; j < configPanel.getCols(); j++){
                    writer.write(canvas.getBoard().getStones()[i][j].getTeam());
                    if(j == configPanel.getCols() - 1){
                        writer.write("\n");
                    }else{
                        writer.write(" ");
                    }
                }
            }
            //write sticks
            for(Map.Entry<Stone, List<Stone>> it : canvas.getBoard().getStickMap().entrySet()) {
                for (Stone stone : it.getValue()) {
                    writer.write(it.getKey().getRow() + " " + it.getKey().getCol() + " " + stone.getRow() + " " + stone.getCol() + "\n");
                }
            }
            writer.close();
        }catch (Exception e){
            err.println("Error writing to file: " + e);
        }
    }

    public void restart(){
        canvas.resize(this);
        repaint();
    }
} 