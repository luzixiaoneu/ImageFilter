package mvc;

import java.io.IOException;

/**
* The concrete class SepiaToneCommand that represent
* the sepia tone operation.
* @author luzixiao.
*/

public class SepiaToneCommand implements Command {

  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    
    if (feature == null || command.length != 1  || command == null) {
      throw new IllegalArgumentException();
    }
    feature.sepiaTone();
  }

}
