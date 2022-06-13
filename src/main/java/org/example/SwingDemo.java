package org.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class SwingDemo extends JFrame {
    public SwingDemo() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(550, 300);
    }
    public void paint(Graphics gp) { super.paint(gp); Graphics2D graphics = (Graphics2D) gp;
        Line2D line = new Line2D.Float(200, 150, 150, 220);
        graphics.draw(line);
    }
    public static void main(String[] args) {
        SwingDemo demo = new SwingDemo();
        demo.setVisible(true);
    }
}