/**
 * MainMenuScreen class represents the main menu screen of Staying Alive.
 * <p>
 * MainMenuScreen implements the Screen interface, 
 * so it has methods to show the screen, move to the next screen, 
 * and get the class' frame.
 * Since Screen implements ActionListener, it also has a method to handle button clicks.
 * 
 * @author Fardin Abbassi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialScreen implements Screen{
    // Tutorial Frame
    static JFrame tutorialFrame = new JFrame("Staying Alive - Tutorial");

    // UI Colours
    final static Color backgroundPurple = new Color(106, 69, 156);
    final static Color tutorialBoxOutline = Color.white;

    // Navigation Icons
    private ImageIcon leftArrowIcon = new ImageIcon(); // add icons
    private ImageIcon rightArrowIcon = new ImageIcon(); // add icons
    private ImageIcon backArrow = new ImageIcon(); // add icons
    private ImageIcon progressDots = new ImageIcon(); // also add progression dots for tutorial steps, add icons

    // Arrow Buttons
    private JButton leftArrowButton = new JButton(leftArrowIcon);
    private JButton rightArrowButton = new JButton(rightArrowIcon);
    private JButton backToPreviousButton = new JButton(backArrow);

    /** ADD DESCRIPTION HERE
     * Handle button clicks on main menu
     * <p>
     * Left arrow button moves tutorial to the previous part, right arrow moves to the next part, 
     * back button moves back to the previous screen.
     * <p>
     * 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Properly fix up tutorial navigation
        if (e.getSource() == leftArrowButton) {}
        else if (e.getSource() == rightArrowButton) {}
        else if (e.getSource() == backToPreviousButton) {this.moveToNextScreen("back");} // set up for different ways to go back?
    }

    /** ADD DESCRIPTION HERE
     * 
     *
     */
    @Override
    public void showScreen() {
		// Set Up Left and Right Arrows
		leftArrowButton.setBounds(200, 200, 20, 20);
        leftArrowButton.addActionListener(this);
		rightArrowButton.setBounds(500, 200, 20, 20);
        rightArrowButton.addActionListener(this);

        // Set up Back Button
        backToPreviousButton.setBounds(20, 20, 20,20);
        backToPreviousButton.addActionListener(this);

        // TODO: Set up progress dots
		
		// Add to frame
		tutorialFrame.getContentPane().add(leftArrowButton);
		tutorialFrame.getContentPane().add(rightArrowButton);
        tutorialFrame.getContentPane().add(backToPreviousButton);
		
		// Set frame
		tutorialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tutorialFrame.getContentPane().setLayout(null);
		tutorialFrame.getContentPane().setBackground(backgroundPurple);
		//tutorialFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/shebab_icon.png"))); // Shebab Kebab logo without text, adjust as needed
		tutorialFrame.setBackground(backgroundPurple); // adjust as needed
        tutorialFrame.setForeground(backgroundPurple);
		tutorialFrame.setVisible(true);
        tutorialFrame.setLocationRelativeTo(null);
    }

    /** ADD DESCRIPTION HERE
     * @param screenToMoveTo
     * @return Screen
     *
    */
    @Override
    public void moveToNextScreen(String screenToMoveTo) {
        // Move back to main menu
        if (screenToMoveTo.equals("back")) {NavigationControl.setCurrentScreen(0);}
    }

    /** ADD DESCRIPTION HERE
     * 
     */
    @Override
    public JFrame getFrame() {return tutorialFrame;}
}
