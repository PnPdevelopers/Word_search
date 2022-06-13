package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.Graphics;

import static java.awt.Color.black;

public class word_Highlighter {

    public static void loadImageFrame() throws IOException {

    }

    public static void paint(Graphics g) {
        g.setColor(black);
        g.drawLine(20, 20, 200, 180);
    }
}
