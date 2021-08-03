package mvc;

import java.io.IOException;

/**
* The concrete class MosaicCommand that represent
* the mosaic operation.
* @author luzixiao.
*/

public class MosaicCommand implements Command {



  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command == null || command.length != 2 || feature == null) {
      throw new IllegalArgumentException();
    }
    int seed = Integer.parseInt(command[1]);
    feature.mosaic(seed);
    
  }
  

}
