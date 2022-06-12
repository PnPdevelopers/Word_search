package org.example;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.awt.Color.black;

public class image_Creator {
    public static void main(String[] args) throws Exception {
        //read the image
        BufferedImage image = ImageIO.read(new File("src/main/java/org/example/white-background-500x500.jpg"));
        //get the Graphics object
        Graphics g = image.getGraphics();
        //set font
        g.setFont(g.getFont().deriveFont(40f));
        g.setColor(black);
        //display the text at the coordinates(x=50, y=150)
        g.drawString("Welcome To WayToLearnX!", 50, 150);
        g.dispose();
        //write the image
        ImageIO.write(image, "png", new File("src/main/java/org/example/outputimage.jpg"));
    }
}