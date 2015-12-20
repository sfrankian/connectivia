/* QuestionsAndAnswers.java
 * Team: Caylene, Stephanie, and Brenna
 * Written by Brenna
 * 
 * Purpose:  Handles the trivia part of the Connectivia application.  The reads in a text file
 * and stores the questions and answers in the text file into a Hash Table.  The questions
 * are then randomly generated as the game is played.
 */

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class QuestionsAndAnswers {
  
  String filename;
  Hashtable<String, String> qa;
  String [] questions;
  String [] answers;
  int numberOfQuestions;
  
  public QuestionsAndAnswers(String filename) {
    this.filename = filename;
    
    // get number of lines to set array sizes
    // code derived from:
    // http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
    try {
      LineNumberReader lnr = new LineNumberReader(new FileReader(new File(filename)));
      lnr.skip(Long.MAX_VALUE);
      int numberOfLines = lnr.getLineNumber()+1;
      numberOfQuestions = numberOfLines;
      int count = 0; //keep track of line number for array insertion  
      // arrays allow us to obtain random answers/questions using integers
      questions = new String[numberOfLines];
      answers = new String[numberOfLines];
      
      // hashtable allows us to quickly search for a answer/question pair
      qa = new Hashtable<String, String>(numberOfLines);
      
      Scanner reader = new Scanner(new File(filename));
      while (reader.hasNext()) {
        String line = reader.nextLine();
        String[] splitline = line.split(","); //assume q and a separated by comma
        qa.put(splitline[0], splitline[1]);
        questions[count] = splitline[0];
        answers[count] = splitline[1];
        count++;
      }
    } catch (IOException ex) {
      System.out.println(ex);
    }
    
  }
  
  
  
  /* toString()
   * Returns a string represention of the current instance*/
  public String toString(){
    String s = "";
    s+=qa.toString();
    return s;
  }
  
  /*generateRandomQ() searches generates a random number and returns a 
   * random question in the hashtable*/
  public String generateRandomQ() {
    Random rand = new Random();
    int num = rand.nextInt(numberOfQuestions);
    return questions[num];
  }
  
  /*generateAnswers() returns an array of Strings based on the given 
   * question. The array is randomly populated with the 3 incorrect answers to 
   * other questions and the one correct answer*/
  public String[] generateAnswers(String question) {
    Random rand = new Random();
    String [] setOfAnswers = new String[4];
    
    // add right answer at a randomly generated index
    int randIndex = (int)(Math.random() *4);
    setOfAnswers[randIndex] = qa.get(question);
    
    // add false answers through random array selection
    // make sure the right answer is only included once
    // we do allow for duplicate wrong answers, though it is rare
    for(int i = 0; i<4; i++) {
      if(setOfAnswers[i] == null) {
        int current = rand.nextInt(numberOfQuestions);
        if (!answers[current].equals(setOfAnswers[randIndex])) {
          setOfAnswers[i] = answers[current];
        } 
        else {
          current = rand.nextInt(numberOfQuestions);
          setOfAnswers[i] = answers[current];
        } 
      }
    }
    return setOfAnswers;
  }
  
  //isCorrect() checks if the given answer matches the correct question to the question
  public boolean isCorrect(String question, String answer) {
    return (qa.get(question).equals(answer));
  }
  
  // MAIN METHOD: TESTING
  //testing
  public static void main(String[] args) {
    QuestionsAndAnswers capitals = new QuestionsAndAnswers("country_capitals.txt");
    //System.out.println(capitals);
    String q = capitals.generateRandomQ();
    System.out.println(q+"\n");
    String[] setOfAnswers = capitals.generateAnswers(q);
    for (int i = 0; i<4; i++) {
      System.out.println(setOfAnswers[i]);
    }
    System.out.println("\nShould be true: " + capitals.isCorrect(q, setOfAnswers[0]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[1]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[2]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[3]));
    
    // testing presidential
    
    QuestionsAndAnswers presidents = new QuestionsAndAnswers("presidential_trivia.txt");
    String qp = presidents.generateRandomQ();
    System.out.println(qp+"\n");
    String[] setOfAnswersp = presidents.generateAnswers(qp);
    for (int i = 0; i<4; i++) {
      System.out.println(setOfAnswersp[i]);
    }
    System.out.println("\nShould be true: " + presidents.isCorrect(qp, setOfAnswersp[0]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[1]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[2]));
    System.out.println("Should be false: " + capitals.isCorrect(q, setOfAnswers[3]));
    
  }
  
}