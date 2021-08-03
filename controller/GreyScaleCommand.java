package controller;

import java.io.IOException;

/**
* The concrete class GreyScaleCommand that 
* represents the grey scale operation.
* @author luzixiao.
*/

public class GreyScaleCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    
    if (command.length != 1 || model == null || command == null) {
      throw new IllegalArgumentException();
    }
    model.applyGreyScale();
  }
  

}
