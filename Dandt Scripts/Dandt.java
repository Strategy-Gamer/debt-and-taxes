package DandtScripts;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException; //Import the IOException class to handle errors

public class Dandt{
    private static String currentDir;

    public static void main(String[] args){

        //Gets the absolute path to this directory and sets the currentDir var to it
		try {
			currentDir = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }

        //Replaces the input in the output.txt file with usable HOI4 code 
        //String[] t = FileManipulator.readFile("output.txt", "DandtScripts");
        //String[] types = Data.RESOURCES;
        //int numStart = 2;
        //FileManipulator.writeToFile("output.txt", "DandtScripts", convertToString(t) + "\n################################################\n" + createCode(t, types, types, numStart, 0));
        
        Demographics.createColorCode("Religion", Demographics.RELIGION_LIST);
    }

    public static String createCode(String[] text, String[] replace, String[] replaceTwo, int numStart, int tabs){
        String[] endText = text;
        String t = convertToString(text);
        int startLine = 0;
        int endLine = 0;
        String find = "[Type]";
        if(t.contains(find)){
            while(convertToString(endText).contains(find + "_START")){
                startLine = FileManipulator.getLine(endText, 0, find + "_START");
                endLine = FileManipulator.getLine(endText, startLine, find + "_END");
                t = "";
                String tbs = "";
                String tbsStr = convertToString(getLines(endText, startLine, startLine+1));
                while(tbsStr.charAt(0) == ' '){
                    tbsStr = tbsStr.substring(1);
                    tbs += " ";
                }
                String tb = tbs + "\t";
                for(int i = 0; i < replace.length; i++){
                    t += tb + "#" + replace[i];
                    String s = convertToString(getLines(endText, startLine+1, endLine));
                    s = findAndReplaceAll(s, "[Type_Num]", "" + (i + numStart));
                    s = findAndReplaceAll(s, "[Type_Two]", toCode(replaceTwo[i]));
                    s = findAndReplaceAll(s, find, toCode(replace[i]));
                    t += newLines(s, 1);
                    t += tbs + "#";
                    if(i < replace.length-1){
                        t+= "\n";
                    }
                }
                endText = removeLines(endText, startLine, endLine);
                endText[startLine-1] += "\n" + tbs + "#List";
                endText[startLine-1] += newLines(t, tabs);
                endText[startLine-1] += "\n" + tbs + "#";
            }
        }
        return convertToString(endText);
    }

    public static String findAndReplaceAll(String text, String find, String replace){
        String t = text;
        int i = 0;
        while(t.contains(find)){
            t = t.replace(find, replace);
            i++;
            if(i > 1000){
                System.out.print("error");
                break;
            }
        }
        return t;
    }

    public static String[] getLines(String[] text, int start, int end){
        if(text.length > 0 && start < end){
            String[] t = new String[end - start];
            for(int i = start; i < end; i++){
                t[i - start] = text[i];
            }
            return t;
        }
        return new String[]{""};
    }

    /**
     * Removes the lines of a String array between the starting and ending lines (both inclusive).
     * @param text String array to be adjusted
     * @param start Starting index of the lines to be removed (Inlcusive)
     * @param end Ending index of the lines to be removed (Inlcusive)
     * @return A new shortened String array.
     */
    public static String[] removeLines(String[] text, int start, int end){
        int length = end - start;
        if(length > 0){ //If the section actually exists
            if(text.length - length - 1 < 0){
                length = text.length - 1;
            }
            String[] newText = new String[text.length - length - 1];
            for(int i = 0; i < text.length; i++){
                if(i < start){
                    newText[i] = text[i];
                }
                else if(i > end){
                    newText[i - length - 1] = text[i];
                }
            }
            return newText;
        }
        return text;
    }

    /**
     * Adds new blank lines to a String array. New lines have the contents of "".
     * @param text String array to be adjusted
     * @param start Starting index of the lines to be added. New lines are added after this point.
     * @param num Number of lines to add
     * @return A new lengthed String array.
     */
    public static String[] addLines(String[] text, int start, int num){
        if(num > 0){ //If there are lines to actually add
            String[] newText = new String[text.length + num];
            for(int i = 0; i < newText.length; i++){
                if(i <= start){
                    newText[i] = text[i];
                }
                else if(i > start + num){
                    newText[i] = text[i-num];
                }
                else{
                    newText[i] = "";
                }
            }
            return newText;
        }
        return text;
    }

    /**
     * Takes a String array and converts it into a normal String
     * @param arr The array to be converted
     * @return A new String where every index in the array is treated as a separate line.
     */
    public static String convertToString(String[] arr){
        String t = "";
        for(String s : arr){
            t += s + "\n";
        }
        return t;
    }

    /**
     * Takes an ArrayList of Strings and converts it into a String Array
     * @param arr The array to be converted
     * @return A new String Array
     */
    public static String[] convertToStringArray(ArrayList<String> arr){
        String[] text = new String[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            text[i] = arr.get(i);
        }
        return text;
    }

    /**
     * Returns a String with the spaces removed
     * @param string The string to be adjusted
     * @return The string with spaces removed
     */
    public static String noSpaces(String string){
        return string.replace(" ", "");
    }

    /**
     * Returns a String with the spaces replaced with underscores and all lower-case.  
     * @param string The string to be adjusted
     * @return The adjusted string
     */
    public static String toCode(String string){
        return string.replace(" ", "_").toLowerCase();
    }

    /**
     * Returns the proper end for variable localization. If it is a modifier, it will return {@code 1%} if it's a modifier, {@code 3} if not. Also contains specific cases.
     * <p>
     * [?variable|locEnd] - locEnd being what this returns
     * @param string The name of the variable.
     * @return LocEnd
     */
    public static String getVariableLocEnd(String string){
        if(string.contains("modifier") || string.contains("Modifier")){
            return "1%";
        }
        if(string.contains("multiplier") || string.contains("Multiplier")){
            return "2";
        }
        if(string.contains("Building Slots")){
            return "0";
        }
        return "3";
    }


    /**
     * Adds new lines to a string. Starts with a new line char. Adds the additional tabs after any new line char (including the first). 
     * <p> 
     * Use is {@code string += newLines(line, tabs)}
     * @param line Line(s) to add
     * @param tabs Number of tabs to add after every new line character.
     * @return A new string.
     */
    public static String newLines(String line, int tabs){
        String t = "";
        for(int i = 0; i < tabs; i++){
            t += "\t";
        }
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '\n'){
                line = line.substring(0, i+1) + t + line.substring(i+1);
            }
        }
        return "\n" + t + line;
    }

    /**
     * Adds a new line to a string. Starts with a new line char. Adds the additional tabs after any only that first new line char. 
     * <p> 
     * Use is {@code string += newLines(line, tabs)}
     * @param line Line to add
     * @param tabs Number of tabs to add after the first new line character.
     * @return A new string.
     */
    public static String newLine(String line, int tabs){
        String t = "";
        for(int i = 0; i < tabs; i++){
            t += "\t";
        }
        return "\n" + t + line;
    }

    public static String newLocEntry(String key, String loc, int tabs){
        return newLine(key + ":0 \"" + loc + "\"", tabs);
    }
    
	public static BufferedImage applyColor(Image image, Color color){
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
		g.dispose();
		return dyed;
    }

    public static String getCurrentDir() {
        return currentDir;
    }
}