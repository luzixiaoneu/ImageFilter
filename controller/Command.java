package controller;

import java.io.IOException;

/**
* The interface Command that represents all the 
* operation commands.
* @author luzixiao.
*/
public interface Command {

  /**
  * This method will execute the command by using the input 
  * parameter ImageModel's corresponding apply method.
  * @param command     the current command to execute.
  * @param model       the ImageModel that contains the image.
  * @throws IOException   will throw the IOException if the input 
  *                       file is not in the current directory.
  */
  
  void execute(String[] command, ImageModel model) throws IOException;
  
}
