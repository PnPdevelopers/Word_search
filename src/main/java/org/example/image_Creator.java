package org.example;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.RenderedImage;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.awt.Color.black;
import static java.awt.Color.red;

public class image_Creator {
    //drawCharacter method for adding individual characters to an image using the character, it's position in a 2D array, and the input and output image file names
    public static void drawCharacter(char chara, int xPos, int yPos, String inputImage, String outputImage) throws IOException, FontFormatException {
        //define the position of the letter
        int xCord = 24 + 80 * xPos + 1;
        int yCord = 70 + 80 * yPos + 1;
        //read the image
        BufferedImage image = ImageIO.read(new File(inputImage));
        //get the Graphics object
        Graphics g = image.getGraphics();
        //Setting font name
        Font FiraCode = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/org/example/FiraCode-Bold1.ttf")).deriveFont(60f);
        g.setFont(FiraCode);
        //Set font color
        g.setColor(black);
        //display the text at the coordinates
        String Schara = Character.toString(chara);
        g.drawString(Schara, xCord, yCord);
        g.dispose();
        //write the image
        ImageIO.write(image, "jpg", new File(outputImage));
    }

    public static void drawLine(int startX, int startY, int endX, int endY, String inputImage, String outputImage) throws IOException, FontFormatException {
        //System.out.println("("+startX+","+startY+") ("+endX+","+endY+")");
        //define the position of the letter
        int xStart = 24 + 80 * startX + 1;
        //System.out.println("X position of line start " + xStart);
        int yStart = 70 + 80 * startY + 1;
        //System.out.println("Y position of line start " + yStart);
        int xEnd = 24 + 80 * endX + 1;
        //System.out.println("X position of line end " + xEnd);
        int yEnd = 70 + 80 * endY + 1;
        //System.out.println("Y position of line end " + yEnd);
        //read the image
        BufferedImage image = ImageIO.read(new File(inputImage));
        //get the Graphics object
        Graphics g = image.getGraphics();
        image_Creator.paintComponent(g,xStart,yStart,xEnd,yEnd);
        g.dispose();
        //write the image
        ImageIO.write(image, "jpg", new File(outputImage));
    }

    public static void paintComponent(Graphics g, int startX, int startY, int endX, int endY) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(red);
        g2.setStroke(new BasicStroke(40));
        g2.draw(new Line2D.Float(startX, startY, endX, endY));
    }

    //drawArray method to crop a blank image to the proper size, then loop the drawCharacter method to fill the cropped image with the 2D array, takes a 2D array as input
    public static void drawArray(char[][] inputArray) throws IOException, FontFormatException {

        //load the blank image
        File imageFile = new File("src/main/java/org/example/white-background-8K.jpg");
        BufferedImage bufferedImage = ImageIO.read(imageFile);

        //determine the height/width of the cropped image
        int outputImageHeight = 24+inputArray.length*(80);
        int outputImageLength = 20+inputArray[0].length*(80);
        BufferedImage croppedImage = bufferedImage.getSubimage(0, 0, outputImageLength-1, outputImageHeight-1);
        RenderedImage image = croppedImage;
        //save the cropped image
        ImageIO.write(image,"jpg", new File("src/main/java/org/example/outputImage.jpg"));

        for(int i = 0; i<Image_Procs.getWords().size();i++){
            drawLine(GFG.coords[i][0], GFG.coords[i][1], GFG.coords[i][2], GFG.coords[i][3], "src/main/java/org/example/outputImage.jpg", "src/main/java/org/example/outputImage.jpg");
        }


        //loop to fill the blank image with the input 2D array
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                drawCharacter(inputArray[j][i], i, j, "src/main/java/org/example/outputImage.jpg", "src/main/java/org/example/outputImage.jpg");
            }
        }

    }
}