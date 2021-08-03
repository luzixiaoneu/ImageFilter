package controller;

import java.io.IOException;

/**
* The concrete class MosaicCommand that represent
* the mosaic operation.
* @author luzixiao.
*/

public class MosaicCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 2 || model == null || command == null) {
      throw new IllegalArgumentException();
    }
    int seed = Integer.parseInt(command[1]);
    model.applyMosaic(seed);
  }
  

}
