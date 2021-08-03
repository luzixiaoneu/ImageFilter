package controller;

import java.io.IOException;
/**
* The class BlurCommand that represent a 
* blur operation command.
* @author luzixiao.
*/

public class BlurCommand implements Command {

  
  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 1 || model == null || command == null) {
      throw new IllegalArgumentException();
    }
    model.applyBlur();

    
  }

}
