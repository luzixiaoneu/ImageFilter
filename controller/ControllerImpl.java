package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
* The concrete class ControllerImpl that implements
* the Controller class as the controller for this program.
* @author luzixiao.
*/

public class ControllerImpl implements Controller {

  /**
  * All the private data members that will be used in this
  * class.
  * model:  the ImageModel that stores the image.
  * fileName: the batch file name.
  * commandMap: a hash map that maps each input command
  * into a Command object.
  */
  
  private ImageModel model;
  private String fileName;
  private Map<String, Command> commandMap;

  /**
  * Constructor for ControleerImpl.
  * @param fileName  the batch file name.
  * @param model   the ImageModel that stores the image.
  */
  
  public ControllerImpl(String fileName, ImageModel model) {
    if (fileName == null || model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.fileName = fileName;
    this.commandMap = new HashMap<String, Command>();
  }

  @Override
  public void setUp() {
    this.commandMap.put("load", new LoadImageCommand());
    this.commandMap.put("save", new SaveCommand());
    this.commandMap.put("blur", new BlurCommand());
    this.commandMap.put("sharpening", new SharpeningCommand());
    this.commandMap.put("greyscale", new GreyScaleCommand());
    this.commandMap.put("sepiatone", new SepiaToneCommand());
    this.commandMap.put("reduce", new ReduceColorDensityCommand());
    this.commandMap.put("mosaic", new MosaicCommand());
    this.commandMap.put("pixelation", new PixelationCommand());
    this.commandMap.put("pattern", new GeneratePatternCommand());  
  }

  @Override
  public void start() throws IOException {

    Scanner sc = new Scanner(new File(fileName));
    while (sc.hasNext()) {
      
      String[] currentLine = sc.nextLine().split(" ");
      Command tempCommand = this.commandMap.getOrDefault(currentLine[0], null);
      if (tempCommand == null) {
        throw new IllegalArgumentException("current command is not supported!");
      }
      else {
        tempCommand.execute(currentLine, model);
      }
    }
    sc.close();
  }


  
  
  
  



}
