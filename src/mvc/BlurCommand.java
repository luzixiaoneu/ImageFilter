package mvc;

import java.io.IOException;
/**
* The class BlurCommand that represent a 
* blur operation command.
* @author luzixiao.
*/

public class BlurCommand implements Command {

  

  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command.length != 1 || feature == null || command == null) {
      throw new IllegalArgumentException();
    }
    feature.blur();
    
  }

}
