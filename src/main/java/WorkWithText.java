package main.java;

import java.io.*;
import java.util.*;

public class WorkWithText {

    private static final String WORDS = "src/main/resources/1500 popular nouns in English.txt";
    private static final String FIGURE = "src/main/resources/figure.txt";
    private static String hidenWord;
    private static char [] visibleLetters;

    private static char [] charsOfHidenWords;

    static char [] getCharsOfHidenWord(){
        return charsOfHidenWords;
    }

    private static final ArrayList<String> listWithWords = new ArrayList<>();
    private static  ArrayList<String> listOfFigures = new ArrayList<>();


    public static String getHidenWord() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(WORDS)))) {
            while (reader.ready()) {
                String str = reader.readLine().toLowerCase();
                if (str.length() < 4) {
                    continue;
                }
                listWithWords.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listWithWords.get((int) (Math.random() * (listWithWords.size())));
    }

    public static char[] startWithTwoLetters(){
        hidenWord = getHidenWord();
        charsOfHidenWords = hidenWord.toCharArray();
        visibleLetters = new char[hidenWord.length()];
        Arrays.fill(visibleLetters, (char) 95);
        int firstOpenLetter = (int)(Math.random() * (hidenWord.length()));
        int secondOpenLetter = (int)(Math.random() * (hidenWord.length()));
        visibleLetters[firstOpenLetter] = hidenWord.charAt(firstOpenLetter);
        boolean isCopy = true;
        loopWhile:
        while(isCopy){
            for(char c : visibleLetters){
                if(c == hidenWord.charAt(secondOpenLetter)){
                    secondOpenLetter = (int)(Math.random() * (hidenWord.length()));
                    continue loopWhile;
                }
            }
            visibleLetters[secondOpenLetter] = hidenWord.charAt(secondOpenLetter);
            isCopy = false;

        }
        for(int i = 0; i < charsOfHidenWords.length; i++) {
            if (i == firstOpenLetter || i == secondOpenLetter) {
                continue;
            }
            if (charsOfHidenWords[i] == visibleLetters[firstOpenLetter] || charsOfHidenWords[i] == visibleLetters[secondOpenLetter]) {
                visibleLetters[i] = charsOfHidenWords[i];
            }
        }
        return visibleLetters;
    }
    public static Set<Character> addTwoOpenLetters(){
        Set<Character> setOfOpenLetters = new HashSet<>();
        for(char c : visibleLetters){
            if(c != (char) 95){
                setOfOpenLetters.add(c);
            }
        }
        return setOfOpenLetters;
    }

    public static ArrayList<String> getFigure() {
        StringBuilder strB = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FIGURE)))) {
            while (reader.ready()) {
                String str = reader.readLine() + "\n";
                if(str.contains("!")){
                    listOfFigures.add(strB.toString());
                    strB.delete(0, strB.length());
                    continue;
                }
                strB.append(str);
            }
        }
    catch(IOException e)

    {
        e.printStackTrace();
    }
        return listOfFigures;
}



}
