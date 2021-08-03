package images;

/**
* An interface ImageFiltering that represents the operation
* of image filtering. This interface will extends the Interface
* ImageProcesser.
* @author luzixiao.
*/
public interface ImageFiltering extends ImageProcesser {
  
  /**
  * Apply the filter to each pixel of the image and return 
  * a new image that is being filtered.
  * @param kernel   the filter matrix you want to filter.
  * @return   a 3d integer array that represents the processed
  *           image.
  */
  public int[][][] imageBlur(double[][] kernel);

  
  /**
  * A method that find the overlapping part in the filter at
  * a given row and column.
  * @param kernel    the filter matrix you want to filter.
  * @param row       the current row number at the image.
  * @param col       the current col number at the image.
  * @return          a 2d double array that represents the
  *                  overlapping on the filter kernel
  *                  at the current row and column.
  */
  
  public double[][] findOverLappingKernel(double[][] kernel, int row, int col);
  
  /**
  * A method that find the overlapping part in the image at a given
  * row and column. 
  * @param kernel     the filter matrix you want to filter.
  * @param row        the current row number at the image.
  * @param col        the current col number at the image.
  * @param layer      the current channel of the image.
  * @return           a 2d integer array that represents the 
  *                   overlapping part on the image at the current 
  *                   row, column and layer.
  */
  public int[][] findOverLappingInImage(double[][] kernel, int row, int col, int layer);
  
  /**
  * Calculate the new pixel with the overpapping part of the image
  * and the filter kernel.
  * @param image   the overlapping part of the image.
  * @param kernel  the overlapping part of the filter kernel.
  * @return  the new pixel value.
  */
  
  public int calculateNewPixel(int[][] image, double[][] kernel);

}
