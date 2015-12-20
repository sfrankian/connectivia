/* BoardPanel.java
 * Group: Brenna Carver, Caylene Parrish, Stephanie Frankian
 * Written by: Caylene and Brenna
 * 
 * Purpose: The BoardPanel class runs the Game class and represents
 * the results on the GUI via a virtual representation of the ConnectFour game.
 * Questions are generated and created via the QuestionsAndAnswers class, an
 * instance of which is generated and utilized in the Game class. These questions 
 * are asked via a Dialog window box, and the choosing of a correct answer from
 * the drop down menu results in the placement of the token.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class BoardPanel extends JPanel {
  //instance variables
  JButton[] columns;
  JButton playAgain;
  JLabel[][] labels; 
  ImageIcon redImg = createImageIcon("red.png","red token");
  ImageIcon blackImg = createImageIcon("black.png","black token");
  ImageIcon emptyImg = createImageIcon("empty.png","empty token");
  Game game;
  JLabel status;
  LinkedList<Integer> playerOneWins;
  LinkedList<Integer> playerTwoWins;
  
  //---------------------------------------------------
  // Constructor takes Game object as input, creates
  // panel on GUI where game play takes place
  //---------------------------------------------------
  
  public BoardPanel(Game g) {
    //creates new LinkedLists
    playerOneWins = new LinkedList<Integer>();
    playerTwoWins = new LinkedList<Integer>();
    game =g;
    JPanel pan = new JPanel();
    pan.setLayout(new GridLayout(6,0));
    JPanel down = new JPanel();
    down.setLayout(new GridLayout(7,0));
    //creates JButton array and JLabel 2-d array
    //and initializes the labels to be the empty image
    columns = new JButton[6]; 
    labels = new JLabel[7][6];
    for(int i=0; i<6; i++){
      columns[i]= new JButton("" +(i+1));
      this.add(columns[i]);
      columns[i].addActionListener(new ButtonListener());
    }
    for(int j=0; j<6;j++){
      for(int k=0; k<6;k++){
        labels[j][k]= new JLabel();
        labels[j][k].setIcon(emptyImg);
        down.add(labels[j][k]);
      }
    }
    JPanel j = new JPanel();
    //starting status
    status = new JLabel("It is currently Black's Turn. Red's turn coming up!");
    playAgain = new JButton("Play Again"); 
    playAgain.addActionListener(new ButtonListener());
    add(pan);
    add(down);  
    j.add(status);
    add(j);
    add(playAgain);
    setPreferredSize (new Dimension(700,600)); 
    j.setBackground(new Color(240,250,142)); 
  }
  
  
  /** 
   * Creates and returns an ImageIcon out of an image file.
   * @param path The path to the imagefile relevant to the current file.
   * @param description A short description to the image.
   * @return ImageIcon An ImageIcon, or null if the path was invalid. 
   */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = Game.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  //----------------------------------------------------
  // playAnotherMatch() resets all "token spaces" to be
  // empty and creates a new Game object
  //----------------------------------------------------
  private  void playAnotherMatch() {
    //reset labels
    for(int j=0; j<6;j++){
      for(int k=0; k<6;k++){
        labels[j][k].setIcon(emptyImg);
      }
    }
    game = new Game(game.file);
    status.setText("New Game! It is currently Black's turn. Black lives = " +
                   game.playerOneLives + " ; Red lives = " + game.playerTwoLives);
  }
  
  //----------------------------------------------------
  // enableButtons() and disableButtons() used for game
  // ending/beginning
  //----------------------------------------------------
  private void disableButtons(){
    for(int k=0;k<6;k++){
      columns[k].setEnabled(false);     
    } 
  }
  private void enableButtons(){
    for(int k=0;k<6;k++){
      columns[k].setEnabled(true);     
    } 
  }
  
  //----------------------------------------------------
  // For when a user clicks on a column button -- 
  // question pops up, token is placed or a life is 
  // decremented, and a status label reports whose turn
  // it is, how many lives are left, and who wins.
  //----------------------------------------------------
  private class ButtonListener implements ActionListener {   
    public void actionPerformed(ActionEvent event) {
      /* find lowest place in column selected -- 
       * this is where token will be placed
       */
      
      for(int i=0; i<6;i++){
        if(event.getSource()==columns[i]){
          int row = game.b.findColumnLow(i);
          
          /*****************************************************
            * Grand if/else: User answers correctly/incorrectly
            * *************************************************
            */
          
          if(game.askQuestion()){
            game.b.placeToken(i);
            
            // nested if/else: game over/not over
            if (game.b.isGameOver()) {
              int winner = game.b.getWinner();
              disableButtons();
              
              // find winner, set status, update LinkedLists
              if (winner==1) {
                labels[row][i].setIcon(blackImg);
                status.setText("Black wins the game! Congratulations! Press 'play again' for another round.");
                playerOneWins.add(1);
                playerTwoWins.add(0);
              } else if (winner==2) {
                labels[row][i].setIcon(redImg);
                status.setText("Red wins the game! Congratulations! Press 'play again' for another round.");
                playerOneWins.add(0);
                playerTwoWins.add(1);
              }
              
            } // end of "if game is over" statement
            
            // begin "game is not over" statement
            else {
              // user places token, statuses update
              if(game.b.getCurrentTurn()==1){
                labels[row][i].setIcon(redImg);
                status.setText("Correct! It is currently Black's turn. Black lives = " +
                               game.playerOneLives + " ; Red lives = " + game.playerTwoLives);
              }
              else if(game.b.getCurrentTurn()==2){
                labels[row][i].setIcon(blackImg);
                
                status.setText("Correct! It is currently Red's turn. Black lives = " +
                               game.playerOneLives + " ; Red lives = " + game.playerTwoLives);
              }
            }
          }
          
          
          /***************************************
            * End grand "if user answers correctly;"
            * begin grand else "user answers incorrectly"
            * ***********************************
            */
          
          
          else {
            // check if lives are at zero  
            if (game.playerOneLives==0){
              status.setText("Red won the game! Congratulations! Press 'play again' for another round.");
              playerOneWins.add(0);
              playerTwoWins.add(1);
              disableButtons();
            }
            else if (game.playerTwoLives==0){
              status.setText("Black won the game! Congratulations! Press 'play again' for another round.");
              playerOneWins.add(1);
              playerTwoWins.add(0);
              disableButtons();
            }   
            //if not at zero, then proceed accordingly with status update
            else {
              if(game.b.getCurrentTurn()==1){
                status.setText("Incorrect! It is currently Black's turn. Black lives = " +
                               game.playerOneLives + " ; Red lives = " + game.playerTwoLives);
              } else if(game.b.getCurrentTurn()==2) {
                status.setText("Incorrect! It is currently Red's turn. Black lives = " +
                               game.playerOneLives + " ; Red lives = " + game.playerTwoLives); 
              } 
            } 
          }
        }    
      }
      
      //if you start anew while the match is not over, you gain a loss in your record
      // if you start anew because a match has ended and the buttons are disabled,
      //then no new victory/loss is added to the respective LinkedLists, and the
      // game begins again
      if(event.getSource().equals(playAgain)) {
        
        if(game.b.getCurrentTurn()==1 && !game.b.isGameOver()){
          playerOneWins.add(0);
          playerTwoWins.add(1);
        } else if(game.b.getCurrentTurn()==2 && !game.b.isGameOver()){
          playerOneWins.add(1);
          playerTwoWins.add(0);
        }
        playAnotherMatch(); 
        enableButtons();
      }
    }
  }
}