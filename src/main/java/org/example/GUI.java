package org.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;

public class GUI implements ActionListener
{
    //For the record: This is the 3rd time that I have rewritten the GUI Code. I pray that this doesn't get deleted.
    //The last time I wrote it, no errors showed up according to the IDE.
    //Initialize global mainframes for Input/Output Frames
    public JFrame mainFrame;

    //Initialize child panels to house things in frames
    JPanel inputPanel;
    JPanel outputPanel;
    JPanel inputPuzzle;
    JPanel inputKey;
    JPanel Submit;

    JPanel outputPuzzle;
    JPanel outputKey;

    //Initialize child buttons for input panels
    JButton puzzleButton;
    JButton keyButton;
    JButton submitButton;

    //Initialize Image icons for output frame

    public static void main(String[] args)
    {
        new GUI();
    }

    public GUI()
    {

        //Locally declare main JFrame
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(700,700));
        mainFrame.setTitle("Input Puzzle + Key Below");

        //New Icon:
        ImageIcon icon = new ImageIcon("src/main/java/org/example/logo.png");
        mainFrame.setIconImage(icon.getImage());

        //Locally declare input JPanels
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputPuzzle = new JPanel();
        inputPuzzle.setSize(250,250);
        inputPuzzle.setLayout(new GridLayout(3,0));

        inputKey = new JPanel();
        inputKey.setSize(250,250);
        inputKey.setLayout(new GridLayout(3,0));

        Submit = new JPanel();
        Submit.setSize(200,200);
        Submit.setLayout(new GridLayout(3,0));

        //Locally declare input buttons
        puzzleButton = new JButton("Upload puzzle");
        puzzleButton.addActionListener(this);

        keyButton = new JButton("Upload key");
        keyButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        //Add everything to input panels/frame
        inputPuzzle.add(puzzleButton);
        inputKey.add(keyButton);
        Submit.add(submitButton);

        inputPanel.add(inputPuzzle, BorderLayout.EAST);
        inputPanel.add(inputKey, BorderLayout.WEST);
        inputPanel.add(Submit, BorderLayout.SOUTH);

        mainFrame.add(inputPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        //
        //
        //
        //OUTPUT ------------------------------------------------------------------------------------------------------
        //Locally declare output JFrame
        outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        //locally declare output JPanels
        outputPuzzle = new JPanel();
        outputPuzzle.setSize(250,250);
        outputPuzzle.setLayout(new GridLayout(3,0));
        outputPuzzle.setBackground(Color.red);

        outputKey = new JPanel();
        outputKey.setSize(250,250);
        outputKey.setLayout(new GridLayout(3,0));

        //Add outputPanels to outputFrame
        outputPanel.add(outputPuzzle, BorderLayout.WEST);
        outputPanel.add(outputKey, BorderLayout.EAST);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == inputPuzzle)
        {
            //API CODE HERE
        }
        else if(e.getSource() == inputKey)
        {
            //API CODE HERE
        }
        else if(e.getSource() == submitButton)
        {
            try {
                word_Highlighter.loadImageFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //SEND TO LOGIC
            //System.out.println("Hello");
            //sets main frame to not visible
            //mainFrame.setVisible(false);
            //removes existing GUI on display
            //mainFrame.getContentPane().removeAll();
            //adds selected parameter GUI
            //mainFrame.getContentPane().add(outputPanel);
            //repacks main frame
            //mainFrame.pack();
            //sets as visible
            //mainFrame.setVisible(true);
        }
    }
}
