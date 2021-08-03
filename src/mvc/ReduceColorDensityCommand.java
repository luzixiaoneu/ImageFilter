package mvc;

import java.io.IOException;

/**
* The concrete class ReduceColorDensityCommand that represent
* the reduce color density operation.
* @author luzixiao.
*/

public class ReduceColorDensityCommand implements Command {

  
  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command == null || command.length != 2 || feature == null) {
      throw new IllegalArgumentException();
    }

    int colorPerChannel = Integer.parseInt(command[1]);
    feature.reduceColorDensity(colorPerChannel);
  }

}
