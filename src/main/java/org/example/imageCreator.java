package org.example;
import com.aspose.imaging.*;
import com.aspose.imaging.imageoptions.*;
import com.aspose.imaging.fileformats.png.*;
import com.aspose.imaging.sources.FileCreateSource;

public class imageCreator {
    //this is just test data for drawing an image from an array
    private char W;
    private char O;
    private char R;
    private char D;
    char[][] testArray = {{W, O, R, D}, {O, R, D, W}, {R, D, W, O}, {D, W, O, R}};

    //this is to determine the size of the output image
    int arrayHeight = testArray.length;
    int arrayLength = testArray[0].length;
    int outputImageHeight = 25+arrayHeight*(60);
    int outputImageLength = 25+arrayLength*(60);

    //this is where the image is created
    // Where created image to store
    String path = "C:/createdImage.png";

    // Create options
    PngOptions options = new PngOptions();
    options.setSource(new FileCreateSource(path, false));
    PngImage wordSearchRemade = (PngImage)Image.create( ,outputImageHeight ,outputImageLength);


}
