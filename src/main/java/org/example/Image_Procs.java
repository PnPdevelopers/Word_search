package org.example;

//import net.sourceforge.tess4j.*;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

public class Image_Procs {

    public static int find_next_column_start(int Start,BufferedImage image){
        for(int W=Start;W<= image.getHeight()-1;W++){
            if(W==image.getWidth()-1){return -1;}
            for (int H=0;H<= image.getHeight()-1;H++){
                if(image.getRGB(W,H)==0xFF000000){
                    return (W);}
            }
        }

        return -1;}

    public static int find_next_column_end(int Start,BufferedImage image){
        for(int W=Start;W<= image.getHeight()-1;W++){
            if(W==image.getWidth()-1){return -1;}
            Boolean found_black = false;
            for (int H=0;H<= image.getHeight()-1;H++){
                if(image.getRGB(W,H)==0xFF000000){
                    found_black = true;}
            }
            if (!found_black){
                return(W);}
        }

        return -1;}

    public static int find_next_row_start(int Start,BufferedImage image){
        for(int H=Start;H<= image.getHeight()-1;H++){
            if(H==image.getHeight()-1){return -1;}
        for (int W=0;W<= image.getWidth()-1;W++){
            if(image.getRGB(W,H)==0xFF000000){
                return (H);}
        }
        }

    return -1;}

    public static int find_next_row_end(int Start,BufferedImage image){
        for(int H=Start;H<= image.getHeight()-1;H++){
            if(H==image.getHeight()-1){return -1;}
            Boolean found_black = false;
            for (int W=0;W<= image.getWidth()-1;W++){
                if(image.getRGB(W,H)==0xFF000000){
                    found_black = true;}
            }
            if (!found_black){
                return(H);}
        }

    return -1;}

    public static List<Integer> scan_column(BufferedImage image){

        List<Integer> located_column = new ArrayList<>();
        int start=find_next_column_start(0,image);
        located_column.add(start);
        boolean keep=true;
        while (keep){
            int end=find_next_column_end(start,image);
            if (end!=-1){
                located_column.add(end);
                start=find_next_column_start(end,image);
                if (start!=-1){
                    located_column.add(start);
                }else{keep=false;}


            }else{keep=false;}
        }


        System.out.println("located_column:"+located_column);
        return located_column;
    }

    public static List<Integer> scan_rows(BufferedImage image){

        List<Integer> located_rows = new ArrayList<>();
        int start=find_next_row_start(0,image);
        located_rows.add(start);
        boolean keep=true;
        while (keep){
            int end=find_next_row_end(start,image);
            if (end!=-1){
                located_rows.add(end);
                start=find_next_row_start(end,image);
            if (start!=-1){
                located_rows.add(start);
            }else{keep=false;}


            }else{keep=false;}
        }


        System.out.println("located_rows:"+located_rows);
        return located_rows;
    }

    public static BufferedImage[][] sudo_grid(String image_path,boolean depug) throws IOException {

        File file = new File(image_path);
        BufferedImage orginalImage = ImageIO.read(file);

        BufferedImage blackAndWhiteImg = new BufferedImage(
                orginalImage.getWidth(), orginalImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);

        ImageIO.write(blackAndWhiteImg, "png", new File("saved.png"));

        BufferedImage picture = ImageIO.read(new File("saved.png"));
        List<Integer> located_rows;
        List<Integer> located_column;
        located_rows=scan_rows(picture);
        located_column=scan_column(picture);


        Graphics2D g2d = picture.createGraphics();
        g2d.setColor(Color.RED);
        //g2d.setStroke(4);
        System.out.println(located_rows);
        for(int x=0;x<= located_rows.size()-1;x++){
            int level =located_rows.get(x);
            g2d.drawRect(0, level,picture.getWidth(), 1);
        }
        for(int x=0;x<= located_column.size()-1;x++){
            int level =located_column.get(x);
            g2d.drawRect(level,0,1, picture.getHeight());
        }

        ImageIO.write(picture, "png", new File("saved_2.png"));

        Rectangle rect;
        Rectangle[][] rect_array = new Rectangle[located_column.size()/2][located_rows.size()/2];
        int count=0;
         for(int y=0;y< located_rows.size()/2;y++){
             int ys=y*2;
             for (int x=0;x<located_column.size()/2;x++){
                 int xs=y*2;
                rect= new Rectangle(located_rows.get(xs),located_column.get(ys),located_column.get(xs+1)-located_column.get(xs),located_rows.get(ys+1)-located_rows.get(ys));
                System.out.println(rect.getX()+":"+rect.getY()+"    "+rect.getWidth()+":"+rect.getHeight());
                rect_array[x][y]=rect;
                 g2d.setColor(Color.RED);
                 g2d.fill(rect);
                 ImageIO.write(picture, "png", new File("saved_dep"+count+".png"));
                 count++;
             }

         }
        ImageIO.write(picture, "png", new File("saved_3.png"));
        g2d.dispose();
         System.out.println(Arrays.deepToString(rect_array));



        return null;
    }




    public static void OCR_Char(String image_path,String data_path,boolean debug) throws Exception {
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
