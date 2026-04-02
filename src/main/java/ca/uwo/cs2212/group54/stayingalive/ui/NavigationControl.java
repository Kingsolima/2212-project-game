/**
 * NavigationControl class both controls the screen navigation and the origin point of the application.
 * <p>
 * NavigationControl initializes all screens, then starts at the main menu screen.
 * It also has a method to update the current screen,
 * and a method to set the current screen to another and update.
 *
 * @author Fardin Abbassi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NavigationControl {
    // Screen Control
    private static Screen[] listOfScreens = new Screen[8];
    private static Screen currentScreen;
    private static int currentScreenIndex  = 0;
    private static int previousScreenIndex = 0;

    // Screen Dimensions
    public final static int screenW = 800; //sets the screen width
    public final static int screenH = 450; //sets the screen height

    /**
     * Updates the current screen by showing it and setting its size to defined dimensions
     * @author Fardin Abbassi
     */
    private static void updateScreen() {
        currentScreen.showScreen();
        if (!currentScreen.getClass().toString().equals("class LoginScreen")){
             currentScreen.getFrame().setSize(screenW, screenH);
             System.out.println(currentScreen.getClass().toString());   // debug
        }
        currentScreen.getFrame().setLocationRelativeTo(null);
    }
    
    /**
     * Sets current screen to the screen at the index of the list of screens and updates screen to match.
     * Disposes of the previous screen to prevent memory leak.
     * @author Fardin Abbassi
     * @param int screenToSet
     */
    public static void setCurrentScreen(int screenToSet) {
        if (currentScreen != null && screenToSet != 1) currentScreen.getFrame().dispose();
        previousScreenIndex = currentScreenIndex;
        currentScreenIndex  = screenToSet;
        currentScreen = listOfScreens[screenToSet];
        updateScreen();
    }

    /**
     * Navigates back to the previous screen.
     * @author Fardin Abbassi
     */
    public static void goBack() {
        setCurrentScreen(previousScreenIndex);
    }
    /**
     * Get screen at the index of the screen list.
     */
    public static Screen getScreen(int index) {return listOfScreens[index];}

    /**
     * Recursively walks every component in the container and scales its font
     * proportionally to the given scale factor.
     * The original font size is stored as a client property on first call so
     * repeated resizes always scale from the base, not from the already-scaled size.
     * @author Fardin Abbassi
     */
    public static void scaleFonts(Container root, float scale) {
        for (Component c : root.getComponents()) {
            if (c instanceof JComponent) {
                JComponent jc = (JComponent) c;
                Float base = (Float) jc.getClientProperty("baseFontSize");
                if (base == null) {
                    base = c.getFont().getSize2D();
                    jc.putClientProperty("baseFontSize", base);
                }
                c.setFont(c.getFont().deriveFont(base * scale));
            }
            if (c instanceof Container) {
                scaleFonts((Container) c, scale);
            }
        }
    }

    /**
     * Attaches a ComponentListener to the given frame that calls scaleFonts
     * whenever the window is resized. Safe to call multiple times — removes
     * any previously attached scaler before adding a new one.
     * @author Fardin Abbassi
     */
    public static void attachFontScaler(JFrame frame) {
        for (ComponentListener cl : frame.getComponentListeners()) {
            frame.removeComponentListener(cl);
        }
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                float scale = Math.min(
                    frame.getWidth()  / (float) screenW,
                    frame.getHeight() / (float) screenH
                );
                scaleFonts(frame.getContentPane(), scale);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
    
    /**
     * Constructor for NavigationControl. Initializes screens, then starts at the main menu screen.
     * @author Fardin Abbassi
     */
    public NavigationControl() {
        // Initialize screens (add as screens are implemented)
        MainMenuScreen mainMenu = new MainMenuScreen();
        LoginScreen loginScreen = new LoginScreen();
        TutorialScreen tutorialScreen = new TutorialScreen();
        PlayerScreen playerScreen = new PlayerScreen(null); // TODO: replace placeholder constructor
        StatsScreen statsScreen = new StatsScreen("Placeholder"); // TODO: Replace placeholder constructor
        GameStoreScreen gameStoreScreen = new GameStoreScreen(3000);        // GameStoreScreen gameStoreScreen = new GameStoreScreen(null);

        // Add screens to list of screens (add as screens are implemented)
        listOfScreens[0] = mainMenu;
        listOfScreens[1] = loginScreen;         // might not be necessary due to implementation as a pop-up
        listOfScreens[2] = tutorialScreen;      // tutorial
        listOfScreens[3] = playerScreen;        // player
        listOfScreens[4] = statsScreen;         // stats
        listOfScreens[5] = gameStoreScreen;     // game store           //listOfScreens[5] = gameStoreScreen;     // game store
        listOfScreens[6] = null;                // parental control
        listOfScreens[7] = null;                // gameplay
        

        // Start at main menu
//        setCurrentScreen(0);
        setCurrentScreen(0);
    }

    // Use as driver for application
    public static void main(String[] args) {
        new NavigationControl();
    }
}