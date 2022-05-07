package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame(){
        super("DataBase Map");
        init();
    }

    private void init(){
        //printing the world image
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("target/worldImage.jpg")));
        try {
            BufferedImage bigm = ImageIO.read(new File("target/worldImage.jpg"));
            setSize(bigm.getWidth(), bigm.getHeight() + 20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DrawingPanel panel = new DrawingPanel();
        add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
