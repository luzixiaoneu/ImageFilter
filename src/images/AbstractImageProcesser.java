package images;

import java.io.IOException;

/**
* An abstract class AbstractImageProcesser that contains 
* some commone methods for the concrete class implementations.
* @author luzixiao.
*/
public abstract class AbstractImageProcesser implements ImageProcesser {
  
  /**
  * All the variables that will be used in its sub concrete classes.
  * image: a 3d integer array that represents the image.
  * height: the height of the image.
  * width: the width of the image.
  * chanel: the color chanel for the image. Red, Green and Blue.
  */
  
  protected int[][][] image;
  protected final int height;
  protected final int width;
  protected final int chanel;
  
  /**
  * The constructor for AbstractImageProcesser.
  * @param fileName     the name for the image file in string.
  * @throws IOException   if the file is not in the project's 
  *                       directory, IOException will be thrown.
  */
  
  public AbstractImageProcesser(String fileName) throws IOException {
    if (fileName == null) {
      throw new IllegalArgumentException();
    }
    this.chanel = 3;
    this.width = ImageUtilities.getWidth(fileName);
    this.height = ImageUtilities.getHeight(fileName);
    this.image = new int[chanel][height][width];
    this.image = ImageUtilities.readImage(fileName);
  }
  
  /**
  * The constructor for AbstractImageProcesser.
  * @param image   the 3d integer array that represents
  *                the image.
  */
  
  public AbstractImageProcesser(int[][][] image) {
    this.image = image;
    this.height = image.length;
    this.width = image[0].length;
    this.chanel = image[0][0].length;
    
  }
  

  @Override
  public int getWidth() {

    return this.width;
  }

  @Override
  public int getHeight() {

    return this.height;
  }

  @Override
  public int[][][] getImage() {
    int[][][] result = new int[this.height][this.width][this.chanel];
    for (int i = 0; i < this.chanel; i++) {
      for (int j = 0; j < this.height; j++) {
        for (int k = 0; k < this.width; k++) {
          result[j][k][i] = this.image[j][k][i];
        }
      }
    }
    return result;
  }

  @Override
  public void setImage(int[][][] image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;   
  }

  @Override
  public int clamp(int pixel, int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException();
    }
    if (pixel < min) {
      return min;
    }
    if (pixel > max) {
      return max;
    }
    return pixel;
  }

}
