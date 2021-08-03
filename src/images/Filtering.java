package images;

import java.io.IOException;

/**
* The concrete class Filtering that implements all the methods
* in the interface ImageFiltering.
* @author luzixiao.
*/

public class Filtering extends AbstractImageProcesser implements ImageFiltering {

  /**
  * The constructor for class Filtering. This constructor will
  * invoke its parent's constructor.
  * @param fileName    the name of the input image file.
  * @throws IOException   will throw IOException if the filename
  *                       can not be found in the directory.
  */
  
  public Filtering(String fileName) throws IOException {
    super(fileName);
  }
  
  /**
  * The constructor for concrete class Filtering. This constructor will
  * invoke its parent AbstractImageProcesser's second constructor.
  * @param image  the 3d integer array that contains the image. 
  */
  
  public Filtering(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] imageBlur(double[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException();
    }
    int[][][] result = new int[this.height][this.width][this.chanel];
    for (int i = 0; i < chanel; i++) {
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          int[][] imageSlice = this.findOverLappingInImage(kernel, j, k, i);
          double[][] filterSlice = this.findOverLappingKernel(kernel, j, k);
          result[j][k][i] = clamp(this.calculateNewPixel(imageSlice, filterSlice), 0, 255);
        }
      }
    }
    return result;
  }


  @Override
  public double[][] findOverLappingKernel(double[][] kernel, int row, int col) {

    if (kernel == null || row >= this.height || col >= this.width) {
      throw new IllegalArgumentException();
    }
    
    int shiftAmount = kernel.length / 2;
    int leftBoundary = col; 
    int rightBoundary = col;
    int upperBoundary = row;
    int lowerBoundary = row;
    int leftShift = 0; 
    int rightShift = 0;
    int upperShift = 0;
    int lowerShift = 0;    
    for (int i = 0; i < shiftAmount; i++) {
      if (leftBoundary > 0) {
        leftBoundary = leftBoundary - 1;
        leftShift += 1;
      }
      if (rightBoundary < this.width - 1) {
        rightBoundary = rightBoundary + 1;
        rightShift += 1;
      }
      if (upperBoundary > 0) {
        upperBoundary = upperBoundary - 1;
        upperShift += 1;
      }
      if (lowerBoundary < this.height - 1) {
        lowerBoundary = lowerBoundary + 1;
        lowerShift += 1;
      }
    }    
    double[][] result = new double[lowerShift + upperShift + shiftAmount]
        [rightShift + leftShift + shiftAmount];   
    int rowCount = 0;
    int colCount = 0;
    for (int i = shiftAmount - upperShift; i <= shiftAmount + lowerShift; i++) {
      for (int j = shiftAmount - leftShift; j <= shiftAmount + rightShift; j++) {
        result[rowCount][colCount] = kernel[i][j];
        colCount ++;
      }
      colCount = 0;
      rowCount++;
    }
    return result;      
  }

  @Override
  public int[][] findOverLappingInImage(double[][] kernel, int row, int col, int layer) {
    if (kernel == null || row >= this.height || col >= this.width || layer >= 3) {
      throw new IllegalArgumentException();
    }
    int shiftAmount = kernel.length / 2;
    int leftBoundary = col; 
    int rightBoundary = col;
    int upperBoundary = row;
    int lowerBoundary = row;
    int leftShift = 0; 
    int rightShift = 0;
    int upperShift = 0;
    int lowerShift = 0;    
    for (int i = 0; i < shiftAmount; i++) {
      if (leftBoundary > 0) {
        leftBoundary -= 1;
        leftShift ++;
      }
      if (rightBoundary < this.width - 1) {
        rightBoundary += 1;
        rightShift ++;
      }
      if (upperBoundary > 0) {
        upperBoundary -= 1;
        upperShift ++;
      }
      if (lowerBoundary < this.height - 1) {
        lowerBoundary ++;
        lowerShift ++;
      }
    }
    int rowCount = 0;
    int colCount = 0;
    int[][] result = new int[lowerShift + upperShift + shiftAmount]
        [rightShift + leftShift + shiftAmount];
    for (int i = row - upperShift; i <= row + lowerShift; i++) {
      for (int j = col - leftShift; j <= col + rightShift; j++) {
        result[rowCount][colCount] = this.image[i][j][layer];
        colCount ++;
      }
      colCount = 0;
      rowCount++;
    }
    return result;
  }

  @Override
  public int calculateNewPixel(int[][] image, double[][] kernel) {
    if (image == null || kernel == null) {
      throw new IllegalArgumentException();
    }
    int result = 0;
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        result += (int) image[i][j] * kernel[i][j];
      }
    }
    return result;
  }











}
