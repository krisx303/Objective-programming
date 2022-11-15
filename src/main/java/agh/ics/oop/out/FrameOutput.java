package agh.ics.oop.out;

import agh.ics.oop.world.IWorldMap;
import agh.ics.oop.world.RectangularMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/** Class responsible for visualizing output using java Swing library.
 *  Because simple IWorldMap interface does not have any information about size of the map or the list of Animals
 *  stored on the map, I decided to use that class only for RectangularMap instances of maps.
 * */
public class FrameOutput implements IOutput{
    JFrame frame;
    RectangularMap map;
    @Override
    public void update() {
        frame.getContentPane().removeAll();
        frame.repaint();
        map.getAnimals().forEach(animal -> {
            JLabel label1 = new JLabel(animal.toString(), SwingConstants.CENTER);
            label1.setSize(40, 40);
            label1.setFont (label1.getFont ().deriveFont (20.0f));
            label1.setBounds(animal.getPosition().x*40, this.map.getSizeVector().y*40-animal.getPosition().y*40, 40, 40);
            label1.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            frame.add(label1);
        });
        frame.revalidate();
        frame.repaint();
        waitASecond();
    }

    private void waitASecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(IWorldMap map) {
        this.map = (RectangularMap) map;
        frame=new JFrame("Animals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(this.map.getSizeVector().x*40+20, this.map.getSizeVector().y*40+80);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}