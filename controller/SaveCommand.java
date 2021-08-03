package controller;

import java.io.IOException;

/**
* The concrete class SaveCommand that represent
* the save operation.
* @author luzixiao.
*/

public class SaveCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command.length != 2 || command == null || model == null) {
      throw new IllegalArgumentException();
    }
    model.generateOutPutImage(command[1]);
  }

}
