package org.example;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.awt.Color.black;
public class image_Creator {
    public static void drawCharacter(String chara, int xPos, int yPos, String inputImage, String outputImage) throws IOException {
        int xCord = 12 + 40 * xPos+1;
        int yCord = 35 + 40 * yPos+1;
            //read the image
            BufferedImage image = ImageIO.read(new File(inputImage));
            //get the Graphics object
            Graphics g = image.getGraphics();
            //Setting font name
            Font myFont1 = new Font("Fira Code", Font.BOLD, 30);
            g.setFont(myFont1);
            //Set font color
            g.setColor(black);
            //display the text at the coordinates(x=50, y=150)
            g.drawString(chara, xCord, yCord);
            g.dispose();
            //write the image
            ImageIO.write(image, "jpg", new File(outputImage));
        }
    public static void drawArray(char[][] inputArray){
        int inputArraylength = inputArray.length; //number of layers y
        for(int i = 0; i < inputArray.length; i++) {
            for(int j = 0; j < inputArray[i].length; j++) {

            }
        }
    }
    }