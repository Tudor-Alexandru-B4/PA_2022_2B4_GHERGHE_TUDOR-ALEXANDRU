package gui;

import objects.Board;
import objects.Stone;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.List;

public class DrawingPanel extends JPanel {

    private final MainFrame frame;
    private Board board;
    private int rows, cols;
    private final int canvasWidth = 400;
    private final int canvasHeight = 400;
    private int boardWidth, boardHeight;
    private int cellWidth, cellHeight;
    private int padX, padY;
    private final int stoneSize = 20;

    public DrawingPanel(MainFrame frame){
        this.frame = frame;
        board = new Board(frame.configPanel.getRows(), frame.configPanel.getCols());
        init(frame.configPanel.getRows(), frame.configPanel.getCols());
    }

    private void init(int rows, int cols){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                board.paintStone(e.getY()/cellWidth, e.getX()/cellHeight);
                repaint();
            }
        });

        setLayout(new FlowLayout());
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    public void resize(MainFrame frame){
        this.rows = frame.configPanel.getRows();
        this.cols = frame.configPanel.getCols();
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        board = new Board(frame.configPanel.getRows(), frame.configPanel.getCols());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }
        //vertical lines
        for(int col = 0; col < cols; col++){
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int x2 = x1;
            int y2 =padY + boardHeight;
            g.drawLine(x1, y1, x2, y2);
        }
        //stones
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
        //sticks
        g.setColor(Color.BLACK);
        int standard = 4;
        for(Map.Entry<Stone, List<Stone>> it : board.getStickMap().entrySet()){
            for(Stone stone : it.getValue()){
                int[] x = new int[4];
                int[] y = new int[4];
                int x1 = it.getKey().getCol() * cellWidth + padX;
                int y1 = it.getKey().getRow() * cellHeight + padY;
                int x2 = stone.getCol() * cellWidth + padX;
                int y2 = stone.getRow() * cellHeight + padY;

                if(x2 < x1){
                    int temp = x1;
                    x1 = x2;
                    x2 = temp;
                    temp = y1;
                    y1 = y2;
                    y2 = temp;
                }

                if(y2 < y1){
                    int temp = y1;
                    y1 = y2;
                    y2 = temp;
                    temp = x1;
                    x1 = x2;
                    x2 = temp;
                }

                if(x1 == x2){
                    x[0] = x1 - standard;
                    x[1] = x1 + standard;
                    x[2] = x2 + standard;
                    x[3] = x2 - standard;
                    y[0] = y[1] = y1 - standard;
                    y[2] = y[3] = y2 + standard;
                }else{
                    x[0] = x[3] = x1 - standard;
                    x[1] = x[2] = x2 + standard;
                    y[0] = y1 + standard;
                    y[1] = y2 + standard;
                    y[2] = y2 - standard;
                    y[3] = y1 - standard;
                }
                g.fillPolygon(x, y, 4);
            }
        }
        //intersections
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                if(Objects.equals(board.getStones()[row][col].getTeam(), "red")){
                    g.setColor(Color.RED);
                    g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                }else if(Objects.equals(board.getStones()[row][col].getTeam(), "blue")){
                    g.setColor(Color.BLUE);
                    g.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
                }
            }
        }
    }

    public void toImage(){
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try{
            ImageIO.write(image, "png", new File("target/image.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Board getBoard(){
        return board;
    }
}
