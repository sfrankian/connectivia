/* Game.java
 * Group: Stephanie, Brenna, and Caylene
 *  Connectivia Game
 *  Implemented by Stephanie and Brenna
 * Purpose: Game.java simulates a game of Connectivia. 
 */

import javax.swing.JOptionPane;


public class Game {
  public int playerOneLives, playerTwoLives, gameWinner;
  public Board b;
  private QuestionsAndAnswers qa;
  public String file;
  
  //---------------------------------------------------
  // Constructor: both players start with three lives
  //---------------------------------------------------
  
  public Game(String filename) {
    playerOneLives = 3;
    playerTwoLives = 3;
    b = new Board();
    qa = new QuestionsAndAnswers(filename);
    gameWinner = 0;
    file = filename;
  }
  
  //---------------------------------------------------
  // determineWinner() checks for winning by opponent's 
  // loss of lives or by achieving a connect-four on the
  // game board
  //---------------------------------------------------
  public int determineWinner() {
    // player one runs out of lives; player two wins
    if(playerOneLives <= 0) {
      gameWinner = 2;
    }
    // player two runs out of lives; player one wins
    if(playerTwoLives <= 0) {
      gameWinner = 1;
    }
    // someone wins the board
    if (b.getWinner() != 0){
      gameWinner = b.getWinner();
    } 
    return gameWinner;
  }
  
  //---------------------------------------------------
  // askQuestion() pops up a dialogue box with a 
  // question for the user. It returns true if the user
  // answered correctly; false, incorrectly. It also
  // decrements a life and takes away a turn to place 
  // a token if the user answers incorrectly.
  //---------------------------------------------------
  public boolean askQuestion() {
    // generate random question and answer choices
    String currentQ = qa.generateRandomQ();
    String [] answers = qa.generateAnswers(currentQ);
    
    // dialogue box
    String s = (String) JOptionPane.showInputDialog (null, currentQ, 
                                                     "Answer carefully, or a life will be deducted!", 
                                                     JOptionPane.PLAIN_MESSAGE, null, 
                                                     answers, answers[0]);
    boolean correct = qa.isCorrect(currentQ,s);
    
    // case where user answers incorrectly
    if (!correct) {
      // decrement life, give opponent turn
      if (b.getCurrentTurn()==1){
        playerOneLives--;
        b.currentTurn=2; 
      } else {
        playerTwoLives--;
        b.currentTurn = 1;
      }  
    }  
    return correct;    
  }
  
}