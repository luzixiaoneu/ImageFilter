package images;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;


/**
* An interface ImagePixelation that represents the operation
* of pixelation. This interface will extends the Interface
* ImageProcesser.
* @author luzixiao.
*/

public interface ImagePixelation extends ImageProcesser {

  /**
  * This method will apply the pixelation and return a 3d
  * integer array that represented the prcessed image.
  * @param chunkNum  the number of super pixels across the width
  *                  of the image.
  * @return  a 3d integer array that represented the prcessed image.
  */
       
  public int[][][] pixelation(int chunkNum);
  
  /**
  * This method will generate a 2d array that reprsents the image's give
  * channel after being converted to the superpixels.
  * @param channel    the given channel of the image.
  * @param heightOverFlow   the overflow part ot height.
  * @param widthOverFlow    the overflow part ot width.
  * @return   a 2d integer array that represents the current channel
  *           after being converted to super pixels.
  */
  public int[][] getNewSuperPixelBlocks(int channel, int heightOverFlow, int widthOverFlow);
  
  /**
  * This method will draw the cross-stich pattern to an output txt file.
  * @param blockNum    the number of superpixels across the width for 
  *                    pixelation part.
  * @param fileName    the output txt file name.
  * @throws IOException   will throw an IOException if the fileName is invald.
  */
  
  public void drawPattern(int blockNum, String fileName) throws IOException;
  
  /**
  * This method will generate a 3d integer array that represents
  * the super pixels for the cross stitch pattern based on blockNum.
  * @param blockNum    the number of pixelation.
  * @return      a 3d integer array that represents all the super pixels
  *              of the cross stitch pattern.
  * @throws IOException     will throw an IOException if the dmc csv file
  *                         path is not valid.
  */
  
  public int[][][] stitchPatternForUi(int blockNum) throws IOException;
  
  /**
  * This method will return a hash map that contains all the dmc colors
  * and their corresponding integers in the original file.
  * @return    a hashmap contains all the dmc colors.
  */
  public Map<Integer, DmcColor> getDmcChart();
  
  /**
  * This method will get all the current in use dmc color legend
  * and convert it in a list.
  * @return     a list contains all the current in use dmc color legend.
  */
  
  public List<DmcColor> gettingAllInUseDmcColor();
  
  /**
  * This method will generate the image that is reprsented in a 3d integer array
  * after user changing the colors in the UI.
  * @param blockNum    the blockNum for pixelation.
  * @param panel       the JPanel from the UI that represents all the super pixels.
  * @return            a 3d integer array repesented the corss stitched pattern image.
  */
  
  public int[][][] generateImageAfterStitchingPattern(int blockNum, JPanel[][] panel);

}
