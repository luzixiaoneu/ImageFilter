package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* The concrete class MockController that will be used
* in Junit test to mock the behavior of the actual controller.
* @author luzixiao.
*/
public class MockController implements Controller {

  /**
  * All the private data members will be used in this class.
  * commands: All the commands that the controller will be
  *           processing.
  * model:  a mock image model that is also a part of the testing.
  * commandMap: a hashmap that maps all the supported operations
  *             to the corresponding command object.
  */
  
  private String[] commands;
  private Map<String, Command> commandMap;
  private ImageModel model;
  
  /**
  * Constructor for MockController.
  * @param model  the model you want to use in this controller.
  * @param commands   all the commands need to be executing.
  */
  
  public MockController(ImageModel model, String[] commands) {
    this.commands = commands;
    this.model = model;
    this.commandMap = new HashMap<String, Command>();
    
  }
  
  @Override
  public void setUp() {
    this.commandMap.put("load", new LoadImageCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("blur", new BlurCommand());
    this.commandMap.put("sharpening", new SharpeningCommand());
    this.commandMap.put("reduce", new ReduceColorDensityCommand());
    this.commandMap.put("greyscale", new GreyScaleCommand());
    this.commandMap.put("sepiatone", new SepiaToneCommand());
    this.commandMap.put("mosaic", new MosaicCommand());
    this.commandMap.put("pixelation", new PixelationCommand());
    this.commandMap.put("pattern", new GeneratePatternCommand());  
    
  }

  @Override
  public void start() throws IOException {
    for (int i = 0; i < this.commands.length; i++) {

      String[] currentCommand = this.commands[i].split(" ");
      
      Command temp = this.commandMap.getOrDefault(currentCommand[0], null);
      if (temp == null) {
        throw new IllegalArgumentException("Command Not supported!");
      }
      else {
        temp.execute(currentCommand, model);
      }
    }
    
  }

}
