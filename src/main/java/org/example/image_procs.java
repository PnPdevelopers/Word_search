package org.example;

import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class image_procs {

    public static boolean detect_whitespace(String image_path,Rectangle bound,double scale) throws IOException {
        double inv_scale=(1-scale);
        int sc_x= (int) (bound.getX()*inv_scale+bound.getX());
        int sc_y= (int) (bound.getY()*inv_scale+bound.getY());
        int sc_w= (int) (bound.getWidth()*scale);
        int sc_h= (int) (bound.getHeight()*scale);
        BufferedImage orginalImage = ImageIO.read(new File(image_path));
        BufferedImage croped = orginalImage.getSubimage(sc_x,sc_y,sc_w,sc_h);



        BufferedImage blackAndWhiteImg = new BufferedImage(croped.getWidth(), croped.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(croped, 0, 0, null);

        JLabel picLabel = new JLabel(new ImageIcon(blackAndWhiteImg));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(blackAndWhiteImg.getWidth(), blackAndWhiteImg.getHeight()));
        f.add(jPanel);
        f.setVisible(true);


        int white_count=0;
        int black_count=0;
        //blackAndWhiteImg
        for (int Y=1; Y<blackAndWhiteImg.getHeight();Y++){
            for (int X=1; X<blackAndWhiteImg.getWidth();X++){
                int clr = blackAndWhiteImg.getRGB(X, Y);
                int red =   (clr & 0x00ff0000) >> 16;
                int green = (clr & 0x0000ff00) >> 8;
                int blue =   clr & 0x000000ff;
                System.out.println("R: "+red);
                System.out.println("G: "+green);
                System.out.println("B: "+blue);
                if(red+green+blue<=382)/*black*/{
                    black_count=black_count+1;
                }
                if(red+green+blue>382)/*white*/{
                    white_count=white_count+1;
                }

            }
        }
        System.out.println("W: "+white_count);
        System.out.println("B: "+black_count);
        return black_count > 0;}
    public static void OCR(String image_path,String data_path,boolean debug) throws Exception {
        ITesseract instance = new Tesseract();
        BufferedImage picture = ImageIO.read(new File(image_path));
        instance.setDatapath(data_path);

        String[] lines_array;
        Graphics2D g = (Graphics2D) picture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);

        for (Word word : instance.getWords(picture, ITessAPI.TessPageIteratorLevel.RIL_SYMBOL)) {
            //String image_path,Rectangle bound,int scale, int threshold
            if (detect_whitespace(image_path,word.getBoundingBox(),1)) {
                System.out.println("found");
                Rectangle rect = word.getBoundingBox();
                //System.out.println(rect.getMinX() + ", " + rect.getMaxX() + ", " + rect.getMinY() + ", " + rect.getMaxY()+ ": " + word.getText() + " : " + word.getConfidence());

                DecimalFormat df = new DecimalFormat("##.");

                g.drawString(word.getText() + "-" + df.format(word.getConfidence()), (int) rect.getMinX() - 10, (int) rect.getMaxY() + 20);
                g.draw(rect);

            }}

        JLabel picLabel = new JLabel(new ImageIcon(picture));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(picture.getWidth(), picture.getHeight()));
        f.add(jPanel);
        f.setVisible(true);


    }


}
