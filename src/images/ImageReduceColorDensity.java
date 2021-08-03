package images;

/**
* An interface that represents all the operations for reducing the image's
* color density. This interface will extends the ImageProcesser interface.
* @author luzixiao.
*/

public interface ImageReduceColorDensity extends ImageProcesser {
  
  /**
  * This method will reduce the color density of the original
  * image to a specified number of colors per channel.
  * @param colorNumber     the number of colors per channel after
  *                        reducing.
  * @return                the processed image that is represented
  *                        as a 3d integer arrary.
  */
  
  public int[][][] imageReduce(int colorNumber);
  
  /**
  * A private helper method that find the cloest palette color
  * of the input oldColor.
  * @param oldColor    The oldColor you want to convert.
  * @param maxColorInChanel     max color numbers per channel.
  * @return  the reduced color in integer.
  */
  
  public int findClosestPaletteColor(int oldColor, int maxColorInChanel); 

}
