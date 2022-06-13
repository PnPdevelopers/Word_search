package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Graphics;

import static java.awt.Color.black;

public class word_Highlighter {

    public static void loadImageFrame() throws IOException {
        String path = "src/main/java/org/example/outputImage.jpg";
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        JLabel label = new JLabel(new ImageIcon(image));
        JFrame f = new JFrame();

        f.getContentPane().add(label);
        f.pack();
        f.setLocation(0, 0);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void paint(Graphics g) {
        g.setColor(black);
        g.drawLine(20, 20, 200, 180);
    }
}
