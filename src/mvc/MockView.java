package mvc;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import images.DmcColor;

/**
* MockView class that simulate the view of the program 
* for testing its connection between the controller. 
* @author luzix
*/

public class MockView {
  

  
  /**
  * This method simulates the load image operation in the UI.
  * @param feature     the controller that do the operation.
  * @param filePath    the path to load the file.
  * @throws IOException    will throw an IOException if the path is invalid.
  */
  public void loadImage(Feature feature, String filePath) throws IOException {
    feature.loadImage(filePath);
  }
  
  
  /**
  * This method simulates the save image operation in the UI.
  * @param feature     the controller that do the operation.
  * @param filePath    the path to load the file.
  * @throws IOException    will throw an IOException if the path is invalid.
  */
  
  public void saveImage(Feature feature, String filePath) throws IOException {
    feature.saveImage(filePath);
  }
  
  
  /**
  * This method simulates the blur image operation in the UI.
  * @param feature  the controller that do the operation.
  */
  
  public void blurImage(Feature feature) {
    feature.blur();
  }
  
  /**
  * This method simulates the sharpening image operation in the UI.
  * @param feature  the controller that do the operation.
  */
  
  public void sharpeningImage(Feature feature) {
    feature.sharpening();
  }
  
  /**
  * This method simulates the greyscale image operation in the UI.
  * @param feature  the controller that do the operation.
  */
  
  public void greyScale(Feature feature) {
    feature.greyScale();
  }
  
  /**
  * This method simulates the sepia tone image operation in the UI.
  * @param feature  the controller that do the operation.
  */
  
  public void sepiaTone(Feature feature) {
    feature.sepiaTone();
  }
  
  /**
  * This method simulates the mosaic image operation in the UI.
  * @param feature  the controller that do the operation.
  * @param seeds    the number of seeds for mosaic.
  */
  public void mosaic(Feature feature, int seeds) {
    feature.mosaic(seeds);
  }
  
  /**
  * This method simulates the reduce color density image operation in the UI.
  * @param feature  the controller that do the operation.
  * @param colorPerChannel    the color per channel for reduce color density.
  */
  
  public void reduceColorDensity(Feature feature, int colorPerChannel) {
    feature.reduceColorDensity(colorPerChannel);
  }
  
  
  /**
  * This method simulates the pixelation image operation in the UI.
  * @param feature     the controller that do the operation.
  * @param chunkNum    the number of chunks for pixelation.
  */

  public void pixelation(Feature feature, int chunkNum) {
    feature.pixelation(chunkNum);
  }
  
  /**
  * This method simulates the create of interactive script for the user.
  * @param script    the content of the script.
  * @throws IOException     will throw an IOExceotion if the path is invalid.
  */
  
  public void executeScript(Feature feature, String script) throws IOException {
    JTextArea content = new JTextArea();
    content.setText(script);
    feature.executeScript(content);
  }
  
  /**
  * This method simulates the create of corss stitch pattern page in the UI.
  * @param feature     the controller that do the operation.
  * @param chunkNum    the number of chunk for pixelation.
  * @throws IOException     will throw IOException.
  */
  
  public void crossStitch(Feature feature, int chunkNum) throws IOException {
    if (chunkNum <= 0) {
      throw new IllegalArgumentException();
    }
    feature.createGridView(chunkNum);
  }
  
  /**
  * This method simulates when user decied to replace a current color in the
  * current pattern to another dmc color.
  * @param feature     the controller that does the operation.
  * @param oldColor    the color to be replaced.
  * @param newColor    the new color.
  */
  
  public void changeStitchPattern(Feature feature, Color oldColor, Color newColor) {
    feature.applyChangeToGrid(oldColor, newColor);
    feature.setDmcLegend(new ArrayList<DmcColor>());
  }
  
  /**
  * This method simulates the action when user decided to display the
  * image for the pattern he/she just stitched.
  * @param feature     the controller that does the operation.
  */
  public void displayImageAfterStitchPattern(Feature feature) {
    feature.setImageAfterStitchPattern();
  }
  
  /**
  * This method generate the txt file after user done with the corss
  * stitch pattern and wants to generate the txt file.
  * @param feature     the controller that does the operation.
  * @param filePath    the file path for the txtr file.
  * @throws IOException    will throw an IOException if file path is
  *                        invalid.
  */
  public void generatePatternTxtFileAfterStitchPattern(Feature feature, String filePath) 
      throws IOException {
    feature.drawPatternForUI(filePath);
  }
  


}
