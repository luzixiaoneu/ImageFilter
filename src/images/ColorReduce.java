package images;

import java.io.IOException;

/**
* The concrete class ColorReduce for reduce color density operations.
* @author luzixiao.
*/

public class ColorReduce extends AbstractImageProcesser implements ImageReduceColorDensity {

  /**
  * The constructor for concrete class ColorReduce. This constructor will 
  * invoke its parent AbstractImageProcesser's constructor.
  * @param fileName      the file name of the input image.
  * @throws IOException  will throw IOException if the file name is not in
  *                      the current directory.
  */
  public ColorReduce(String fileName) throws IOException {
    super(fileName);
  }
  
  /**
  * The constructor for concrete class ColorReduce. This constructor will
  * invoke its parent AbstractImageProcesser's second constructor.
  * @param image  the 3d integer array that contains the image. 
  */
  public ColorReduce(int[][][] image) {
    super(image);
  }

  @Override
  public int[][][] imageReduce(int colorNumber) {
    if (colorNumber <= 1) {
      throw new IllegalArgumentException();
    }
    int[][][] result = this.imageCopy();             
    for (int j = 0; j < this.height; j++) {
      for (int k = 0; k < this.width; k++) {
        for (int i = 0; i < this.chanel; i++) {
          int oldColor = result[j][k][i];
          int newColor = this.findClosestPaletteColor(oldColor, colorNumber);
          int error = oldColor - newColor;               
          result[j][k][i] = newColor;         
          if (k + 1 < this.width) {          
            result[j][k + 1][i] = (int) (7.0  / 16 * error) + result[j][k + 1][i];        
          }
          if (j + 1 < this.height && k > 0) {            
            result[j + 1][k - 1][i] = (int) (3.0 / 16 * error ) + result[j + 1][k - 1][i];
          }
          if (j + 1 < this.height) {            
            result[j + 1][k][i] = (int) (5.0 / 16  * error) + result[j + 1][k][i];
          }
          if (j + 1 < this.height  && k + 1 < this.width) {            
            result[j + 1][k + 1][i] = (int) (1.0 / 16 * error) + result[j + 1][k + 1][i];

          }      
        }
      }
    }

    for (int i = 0; i < this.chanel; i++) {
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          result[j][k][i] = this.clamp(result[j][k][i], 0, 255);
        }
      }
    }
 
     
    return result;
  }
  
  

  @Override
  public int findClosestPaletteColor(int oldColor, int maxColorInChanel) {   
    if (maxColorInChanel <= 1) {
      throw new IllegalArgumentException();
    }
    int[] thresHold = new int[maxColorInChanel];  
    for (int i = 0; i < maxColorInChanel; i++) {
      thresHold[i] = i * (256 / (maxColorInChanel - 1));
    }
    int cloest = Integer.MAX_VALUE;
    int index = 0;
    for (int i = 0; i < maxColorInChanel; i++) {    
      if (Math.abs(oldColor - thresHold[i]) < cloest) {
        cloest = Math.abs(oldColor - thresHold[i]);
        index = i;
      }
    }
    return thresHold[index];
  }
  
  /**
  * A private helper method that makes a copy
  * of the 3d array image into a same 3d integer
  * array.
  * @return   the copy of the image array.
  */
  
  private int[][][] imageCopy() {
    int[][][] result = new int[this.height][this.width][this.chanel];
    for (int i = 0; i < this.chanel; i++) {
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          result[j][k][i] = image[j][k][i];
        }
      }
    }
    return result;
  }

}
