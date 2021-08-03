package controller;

import java.io.IOException;

/**
* The concrete class SharpeningCommand that represent
* the sharpening operation.
* @author luzixiao.
*/

public class SharpeningCommand implements Command {
  

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 1) {
      throw new IllegalArgumentException();
    }
    model.applySharpening();
    
  }

}
