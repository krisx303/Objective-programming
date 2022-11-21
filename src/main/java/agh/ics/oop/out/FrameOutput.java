package agh.ics.oop.out;

import agh.ics.oop.world.IWorldMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FrameOutput implements IOutput{
    JFrame frame;
    IWorldMap map;
    private int x = 12, y = 12;
    private int xOffset = 2, yOffset = 2;
    @Override
    public void update() {
        frame.getContentPane().removeAll();
        frame.repaint();
        map.getMapElements().forEach(animal -> {
            JLabel label1 = new JLabel(animal.toString(), SwingConstants.CENTER);
            label1.setSize(40, 40);
            label1.setFont (label1.getFont ().deriveFont (20.0f));
            label1.setBounds(animal.getPosition().x*40 + 40*xOffset, (-40*yOffset)+y*40-animal.getPosition().y*40, 40, 40);
            label1.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            frame.add(label1);
        });
        frame.revalidate();
        frame.repaint();
        waitASecond();
    }

    private void waitASecond(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(IWorldMap map) {
        this.map = map;
        frame=new JFrame("Animals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(x*40+20, y*40+80);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}