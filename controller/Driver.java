  package controller;

import java.io.IOException;


/**
* The driver class that contains the main method
* of the program.
* @author luzixiao.
*/
public class Driver {

  /**
  * The main method of the program.
  * @param args   command line arguments.
  * @throws IOException  will thorow IOException if the image
  *                      is not in the current directory.
  */
  
  public static void main(String[] args) throws IOException {
    try {
      if (args.length != 1) {
        throw new IllegalArgumentException();
      }
      System.out.println("Processing...");
      ImageModel imageModel = new ImageModelImpl();
      Controller controller = new ControllerImpl(args[0], imageModel);
      controller.setUp();
      controller.start();
      System.out.println("done!");
    }
    catch (IOException ioException) {
      System.out.println("Load Image failed!");
    }
    catch (IllegalStateException illegalState) {
      System.out.println("Please Load Image First!");
    }
    
  }

}
