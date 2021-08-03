package controller;

import java.io.IOException;

/**
* The concrete class PixelationCommand that represent
* the pixelation operation.
* @author luzixiao.
*/

public class PixelationCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 2 || model == null || command == null) {
      throw new IllegalArgumentException();
    }
    int blockNum = Integer.parseInt(command[1]);
    model.applyPixelation(blockNum);
  }
  

}
