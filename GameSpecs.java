/* Total.java
 * Team: Caylene, Stephanie, and Brenna
 * Written by Caylene
 * 
 * Purpose: Prompts the user to pick a trivia category, and then creates the main GUI window
 * from their selection.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameSpecs extends JPanel {
  //instance variables
  String[] categoryChoice;
  JPanel instructions, updateSchool, addInformation,showChoice,info;
  JLabel players, categories, choice, decision,instructionLabel;
  JComboBox categoryBox, playerBox;
  JButton updateSpecs;
  int playerCount; 
  String category; 
  String game;
  
  //constructor
  public GameSpecs() {
    
    //sets a box layout and sets the values of the dropdown boxes with arrays
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    categoryChoice = new String [] {"None","Presidential","Country Capitals", "Literature"};
    //creates a new top panel and a label in it, setting the background color and adding it to the main panel
    instructions = new JPanel();
    info = new JPanel();
    String text = "<html><p>Please specify " +
      "the category of questions you would like to play. Then click 'Submit' "+
      "to have your choices recorded</p></html>";
    instructionLabel = new JLabel(text);
    instructionLabel.setPreferredSize(new Dimension(500,300));
    instructionLabel.setVerticalAlignment(JLabel.TOP);
    instructions.setBackground(new Color(255,255,255));
    info.setBackground(new Color(255,255,255));
    info.setPreferredSize(new Dimension(500,100));
    info.add(instructionLabel);
    instructions.add(info);
    add(instructions);
    
    //creating a new panel for adding school information
    addInformation = new JPanel();
    
    //add a dropdown menu for category
    categories = new JLabel("Question Category: ");
    categoryBox = new JComboBox(categoryChoice);
    
    
    //creates a submit button
    updateSpecs = new JButton("Submit");
    updateSpecs.addActionListener(new ButtonListener());
    
    //creates a  panel that updates to show the decision
    showChoice = new JPanel();
    decision = new JLabel("");
    decision.setHorizontalAlignment(JLabel.CENTER);
    
    //add the different dropdown boxes and labels and buttons etc to the second panel
    addInformation.add(categories);
    addInformation.add(categoryBox);
    addInformation.add(updateSpecs);
    
    //add the second and third panel to the main panel
    add(addInformation);  
    add(showChoice);
    
    //set the background color and layout 
    showChoice.setBackground(new Color(255,255,255));
    showChoice.setLayout(new GridLayout(5,1));
    showChoice.add(decision);
    addInformation.setBackground(new Color(255,255,255));
    //set preferred size of the GUI box
    setPreferredSize (new Dimension(300,200));  
  }
  
  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      category = (String)categoryBox.getSelectedItem();
      decision.setText("You are engaged in a two-player match on "+ categoryBox.getSelectedItem()+" trivia");
      if(category =="Presidential"){
        //game = "presidential_trivia.txt";
        ConnectiviaGUI pres = new ConnectiviaGUI(new Game("presidential_trivia.txt"));
      }
      else if(category=="Country Capitals"){
        // game = "country_capitals.txt";
        ConnectiviaGUI country = new ConnectiviaGUI(new Game("country_capitals.txt"));
      } else if(category =="Literature"){
         ConnectiviaGUI lit = new ConnectiviaGUI(new Game("literature.txt"));
      } else{
        decision.setText("Choose one!");
      }
    }
  }
}