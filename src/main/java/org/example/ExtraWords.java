package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.awt.Color.black;
import static java.awt.Color.green;

public class ExtraWords {
    public static void makeList() throws IOException, FontFormatException {

        //uses scanner to make a new list of the contents of words.txt, a file with 479k English words
        Scanner s = new Scanner(new File("src/main/java/org/example/uploads/words.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();


        String[] allwords = new String[list.size()];
        //removes words that contain punctuation or numbers, and words shorter than 3 letters. capitalizes all usable words
        for(int i=0;i<list.size();i++){
            if(!(list.get(i).matches("[a-zA-Z]+")) || (list.get(i).length()<3)){
                list.remove(i);
            }else{
                allwords[i] = list.get(i).toUpperCase();
            }
        }
    }
}
