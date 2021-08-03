package controller;

import java.io.IOException;

/**
* The concrete class ReduceColorDensityCommand that represent
* the reduce color density operation.
* @author luzixiao.
*/

public class ReduceColorDensityCommand implements Command {

  
  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 2 || command == null || model == null) {
      throw new IllegalArgumentException();
    }

    int colorPerChannel = Integer.parseInt(command[1]);
    model.applyReduceColorDensity(colorPerChannel);
  }

}
