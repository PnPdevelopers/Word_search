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
import static org.example.Main.puzzle;

public class ExtraWords {
    public static List<String> makeList() throws FileNotFoundException {

        //uses scanner to make a new list of the contents of words.txt, a file with 479k English words
        Scanner s = new Scanner(new File("src/main/java/org/example/uploads/words.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();


        List<String> allWords = new ArrayList<String>();
        //removes words that contain punctuation or numbers, and words shorter than 3 letters. capitalizes all usable words
        for(int i=0;i<list.size();i++){
            if(!(list.get(i).matches("[a-zA-Z]+")) || (list.get(i).length()<4)){
                list.remove(i);
            }else{
                allWords.add(list.get(i).toUpperCase());
            }
        }
        return allWords;
    }


    // Rows and columns in the given grid
    static int R, C;

    // For searching in all 8 direction
    static int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };

    //Initializes variables to return final coordinate data
    static int eR = -1;
    static int eC = -1;


    // This function searches in all 8-direction from point (row, col) in grid[][]
    static Boolean extraSearch2D(char[][] grid, int row, int col, String word){
        // If first character of word doesn't match with given starting point in grid.
        if (grid[row][col] != word.charAt(0))
            return false;

        int len = word.length();

        // Search word in all 8 directions starting from (row, col)
        for (int dir = 0; dir < 8; dir++) {
            // Initialize starting point for current direction
            int k, rd = row + x[dir], cd = col + y[dir];
            // First character is already checked, match remaining characters
            for (k = 1; k < len; k++) {
                // If out of bound break
                if (rd >= R || rd < 0 || cd >= C || cd < 0)
                    break;
                // If not matched, break
                if(k == len){
                    break;
                }
                if (grid[rd][cd] != word.charAt(k))
                    break;
                // Moving in particular direction
                rd += x[dir];
                cd += y[dir];
            }
            // If all character matched, then value of must be equal to length of word
            if (k == len) {
                //Since the algorithm finishes one letter past the end of the word, take a step back by inversing the direction data to get the end coordinate of the word.
                eR = rd + (-1*x[dir]);
                eC = cd + (-1 * y[dir]);
                System.out.println(word+" found at ("+col+","+row+") ("+eC+","+eR+")");
                return true;
            }
        }
        return false;
    }

    // Searches given word in a given matrix in all 8 directions

    //Creates list to add position data to be returned in image to user
    //also makes an integer to add data to this list in the loop
    //list is a 2D array with a row for every character in the word search in case of repeat word finds
    private static Integer[][] extraCoords = new Integer[(ImageProc.getPuzzle(1).length * ImageProc.getPuzzle(1)[0].length)*(10^3)][4];
    static int i = 0;

    static void extraPatternSearch(
            char[][] grid,
            String word)
    {
        R = grid.length;
        C = grid[0].length;
        // Consider every point as starting point and search given word
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (grid[row][col]==word.charAt(0) && extraSearch2D(grid,row,col,word)){
                    //Append coordinate data of successful word finds to global 2D array of coordinates
                    extraCoords[i][0] = row;
                    extraCoords[i][1] = col;
                    extraCoords[i][2] = eR;
                    extraCoords[i][3] = eC;
                    i+=1;
                }
            }
        }
    }

    static void extraAllWords() throws IOException, FontFormatException {
        List<String> words = makeList(); //gets words
        for(int i = 0; i<= words.size()-1; i++){
            extraPatternSearch(puzzle,words.get(i));
        }
    }

    static Integer[][] extraSolvedList(){
        return extraCoords;
    }
}
