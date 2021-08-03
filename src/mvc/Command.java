package mvc;

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
  * @param feature       The controller that holds these command 
  *                      for operations.
  * @throws IOException   will throw the IOException if the input 
  *                       file is not in the current directory.
  */
  
  void execute(String[] command, Feature feature) throws IOException;
  
}
