package org.example;

//import net.sourceforge.tess4j.*;

import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//import static net.sourceforge.tess4j.ITessAPI.TessPageSegMode.PSM_SINGLE_CHAR;

public class ImageProc {

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

    public static BufferedImage[][] sudo_grid(String image_path,String data_path,boolean depug) throws IOException, TesseractException {

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

        ITesseract instance = new Tesseract();
        instance.setDatapath(data_path);
        //instance.setPageSegMode(PSM_SINGLE_CHAR);

        Rectangle rect;
        String[][] char_array = new String[located_column.size()/2][located_rows.size()/2];

        int count=0;
        System.out.println("y axis:"+located_rows.size()/2);
        System.out.println("y axis from:"+located_rows.size());
        int count_y=0;
         for(int y=0;y<located_rows.size();){
             int ys=y;
             System.out.println("x axis:"+located_column.size()/2);
             System.out.println("x axis from:"+located_column.size());
             int count_x=0;
             for (int x=0;x<located_column.size();){
                 int xs=x;
                 System.out.println("at count:"+count+", looking at y="+(ys+1)+",x="+(xs+1));
                rect= new Rectangle(located_column.get(xs),located_rows.get(ys),located_column.get(xs+1)-located_column.get(xs),located_rows.get(ys+1)-located_rows.get(ys));
                System.out.println(rect.getX()+":"+rect.getY()+"    "+rect.getWidth()+":"+rect.getHeight());

                 BufferedImage croped= picture.getSubimage((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
                 ImageIO.write(croped, "png", new File("saved_croped_count"+count+".png"));

                String read = instance.doOCR(croped);
                if (read.length()==0){
                    read=" ";}
                 if (read.equals("1")){
                     read="I";}


                System.out.println("The letter string is:<"+read+">"+" at x="+count_x+" and y="+count_y);
                 System.out.println();
                 System.out.println();
                 String filler=read;
                char_array[count_x][count_y]=filler;
                 g2d.setColor(Color.RED);
                 g2d.fill(rect);
                 //ImageIO.write(picture, "png", new File("saved_dep"+count+".png"));
                 count_x++;
                 count++;
             x=x+2;}
             count_x++;
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

    public static void OCR_Char(String image_path,String data_path,boolean debug) throws Exception {
        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("src/main/java/org/example/tessdata");
            tesseract.setLanguage("eng");
            // the path of your tess data folder
            // inside the extracted file
            String text
                    = tesseract.doOCR(new File("src/main/java/org/example/uploads/cross_puzzle_test.png"));

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
    public static char[][] makePuzzle(int which) {
        char[][] blank = new char[0][0];
        if (which == 1) {
            char[][] words = {
                    {'W', 'V', 'E', 'R', 'T', 'I', 'C', 'A', 'L', 'L'},
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
        }else if (which == 2) {
            char[][] words = {
                    {'V', 'B', 'R', 'E', 'E', 'F', 'I', 'S', 'H', 'R', 'A', 'C', 'H', 'P'},
                    {'A', 'N', 'A', 'C', 'R', 'O', 'C', 'O', 'D', 'I', 'L', 'E', 'E', 'B'},
                    {'A', 'O', 'S', 'T', 'R', 'I', 'C', 'H', 'T', 'E', 'G', 'R', 'D', 'A'},
                    {'I', 'A', 'D', 'D', 'H', 'C', 'H', 'E', 'E', 'T', 'A', 'H', 'G', 'D'},
                    {'B', 'H', 'R', 'O', 'D', 'R', 'A', 'V', 'E', 'N', 'E', 'N', 'E', 'G'},
                    {'E', 'Y', 'W', 'D', 'L', 'S', 'A', 'M', 'O', 'L', 'E', 'L', 'H', 'E'},
                    {'A', 'R', 'T', 'P', 'V', 'P', 'R', 'C', 'B', 'O', 'L', 'R', 'O', 'R'},
                    {'R', 'H', 'T', 'O', 'A', 'A', 'H', 'C', 'R', 'O', 'W', 'A', 'G', 'H'},
                    {'C', 'C', 'A', 'N', 'N', 'O', 'R', 'I', 'A', 'Z', 'E', 'B', 'R', 'A'},
                    {'H', 'A', 'N', 'Y', 'T', 'A', 'E', 'K', 'N', 'I', 'N', 'A', 'W', 'A'},
            };
            return words;
        }else if (which == 3) {
            Random r = new Random();

            char[][] words = new char[ThreadLocalRandom.current().nextInt(3, 15 + 1)][ThreadLocalRandom.current().nextInt(3, 15 + 1)];
            for(char[] line: words){
                for(int i = 0; i<line.length; i++){
                    char c = (char)(r.nextInt(26) + 'A'); //makes random character
                    line[i] = c; //populates list
                    System.out.print(line[i]);
                }
                System.out.println("");
            }

            return words;
        }
        return blank;
    }




    //Backup shortcut list of key words to be used if the ocr is not working
    public static List<String> getWords(int which) {
        List<String> puzzle = new ArrayList<>();
        if(which == 1){
            puzzle = Arrays.asList(
                    "SEEK",
                    "FIND",
                    "RANDOM",
                    "SLEUTH",
                    "BACKWARD",
                    "VERTICAL",
                    "DIAGONAL",
                    "WIKIPEDIA",
                    "HORIZONTAL",
                    "WORDSEARCH"
            );
        }else if(which == 2){
            puzzle = Arrays.asList(
                    "FISH",
                    "CROCODILE",
                    "OSTRICH",
                    "CHEETAH",
                    "RAVEN",
                    "MOLE",
                    "CROW",
                    "BEAR",
                    "PONY",
                    "ANT",
                    "EEL",
                    "HEDGEHOG",
                    "BADGER",
                    "ZEBRA",
                    "BAT",
                    "DOLPHIN",
                    "AARDVARK"
            );
        }else if (which ==3){
            puzzle = Arrays.asList( //necessary for errors
                    "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH");
        }

        return puzzle;
    }
}
