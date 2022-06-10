package org.example;

//import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

public class Image_Procs {




    /*public static void OCR_Char(String image_path,String data_path,boolean debug) throws Exception {
        //makes the Tesseract instance and fetches the image.
        ITesseract instance = new Tesseract();
        //primes the image for use.
        BufferedImage picture = ImageIO.read(new File(image_path));
        //sets the data path for the tried data
        instance.setDatapath(data_path);

        //This would be used if we are getting just one character.
        //instance.setPageSegMode(PSM_SINGLE_CHAR);

        //Makes the list for the output
        List<Word> rows = new ArrayList<Word>(); //Initializes the List<Word> rows, which has an object for each line of the puzzle
        //list of all the letters line by line in HOCR format
        rows = instance.getWords(picture, ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE);

        //sets max confidence level container.
        float max = 0;
        // Error detection removing all spaces and changes all "1" to "I"
        for (Word word : rows) {
            String line = word.getText();
            line = line.replace('1','I'); //replaces the misread 1's to I's
            line = line.replaceAll("\\s", ""); //removes phantom spaces

            // Used to find the max confidence.
            if(word.getConfidence()>max){
                max = word.getConfidence();
            }
            System.out.println(line);}
        //used to debug the process for error detection.
            if (debug){
                Graphics2D g = (Graphics2D) picture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);


        JLabel picLabel = new JLabel(new ImageIcon(picture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);}
            //end of debug code
            }
    */
    //Backup shortcut list of letter as a word search to be used if the ocr is not working
    public static char[][] getPuzzle() {
        char[][] words = {
                {'W', 'V', 'E', 'R', 'T', 'I', 'C', 'A', 'L','L'},
                {'R', 'O', 'O', 'A', 'F', 'F', 'L', 'S', 'A', 'B'},
                {'A', 'C', 'R', 'I', 'L', 'I', 'A', 'T', 'O', 'A'},
                {'N', 'D', 'O', 'D', 'K', 'O', 'N', 'W', 'D', 'C'},
                {'D', 'R', 'K', 'E', 'S', 'O', 'O', 'D', 'D', 'K'},
                {'O', 'E', 'E', 'P', 'Z', 'E', 'G', 'L', 'I', 'W'},
                {'M', 'S', 'I', 'I', 'H', 'O', 'A', 'E', 'R', 'A'},
                {'A', 'L', 'R', 'K', 'R', 'R', 'I', 'R', 'E', 'R'},
                {'K', 'O', 'D', 'I', 'D', 'E', 'D', 'R', 'C', 'D'},
                {'H', 'E', 'L', 'W', 'S', 'L', 'E', 'U', 'T', 'H'},
                };
        return words;
    }

    //Backup shortcut list of key words to be used if the ocr is not working
    public static List<String> getWords() {
        List<String> puzzle = Arrays.asList(
                "SEEK",
                "FIND",
                "RANDOM",
                "SLEUTH",
                "BACKWARD",
                "VERTICAL",
                "DIAGONAL",
                "WIKIPEDIA",
                "HORIZONTAL",
                "WORDSEARCH");
        return puzzle;
    }
}
