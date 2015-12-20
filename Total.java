/* Total.java
 * Team: Caylene, Stephanie, and Brenna
 * Connectivia Game
 *  Written by Caylene
 * The class Total is the driver class for the entire GUI, creating instances
 * of the different Panels and adding them to the composite GUI. The order in which they are added
 * is reflected in the tab layout as well, with Evaluate at the end of the tabs.
 */
import javax.swing.*;
import java.awt.*;
public class Total {
  public Total(){
    
    //creates the overall JFrame that contains the whole game
    JFrame frame = new JFrame("Connectivia"); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //a new instance of GameSpecs, which gathers the category information
    JTabbedPane tabbed = new JTabbedPane();
    GameSpecs g = new GameSpecs();
    tabbed.addTab("Game Specs", g); 
    
    //necessary to make the GUI visible
    frame.setPreferredSize(new Dimension(550,400));
    frame.getContentPane().add(tabbed);
    frame.pack();
    frame.setVisible(true);
  }
  
  //The main method where we run the finished version of the Connectivia game
  public static void main(String[] args){
    Total gui = new Total();
  }
}
