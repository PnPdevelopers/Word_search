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
    JFrame inputFrame;
    JFrame outputFrame;

    //Initialize child panels to house things in frames
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
        //Locally declare input JFrame
        JFrame inputFrame = new JFrame();
        inputFrame.setDefaultCloseOperation(inputFrame.EXIT_ON_CLOSE);
        inputFrame.setPreferredSize(new Dimension(700,700));
        inputFrame.setTitle("Input Puzzle + Key Below");
        inputFrame.setLayout(new BorderLayout());

        //Locally declare input JPanels
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

        inputFrame.add(inputPuzzle, BorderLayout.EAST);
        inputFrame.add(inputKey, BorderLayout.WEST);
        inputFrame.add(Submit, BorderLayout.SOUTH);

        inputFrame.pack();
        inputFrame.setVisible(true);
        //
        //
        //
        //OUTPUT ------------------------------------------------------------------------------------------------------
        //Locally declare output JFrame
        JFrame outputFrame = new JFrame();
        outputFrame.setDefaultCloseOperation(inputFrame.EXIT_ON_CLOSE);
        outputFrame.setPreferredSize(new Dimension(700,700));
        outputFrame.setTitle("Output");
        outputFrame.setLayout(new BorderLayout());

        //locally declare output JPanels
        JPanel outputPuzzle = new JPanel();
        outputPuzzle.setSize(250,250);
        outputPuzzle.setLayout(new GridLayout(3,0));
        outputPuzzle.setBackground(Color.red);

        JPanel outputKey = new JPanel();
        outputKey.setSize(250,250);
        outputKey.setLayout(new GridLayout(3,0));
        outputKey.setBackground(Color.blue);


        //Add outputPanels to outputFrame
        outputFrame.add(outputPuzzle, BorderLayout.WEST);
        outputFrame.add(outputKey, BorderLayout.EAST);

        outputFrame.pack();
        outputFrame.setVisible(false);

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
            inputFrame.setVisible(false);
            outputFrame.setVisible(true);
        }
    }
}
