package org.example;

//import net.sourceforge.tess4j.*;

import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

//import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

public class Image_Procs {
    public static BufferedImage rip_crop(BufferedImage image) throws IOException {
        int Left_point=0;
        int Top_point=0;
        int Width_point=0;
        int Height_point=0;
        int off_set=0;

        Left_point=find_next_column_start(0,image)+off_set;
        Width_point=find_next_column_start_inv(image)-Left_point-off_set;
        Top_point=find_next_row_start(0,image)+off_set;
        Height_point=find_next_row_start_inv(image)-Top_point-off_set;

        Rectangle rect=new Rectangle(Left_point,Top_point,Width_point,Height_point);


        BufferedImage croped= image.getSubimage(Left_point,Top_point,Width_point,Height_point);
        return croped;
    }
    public static BufferedImage joinBufferedImage_left_right(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 5;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;}

    public static BufferedImage joinBufferedImage_top_bottom(BufferedImage img1, BufferedImage img2) {
        int offset = 10;
        int height = img1.getHeight() + img2.getHeight() + offset;
        int width  = Math.max(img1.getWidth(), img2.getWidth());
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, img1.getHeight() + offset);
        g2.dispose();
        System.out.println("it ran");
        return newImage;}



    public static int find_next_column_start_inv(BufferedImage image){
        for(int W=image.getWidth()-1;W>0;W--){
            for (int H=0;H<= image.getHeight()-1;H++){
                if(image.getRGB(W,H)==0xFF000000){
                    return (W);}
            }
        }

        return -1;}

    public static int find_next_row_start_inv(BufferedImage image){
        for(int H=image.getHeight()-1;H>0;H--){
            for (int W=0;W<= image.getWidth()-1;W++){
                if(image.getRGB(W,H)==0xFF000000){
                    return (H);}
            }
        }

        return -1;}
    public static int find_next_column_start(int Start,BufferedImage image){
        for(int W=Start;W<= image.getWidth()-1;W++){
            if(W==image.getWidth()-1){return -1;}
            for (int H=0;H<= image.getHeight()-1;H++){
                if(image.getRGB(W,H)==0xFF000000){
                    return (W);}
            }
        }

        return -1;}



    public static int find_next_column_end(int Start,BufferedImage image){
        for(int W=Start;W<= image.getWidth()-1;W++){
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

    public static BufferedImage[][] sudo_grid(String image_path,String data_path,boolean depug) throws Exception {

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
        /*for(int x=0;x<= located_rows.size()-1;x++){
            int level =located_rows.get(x);
            g2d.drawRect(0, level,picture.getWidth(), 1);
        }
        for(int x=0;x<= located_column.size()-1;x++){
            int level =located_column.get(x);
            g2d.drawRect(level,0,1, picture.getHeight());
        }*/

        ImageIO.write(picture, "png", new File("saved_2.png"));

        ITesseract instance = new Tesseract();
        instance.setDatapath(data_path);
        //instance.setPageSegMode(PSM_SINGLE_CHAR);

        Rectangle rect;
        String[][] char_array = new String[located_column.size()/2][located_rows.size()/2];

        int count=0;
        //System.out.println("y axis:"+located_rows.size()/2);
        //System.out.println("y axis from:"+located_rows.size());
        int count_y=0;
        BufferedImage brick =  ImageIO.read(new File("src/main/java/org/example/blank whiteg.png"));
         for(int y=0;y<located_rows.size();){
             int ys=y;
             //System.out.println("x axis:"+located_column.size()/2);
             //System.out.println("x axis from:"+located_column.size());
             int count_x=0;
             BufferedImage line =  ImageIO.read(new File("src/main/java/org/example/blank whiteg.png"));
             for (int x=0;x<located_column.size();){
                 int xs=x;
                 //System.out.println("at count:"+count+", looking at y="+(ys+1)+",x="+(xs+1));
                rect= new Rectangle(located_column.get(xs),located_rows.get(ys),located_column.get(xs+1)-located_column.get(xs),located_rows.get(ys+1)-located_rows.get(ys));
                //System.out.println(rect.getX()+":"+rect.getY()+"    "+rect.getWidth()+":"+rect.getHeight());

                 BufferedImage croped= picture.getSubimage((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
                 //ImageIO.write(croped, "png", new File("saved_croped_count"+count+".png"));
                 //ImageIO.write(line, "png", new File("line_sub_count"+count_y+","+count_x+".png"));
                 croped=rip_crop(croped);

                 line=joinBufferedImage_left_right(line,croped);
                 //ImageIO.write(line, "png", new File("line_sub_count"+count_y+","+count_x+".png"));






                //System.out.println("The letter string is:<"+read+">"+" at x="+count_x+" and y="+count_y);
                 //System.out.println();
                 //System.out.println();

                //char_array[count_x][count_y]=filler;
                 g2d.setColor(Color.RED);
                 g2d.fill(rect);
                 //ImageIO.write(picture, "png", new File("saved_dep"+count+".png"));
                 count_x++;
                 count++;
             x=x+2;
             count_x++;}
             line=rip_crop(line);


                 brick=joinBufferedImage_top_bottom(brick,line);
                 ImageIO.write(line, "png", new File("line_brick.png"));



             OCR_Char(line,true);
             ImageIO.write(line, "png", new File("line_"+count_y+".png"));
         y=y+2;
         count_y++;}
        ImageIO.write(picture, "png", new File("saved_3.png"));
        g2d.dispose();
         //System.out.println(Arrays.deepToString(char_array));
        //System.out.println("\n\n\n\n");

        int rows=10;
        int columns=10;

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<columns; j++) {

                System.out.print(char_array[i][j]);
            }
            System.out.println(char_array);
        }
        return null;
    }

<<<<<<< Updated upstream
    public static void OCR2_Char(String image_path,String data_path,boolean debug) throws Exception {
        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("src/main/java/org/example/tessdata");

=======
    public static void OCR_Char(BufferedImage image_path,boolean debug) throws Exception {
        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("src/main/java/org/example/tessdata_best");
            //tesseract.setLanguage("eng");
>>>>>>> Stashed changes
            // the path of your tess data folder
            // inside the extracted file
            String text
                    = tesseract.doOCR(image_path);

            // path of your image file
            //System.out.print(text);
            String[] splited=text.split("\n");
            for(String filler:splited){
                filler = filler.replaceAll("\\s", "");
                System.out.println(filler+filler.length());
            }


        } catch (TesseractException e) {
            e.printStackTrace();
        }
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
