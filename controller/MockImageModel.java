package controller;

import java.io.IOException;

/**
* The concrete class MockImageModel that represented
* the image model will be used in the Junit test.
* @author luzixiao.
*/

public class MockImageModel implements ImageModel {

  /**
  * All the private data members that will be used
  * in this class.
  * sb:  a string builder object to store which operation
  *      is being called.
  * image:  a mock 3d integer array that represents the image.
  */
  
  private StringBuilder sb;
  private int[][][] image;
  
  
  /**
  * Constructor for MockImageModel.
  * In this constructor, the image is set to be null
  * which means currently no image has been loaded.
  * In the load operation, this 3d array will be initlaized. 
  */
  
  public MockImageModel() {
    this.sb = new StringBuilder();
    this.image = null;
  }

  @Override
  public String toString() {
    return this.sb.toString();
  }
  
  @Override
  public void applyBlur() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    sb.append("blur ");
  }

  @Override
  public void applySharpening() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    sb.append("sharp ");
  }

  @Override
  public void applyGreyScale() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    sb.append("grey ");
  }

  @Override
  public void applySepiaTone() {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    sb.append("sepia ");  
  }

  @Override
  public void applyReduceColorDensity(int colorPerChannel) {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (colorPerChannel <= 0) {
      throw new IllegalArgumentException();
    }
    sb.append("reduce ");
  }

  @Override
  public void applyMosaic(int seeds) {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (seeds <= 0) {
      throw new IllegalArgumentException();
    }
    sb.append("mosaic " + seeds + " ");  
  }

  @Override
  public void applyPixelation(int blockNum) {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    sb.append("pixelation " + blockNum + " "); 
  }

  @Override
  public void generatePattern(int blockNum, String fileName) throws IOException {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (blockNum <= 0) {
      throw new IllegalArgumentException();
    }
    sb.append("pattern " + fileName + " ");
  }

  @Override
  public void generateOutPutImage(String fileName) throws IOException {
    if (this.image == null) {
      throw new IllegalStateException();
    }
    if (fileName == null) {
      throw new IllegalArgumentException();
    }
    sb.append("save " + fileName + " ");
    
  }

  @Override
  public void setImage(int[][][] image) {
    this.image = image;
  }

  @Override
  public int[][][] getImage() {
    return this.image;
    
  }

  @Override
  public void loadImage(String fileName) throws IOException {
    sb.append("load ") ;
    sb.append(fileName + " ");
    this.image = new int[1][1][1];
    
  }

}
