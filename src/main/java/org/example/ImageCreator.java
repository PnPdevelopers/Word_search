package org.example;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.RenderedImage;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static java.awt.Color.*;

public class ImageCreator {
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
        Font FiraCode = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/org/example/uploads/FiraCode-Bold1.ttf")).deriveFont(60f);
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

    public static void drawLine(int width, Color color, int startX, int startY, int endX, int endY, String inputImage, String outputImage) throws IOException, FontFormatException {
        //System.out.println("("+startX+","+startY+") ("+endX+","+endY+")");
        //math for line positioning based on 2D array position
        int xStart = 43 + 80 * startX;
        //System.out.println("X position of line start " + xStart);
        int yStart = 50 + 80 * startY;
        //System.out.println("Y position of line start " + yStart);
        int xEnd = 43 + 80 * endX;
        //System.out.println("X position of line end " + xEnd);
        int yEnd = 50 + 80 * endY;
        //System.out.println("Y position of line end " + yEnd);
        //read the image
        BufferedImage image = ImageIO.read(new File(inputImage));
        //get the Graphics object
        Graphics g = image.getGraphics();
        ImageCreator.paintComponent(width, color, g ,xStart,yStart,xEnd,yEnd);
        g.dispose();
        //write the image
        ImageIO.write(image, "jpg", new File(outputImage));
    }

    public static void paintComponent(int width, Color color, Graphics g, int startX, int startY, int endX, int endY) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(width));
        g2.draw(new Line2D.Float(startX, startY, endX, endY));
    }

    //drawArray method to crop a blank image to the proper size, then loop the drawCharacter method to fill the cropped image with the 2D array, takes a 2D array as input
    public static void drawArray(char[][] inputArray) throws IOException, FontFormatException {

        //load the blank image
        File imageFile = new File("src/main/java/org/example/uploads/white-background-8K.jpg");
        BufferedImage bufferedImage = ImageIO.read(imageFile);

        //determine the height/width of the cropped image
        int outputImageHeight = 24+inputArray.length*(80);
        int outputImageLength = 20+inputArray[0].length*(80);
        BufferedImage croppedImage = bufferedImage.getSubimage(0, 0, outputImageLength-1, outputImageHeight-1);
        RenderedImage image = croppedImage;
        //save the cropped image
        ImageIO.write(image,"jpg", new File("src/main/java/org/example/uploads/outputImage.jpg"));

            //For each word, draws outer black line then inner yellow line to appear to have a black border around a yellow line
        for (Integer[] coord : SolveAlgorithm.coords) {
            if (coord[0] != null) {
                drawLine(48, black, coord[1], coord[0], coord[3], coord[2], "src/main/java/org/example/uploads/outputImage.jpg", "src/main/java/org/example/uploads/outputImage.jpg");
                drawLine(45, yellow, coord[1], coord[0], coord[3], coord[2], "src/main/java/org/example/uploads/outputImage.jpg", "src/main/java/org/example/uploads/outputImage.jpg");
            }
        }


        //loop to fill the blank image with the input 2D array
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                drawCharacter(inputArray[i][j], j, i, "src/main/java/org/example/uploads/outputImage.jpg", "src/main/java/org/example/uploads/outputImage.jpg");
            }
        }

    }
}