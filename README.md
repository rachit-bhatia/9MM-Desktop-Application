# FIT3077_project
- All the latest Java Code for the application is present in the "Sprint 3.src.game" folder
- The UML and the Design Rationale docs are present in the "Sprint 3.Design Docs" Folder

### Steps to run the application:
- The Application can be run by launching the jar file (9MM_DevDynasty.jar) in the Sprint 3 folder
- The jar file can be launched on both macOS and Windows (preferably Windows)
- In order to be able to run the jar file, the device should have at least Java Version 17.0.2 installed. Newer versions will also work.
- To install the correct JDK using IntelliJ Idea IDE, navigate to Project Structure in the File menu and choose SDK 17 under Project
- If there are any issues faced while launching 9MM_DevDynasty.jar, or it cannot be run, the 9MM application can still be launched by simply running the ApplicationDriver class file

    For macOS, if the error regarding "Unidentified developer" is encountered, right-click on the 9MM_DevDynasty.jar file and select 'Open'. Then select 'Open' from the dialog box that appears. If too many errors persist on macOS, proceed running the application on a Windows OS system.

### Creating a new jar file using IntelliJ IDEA:
- The application can be run by simply launching the provided jar file (9MM_DevDynasty.jar). The steps below describe how the jar file was generated using the IntelliJ IDEA IDE.
- Go to File > Project Structure
- Under Artifacts, click on the '+' sign and select JAR > modules with dependencies. Select the main class to be as ApplicationDriver.java
- Go to Build > Build Artifacts, and select the jar file
- Go to Edit Configurations, click on the '+' sign and select JAR Application. In Path to JAR, select the JAR file generated in the 'out' folder.
- Under Before Launch, click on the '+' sign and select Build artifacts, then select the jar file and click Apply - Ok.
- Locate the jar file in the 'out' folder and launch it
