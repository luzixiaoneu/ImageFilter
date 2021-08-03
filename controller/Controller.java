package controller;

import java.io.IOException;


/**
* The interface Controller that represents the
* controller of the program.
* @author luzixiao.
*/

public interface Controller {

  /**
  * This method will setUp all the supported command
  * into a hash map. 
  */
  
  public void setUp();
  
  /**
  * This method will start the controller until all
  * the command has been executed.
  * @throws IOException   will throw IOException if
  *                       the command file is not in
  *                       the current directory.
  */
  
  public void start() throws IOException;
  
  
  
}
