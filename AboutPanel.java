/* AboutPanel.java
 * Group: Brenna Carver, Caylene Parrish, Stephanie Frankian
 * Written by: Caylene Parrish
 * Purpose: The AboutPanel contains the information for playing the
 * game properly, so as to acquaint the user with the game in an 
 * expedient manner. The documentation will be as specific as possible,
 * and because this page only contains the text transcript, there are no
 * Listeners in this class.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;

public class AboutPanel extends JPanel{
  JLabel tokenPic; //the picture of the happy red token!
  public AboutPanel() {
    //sets background color and BorderLayout type
    setBackground(new Color(255,204,204)); 
    //BorderLayout is ideal for listing elements in compass locations
    setLayout(new BorderLayout());
    
    //creates separate labels on each line of the panel
    JLabel instruct = new JLabel();
    instruct.setIcon(new ImageIcon("title.png")); //adds the title
    String text = "<html><p>The Connectivia game strives to engage players in a game of the family game night classic,"+
      "ConnectFour, with a slight twist. Before you start the game, please take some time to read the rules below! <br><br> "+
      " In Connectivia, the players must answer questions in the category they chose correctly in order to place a token."+
      "<br>Like regular ConnectFour, the game is won when four tokens align horizontally, vertically, or diagonally. "+
      "<br>Unlike regular ConnectFour, though, the question prompt will, if answered incorrectly, decrement "+
      "the amount of lives a player has. A player starts at three lives, with no opportunity to regain new lives, so "+
      "choose your answer wisely!<br> "+
      "The  players will alternate turns, for the program will track whose turn it is in the match.<br> "+
      "In addition to the game itself, the players can play as many games as they would like by clicking 'Play Again', and the "+
      "victories of each player for the entire session will be updated and printed for the players on the third page.</p></html>";
    
    JLabel one = new JLabel(text); //adds the first paragraph
    //sets the alignment of the Connectivia title
    instruct.setHorizontalAlignment(JLabel.CENTER);
    instruct.setVerticalAlignment(JLabel.BOTTOM);
    //centers the text
    one.setHorizontalAlignment(JLabel.CENTER);
    //sets the font to Courier New
    one.setFont(new Font("Lucida Sans", Font.BOLD, 14));  
    //adds the labels to the panel
    add(instruct,BorderLayout.NORTH);
    add(one,BorderLayout.CENTER);
    tokenPic = new JLabel();
    
    //adds an image to the right of the panel
    tokenPic.setIcon(new ImageIcon("happyRed.png"));
    tokenPic.setHorizontalAlignment(JLabel.RIGHT);
    add(tokenPic, BorderLayout.SOUTH);
    setPreferredSize (new Dimension(700,500)); 
  }
}