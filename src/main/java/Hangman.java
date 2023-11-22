package main.java;

import java.util.*;

public class Hangman {

    private Scanner scanner = new Scanner(System.in);

    boolean isWon = false;
    private int countMistakes = 0;

    public void exitOrNewGame(){
        boolean next = false;
        System.out.println("\nstart a new game? Y : N");
        while (!next){
            String yOrN = scanner.next();
            if(!yOrN.equalsIgnoreCase("y") && !yOrN.equalsIgnoreCase("n")){
                System.out.println("enter correct letter , y : n");
            } else {
                if(yOrN.equalsIgnoreCase("y")){
                    next = true;
                    startGame();
                } else{
                    System.out.println("Game over");
                    return;
                }
            }
        }


    }


    private void startGame(){
       char [] visibleLetters = WorkWithText.startWithTwoLetters();
       char [] charsOfHidenWord = WorkWithText.getCharsOfHidenWord();
       List<String> listOffigures = WorkWithText.getFigure();
       Set<Character> usedLetters = WorkWithText.addTwoOpenLetters();
       while(countMistakes <= 6 && !isWon){
           System.out.println(visibleLetters);
           System.out.printf("Enter the next letter \n%s", listOffigures.get(countMistakes));
         char charChoised = scanner.next().toLowerCase().charAt(0);
         if(usedLetters.contains(charChoised)){
             System.out.println("This letter used already, enter another letter");
         } else{
             usedLetters.add(charChoised);
             int count1 = 0;
             for(int i = 0; i < charsOfHidenWord.length; i++){
                 if (charsOfHidenWord[i] == charChoised){
                     count1++;
                     System.out.println("one more letter guessed, nice!");
                     usedLetters.add(charChoised);
                     visibleLetters[i] = charsOfHidenWord[i];
                 }
                 int count  = 0;
                 for(char c : visibleLetters){
                     if (c == 95){
                         count++;
                     }
                 }
                 if(count == 0){
                     System.out.printf("You won, congratulations!!!\nguessed word was ***   %s  ***", String.valueOf(charsOfHidenWord));
                     isWon = true;
                     break;
                 }
             }
             if(count1 == 0){
                 countMistakes++;
                 if(countMistakes == 6){
                     System.out.println(listOffigures.get(6));
                     System.out.printf("Game Over, you made 6 mistakes, \n guessed word was ***   %s  ***", String.valueOf(charsOfHidenWord));
                     break;
                 }
             }
         }
       }
       countMistakes = 0;
        isWon = false;
       exitOrNewGame();
    }

}
