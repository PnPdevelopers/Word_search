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
        //Setting font name
        Font myFont1 = new Font("Fira Code", Font.BOLD, 40);
        g.setFont(myFont1);
        //Set font color
        g.setColor(black);
        //display the text at the coordinates(x=50, y=150)
        g.drawString("Welcome To WayToLearnX!", 50, 150);
        g.dispose();
        //write the image
        ImageIO.write(image, "png", new File("src/main/java/org/example/outputimage.jpg"));
    }
}