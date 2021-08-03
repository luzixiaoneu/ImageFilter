package controller;

import java.io.IOException;


/**
* The concrete class GeneratePatternCommand that 
* represents the Generate Pattern operation.
* @author luzixiao.
*/
public class GeneratePatternCommand implements Command {

  @Override
  public void execute(String[] command, ImageModel model) throws IOException {
    if (command == null || model == null) {
      throw new IllegalArgumentException();
    }
    int blockNum = 150;
    if (command.length != 2) {
      throw new IllegalArgumentException();
    }
    
    String name = command[1];
    model.generatePattern(blockNum, name);
  }

}
