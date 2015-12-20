/* Board.java
 * Team: Stephanie, Brenna, and Caylene  
 *  Connectivia Game
 * Written by Stephanie
 * Purpose: The Board.java class controls the Connect Four aspect of Connectivia.  Through the use of a 2D array,
 * the class handles game tokens placed into its board and determines whether there was a winner or not.
 */
import java.util.*;

public class Board {
  //instance variables
  private int board[][];
  private int playerOne, playerTwo,  winner;
  public int currentTurn;
  
  //the Board() constructor
  public Board() {
    /*uses a two dimension array to hold a representation of the Connect Four tokens.
     * Player One's Token is represented by the integer, 1, and Player Two's is represented
     * by 2.  
     */
    board = new int[6][7];  
    playerOne = 1;
    playerTwo = 2;
    currentTurn = 1;
    winner = 0;
  }
  
  // getters
  public int getWinner() {
    return winner;
  }
  public int getCurrentTurn(){
    return currentTurn; 
  }
  public int whoseTurn() {
    return currentTurn;
  }
  
  //setter to change the player whose turn it is
  public void changeTurn(){
    if (currentTurn == 1) {
      currentTurn = 2; 
    } else {
      currentTurn = 1;
    }
  }
  
  /* placeToken()
   * This method takes in user input and places
   * the token of the current player into the board based on this 
   * value.  This zero-parameter placeToken() is solely 
   * used for testing of the Board class  */
  public void placeToken() {
    System.out.println("Enter a column number: ");
    Scanner scan = new Scanner(System.in);
    int column = scan.nextInt();
    //if the user selects an invalid column, they will be prompted to choose again
    if(column <=6 && column>=0) {
      for(int x = 5; x>=0; x--) {
        if(board[x][column] == 0) {
          board[x][column] = currentTurn;
          break;
        }
      }
      if(currentTurn == playerOne) {
        currentTurn = playerTwo;
      }
      else {
        currentTurn = playerOne;
      }  
    }
    else {
      System.out.println("Choose a valid column!");
      placeToken();
    }
  }
  
  
  /* findColumnLow(int column)
   * This method takes a int value that represents a column number. It then searches
   * for the first open spot in the column and returns the position of this open
   * slot. */
  public int findColumnLow(int column){
    for(int x = 5; x>=0; x--) {
      if(board[x][column] == 0) {
        return x;
      }
    }
    return 0;
  }
  
  
  /* placeToken(int column)
   * This method takes an integer value as a parameter and places
   * the token of the current player into the board based on this 
   * value. The integer is retrieved based on which column button the 
   * user presses in the GUI. */
  public void placeToken(int column) {
    int columnLow = findColumnLow(column);
    board[columnLow][column] = currentTurn;
    changeTurn();
  }
  
  /*toString()
   * a String representation of the ConnectFour board*/
  public String toString() {
    String s = "";
    for(int i = 0; i<6; i++) {
      for(int j =0; j<7; j++) {
        s += board[i][j];
        s += "  ";
      }
      s += "\n";
    }
    return s;
  }
  
  /*checkTie() 
   * Loops through the 2d array representing the connect four board to make sure
   * there was not tied. A game would be tied if every single token was placed and
   * no one had four in a row. */
  public boolean checkTie() {
    for(int x =0; x<6; x++) {
      for(int y =0; y<7; y++) {
        if(board[x][y] == 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  /* checkColumnVictory()
   * Loops through the entire gameboard and returns true if it finds any 
   * instance where there are 4 tokens of the same kind in a column. It also
   * updates the instance variable, winner, to indicate if player One or 
   * player Two had the winning 4 in a column move.*/
  public boolean checkColumnVictory() {
    for(int y =0; y<7; y++) {
      //current represents the number of tokens in a column
      int current =0;
      for(int x = 1; x<6; x++) {
        if(board[x][y] != 0 && board[x][y] == board[x-1][y]) {
          current++;
        }
        else{
          current = 1;
        }
        //if current is greater than or equal to 4, then there are 4+ tokens 
        //of the same variety in the same column row
        if(current >= 4) {
          winner = board[x][y];
          return true;
        }
      } 
    } 
    return false;
  }
  
  /* checkRowVictory()
   * Loops through the entire gameboard and returns true if it finds any 
   * instance where there are 4 tokens of the same kind in a row. It also
   * updates the instance variable, winner, to indicate if player One or 
   * player Two had the winning 4 in a row move.*/
  public boolean checkRowVictory() {
    for(int x =0; x<6; ++x) {
      int currentInARow =1;
      for(int y = 1; y<7; y++) {
        if(board[x][y] != 0 && board[x][y] == board[x][y-1]) {
          currentInARow++;
        }        
        if(currentInARow >= 4) {
          winner = board[x][y-1];
          return true;
        }     
      }      
    } 
    return false;
  }
  
  
  /* checkDiagonalVictory()
   * There are two diagonal scenarios in Connect Four. checkDiagonalVictory() looks to see if
   * either one of these types of diagonals are present in the board. If any of these scenarios 
   * are satisfied, then true is returned and the method ends.  Otherwise, we check each 
   * scenario and if there is no diagonal victory, false is returned.
   */
  public boolean checkDiagonalVictory() {
    //Scenario One:
    for(int x = 3; x<6; x++) {
      for(int y =0; y<4; y++) {
        if(board[x][y]!= 0) {
          if(board[x][y] == board[x-1][y+1] && board[x][y]== board[x-2][y+2] && board[x][y]== board[x-3][y+3]) {
            winner = board[x][y];
            return true;
          }
        }
      }
    }
    
    //Scenario Two:
    for(int y = 0; y<4; y++) {
      for(int x =0; x<3; x++) {
        if(board[x][y]!= 0) {
          if(board[x][y] == board[x+1][y+1] && board[x][y]== board[x+2][y+2] && board[x][y]== board[x+3][y+3]) {
            winner = board[x][y];
            return true;
          }
        }
      }
    }    
    //if true wasn't already returned, then false must be returned
    return false;  
  }
  
  //isGameOver() returns true if the game was won in any of the 3 different ways
  //or if a tie occurred.
  public boolean isGameOver() {
    return checkColumnVictory() || checkRowVictory() || checkTie() || checkDiagonalVictory();
  }
  
  //main method for testing
  public static void main(String[] args) {
    Board b = new Board();    
    while(b.isGameOver() == false) {
      System.out.println("It is Player " + b.getCurrentTurn() + "'s turn.");
      b.placeToken();
      System.out.println(b.toString());     
    }
    //prints winner if there was a winner
    if(b.getWinner() != 0) {
      System.out.println("Winner was Player " + b.getWinner() + "!");
    }
    if(b.getWinner() == 0) {
      System.out.println("Tie!");
    }
  }  
}