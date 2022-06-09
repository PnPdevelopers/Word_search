package org.example;


//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.

//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.
import java.util.List;
import java.util.Arrays;

public class Main {
    //Had to change Main to not be static to account for the GUI. Just doing what the IDE told me as a suggestion.
    public static void main(String[] args) throws Exception {
        //String image_path = "cross_puzzle_test.png";
        //String data_path = "tessdata";
        //Image_Procs.OCR_Char(image_path, data_path, true);
        char[][] puzzle = Image_Procs.getPuzzle();
        List<String> words = Image_Procs.getWords();
        for(int i = 0; i<= words.size()-1; i++){
            GFG.patternSearch(puzzle,words.get(i));
        }

        GUI.main(null);
    }
}

