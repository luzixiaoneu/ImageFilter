package images;

import java.io.IOException;


/**
* The concrete class ColorTransformation for color transformation 
* operations.
* @author luzixiao.
*/

public class ColorTransformation extends AbstractImageProcesser
    implements ImageColorTransformation {

  /**
  * The constructor for ColorTransformation. The constructor will
  * invoker its parent, the AbstractImageProcesser's constructor.
  * @param fileName the name of the input image file.
  * @throws IOException  will throw an IOException if the file
  *         is not found in the current directory.
  */
  
  public ColorTransformation(String fileName) throws IOException {
    super(fileName);
  }
  
  /**
  * The constructor for concrete class ColoTransformation. This constructor will
  * invoke its parent AbstractImageProcesser's second constructor.
  * @param image  the 3d integer array that contains the image. 
  */
  
  public ColorTransformation(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] transferColor(double[][] kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException();
    }
    int[][][] result = new int[this.height][this.width][this.chanel];
    for (int k = 0; k < this.width; k++) {
      for (int j = 0; j < this.height; j++) {
        for (int i = 0; i < chanel; i++) {
          int[] rgbSlice = this.getRGBAtGivenRowAndCol(j, k);
          int[] calculateNewRGB = this.calculateNewRGB(rgbSlice, kernel);
          result[j][k][i] = calculateNewRGB[i];
        }
      }
    }
    return result;
  }


  @Override
  public int[] getRGBAtGivenRowAndCol(int row, int col) {
    if (row >= this.height || col >= this.width) {
      throw new IllegalArgumentException();
    }
    int[] result = new int[3];
    result[0] = this.image[row][col][0];
    result[1] = this.image[row][col][1];
    result[2] = this.image[row][col][2];
    return result;
  }

  @Override
  public int[] calculateNewRGB(int[] rgb, double[][] kernel) {
    if (rgb == null || kernel == null) {
      throw new IllegalArgumentException();
    }
    int[] result = new int[3];
    for (int i = 0; i < 3; i++) {
      double temp = 0;
      for (int j = 0; j < 3; j++) {
        temp += rgb[j] * kernel[i][j];
      }
      result[i] = this.clamp((int) temp, 0, 255);
    }   
    return result;
  }

}
