package images;

/**
* The ImageProcesser interface that represents 
* all the common methods that will be used in different
* operations.
* @author luzixiao.
*/

public interface ImageProcesser {
  
  /**
  * The filter kernel for blur operations.
  */
  
  public final double[][] blur = new double[][]{
    {0.0625, 0.125, 0.0625},
    {0.125, 0.25, 0.0625},
    {0.0625, 0.125, 0.0625}
  };
  
  /**
  * The filter kernel for sharpening operations.
  */
  
  public final double[][] sharpening = new double[][] {
    {-0.125, -0.125, -0.125, -0.125, -0.125},
    {-0.125, 0.25, 0.25, 0.25, -0.125}, 
    {-0.125, 0.25, 1, 0.25, -0.125},
    {-0.125, 0.25, 0.25, 0.25, -0.125},
    {-0.125, -0.125, -0.125, -0.125, -0.125}
  };
  
  /**
  * The grey scale color transformation matrix.
  */
  
  public final double[][] greyScale = new double[][] {
    {0.2126, 0.7152, 0.0722},
    {0.2126, 0.7152, 0.0722},
    {0.2126, 0.7152, 0.0722}
  };
  
  /**
  * The sepia tone color transformation matrix.
  */
  public final double[][] sepiaTone = new double[][] {
    {0.393, 0.769, 0.189},
    {0.349, 0.686, 0.168},
    {0.272, 0.534, 0.131}
  };
   
  /**
  * Return the image that is stored in a 3d integer array.
  * @return   a 3d integer array that represents the image.
  */
  public int[][][] getImage();

  /**
  * Set the image 3d array.
  * @param image  the image you want to set.
  */
  
  public void setImage(int[][][] image);
  
  /**
  * Get the height of the image.
  * @return  the height of the image in integer.
  */
  
  public int getHeight();
  
  /**
  * Get the width of the image.
  * @return  the width of the image in integer.
  */
  
  public int getWidth();

  /**
  * Clamp the pixel to set the current pixel in the
  * range of min to max in order to properly display
  * the images.
  * @param pixel   the pixel you want to clamp.
  * @param min     the minimum value of the range.
  * @param max     the maximum value of the range.
  * @return        the clamped pixel within the range
  *                of min-max.
  */
  
  public int clamp(int pixel, int min, int max);
  
  
  
}