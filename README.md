# GrProgFruityProjekt

This project is an exam project for the ITU Software Development Bachelor. Semester 1. course "Grundlæggende Programmering". 
This project is our take on creating a streaming service. (Which can do almost everything but stream movies).
This project uses the java SDK version 19.0.1. And was made with the JavaFX Framework.


To download the working build of this project go to the link 
https://drive.google.com/file/d/1tyw_50GexyUOtQfzq3KVoE2a0yV0AiOH/view?usp=share_link

This is using GoogleDrive (We are working on a better fix) to download the build folder from the project.
To run the project open and execute the run.bat file.
Just make sure to actually download the file to your PC, instead of just opening the files in Google Drive.

If the project encounters an error whilst opening after running the .bat file or it just closes immediatly. You are probably missing the right version of java.
Try downloading the correct version. If this does not work, try running run.bat from the command line, this should print it's error.

This project also implements the JavaFX framework to create it's GUI.
  - Though there is no need to worry about installing this. It is already included in the dependencies of the build.
  
 If you are trying to create a build of your own project with JavaFX aswell and are encountering a lot of errors.
 This is the way we did it (using Intellij IDEA):
  - In IDEA under "File/Project Structure" create a new artifact which is a new .Jar with dependencies from modules
  - The main class should never be the JavaFX created class Main.java but another class Launcher.java which calls the static main() in Main.Java
  - Then click on "copy to the output directory and link via manifest" and create a new directory
  - When this is done click Build/Build Artifacts then select the new artifact and select Build.
  - You should now find this directory under out/artifacts in your project
  - Move the elements of this directory to a new one titled "Build" (or something like that)
  - In this new directory create a folder "Dependencies" and move the .jar files into this
  - Find your download of the "javafx-sdk-19" (assuming JavaFX version 19) folder and copy this folder into the Dependencies folder
  - In your build folder you should now be able to copy the run.bat from this project:
  - java --module-path "Dependencies/javafx-sdk-19/lib" --add-modules javafx.controls,javafx.fxml -jar Dependencies/"ProjectName".jar
  - This just makes sure to add the correct modules to the Java VM, when running the 
  - If you still encounter errors when running now, run the run.bat in the command line.
  - This error is most likely due to reading/writing files placed in the correct folders.
  - The root folder when running the project will now be the /Build folder
  - In our project this is why the Build/Data folder is there

If all of this doesn't work
Try reaching out to the creators of the project,
using Google
or using ChatGPT
