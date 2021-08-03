package controller;

import java.io.IOException;
import images.ColorReduce;
import images.ColorTransformation;
import images.Filtering;
import images.ImageColorTransformation;
import images.ImageFiltering;
import images.ImageMosaic;
import images.ImagePixelation;
import images.ImageProcesser;
import images.ImageReduceColorDensity;
import images.Mosaic;
import images.Pixelation;
import images.ImageUtilities;

/**
* Concrete class ImageModelImpl that represent the ImageMode
* will be used in the contrller.
* @author luzixiao.
*/

public class ImageModelImpl implements ImageModel {

  /**
  * All the private data members that will be used in
  * this class.
  * image: the 3d integer array that represents the image.
  * width: the width of the image.
  * height: the height of the image.
  */
  
  private int[][][] image;
  private int width;
  private int height;
  
  /**
  * Constructor for the class ImageModelImpl.
  * @param image   the image in integer 3d array.
  */
  public ImageModelImpl(int[][][] image) {
    if (image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    this.width = image[0].length;
    this.height = image.length;

  }
  
  /**
  * The default constructor for class ImageModelImpl.
  * This constructor will set the image array to null,
  * which means currently no image file has been loaded 
  * yet.
  */
  
  public ImageModelImpl() {
    this.image = null;
    this.width = 0;
    this.height = 0;

  }

  @Override
  public void applyBlur() {
    if (this.image == null) {
      throw new IllegalArgumentException();
    }
    ImageFiltering temp = new Filtering(this.image);
    this.image = temp.imageBlur(ImageProcesser.blur);
  }

  @Override
  public void applySharpening() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    ImageFiltering temp = new Filtering(this.image); 
    this.image = temp.imageBlur(ImageProcesser.sharpening);
  }

  @Override
  public void applyGreyScale() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    ImageColorTransformation temp = new ColorTransformation(this.image);
    this.image = temp.transferColor(ImageProcesser.greyScale);
  }

  @Override
  public void applySepiaTone() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    ImageColorTransformation temp = new ColorTransformation(this.image);
    this.image = temp.transferColor(ImageProcesser.sepiaTone);
    
  }

  @Override
  public void applyReduceColorDensity(int colorPerChannel) {
    if (this.image == null ) {
      throw new IllegalStateException();
    }
    if (colorPerChannel <= 0) {
      throw new IllegalArgumentException();
    }
    ImageReduceColorDensity temp = new ColorReduce(this.image);
    this.image = temp.imageReduce(colorPerChannel);
  }

  
  @Override
  public void applyMosaic(int seeds) {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (seeds <= 0) {
      throw new IllegalArgumentException();
    }
    ImageMosaic temp = new Mosaic(this.image);
    this.image = temp.applyMosaic(seeds);   
  }
  
  @Override
  public void applyPixelation(int blockNum) {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    ImagePixelation temp = new Pixelation(this.image);
    this.image = temp.pixelation(blockNum);
  }

  @Override
  public void generatePattern(int blockNum, String fileName) throws IOException {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (blockNum <= 0 || fileName == null) {
      throw new IllegalArgumentException();
    }
    ImagePixelation temp = new Pixelation(this.image);
    temp.drawPattern(blockNum, fileName);
    
  }

  @Override
  public void generateOutPutImage(String fileName) throws IOException {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (fileName == null) {
      throw new IllegalArgumentException();
    }
    int [][][] result = this.getImage();
    ImageUtilities.writeImage(result, this.width, this.height, fileName);
    
  }
  
  @Override
  public void loadImage(String fileName) throws IOException {
 
    if (fileName == null) {
      throw new IllegalArgumentException();
    }
    this.image = ImageUtilities.readImage(fileName);
    this.width = ImageUtilities.getWidth(fileName);
    this.height = ImageUtilities.getHeight(fileName);
     
  }

  @Override
  public void setImage(int[][][] image) {
    this.image = image;
    
  }

  @Override
  public int[][][] getImage() {
    int[][][] result = new int[this.image.length][this.image[0].length][this.image[0][0].length];
    for (int i = 0; i < this.image[0][0].length; i++) {
      for (int j = 0; j < this.image.length; j++) {
        for (int k = 0; k < this.image[0].length; k++) {
          result[j][k][i] = this.image[j][k][i];
        }
      }
    }
    return result;
    
  }





}
