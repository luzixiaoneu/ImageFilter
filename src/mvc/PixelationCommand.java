package mvc;

import java.io.IOException;

/**
* The concrete class PixelationCommand that represent
* the pixelation operation.
* @author luzixiao.
*/

public class PixelationCommand implements Command {

  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command == null || command.length != 2  || feature == null) {
      throw new IllegalArgumentException();
    }
    int blockNum = Integer.parseInt(command[1]);
    feature.pixelation(blockNum);
  }
  

}
