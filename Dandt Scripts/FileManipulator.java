package DandtScripts;

import java.io.File;
import java.io.FileNotFoundException; //Import to handle errors
import java.util.Scanner; //Import to read text files
import java.util.ArrayList;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.io.FileWriter; //Import the FileWriter class
import java.io.IOException; //Import the IOException class to handle errors

import javax.imageio.ImageIO;

public class FileManipulator {

    /**
     * Returns all files and folders of a directory. This does not include folders or files within the folders in the chosen directory. 
     * @param directory The directory to be read from
     * @return A File Array of every file in the directory. It is not guaranteed to be in alphabetical order.
     * @see File
     */
    public static File[] getAllFilesInDirectory(String directory){
        File dir = new File(Dandt.getCurrentDir() + "/" + directory); //The directory
        return dir.listFiles();
    }





    /**
     * Reads from a file and returns its contents as a String array.
     * @param fileName The name of the file (File extension included)
     * @param directory The path to the file from the debtandtaxes folder
     * @param text The text to replace the file's content with
     * @return An ArrayList of Strings of each line in the file. If the file fails to be read, it returns a zero length String array.
     */
    public static String[] readFile(String fileName, String directory){
        return readFile(new File(Dandt.getCurrentDir() + "/" + directory + "/" + fileName));
    }

    /**
     * Reads from a file and returns its contents as a String array.
     * @param file The file to be read from
     * @return An ArrayList of Strings of each line in the file. If the file fails to be read, it returns a zero length String array.
     */
    public static String[] readFile(File file){
        try {
            Scanner scan = new Scanner(file);
            ArrayList<String> data = new ArrayList<String>();

            //Loops through the data and adds it to the ArrayList 
            while (scan.hasNextLine()){
                data.add(scan.nextLine());
            }
            scan.close();
            return Dandt.convertToStringArray(data);
        } 
        catch (FileNotFoundException e) {
            //System.out.println("An error has occured after attempting to read from " + file.getAbsolutePath());
            //e.printStackTrace();
        }
        return new String[0];
    }



    /**
     * Finds the first line of the String array at or after the starting line with the defined text 
     * @param text Text to parse through
     * @param start The line in the text to start parsing at
     * @param string The string to find
     * @return The line where the string is found. Returns -1 if it is not found or if the starting index is larger than the size of the text.
     */
    public static int getLine(String[] text, int start, String string){
        if(start < 0){
            start = 0;
        }
        if(start < text.length){
            for(int i = start; i < text.length; i++){
                if(text[i].contains(string)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Parses through the provided line and returns the first number in the text. 
     * <p>
     * Starts at the first number (Or a negative sign) that is spaced between another alphabetical letter by at least one non-alphabetical letter. Ends at a non-number. 
     * <p>
     * Returns 0 if no number is found.
     * @param text Text to parse through
     * @return The first number in the text.
     */
    public static int getNumber(String text){
        boolean nonAlphabeticalLetter = true;
        String number = "";

        //Parse through each character in the line
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if((c >= '0' && c <= '9') || (c == '-' && number.length() == 0)){
                if(nonAlphabeticalLetter){
                    number += c;
                }
            }
            else if(number.length() > 0){
                return Integer.parseInt(number);
            }
            else if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
                nonAlphabeticalLetter = false;
            }
            else{
                nonAlphabeticalLetter = true;
            }
        }
        if(number.length() > 0){
            return Integer.parseInt(number);
        }
        return 0;
    }

    /**
     * Finds the matching curly brace of the first open curly brace in the starting line in the provided text. There may be multiple curly braces per line. If the starting line has no curly braces, then this returns the starting line.
     * @param text Text to parse through
     * @param start The line in the text to start parsing at
     * @return The line where the matching curly brace is found. Returns -1 if not found. (If so, fix your code)
     */
    public static int findMatchingCurlyBrace(String[] text, int start){
        int unmatchedBraces = 0;
        int i = start;
        do{
            //Parse through each character in the line
            for(int c = 0; c < text[i].length(); c++){
                if(text[i].charAt(c) == '{'){
                    unmatchedBraces++;
                }
                else if(text[i].charAt(c) == '}' && unmatchedBraces > 0){
                    unmatchedBraces--;
                }
            }
            if(unmatchedBraces == 0){
                return i;
            }

            i++;
        } while(i < text.length);

        return -1;
    }





    /**
     * Writes to a file (Replacing the entire file)
     * @param fileName The name of the file (File extension included)
     * @param directory The path to the file from the debtandtaxes folder
     * @param text The text to replace the file's content with
     */
    public static void writeToFile(String fileName, String directory, String text){
        writeToFile(new File(Dandt.getCurrentDir() + "/" + directory + "/" + fileName), text);
    }

    /**
     * Writes to a file (Replacing the entire file)
     * @param file The file to be written to
     * @param text The text to replace the file's content with
     */
    public static void writeToFile(File file, String text){
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath()); //Creates the writer to the file
            writer.write(text);
            writer.close();
        } 
        catch (IOException e) {
            System.out.println("An error has occured after attempting to write to " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    /**
     * Writes to a file (Replacing the entire file)
     * @param fileName The name of the file (File extension included)
     * @param directory The path to the file from the debtandtaxes folder
     * @param text The text to replace the file's content with in an array form where every index in the array is treated as a separate line.
     */
    public static void writeToFile(String fileName, String directory, String[] text){
        writeToFile(fileName, directory, Dandt.convertToString(text));
    }

    /**
     * Writes to a file (Replacing the entire file)
     * @param file The file to be written to
     * @param text The text to replace the file's content with
     */
    public static void writeToFile(File file, String[] text){
        writeToFile(file, Dandt.convertToString(text));
    }

    /**
     * Writes to a new file with an image, making the file a png
     * @param image The image to replace the contents of the file with.
     * @param fileName The name of the file (File extension included)
     * @param directory The path to the file from the debtandtaxes folder
     */
    public static void exportImage(BufferedImage image, String fileName, String directory){
        File outputfile = new File(Dandt.getCurrentDir() + "/" + directory + "/" + fileName + ".png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    
    public static Image getGraphicsImage(String name, String startingDirectory){
        return new ImageIcon(getGraphicsFile(name, startingDirectory)).getImage();
    }
    public static String getGraphicsFile(String name, String startingDirectory){
        String location = "";
        File file = new File(Dandt.getCurrentDir() + "/" + startingDirectory);
        File[] list = file.listFiles();
        if(list!=null)
        for (File fil : list){
            if(fil.getName().equals(name + ".png")){
                return fil.getParentFile() + "\\" + fil.getName();
            }
            location = findFile(name + ".png",fil);
            if(location.length() > 0){
                return location;
            }
        }
        return null; //returns null if nothing is found
    }
    private static String findFile(String name, File file){
        File[] list = file.listFiles();
        if(list!=null)
        for (File fil : list){
            String location = "";
            if(fil.isDirectory()){
                location = findFile(name,fil);
                if(location.length() > 0){
                    return location;
                }
            }
            else if(name.equals(fil.getName())){
                return fil.getParentFile() + "\\" + name;
            }
        }
        return "";
    }

    public static void addToFile(String fileName, String directory, String text){
        String[] t = readFile(fileName, directory);
        if(t.length > 0){
            t[t.length-1] += "\n" + text;
            writeToFile(fileName, directory, t);
        }
        else{
            writeToFile(fileName, directory, "#This file is automatically created by the script in /DandtScripts.\n\n" + text);
        }
    }

    public static void deleteFile(String fileName, String directory){
        File file = new File(Dandt.getCurrentDir() + "/" + directory + "/" + fileName);
        if(!file.delete()){
            System.out.println("Failed to delete " + file.getName());
        }
    }
}
