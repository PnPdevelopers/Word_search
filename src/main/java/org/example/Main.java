package org.example;


//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.

//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.
import java.util.List;

import static java.awt.Color.*;

public class Main {

    static char[][] puzzle = ImageProc.getPuzzle(1); //gets puzzle

    //Had to change Main to not be static to account for the GUI. Just doing what the IDE told me as a suggestion.
    public static void main(String[] args) throws Exception {
        //String image_path = "cross_puzzle_test.png";
        //String data_path = "tessdata";
        ImageProc.OCR_Char("src/main/java/org/example/Picture Uploads/be_safe_word_search-1.png", "src/main/java/org/example/tessdata", true);
        //Image_Procs.sudo_grid("src/main/java/org/example/Picture Uploads/be_safe_word_search-1.png","src/main/java/org/example/tessdata",true);

        SolveAlgorithm.allWords(); //solves for all  words
        ExtraWords.extraAllWords(); //solves for keywords

        ImageCreator.drawArray(puzzle, yellow, white); //draws puzzle

        GUI.main(null);
        //word_Highlighter.paint();
    }
}