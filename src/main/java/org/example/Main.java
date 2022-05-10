package org.example;


//find letter location
//https://stackoverflow.com/questions/7314573/how-to-get-coordinates-of-recognized-characters#:~:text=Try%20the%20executable%20of%20tesseract.%20Use%20the%20command,the%20coordinates%20of%20each%20character%2C%20one%20per%20row.


public class Main {


    public static void main(String[] args) throws Exception{
        String image_path = "cross_puzzle_test.png";
        String data_path = "tessdata";
        Image_Procs.OCR_Char(image_path,data_path,true);
}
}