package mvc;

import java.io.IOException;

/**
* The concrete class GreyScaleCommand that 
* represents the grey scale operation.
* @author luzixiao.
*/

public class GreyScaleCommand implements Command {



  @Override
  public void execute(String[] command, Feature feature) throws IOException {
    if (command.length != 1 || feature == null || command == null) {
      throw new IllegalArgumentException();
    }
    
    feature.greyScale();
  }
  

}
