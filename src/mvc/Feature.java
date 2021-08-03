package mvc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import images.DmcColor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
* An interface Feature that represents all the features 
* that will be added to the View.
* @author luzixiao.
*/
public interface Feature {
  
  /**
  * This method will load the input image file and store
  * it as a 3d integer array in the model class.
  * @param fileName   path of the input image file.
  * @throws IOException    will throw an exception if the path
  *                        can not be found. 
  */
  
  void loadImage(String fileName) throws IOException;
  
  /**
  * This method will save the current image to an image
  * file at the given path.
  * @param fileName   the path for the file.
  * @throws IOException    will throw an exception if the path
  *                        can not be found.
  */
  
  void saveImage(String fileName) throws IOException;
  
  /**
  * This method will apply the grey scale filter 
  * to the current image.
  */
  
  void greyScale();
  
  /**
  * This method will apply the sharpening filter 
  * to the current image.
  */
  
  void sharpening();
  
  /**
  * This method will apply the blur filter 
  * to the current image.
  */
  
  void blur();
  
  /**
  * This method will apply the sepia tone filter 
  * to the current image.
  */
  
  void sepiaTone();
  
  /**
  * This method will reduce the color density of the current image
  * to the specified number of color per channel.
  * @param colorPerChannel   The number of color per channel
  *                          after reducing.
  */
  
  void reduceColorDensity(int colorPerChannel);
  
  /**
  * This method will apply the mosaic filter to the current image
  * withe specified number of seeds.
  * @param seed     the number of seeds for the mosaic operation.
  */
  void mosaic(int seed);  
  
  /**
  * This method will apply the pixelation filter to the current image
  * withe specified number of chunks.
  * @param chunkNum    The number of chunks for pixelation.
  */
  void pixelation(int chunkNum);
  

  /**
  * This method will apply the cross stitch pattern and generate the
  * txt file in the script mode.
  * @param fileName    the file path for the output txt.
  * @throws IOException   will throw an IOException if the path is invalid.
  */
  
  void drawPatternForScript(String fileName) throws IOException;
  
  /**
  * This method will generate the output txt file in the interactive mode.
  * @throws IOException    Will throw an IOException if the output path
  *                        is not valid.
  */
  
  void drawPatternForUI(String path) throws IOException;
  
  /**
  * This method will convert the 3d integer array image to a BufferedImage
  * that can be displayed in the UI.
  * @return    The current image in BufferedImage type.
  */
  
  
  
  BufferedImage getImage();
  
  /**
  * This method will create the cross stitch pattern grid in the UI. The grid
  * is constructed with a 2d JPanel array that each element represents a superpixel.
  * The superpixel's color will be displayed by that panel's background color. Once 
  * the grid is constructed, return a JScrollPane that contains the grid.
  * @return     a JScrollPane that contains the 2d JPanel array that represents
  *             all the super pixels.
  * @throws IOException   will throw an IOException if the path is invalid.
  */
  
  public JScrollPane createGridView(int chunkNum) throws IOException;
  
  /**
  * This method will apply the change to the cross stitch pattern when user selected
  * a new DMC color to replace one of the exisited Dmc colr in the pattern, and display
  * the new pattern.
  * @param oldColor     The old color the user is trying to replace.
  * @param newColor     The new color the user wants for substitute.
  */
  
  void applyChangeToGrid(Color oldColor, Color newColor);
  
  /**
  * This method will get a list contains all the DmcColor that is currently
  * being used in the cross stitch pattern.
  * @return     a list contains all the current in use DmcColor.
  */
  
  List<DmcColor> getLegendInUse();
  
  /**
  * This method will get a map that represents all the 489 possible DMC Colors
  * and their corresponding index as the key.
  * @return      a map contains all the DmcColor and their index in the chart.
  */
  
  Map<Integer, DmcColor> getDmcChart();
  
  /**
  * This method will calculate the new image after the cross
  *  sititch is complete.   
  */
  
  void setImageAfterStitchPattern();
  
  /**
  * This method will find the index of oldColor button's background color
  * in the list that contains all the currently used DmcColors.
  * @param oldColor     the button's back ground color you want to to
  *                     find the index
  * @return             index of that color in the currently used dmc color
  *                     list.
  */
  
  int findIndexFromLegendInUse(JButton oldColor);
  
  /**
  * This method will set the model's dmcLegend list that
  * represents all the current used dmc color in the current pattern.
  * @param newLegend    the list you want to set.
  */
  
  void setDmcLegend(List<DmcColor> newLegend);
  
  /**
  * This method will display a * symbol at all the colors that the
  * user decided to remove.
  * @param colorToDiscard    The color user wants to remove from 
  *                          the pattern.
  */
  
  void displayDiscardedPattern(Color colorToDiscard);//
  
  /**
  * This method will execute the script that the user created in
  * the UI and execute its commands.
  * @param textfield      the JTextArea that user created their script.
  * @throws IOException   Will throw an IOException when user trying to
  *                       load or save to an invalid path.
  */
  
  void executeScript(JTextArea textfield) throws IOException;
  


  
  
}




