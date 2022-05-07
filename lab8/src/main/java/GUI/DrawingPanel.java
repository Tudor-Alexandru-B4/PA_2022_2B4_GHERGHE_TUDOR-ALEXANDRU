package GUI;

import DAO.CityDAO;
import others.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DrawingPanel extends JPanel {

    private final static double RADIUS_MAJOR = 6378137.0;
    private final static double RADIUS_MINOR = 6356752.3142;
    private List<CountryInfo> infoList;

    public DrawingPanel(){
        setInfoList();
        init();
    }

    private void init(){
        setOpaque(false);
        try {
            BufferedImage bigm = ImageIO.read(new File("target/worldImage.jpg"));
            setSize(bigm.getWidth(), bigm.getHeight() + 20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i = 0; i < infoList.size(); i++){
            if(!infoList.get(i).getName().equals("London")){
                g.setColor(Color.RED);
                System.out.println(xAxisProjection(infoList.get(i).getLatitude()) + " = " + yAxisProjection(infoList.get(i).getLongitude()));
                g.fillOval(xAxisProjection(infoList.get(i).getLongitude()), yAxisProjection(infoList.get(i).getLatitude()),10,10);
            }
        }
    }

    public void setInfoList() {
        CityDAO city = new CityDAO(new Database());
        try {
            this.infoList = city.getCountryInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int xAxisProjection(double input) {
        return (int)( (input + 180)*(getWidth()/360) );
    }

    int yAxisProjection(double input) {
        var rad = input*Math.PI/180;
        var proj = Math.log(Math.tan( (Math.PI/4)+(rad/2) ));
        return (int)( (getHeight()/2)-(getWidth()*proj/(2*Math.PI)) );
    }
}
