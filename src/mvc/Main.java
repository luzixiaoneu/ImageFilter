package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
* The main class of the ImageProcessing MVC Program
* which contains the main method for this program.
* @author luzixiao.
*/

public class Main {

  /**
  * The main method of the program. It has 2 modes, script mode
  * and the UI Mode.
  * @param args     command line argument.
  * @throws IOException    will throw an IOException if 
  *                        load or save file with an invalid
  *                        path.
  */
  
  public static void main(String[] args) throws IOException {
    if (args.length > 2) {
      throw new IllegalArgumentException();
    }
    
    if (args.length == 1 && args[0].equals("-interactive")) {
      IView frame = new JFrameView("Image Processer");
      ImageModel model = new ImageModelImpl();
      Controller controller = new Controller(frame, model);
      controller.start();
    }
    
    if (args.length == 2 && args[0].equals("-script")) {
      ImageModel model = new ImageModelImpl();
      Controller controller = new Controller(null, model);
      File path = new File(args[1]);
      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), 
          "UTF-8"));
      String line = "";
      while ((line = reader.readLine()) != null) {
        String[] currentCommand = line.split(" ");
        controller.executeScriptForScriptMode(currentCommand);
      }
      System.out.println("Complete!");
      reader.close();
      System.exit(0);
    }
    
  }

}
