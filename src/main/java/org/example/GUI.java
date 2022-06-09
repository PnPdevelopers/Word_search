package org.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class GUI implements ActionListener
{
    //For the record: This is the 3rd time that I have rewritten the GUI Code. I pray that this doesn't get deleted.
    //The last time I wrote it, no errors showed up according to the IDE.

    //Initialize global mainframes for Input/Output Frames
    JFrame mainFrame;

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

    public void switchWindows(JPanel inputPanel)
    {
        //sets main frame to not visible
        mainFrame.setVisible(false);
        //removes existing GUI on display
        mainFrame.getContentPane().removeAll();
        //adds selected parameter GUI
        mainFrame.getContentPane().add(inputPanel);
        //repacks main frame
        mainFrame.pack();
        //sets as visible
        mainFrame.setVisible(true);
    }

    //Initialize Image icons for output frame

    public static void main(String[] args)
    {
        new GUI();
    }

    public GUI()
    {
        //Locally declare main JFrame
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(700,700));
        mainFrame.setTitle("Input Puzzle + Key Below");

        //Locally declare input JPanels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JPanel inputPuzzle = new JPanel();
        inputPuzzle.setSize(250,250);
        inputPuzzle.setLayout(new GridLayout(3,0));

        JPanel inputKey = new JPanel();
        inputKey.setSize(250,250);
        inputKey.setLayout(new GridLayout(3,0));

        JPanel Submit = new JPanel();
        Submit.setSize(200,200);
        Submit.setLayout(new GridLayout(3,0));

        //Locally declare input buttons
        JButton puzzleButton = new JButton("Upload puzzle");
        puzzleButton.addActionListener(this);

        JButton keyButton = new JButton("Upload key");
        keyButton.addActionListener(this);

        JButton submitButton = new JButton("Submit");
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
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        //locally declare output JPanels
        JPanel outputPuzzle = new JPanel();
        outputPuzzle.setSize(250,250);
        outputPuzzle.setLayout(new GridLayout(3,0));
        outputPuzzle.setBackground(Color.red);

        JPanel outputKey = new JPanel();
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
            //SEND TO LOGIC
            System.out.println("Hello");
            switchWindows(outputPanel);
        }
    }
}
