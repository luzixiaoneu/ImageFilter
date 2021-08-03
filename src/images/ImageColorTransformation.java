package images;

/**
* An interface ImageColorTransformation that represents all the
* operations for color transformation. This interface will extends
* the ImageProcesser.
* @author luzixiao.
*/

public interface ImageColorTransformation extends ImageProcesser {
  
  /**
  * This method will transfer the image's color by applying the 
  * input filter kernel. 
  * @param  kernel   the filter kernel that will be applied to 
  *         the image.
  * @return a processed image that stores in a 3d integer array.
  */
  
  public int[][][] transferColor(double[][] kernel);
   
  /**
  * This method will get the RGB values at a given row and
  * column number in the image.
  * @param row   the current row number of the image.
  * @param col   the current column number of the image.
  * @return  a 1d integer array that stores the values of the
  *          RGB values. 
  */
  
  public int[] getRGBAtGivenRowAndCol(int row, int col);
  
  /**
  * This method will calculate the new RGB values after applying
  * the filter kernel.
  * @param rgb   the integer array that represents the RGB values.
  * @param kernel   the filter kernel in double 2d array.
  * @return   the new RGB values that stores in a 1d integer array.
  */
  
  public int[] calculateNewRGB(int[] rgb, double[][] kernel);

}
