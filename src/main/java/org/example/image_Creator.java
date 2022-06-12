package org.example;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.awt.Color.black;
public class image_Creator {
    public static void drawCharacter(char chara, int xPos, int yPos, String inputImage, String outputImage) throws IOException {
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
            String Schara = Character.toString(chara);
            g.drawString(Schara, xCord, yCord);
            g.dispose();
            //write the image
            ImageIO.write(image, "jpg", new File(outputImage));
        }
    public static void drawArray(char[][] inputArray) throws IOException {
        drawCharacter(inputArray[0][0],0,0,"src/main/java/org/example/white-background-500x500.jpg","src/main/java/org/example/outputImage.jpg");
        for(int i = 0; i < inputArray.length; i++) {
            for(int j = 0; j < inputArray[i].length; j++) {
<<<<<<< HEAD
                drawCharacter(inputArray[i][j],i,j,"src/main/java/org/example/outputImage.jpg","src/main/java/org/example/outputImage.jpg");
=======

>>>>>>> 01ada0029203883f78817b5c4264b1594f9f5882
            }
        }
    }
    }