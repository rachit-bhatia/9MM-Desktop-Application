package game;

import javax.swing.*;
import java.awt.*;

/**
 * A MainWindow of the Application
 * <p>
 * Created  by Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Shoumil
 */
public abstract class MainWindow {

    /**
     * instantiating the main application window
     */
    public static JFrame mainWindow = new JFrame("9 Men's Morris");

    /**
     * accessing the dimensions of the screen
     */
    private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();



    public static void setupMainWindow(){
        //setting the dimensions of the main window frame based on the screen size
        mainWindow.setSize(screenDimension.width, screenDimension.height);  //window size is of the screen size
        mainWindow.setLocation(0,0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default closing action

        mainWindow.getContentPane().add(new MainPagePanel(screenDimension));

        mainWindow.setVisible(true);

    }





}
