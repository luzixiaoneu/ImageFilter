package mvc;

import java.io.IOException;


/**
* The concrete class GeneratePatternCommand that 
* represents the Generate Pattern operation.
* @author luzixiao.
*/
public class GeneratePatternCommand implements Command {



  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    // TODO Auto-generated method stub
    if (command == null || feature == null) {
      throw new IllegalArgumentException();
    }
    if (command.length != 2) {
      throw new IllegalArgumentException();
    }
    
    String name = command[1];
    feature.drawPatternForScript(name);
  }

}
