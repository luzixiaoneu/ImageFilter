package mvc;

import java.io.IOException;

/**
* The concrete class SaveCommand that represent
* the save operation.
* @author luzixiao.
*/

public class SaveCommand implements Command {



  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command == null || command.length != 2  || feature == null) {
      throw new IllegalArgumentException();
    }
    feature.saveImage(command[1]);
    
  }

}
