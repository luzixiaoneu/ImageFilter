package mvc;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import images.DmcColor;

/**
* Interface ImageModel that represents the
* Model for MVC in this program.
* @author luzixiao.
*/

public interface ImageModel {

  
  /**
  * This method will set the image value for the
  * ImageModel class.
  * @param image    The image value in 3d array that
  *                 you want to set.
  */
  
  void setImage(int[][][] image);
  
  /**
  * This method will return the image in 3d integer array.
  * @return    a 3d integer array that represent the image.
  */
  
  int[][][] getImage();
  
  /**
  * This method will return the map contains all 489 dmc colors.
  * @return     a hash map contains all dmc colors and their index.
  */
  
  Map<Integer, DmcColor> getDmcChart();
  
  /**
  * This method will set the dmcChart hash map to a specific
  * map.
  * @param dmcChart     The dmc color chart you want to set.
  */
  
  void setDmcChart(Map<Integer, DmcColor> dmcChart);
  
  /**
  * This method will return a list that contains all the
  * dmc colors that is currently in used in the pattern
  * as the legend.
  * @return     a list of dmc colors that represents the 
  *             currently dmc legend in the use.
  */
  
  List<DmcColor> getDmcLegend();
  
  /**
  * This method will set the current in used dmc legend
  * to a specific dmclegend.
  * @param newLegend     the dmc legend you want to set.
  */
  void setDmcLegend(List<DmcColor> newLegend);
  
  /**
  * This method will set the panel grid that represents
  * the each super pixels in cross stitch pattern.
  * @param panels    the panel grid based on the current super
  *                  pixels color.
  */
  
  void setPanelGrid(JPanel[][] panels);
  
  /**
  * This method will return the panel grid that represents the
  * superpixels in corss stitch pattern.
  * @return  a 2d array that stroes all the panels as the
  *          super pixels.
  */
  
  JPanel[][] getPanelGrid();
  
  /**
  * This method will set the panel at a given location to a 
  * new panel in the cross stitch pattern. Each panel represents
  * a superpixel.
  * @param row     row number.
  * @param col     col number.
  * @param newPanel     the new panel you want to replace.
  */
  
  void setPanelsAtPisition(int row, int col, JPanel newPanel);
  
  /**
  * This method will get the panel at a give location in the cross 
  * stitch pattern. Each panel represents a super pixel.
  * @param row    row number.
  * @param col    col number.
  * @return
  */
  
  JPanel getPanelAtPosition(int row, int col);
  
  /**
  * This method will set the background color of a given panel. 
  * Each panel represents a super pixel in the cross stitch pattern.
  * @param row     row number.
  * @param col     col number.
  * @param newColor     the new color you want to set to this panel(Super
  *                      pixel).
  */
  
  void setBackGroundColorAtPosition(int row, int col, Color newColor);
  
  /**
  * Set the number of chunks for pixelation.
  * @param chunks     the number of chunks for pixelation.
  */
  
  void setChunkNumber(int chunks);
  
  /**
  * Return the number of chunks for pixelation.
  * @return     the number of chunks.
  */
  int getChunkNumber();
  
  /**
  * This method will mark the panel at a specific 
  * row and column with a * symbol to represent
  * this color is removed from the pattern. 
  * @param row     row number.
  * @param col     col number.
  */
  
  public void markDiscardArea(int row, int col);
  
  /**
  * This method will return the map that contains
  * the command string mapped to its corresponding
  * command object.
  * @return    a hash map contains the key command string
  *            to its command object.
  */
  
  public Map<String, Command> getCommandMap();
  
}