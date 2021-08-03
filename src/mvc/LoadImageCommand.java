package mvc;

import java.io.IOException;

/**
* The concrete class LoadImageCommaand that represent
* the load operation.
* @author luzixiao.
*/

public class LoadImageCommand implements Command {

  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command == null || command.length != 2  || feature == null) {
      throw new IllegalArgumentException();
    }
    feature.loadImage(command[1]);
    
  }


}
