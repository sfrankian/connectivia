/* Total.java
 * Team: Caylene, Stephanie, and Brenna
 * Written by Caylene
 * 
 * Purpose: Displays the win record of each player based on previous wins within the
 * same game session.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class MatchHistoryPanel extends JPanel {
  int p1Wins; 
  int p2Wins;
  int oldP1;
  int oldP2;
  JButton update;
  JLabel words;
  JPanel tokens, middle, top;
  LinkedList p1;
  LinkedList p2;
  ImageIcon redImg = createImageIcon("red.png","red token");
  ImageIcon blackImg = createImageIcon("black.png","black token");
  //constructor
  public MatchHistoryPanel(LinkedList play1, LinkedList play2) {
    setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
    String text = "<html><p>Here are the number of matches won by "+
      " each player for this in-session game tournament!</p></html>";
    JLabel one = new JLabel(text); //adds the first parag
    one.setFont(new Font("Lucida Sans", Font.BOLD, 13));  
    p1=play1;
    p2=play2;
    update = new JButton("Update");
    words = new JLabel();
    top = new JPanel();
    middle = new JPanel();
    top.add(one);
    middle.add(update);
    update.addActionListener(new ButtonListener());
    middle.add(words);
    tokens = new JPanel();
    tokens.setLayout(new GridLayout(6,6));
    setPreferredSize (new Dimension(600,500)); 
    add(top);
    add(middle);
    add(tokens);
    
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
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      // initialize p1Wins and p2Wins at 0 so that
      // the correct amount is counted and wins don't
      // compound every time the "Update" button is clicked
      p1Wins = 0;
      p2Wins = 0;
      
      for(int i=0; i<p1.size(); i++){
        if(p1.get(i).equals(1)){
          p1Wins++; 
          if(i>=oldP1){
            JLabel temp = new JLabel();
            temp.setIcon(blackImg);
            tokens.add(temp); 
          }
        } else if(p2.get(i).equals(1)){
          p2Wins++; 
          if(i>=oldP1){
            JLabel temp = new JLabel();
            temp.setIcon(redImg);
            tokens.add(temp);
          }
        }
      }      
      oldP1=p1.size();
      words.setText("<html><strong><font color=\"black\">Black Total Victories |</strong></font> " + 
                    p1Wins + "             <strong><font color=\"red\">| Red Total Victories " + p2Wins + "</font></strong></html>");
    }
  }
}

