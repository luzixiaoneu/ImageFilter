package controller;

import java.io.IOException;

/**
* The concrete class LoadImageCommaand that represent
* the load operation.
* @author luzixiao.
*/

public class LoadImageCommand implements Command {

  
  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 2 || command == null || model == null) {
      throw new IllegalArgumentException();
    }
    
    model.loadImage(command[1]);

  }

}
