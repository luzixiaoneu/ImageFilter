package controller;

import java.io.IOException;

/**
* The concrete class SepiaToneCommand that represent
* the sepia tone operation.
* @author luzixiao.
*/

public class SepiaToneCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    
    if (command.length != 1 || model == null || command == null) {
      throw new IllegalArgumentException();
    }
    model.applySepiaTone();
  }

}
