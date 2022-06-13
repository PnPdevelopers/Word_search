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
        Image_Procs.OCR_Char("src/main/java/org/example/Picture Uploads/be_safe_word_search-1.png", "src/main/java/org/example/tessdata", true);
        Image_Procs.sudo_grid("src/main/java/org/example/Picture Uploads/be_safe_word_search-1.png","src/main/java/org/example/tessdata",true);
        char[][] puzzle = Image_Procs.getPuzzle();
        List<String> words = Image_Procs.getWords();
        image_Creator.drawArray(puzzle);
        for(int i = 0; i<= words.size()-1; i++){
            GFG.patternSearch(puzzle,words.get(i));
            //Prints solution coordinate data found, temporary until final output is completed.
            System.out.println(
                    words.get(i)+
                            " found at ("+
                            GFG.coords[i][0]+
                            ","+
                            GFG.coords[i][1]+
                            ") ("+
                            GFG.coords[i][2]+
                            ","+
                            GFG.coords[i][3]+
                            ")");
        }
        //GUI.main(null);
    }
}

