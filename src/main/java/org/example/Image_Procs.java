package org.example;

import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Image_Procs {

    public static boolean Detect_Valid(Word word, String[] proven, int place, boolean debug) {
        // reading the image as a whole is far more reliable than reading the character sepretly.


    return true;}



    public static void OCR_Char(String image_path,String data_path,boolean debug) throws Exception {
        //makes the Tesseract instance and fetches the image.
        ITesseract instance = new Tesseract();
        BufferedImage picture = ImageIO.read(new File(image_path));
        instance.setDatapath(data_path);
        List<Word> rows = new ArrayList<Word>(); //Initializes the List<Word> rows, which has an object for each line of the puzzle
        rows = instance.getWords(picture, ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE);
        
        float max = 0;
        for (Word word : rows) {
            String line = word.getText();
            line.replace('1','I');
            line.replaceAll("\\s", "");
            
            if(word.getConfidence()>max){
                max = word.getConfidence();
            }






            System.out.println(word);
        }
        


        /*Graphics2D g = (Graphics2D) picture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        int count=0;*/
        /*
        for (Word word : instance.getWords(picture, ITessAPI.TessPageIteratorLevel.RIL_SYMBOL)) {
            //String image_path,Rectangle bound,int scale, int threshold
            if (true) {
                Rectangle rect = word.getBoundingBox();



                if (debug){
                DecimalFormat df = new DecimalFormat("##.");
                g.drawString(word.getText() + "-" + df.format(word.getConfidence()), (int) rect.getMinX() - 10, (int) rect.getMaxY() + 20);
                g.draw(rect);}

            }}*/


       /* if (debug){
        JLabel picLabel = new JLabel(new ImageIcon(picture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);}*/



    }
/*
    //This colects the words in the Key.
    public static String[] OCR_Key(String image_path,String data_path,boolean debug){
        String[] temp = new String[0];


    return(temp);}*/

}
