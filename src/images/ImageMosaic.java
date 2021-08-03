package images;

import java.util.List;

/**
* An interface ImageMosaic that represents the operation
* of image mosaic. This interface will extends the Interface
* ImageProcesser.
* @author luzixiao.
*/

public interface ImageMosaic extends ImageProcesser {
  
  /**
  * This method will generate random pixels around the
  * image based on the number of seeds.
  * @param numSeeds   the number of seeds for mosaic.
  */
  public void generateRandomPixels(int numSeeds); 
  
  /**
  * This method will find the cloest pixel of a pixel
  * at the give row and column and store the cloest 
  * pixel's row and col in a list.
  * @param row  the given row.
  * @param col  the given column.
  * @return  a list that contains only 2 values. The first
  *          is the row value of the cloest pixel and the
  *          second is the column value of the cloest pixel.
  */
  public List<Integer> findCloestPixel(int row, int col);
  
  /**
  * This method will apply the mosaic filter with numbr
  * of seeds as the number of random pixels selected 
  * across the image.
  * @param numSeeds   the number of seeds.
  * @return  a 3d integer array that represents the image.
  */
  public int[][][] applyMosaic(int numSeeds);
}
