package org.example;


//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.

import com.sun.jna.Pointer;
import net.sourceforge.tess4j.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws Exception{
        String image_path = "wordsearch.jpg";
        String data_path = "tessdata";
        image_procs.OCR(image_path,data_path,false);
}
}