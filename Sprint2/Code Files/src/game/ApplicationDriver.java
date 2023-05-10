package game;

/**
 * Steps to run and test the application:
 * - The Application can be run by launching the jar file (9MM_DevDynasty.jar)
 * - In order to be able to run the jar file, the device should have at least Java Version 17.0.2 installed. Newer versions will also work.
 * - To install the correct JDK using IntelliJ Idea IDE, navigate to Project Structure in the File menu and choose SDK 17 under Project
 *
 * - If there are any issues faced while launching 9MM_DevDynasty.jar, or it cannot be run, the 9MM application can still be launched by simply running this file i.e. the ApplicationDriver class file.
 */
public class ApplicationDriver {

    /** Driver to run the Application */

    public static void main(String[] args) {
        MainWindow.setupMainWindow();
    }
}
