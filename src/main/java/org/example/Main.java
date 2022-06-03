package org.example;


//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.swing.*;
import java.awt.*;

public class Main implements ActionListener {

    //Global Delcaration
    JFrame InputFrame;
    JFrame DisplayFrame;

    //Global Declaration of JPanels
    JPanel puzzlePanel;
    JPanel keyPanel;

    JPanel DisplayPuzzlePanel;
    JPanel DisplayKeyPanel;

    //Global Declaration of JButtons
    JButton puzzleUploadButton;
    JButton keyUploadButton;

    JButton submitButton;
    public static void main(String[] args) throws Exception{
        String image_path = "cross_puzzle_test.png";
        String data_path = "tessdata";
        Image_Procs.OCR_Char(image_path,data_path,true);
        new Main();
    }

    public Main()
    {
        //Input JFrame which houses input for user to upload image of puzzle/key
        JFrame inputFrame = new JFrame();
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setTitle("Upload Puzzle/Key Here");
        //arbitrary size for now
        inputFrame.setSize(200,500);

        //Input JPanel where user upload puzzle IMG
        puzzlePanel = new JPanel();
        //Arbitrary Size & Layout for now
        puzzlePanel.setLayout(new BoxLayout(puzzlePanel, BoxLayout.Y_AXIS));
        puzzlePanel.setSize(100,100);

        //Input JPanel where user uploads key IMG
        keyPanel = new JPanel();
        //Arbitrary Size & Layout for now
        keyPanel.setLayout(new BoxLayout(keyLayout, BoxLayout.Y_AXIS));
        keyPanel.setSize(100,100);

        //Input Prompt for puzzlePanel
        puzzleUploadButton = new JButton("Upload");
        puzzleUploadButton.addActionListener(this);
        //Arbitrary Button Size for now
        puzzleUploadButton.setPreferredSize(new Dimension(40,40));

        //Input Prompt for keyPanel
        keyUploadButton = new JButton("Upload");
        keyUploadButton.addActionListener(this);
        //Arbitrary Button size for now
        keyUploadButton.setPreferredSize(new Dimension(40,40));

        //Submit prompt for inputFrame
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        //Arbitrary Button size for now
        submitButton.setPreferredSize(new Dimension(40,40));

        //link JButtons to respective JPanels
        puzzlePanel.add(puzzleUploadButton);
        keyPanel.add(keyUploadButton);

        //add panels and submit button to InputFrame
        inputFrame.add(puzzlePanel);
        inputFrame.add(keyPanel);
        inputFrame.add(submitButton);

        inputFrame.pack();
        inputFrame.setVisible(true);

        //DISPLAY PANEL
        DisplayFrame = new JFrame();
        DisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DisplayFrame.setTitle("Results");
        DisplayFrame.setSize(200,500);

        //Locally initialize panel to display solved puzzle
        DisplayPuzzlePanel = new JPanel();
        //add finished puzzle image to this panel

        //Locally initialize panel to display solved key
        DisplayKeyPanel = new JPanel();
        //add finished key image to this panel

        DisplayFrame.add(DisplayKeyPanel);
        DisplayFrame.add(DisplayPuzzlePanel);

        DisplayFrame.pack();
        DisplayFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == puzzleUploadButton)
        {
            //API CODE HERE
        }
        else if (e.getSource() == keyUploadButton)
        {
            //API CODE HERE
        }
        else if (e.getSource() == submitButton)
        {
            JComponent inputFrame;
            inputFrame.setVisible(false);
            JComponent displayFrame;
            displayFrame.setVisible(true);
        }
    }
}